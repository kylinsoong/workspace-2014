// Copyright 2010 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jbpm.workflow.core.node.DataAssociation;
import org.jbpm.workflow.core.node.SubProcessNode;
import org.jbpm.workflow.core.node.WorkItemNode;
import org.jbpm.workflow.instance.node.SubProcessNodeInstance;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;

import com.et.idp.wf.model.WorkflowInitiate;
import com.et.idp.wf.model.service.ServiceFinish;
import com.et.idp.wf.model.service.ServiceInitiate;
import com.et.idp.wf.model.service.ServiceProgress;
import com.et.idp.wf.model.service.ServiceStart;
import com.et.idp.wf.util.ProcessUtil;

/**
 * Workflow engine.<br>
 * <br>
 * Implementation note: a copy is made of all incoming messages before modifying
 * them, so that there are no inadvertent side effects for external code calling
 * this class.
 * 
 * @author mimartin
 */
public class WorkflowEngine implements WorkflowManager
{
    private static final Logger LOG = Logger.getLogger( WorkflowEngine.class );

    private KieSessionFactory kieSessionFactory;

    public WorkflowEngine()
    {
        super();
    }

    /**
     * @param kieSessionFactory the kieSessionFactory to set
     */
    public void setKieSessionFactory( KieSessionFactory kieSessionFactory )
    {
        this.kieSessionFactory = kieSessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String initiateWorkflow( final WorkflowInitiate options )
    {
        KieSession processSession = kieSessionFactory.getSesionForNewProcessInstance();
        return new SessionRequestHandler( processSession ).initiateWorkflow( options );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyWorkflowStarted( final WorkflowProcessInstance execution )
    {
        KieSession processSession = kieSessionFactory.getSesionForProcessInstance( execution.getId() );
        new SessionRequestHandler( processSession ).notifyWorkflowStarted( execution );
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyWorkflowFinished( final WorkflowProcessInstance execution )
    {
        KieSession processSession = kieSessionFactory.getSesionForProcessInstance( execution.getId() );
        new SessionRequestHandler( processSession ).notifyWorkflowFinished( execution );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initiateService( final long processInstanceId, final ServiceInitiate options )
    {
        KieSession processSession = kieSessionFactory.getSesionForProcessInstance( processInstanceId );
        new SessionRequestHandler( processSession ).initiateService( processInstanceId, options );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startServiceStep( final String executionId, final ServiceStart options )
    {
        KieSession processSession = getSessionForExecutionId( executionId );
        new SessionRequestHandler( processSession ).startServiceStep( executionId, options );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void progressServiceStep( final String executionId, final ServiceProgress options )
    {
        KieSession processSession = getSessionForExecutionId( executionId );
        new SessionRequestHandler( processSession ).progressServiceStep( executionId, options );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finishServiceStep( final String executionId, final ServiceFinish options )
    {
        KieSession processSession = getSessionForExecutionId( executionId );
        new SessionRequestHandler( processSession ).finishServiceStep( executionId, options );
    }

    /**
     * Get the KieSession for a given execution id.
     * 
     * @param executionId
     * @return the KieSession
     */
    private KieSession getSessionForExecutionId( final String executionId )
    {
        return kieSessionFactory.getSesionForProcessInstance( ProcessUtil.getProcessInstanceId( executionId ) );
    }
    
    /**
     * This class provides a convenient way to store a reference to the KieSession that is looked
     * up/created for each request made to the WorkflowEngine, while still providing access to
     * all of WorkflowEngine's dependencies. A new instance is created for each request, and the
     * appropriate KieSession instance is injected to each instance via the constructor.
     */
    private class SessionRequestHandler implements WorkflowManager
    {
        private KieSession processSession;
        
        /**
         * @param processSession
         */
        public SessionRequestHandler( KieSession processSession )
        {
            super();
            this.processSession = processSession;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String initiateWorkflow( final WorkflowInitiate options )
        {
            final String key = options.getWorkflowKey();

            final Map<String, Object> variables = new HashMap<String, Object>();

            // store initial parameters for reference later
            final Map<String, String> initParams = new HashMap<String, String>();
//            putIfNotNull( initParams, MessageKeys.WF_PARENT_PROCESS_ID, options.getParentProcessId() );
//            putIfNotNull( initParams, MessageKeys.WF_PARENT_EXECUTION_ID, options.getParentExecutionId() );
//            putIfNotNull( initParams, MessageKeys.WF_PARENT_STEP_ID, options.getParentStepId() );
//            putIfNotNull( initParams, MessageKeys.WF_PARENT_STEP_NAME, options.getParentStepName() );
//            putIfNotNull( initParams, MessageKeys.WF_MODULO_KEY, options.getModuloKey() );
//            putIfNotNull( initParams, MessageKeys.WF_WORKFLOW_ENV, options.getWorkflowEnv() );
//            putIfNotNull( initParams, MessageKeys.WF_WORKFLOW_ENV_DETAIL, options.getWorkflowEnvDetail() );
//            variables.put( VariableNames.INTERNAL_INIT_MSG, initParams );

            // set inputs as process variables
            if ( options.getInputs() != null )
            {
                variables.putAll( options.getInputs() );
            }

            WorkflowProcessInstance processInstance = null;
            try
            {
                // start the workflow
                processInstance = (WorkflowProcessInstance) processSession.startProcess( key, variables );
            }
            catch ( final RuntimeException e )
            {
                throw new RuntimeException( "Error while starting workflow (" + key + ")", e );
            }
            // Note: event topic notification is taken care of by WorkflowLifecycleListener
            return ProcessUtil.getExecutionIdString( processInstance );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyWorkflowStarted( final WorkflowProcessInstance execution )
        {
//            // determine actual values of instance attributes
//            final Process processDef = execution.getProcess();
//            final String version = String.valueOf( processDef.getVersion() );
//            final String key = processDef.getId();
//            final String processId = ProcessUtil.getExecutionIdString( execution );
//    
//            // build common parameters map
//            final Map<String, String> commonParams = new HashMap<String, String>();
//            final Map<String, String> initParams = VariableUpdate.getInitialParameters( execution );
//            if ( initParams != null )
//            {
//                commonParams.putAll( initParams );
//            }
//            commonParams.put( MessageKeys.WF_WORKFLOW_KEY, key );
//            commonParams.put( MessageKeys.WF_WORKFLOW_VERSION, version );
//            commonParams.put( MessageKeys.WF_PROCESS_ID, processId );
//            commonParams.putAll( getParentParameters( execution, initParams ) );
//    
//            ModuloKeyGenerator.addKey( commonParams ); // create modulo key if this workflow was user-initiated
//    
//            VariableUpdate.initCommonParams( execution, commonParams );
//    
//            // also save workflow info as process variables
//            final Map<String, String> variables = new HashMap<String, String>();
//            copy( variables, commonParams, MessageKeys.WF_WORKFLOW_KEY );
//            copy( variables, commonParams, MessageKeys.WF_WORKFLOW_VERSION );
//            copy( variables, commonParams, MessageKeys.WF_PARENT_PROCESS_ID );
//            copy( variables, commonParams, MessageKeys.WF_PARENT_EXECUTION_ID );
//            copy( variables, commonParams, MessageKeys.WF_PARENT_STEP_ID );
//            copy( variables, commonParams, MessageKeys.WF_PARENT_STEP_NAME );
//            VariableUpdate.setVariables( execution, variables );
//    
//            // build event message
//            final Map<String, String> parameters = new HashMap<String, String>();
//            parameters.putAll( VariableUpdate.getCommonParams( execution ) );
//            parameters.put( MessageKeys.WF_VERSION_SPECIFIED, Boolean.FALSE.toString() );
//    
//            // get initial variables - this method will allow us to get the input variables
//            // even when a workflow is started via <sub-process>
//            final Map<String, String> inputs =
//                    MapUtil.filterWithoutPrefix( VariableNames.INTERNAL,
//                            MapUtil.getStrings( VariableUpdate.getVariables( execution ) ) );
//            parameters.putAll( MapUtil.addPrefix( MessageKeys.WF_INPUT_PREFIX, inputs ) );
//    
//            fireWorkflowStarted( parameters );
        }
    
        /**
         * Get parameters related to the parent workflow (if one exists) and return
         * those properties in a map. This handles both cases - where the parent
         * workflow ids are specified as part of the initial parameters passed to
         * Workflow, and where the current workflow is a jbpm subprocess.
         * 
         * @param execution the current execution
         * @param initParams initial parameters sent to start the current workflow
         * @return map of parameters that should be added to the event message /
         *         common params
         */
        private Map<String, String> getParentParameters( final WorkflowProcessInstance execution,
                final Map<String, String> initParams )
        {
            final String currentProcessId = ProcessUtil.getExecutionIdString( execution );
            String rootProcessId = null;
            String parentProcessId = null;
            String parentExecutionId = null;
            String parentStepId = null;
            String parentStepName = null;
            String moduloKey = null;
            String environment = null;
            String environmentDetail = null;
            boolean hasParent = false;
    
//            long parentProcessInstanceId = ( (ProcessInstanceImpl) execution ).getParentProcessInstanceId();
//            if ( parentProcessInstanceId != 0 )
//            {
//                // current workflow is subprocess
//                hasParent = true;
//                WorkflowProcessInstance parentProcessInstance = getProcessInstance( parentProcessInstanceId );
//                parentProcessId = ProcessUtil.getExecutionIdString( parentProcessInstance );
//                parentExecutionId = parentProcessId;
//                // if we started from a sub-process, then we need to generate the
//                // step id since SubProcessNodeInstance doesn't do that itself
//                parentStepId = generateSubProcessStepId( parentProcessInstance );
//                parentStepName = getSubProcessNodeName( parentProcessInstance, execution );
//                // carry over parent's modulo key, root id and environment parameters
//                final Map<String, String> commonParentParams = VariableUpdate.getCommonParams( parentProcessInstance );
//                moduloKey = commonParentParams.get( MessageKeys.WF_MODULO_KEY );
//                environment = commonParentParams.get( MessageKeys.WF_WORKFLOW_ENV );
//                environmentDetail = commonParentParams.get( MessageKeys.WF_WORKFLOW_ENV_DETAIL );
//                rootProcessId = commonParentParams.get( MessageKeys.WF_ROOT_PROCESS_ID );
//            }
//            else if ( initParams != null && initParams.get( MessageKeys.WF_PARENT_PROCESS_ID ) != null )
//            {
//                // parent keys specified at workflow initialization
//                hasParent = true;
//                parentProcessId = initParams.get( MessageKeys.WF_PARENT_PROCESS_ID );
//                parentExecutionId = initParams.get( MessageKeys.WF_PARENT_EXECUTION_ID );
//                parentStepId = initParams.get( MessageKeys.WF_PARENT_STEP_ID );
//                parentStepName = initParams.get( MessageKeys.WF_PARENT_STEP_NAME );
//                environment = initParams.get( MessageKeys.WF_WORKFLOW_ENV );
//                environmentDetail = initParams.get( MessageKeys.WF_WORKFLOW_ENV_DETAIL );
//    
//                final Map<String, String> commonParentParams = getCommonParams( parentExecutionId );
//                rootProcessId = commonParentParams.get( MessageKeys.WF_ROOT_PROCESS_ID );
//            }
//    
            final Map<String, String> parentParameters = new HashMap<String, String>();
//            if ( hasParent )
//            {
//                parentParameters.put( MessageKeys.WF_PARENT_PROCESS_ID, parentProcessId );
//                parentParameters.put( MessageKeys.WF_PARENT_EXECUTION_ID, parentExecutionId );
//                parentParameters.put( MessageKeys.WF_PARENT_STEP_ID, parentStepId );
//                parentParameters.put( MessageKeys.WF_PARENT_STEP_NAME, parentStepName );
//                if ( moduloKey != null )
//                {
//                    parentParameters.put( MessageKeys.WF_MODULO_KEY, moduloKey );
//                }
//                if ( environment != null )
//                {
//                    parentParameters.put( MessageKeys.WF_WORKFLOW_ENV, environment );
//                    if ( environmentDetail != null )
//                    {
//                        parentParameters.put( MessageKeys.WF_WORKFLOW_ENV_DETAIL, environmentDetail );
//                    }
//                }
//                if ( rootProcessId == null )
//                {
//                    LOG.warn( "Workflow " + currentProcessId + " has parent " + parentExecutionId
//                            + " but parent has no root workflow id." );
//                }
//            }
//            else
//            {
//                // no parent process, so the current workflow is the root
//                rootProcessId = currentProcessId;
//            }
//            parentParameters.put( MessageKeys.WF_ROOT_PROCESS_ID, rootProcessId );
    
            return parentParameters;
        }
    
        /**
         * Generate a new, unique step id for a subprocess step.
         * 
         * @param parentProcessInstance the parent workflow instance
         * @return a new step id
         */
        private String generateSubProcessStepId( WorkflowProcessInstance parentProcessInstance )
        {
//            Long stepIdSeq = (Long) parentProcessInstance.getVariable( VariableNames.INTERNAL_SUB_PROCESS_STEP_ID_SEQ );
//            if ( stepIdSeq == null )
//            {
//                stepIdSeq = 1L;
//            }
//            else
//            {
//                stepIdSeq++;
//            }
//            parentProcessInstance.setVariable( VariableNames.INTERNAL_SUB_PROCESS_STEP_ID_SEQ, stepIdSeq );
//    
//            return ProcessUtil.getExecutionIdString( parentProcessInstance ) + ".sp-" + stepIdSeq;
            return null;
        }
    
        /**
         * Get the node name of the SubProcessNode corresponding to a child process.
         * 
         * @param parentProcessInstance the parent workflow process instance
         * @param childProcessInstance the child workflow process instance
         * @return the SubProcessNode name, if one can be found; otherwise,
         *         "<unknown>"
         */
        private String getSubProcessNodeName( WorkflowProcessInstance parentProcessInstance,
                WorkflowProcessInstance childProcessInstance )
        {
            //  Look up all active SubProcessNodes from the parent process instance. Use the process id
            //  specified in the nodes to try to find a match to the child process' process id.
            //  
            //  If a match is found, then continue; if not, then log a warning and set the step name to
            //  "<unknown>". This should only happen if we use one of the more advanced strategies that
            //  callActivity supports for looking up process id (see code in SubProcessNodeInstance
            //  #internalTrigger), such as process name or interpolation of variables. This lookup
            //  will also fail if there is more than one callActivity node in a single parent workflow
            //  that both call the same child process defintion (more than one match will be found).
    
            String childProcessId = String.valueOf( childProcessInstance.getProcessId() );
    
            // get all active SubProcessNodeInstances from the parent workflow instance
            // try to find one that matches the process id of the child process
    
            List<String> matchingNodeNames = new ArrayList<String>();
            final Collection<NodeInstance> activeNodes = parentProcessInstance.getNodeInstances();
            for ( NodeInstance nodeInstance : activeNodes )
            {
                if ( nodeInstance instanceof SubProcessNodeInstance )
                {
                    SubProcessNode subProcessNode = (SubProcessNode) ( (SubProcessNodeInstance) nodeInstance ).getNode();
                    String nodeProcessId = subProcessNode.getProcessId();
                    if ( childProcessId.equals( nodeProcessId ) )
                    {
                        // found one!
                        matchingNodeNames.add( nodeInstance.getNodeName() );
                    }
                }
            }
    
            // verify we got one and only one match
            if ( matchingNodeNames.size() == 1 )
            {
                return matchingNodeNames.get( 0 );
            }
    
            String message;
            if ( matchingNodeNames.isEmpty() )
            {
                message = "Could not find matching SubProcessNode";
            }
            else
            // matches.size() > 1
            {
                message = "Found multiple matching SubProcessNodes (" + matchingNodeNames + ")";
            }
            LOG.warn( message + " in parent '" + ProcessUtil.getExecutionIdString( parentProcessInstance ) + "' for child '" +
                    ProcessUtil.getExecutionIdString( childProcessInstance ) + "'" );
            return "<unknown>";
        }
    
        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyWorkflowFinished( final WorkflowProcessInstance execution )
        {
//            final Map<String, String> parameters = new HashMap<String, String>();
//            parameters.putAll( VariableUpdate.getCommonParams( execution ) );
//    
//            // add prefix to process vars
//            final Map<String, String> variables = MapUtil.getStrings( VariableUpdate.getVariables( execution ) );
//            parameters.putAll( MapUtil.addPrefix( MessageKeys.WF_VAR_PREFIX, variables ) );
//    
//            fireWorkflowFinished( parameters );
        }
    
        /**
         * Initiate a service job by sending a message to the service. Also notify
         * the event topic of this action.
         * 
         * @param processInstanceId
         * @param options
         */
        @Override
        public void initiateService( final long processInstanceId, final ServiceInitiate options )
        {
            final WorkflowProcessInstance execution = getProcessInstance( processInstanceId );
            final String executionId = getExecutionId( processInstanceId );
            final String stepId = ProcessUtil.getStepId( execution, options.getWorkItemId() );
            final String stepName = options.getStepName();
            
            try
            {
                // initialize magic variables
//                VariableUpdate.initializeMagicVariables( execution, options, stepId );
    
                // create and send message
                HashMap<String, String> message = createServiceInitiateMessage( execution, options, stepId, stepName );
//                fireStepInitiated( message ); // TODO include custom headers in message body of event?
                sendServiceMessage( message, options );
            }
            catch ( final Exception e )
            {
                LOG.warn( "Initiating ExternalServiceTask step (" + stepName + ") workflow (" + executionId + ")", e );
    
                // something failed when trying to initiate the service - treat like a regular ETL error
    
                final ServiceFinish error = new ServiceFinish( options );
                error.setStepName( stepName );
                error.setStepId( stepId );
                error.setStatusDetail( e.getMessage() );
//                error.setStatus( ServiceValues.ERROR );
//                error.setResultCode( ServiceValues.ETL_INITIATE_ERROR_RESULT_CODE );
                finishServiceStep( executionId, error );
            }
        }
    
        /**
         * {@inheritDoc}
         */
        @Override
        public void startServiceStep( final String executionId, final ServiceStart options )
        {
            final String stepName = options.getStepName();
            checkStepName( executionId, stepName );
    
            updateMagicVariablesForServiceStart( executionId, options );
    
            // build event message
            final Map<String, String> eventParameters = new HashMap<String, String>();
//            eventParameters.put( MessageKeys.EVENT_TYPE, EventValues.WORKFLOW_STEP );
//            eventParameters.put( MessageKeys.SERVICE_MESSAGE_TYPE, ServiceValues.UPDATE_STATUS );
//            eventParameters.put( ServiceKeys.SERVICE_STATUS, ServiceValues.START );
//            eventParameters.put( ServiceKeys.SERVICE_STEP_NAME, stepName );
//            eventParameters.put( ServiceKeys.SERVICE_STEP_ID, options.getStepId() );
//            eventParameters.put( ServiceKeys.SERVICE_KEY, options.getServiceName() );
//            eventParameters.put( ServiceKeys.SERVICE_VERSION, options.getServiceVersion() );
//            eventParameters.put( ServiceKeys.SERVICE_INSTANCE_ID, options.getInstanceId() );
//            eventParameters.put( ServiceKeys.SERVICE_MACHINE_NAME, options.getMachineName() );
//            eventParameters.put( ServiceKeys.SERVICE_EVENT_TIMESTAMP, options.getTimestamp() );
            eventParameters.putAll( getCommonParams( executionId ) );
            addWorkflowVarParameters( executionId, eventParameters );
    
//            fireStepStarted( eventParameters );
        }
    
        /**
         * @param executionId
         * @param options
         */
        private void updateMagicVariablesForServiceStart( final String executionId, final ServiceStart options )
        {
            final WorkflowProcessInstance execution = getWorkflowInstance( executionId );
//            final Map<String, Object> variable = (Map<String, Object>) execution.getVariable( VariableNames.PREVIOUS );
//            variable.put( VariableNames.MACHINE_NAME, options.getMachineName() );
//            variable.put( ServiceKeys.SERVICE_KEY, options.getServiceName() );
//            variable.put( ServiceKeys.SERVICE_VERSION, options.getServiceVersion() );
//            variable.put( ServiceKeys.SERVICE_START_TIMESTAMP, options.getTimestamp() );
//            variable.put( VariableNames.INSTANCE_ID, options.getInstanceId() );
//    
//            execution.setVariable( VariableNames.PREVIOUS, variable );
        }
    
        /**
         * {@inheritDoc}
         */
        @Override
        public void progressServiceStep( final String executionId, final ServiceProgress options )
        {
            checkStepName( executionId, options.getStepName() );
    
//            // build event message
//            final Map<String, String> eventParameters = new HashMap<String, String>();
//            eventParameters.put( MessageKeys.SERVICE_MESSAGE_TYPE, ServiceValues.UPDATE_PROGRESS );
//            eventParameters.put( MessageKeys.EVENT_TYPE, EventValues.WORKFLOW_STEP );
//            eventParameters.put( ServiceKeys.SERVICE_STEP_NAME, options.getStepName() );
//            eventParameters.put( ServiceKeys.SERVICE_STEP_ID, options.getStepId() );
//            eventParameters.put( ServiceKeys.SERVICE_KEY, options.getServiceName() );
//            eventParameters.put( ServiceKeys.SERVICE_VERSION, options.getServiceVersion() );
//            if ( options.getServiceCategory() != null )
//            {
//                eventParameters.put( ServiceKeys.SERVICE_CATEGORY, options.getServiceCategory() );
//            }
//            eventParameters.put( ServiceKeys.SERVICE_INSTANCE_ID, options.getInstanceId() );
//            eventParameters.put( ServiceKeys.SERVICE_MACHINE_NAME, options.getMachineName() );
//            eventParameters.put( ServiceKeys.SERVICE_STATUS_DETAIL, options.getStatusDetail() );
//            eventParameters.put( ServiceKeys.SERVICE_EVENT_TIMESTAMP, options.getTimestamp() );
//            eventParameters.putAll( MapUtil.addPrefix( ServiceKeys.SERVICE_OUTPUT_PREFIX, options.getOutputs() ) );
//            eventParameters.putAll( getCommonParams( executionId ) );
//            addWorkflowVarParameters( executionId, eventParameters );
//    
//            fireStepProgressed( eventParameters );
        }
    
        /**
         * {@inheritDoc}
         */
        @Override
        public void finishServiceStep( final String executionId, final ServiceFinish options )
        {
            checkStepName( executionId, options.getStepName() );
    
            final String signalStep = options.getStepName();
            try
            {
                // notify the event topic that this step has finished
                final WorkflowProcessInstance execution = getWorkflowInstance( executionId );
                notifyServiceFinished( execution, options );
    
                // process outputs from workItem
                final Map<String, Object> outputs = mapOutputParameters( execution, options );
    
                // tell jbpm to continue the workflow to the next step
                final long workItemId = ProcessUtil.getWorkItemId( options.getStepId() );
                processSession.getWorkItemManager().completeWorkItem( workItemId, outputs );
            }
            catch ( final RuntimeException e )
            {
//                throw new RuntimeException( "Signaling (" + SignalNames.FINISH + ") step (" + signalStep + ") workflow (" + executionId
//                        + ")", e );
            }
        }
        
        /**
         * Notify listeners that a service has finished.
         */
        private void notifyServiceFinished( final WorkflowProcessInstance execution, final ServiceFinish options )
        {
//            if ( options.getTimestamp() == null )
//            {
////                options.setTimestamp( EventUtil.getTimestamp() );
//            }
//            if ( options.getServiceCategory() == null )
//            {
//                options.setServiceCategory( "" );
//            }
//            if ( options.getOutputs() == null )
//            {
//                options.setOutputs( new HashMap<String, String>() );
//            }
//
//            final String executionId = ProcessUtil.getExecutionIdString( execution );
//
//            // update magic variables
//            updateMagicVariablesForServiceFinish( execution, options );
//
//            // build event message
//            final Map<String, String> eventParameters = new HashMap<String, String>();
//            eventParameters.put( MessageKeys.SERVICE_MESSAGE_TYPE, ServiceValues.UPDATE_STATUS );
//            eventParameters.put( MessageKeys.EVENT_TYPE, EventValues.WORKFLOW_STEP );
//            eventParameters.put( ServiceKeys.SERVICE_KEY, options.getServiceName() );
//            eventParameters.put( ServiceKeys.SERVICE_VERSION, options.getServiceVersion() );
//            eventParameters.put( ServiceKeys.SERVICE_CATEGORY, options.getServiceCategory() );
//            eventParameters.put( ServiceKeys.SERVICE_MACHINE_NAME, options.getMachineName() );
//            eventParameters.put( ServiceKeys.SERVICE_INSTANCE_ID, options.getInstanceId() );
//            eventParameters.put( ServiceKeys.SERVICE_STEP_ID, options.getStepId() );
//            eventParameters.put( ServiceKeys.SERVICE_STEP_NAME, options.getStepName() );
//            eventParameters.put( ServiceKeys.SERVICE_STATUS, options.getStatus() );
//            eventParameters.put( ServiceKeys.SERVICE_STATUS_DETAIL, options.getStatusDetail() );
//            eventParameters.put( ServiceKeys.SERVICE_RESULT_CODE, options.getResultCode() );
//            eventParameters.put( ServiceKeys.SERVICE_EVENT_TIMESTAMP, options.getTimestamp() );
//            eventParameters.putAll( MapUtil.addPrefix( ServiceKeys.SERVICE_OUTPUT_PREFIX, options.getOutputs() ) );
//            eventParameters.putAll( VariableUpdate.getCommonParams( execution ) );
//            eventParameters.put( MessageKeys.WF_EXECUTION_ID, executionId );
//            addWorkflowVarParameters( executionId, eventParameters );

//            fireStepFinished( eventParameters );
        }
    
        /**
         * Update process variables according to the output variables from the task.
         * If mappings are specified, maps output parameters to process variables;
         * if a given output is in the mapping but not in the output parameters, a
         * process variable will be created with a null value.<br>
         * <br>
         * If mapping is <code>null</code>, all output parameters will be mapped
         * into process variables, prefixed with <code>X_</code>, where
         * <code>X</code> is the name of the workflow step. If a given output
         * parameters
         * 
         * @param options
         * @return targetParameters
         * @throws MissingOutputParameterException if any mapped output parameters
         *         are missing
         */
        private Map<String, Object> mapOutputParameters( final WorkflowProcessInstance execution, final ServiceFinish options )
        {
            final Map<String, String> outputParameters = options.getOutputs();
            final Map<String, String> mapping = getWorkItemOutputMappings(execution, options.getStepId());
            final Map<String, Object> targetVariables = new HashMap<String, Object>();
            
            System.out.println( mapping );
            if ( mapping == null || mapping.isEmpty() )
            {
                // auto-map by prepending all outputs with step name
                final String stepName = options.getStepName();
//                targetVariables.putAll( MapUtil.addPrefix( stepName + "_", outputParameters ) );
            }
            else
            {
                // apply configured mappings
//                VariableUpdate.processMappings( outputParameters, mapping, targetVariables );
            }
    
            return targetVariables;
        }
        
        /**
         * Retrieve the mapping of variables in the WorkItem
         * 
         * @param processInstance
         * @param stepId
         * @return Map of source parameters and their target variables
         */
        private Map<String, String> getWorkItemOutputMappings(final WorkflowProcessInstance processInstance, final String stepId)
        {
            final Map<String, String> resultMappings = new HashMap<String, String>();
            final WorkItemNode workItemNode = ProcessUtil.getWorkItemNode( processInstance, stepId );
            
            // iterate over node output associations
            for ( Iterator<DataAssociation> iterator = workItemNode.getOutAssociations().iterator(); iterator.hasNext(); )
            {
                final DataAssociation association = iterator.next();
                resultMappings.put( association.getSources().get( 0 ), association.getTarget() );
            }
    
            return resultMappings;
        }
    
        /**
         * Verify that the current step name in the execution is the same as the
         * what was passed in with the message. If not, either a bad message was
         * sent, or we are processing the message out of order. Either way, if you
         * try to process the message, we'll probably mess up our workflow state.
         * 
         * @param executionId id of the current execution
         * @param stepName step name
         */
        private void checkStepName( final String executionId, final String stepName )
        {
//            stepNameCheckRetryExecutor.run( new DefaultAction<Void>( "verify step name" )
//            {
//                @Override
//                public void run() throws Exception
//                {
                    // TODO rewrite this: there can be multiple active nodes at once - need to check
                    //      that the given name matches at least one
                    //
                    //  OR: we can verify that the workItemId of the current (incoming) request matches
                    //      a work item that actually exists and is active
                    //
                    // Also: TODO rename this method to checkStepActive(...)
    
                    final String currentStep = getCurrentActivityName( executionId );
                    if ( currentStep == null )
                    {
                        throw new RuntimeException( "Execution with id '" + executionId +
                                "' no longer exists. Duplicate message, or workflow was manually terminated?" );
                    }
                    else if ( !currentStep.equals( stepName ) )
                    {
                        throw new RuntimeException( "Step name of msg (" + stepName + ") does not match current wf step (" +
                                currentStep + ")" );
                    }
//                }
//            } );
        }
    
        /**
         * Get the name of the current activity of an execution.
         * 
         * @param executionId execution id
         * @return name of the activity, or null if the execution does not exist
         */
        private String getCurrentActivityName( final String executionId )
        {
            String name = null;
            final WorkflowProcessInstance execution = getWorkflowInstance( executionId );
            if ( execution != null )
            {
                final Collection<NodeInstance> activeNodes = execution.getNodeInstances();
                if ( !activeNodes.isEmpty() )
                {
                    final NodeInstance firstNode = activeNodes.iterator().next();
                    name = firstNode.getNodeName();
                }
            }
            return name;
        }
    
        /**
         * Add message parameters for workflow variables (wf_var_*).
         * 
         * @param executionId current execution id
         * @param eventParameters map to which parameters should be added
         */
        private void addWorkflowVarParameters( final String executionId, final Map<String, String> eventParameters )
        {
            final Map<String, String> variables = getVariables( executionId );
//            eventParameters.putAll( MapUtil.addPrefix( MessageKeys.WF_VAR_PREFIX, variables ) );
        }
    
        /**
         * Get all string process variables.
         * 
         * @param executionId current execution id
         * @return the variables
         */
        private Map<String, String> getVariables( final String executionId )
        {
            final WorkflowProcessInstance execution = getWorkflowInstance( executionId );
//            final Map<String, Object> variables = VariableUpdate.getVariables( execution );
//            return MapUtil.getStrings( variables );
            return null;
        }
    
        /**
         * Get common message parameters (workflow key, version, env, parent,
         * modulo, etc.) from the special process variables. The returned map will
         * also include the execution id, for convenience.
         * 
         * @param executionId the current execution id
         * @return the parameters
         * @see {@link VariableUpdate#getCommonParams(ProcessInstance)}
         */
        private Map<String, String> getCommonParams( final String executionId )
        {
            final WorkflowProcessInstance processInstance = getWorkflowInstance( executionId );
//            final Map<String, String> params = new HashMap<String, String>(
//                    (Map<String, String>) processInstance.getVariable( VariableNames.INTERNAL_COMMON ) );
////            params.put( MessageKeys.WF_EXECUTION_ID, executionId );
//            return params;
            return null;
        }
    
        /**
         * Get a process instance for a "execution id" .
         * 
         * @param executionId
         * @return the process instance
         */
        private WorkflowProcessInstance getWorkflowInstance( final String executionId )
        {
            final long processInstanceId = ProcessUtil.getProcessInstanceId( executionId );
            final WorkflowProcessInstance execution = getProcessInstance( processInstanceId );
            return execution;
        }
    
        /**
         * Get a process instance for a process instance id.
         * 
         * @param processInstanceId
         * @return the process instance
         */
        private WorkflowProcessInstance getProcessInstance( final long processInstanceId )
        {
            return (WorkflowProcessInstance) processSession.getProcessInstance( processInstanceId );
        }
    
        /**
         * Get the execution id equivalent for a given process instance id.
         * 
         * @param processInstanceId
         * @return the execution id
         */
        private String getExecutionId( final long processInstanceId )
        {
            WorkflowProcessInstance processInstance = getProcessInstance( processInstanceId );
            String executionId = ProcessUtil.getExecutionIdString( processInstance );
            return executionId;
        }
    }
    
    /**
     * Get a new, unique file prefix for use by an ETL service.
     * 
     * @return file prefix
     */
    public String getNextServiceFilePrefix()
    {
//        return filePrefixRetryExecutor.run( new DefaultAction<String>( "call FileNaming service" )
//        {
//            @Override
//            public String run( final int attemptNumber ) throws Exception
//            {
//                return fileNamingService.getNextFilePrefix();
//            }
//        } );
        return null;
    }
    
    /**
     * Initiate a service job by sending a message to the service. Also notify
     * the event topic of this action.
     * 
     * @param execution
     * @param options
     * @param stepId
     * @param stepName
     * @return the message
     */
    private HashMap<String, String> createServiceInitiateMessage( final WorkflowProcessInstance execution, ServiceInitiate options,
            final String stepId, final String stepName )
    {
//        if ( options.getInputs() == null )
//        {
//            options.setInputs( new HashMap<String, String>() );
//        }
//
//        final String moduloKey = VariableUpdate.getCommonParams( execution ).get( MessageKeys.WF_MODULO_KEY );
//        final Map<String, String> inputs = options.getInputs();
//
//        // add system parameters as inputs
//        if ( options.isIncludeSystemParameters() )
//        {
//            inputs.put( MessageKeys.WF_PROCESS_ID, ProcessUtil.getExecutionIdString( execution ) );
//            inputs.put( MessageKeys.WF_EXECUTION_ID, ProcessUtil.getExecutionIdString( execution ) );
//            inputs.put( ServiceKeys.SERVICE_STEP_ID, stepId );
//            inputs.put( ServiceKeys.SERVICE_STEP_NAME, stepName );
//            inputs.put( MessageKeys.WF_MODULO_KEY, moduloKey );
//        }
//
//        // build the message to send to the service
        final HashMap<String, String> message = new HashMap<String, String>();
//        if ( options.getIncludeFilePrefix() )
//        {
//            final String filePrefix = getNextServiceFilePrefix();
//            message.put( ServiceKeys.SERVICE_FILE_PREFIX, filePrefix );
//        }
//        message.put( MessageKeys.SERVICE_MESSAGE_TYPE, ServiceValues.INITIATE );
//        message.put( ServiceKeys.SERVICE_STEP_NAME, stepName );
//        message.put( ServiceKeys.SERVICE_STEP_ID, stepId );
//        if ( options.getServiceCategory() != null )
//        {
//            message.put( ServiceKeys.SERVICE_CATEGORY, options.getServiceCategory() );
//        }
//        else
//        {
//            message.put( ServiceKeys.SERVICE_KEY, options.getServiceName() );
//        }
//        message.put( ServiceKeys.SERVICE_EVENT_TIMESTAMP, EventUtil.getTimestamp() );
//        message.putAll( MapUtil.addPrefix( ServiceKeys.SERVICE_INPUT_PREFIX, inputs ) );
//        message.putAll( VariableUpdate.getCommonParams( execution ) );

        return message;
    }

    /**
     * Send a message to a service.
     * 
     * @param message
     * @param options
     */
    private void sendServiceMessage( final Map<String, String> message, final ServiceInitiate options )
    {
//        final Map<String, String> customHeaders = options.getAddlHeaders();
//
//        // populate message body
//        final Map<String, String> msgBody = new HashMap<String, String>();
//        if ( customHeaders != null )
//        {
//            msgBody.putAll( customHeaders );
//        }
//        msgBody.putAll( message );
//
//        // populate message headers
//        final Map<String, String> msgHeaders = new HashMap<String, String>();
//        if ( customHeaders != null )
//        {
//            msgHeaders.putAll( customHeaders );
//        }
//        msgHeaders.putAll( messageHeaderFilter.filter( msgBody ) );
//
//        if ( options.getServiceCategory() != null )
//        {
//            messageSender.sendCategoryMessage( msgBody, msgHeaders, options.getServiceCategory() );
//        }
//        else
//        {
//            messageSender.sendServiceMessage( msgBody, msgHeaders, options.getServiceName() );
//        }
    }

    private void updateMagicVariablesForServiceFinish( final WorkflowProcessInstance execution, final ServiceFinish options )
    {
//        final Map<String, Object> variable = (Map<String, Object>) execution.getVariable( VariableNames.PREVIOUS );
//        variable.put( VariableNames.STATUS, options.getStatus() );
//        variable.put( VariableNames.RESULT_CODE, options.getResultCode() );
//        variable.put( VariableNames.STATUS_DETAIL, options.getStatusDetail() );
//        variable.put( ServiceKeys.SERVICE_EVENT_TIMESTAMP, options.getTimestamp() );
//
//        // Set variables that would normally be set by a "service start" message, since that message is not
//        // always sent to Workflow (see IDPETLC-64), but don't overwrite existing values if present
//        putIfNotSet( variable, VariableNames.MACHINE_NAME, options.getMachineName() );
//        putIfNotSet( variable, ServiceKeys.SERVICE_CATEGORY, options.getServiceCategory() );
//        putIfNotSet( variable, ServiceKeys.SERVICE_KEY, options.getServiceName() );
//        putIfNotSet( variable, ServiceKeys.SERVICE_VERSION, options.getServiceVersion() );
//        putIfNotSet( variable, ServiceKeys.SERVICE_START_TIMESTAMP, options.getTimestamp() );
//        putIfNotSet( variable, VariableNames.INSTANCE_ID, options.getInstanceId() );
//
//        if ( variable.get( ServiceKeys.SERVICE_CATEGORY ) == null )
//        {
//            variable.put( ServiceKeys.SERVICE_CATEGORY, "" );
//        }
//
//        // output variables
//        final Map<String, String> outputs = (Map<String, String>) variable.get( VariableNames.OUTPUT );
//        outputs.putAll( options.getOutputs() );
//
//        execution.setVariable( VariableNames.PREVIOUS, variable );
    }
    
    /**
     * Copy a key from one map to another.
     * 
     * @param target
     * @param source
     * @param key
     */
    private void copy( final Map<String, String> target, final Map<String, String> source,
            final String key )
    {
        target.put( key, source.get( key ) );
    }

    /**
     * Put a key into a map, if and only if the value is not null.
     * 
     * @param target
     * @param key
     * @param value
     */
    private <T> void putIfNotNull( final Map<String, ? super T> target, final String key, final T value )
    {
        if ( value != null )
        {
            target.put( key, value );
        }
    }

    /**
     * Put a key into a map, if and only if the key is not already set to a
     * non-empty value.
     * 
     * @param target
     * @param key
     * @param value
     */
    private <T> void putIfNotSet( final Map<String, ? super T> target, final String key, final T value )
    {
        final Object origValue = target.get( key );
        if ( origValue == null || "".equals( origValue ) )
        {
            putIfNotNull( target, key, value );
        }
    }
}
