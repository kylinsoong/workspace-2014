package com.et.idp.wf.activity;

import java.util.Map;

import org.jbpm.workflow.instance.node.WorkItemNodeInstance;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkflowProcessInstance;

/**
 * Abstract class for {@link WorkItemHandler} classes.
 * 
 * @author acmyers
 */
public abstract class CustomTask
{

    protected static final String CONFIG_PREFIX = "config_";

    protected static final String INPUT_PREFIX = "in_";

    protected KieSession ksession;

    public CustomTask( KieSession ksession )
    {
        this.ksession = ksession;
    }

    /**
     * Get the {@link WorkflowProcessInstance} for this work item handler.
     * 
     * @param workItem
     * @return The {@link WorkflowProcessInstance}
     */
    public WorkflowProcessInstance getProcessInstance( final WorkItem workItem )
    {
        return (WorkflowProcessInstance) ksession.getProcessInstance( workItem.getProcessInstanceId() );
    }

    /**
     * Get the step name of the task node.
     * 
     * @param workItemId
     * @param processInstance
     * @return The step name
     */
    public String getStepName( final WorkItem workItem )
    {
        String stepName = null;

        for ( NodeInstance nodeInstance : getProcessInstance( workItem ).getNodeInstances() )
        {
            if ( nodeInstance instanceof WorkItemNodeInstance )
            {
                WorkItemNodeInstance workItemNode = (WorkItemNodeInstance) nodeInstance;
                if ( workItemNode.getWorkItem().getId() == workItem.getId() )
                {
                    stepName = nodeInstance.getNodeName();
                    break;
                }
            }
        }

        return stepName;
    }

//    /**
//     * Get a map of all parameters for work item inputs.
//     * 
//     * @param workItem
//     * @return
//     */
//    public Map<String, Object> getInputs( final WorkItem workItem )
//    {
//        return MapUtil.filterRemovePrefix( INPUT_PREFIX, workItem.getParameters() );
//    }
//
//    /**
//     * Get all inputs - a merge of specified inputs and "system" inputs, if
//     * requested.
//     * 
//     * @param workItem current workItem
//     * @return map of inputs
//     */
//    protected Map<String, String> getAllInputs( final WorkItem workItem )
//    {
//        return VariableUpdate.flattenInputs( getProcessInstance( workItem ), getInputs( workItem ) );
//    }
//
//    /**
//     * Get a map of all parameters for work item config.
//     * 
//     * @param workItem
//     * @return
//     */
//    public Map<String, Object> getConfigParameters( final WorkItem workItem )
//    {
//        return MapUtil.filterRemovePrefix( CONFIG_PREFIX, workItem.getParameters() );
//    }

    /**
     * Get a parameter value as a boolean object.
     * 
     * @param name
     * @param params
     * @return The parameter value as a {@link Boolean}
     */
    public boolean getBooleanParameter( final String name, final Map<String, Object> params )
    {
        Object value = params.get( name );
        if ( value == null )
        {
            // default to false if null
            return false;
        }

        if ( value instanceof String )
        {
            return Boolean.parseBoolean( (String) value );
        }
        return (Boolean) value;
    }

}
