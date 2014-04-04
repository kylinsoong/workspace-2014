package com.et.idp.wf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drools.core.process.instance.WorkItemManager;
import org.jbpm.process.instance.ProcessInstance;
import org.jbpm.workflow.core.node.WorkItemNode;
import org.jbpm.workflow.instance.node.WorkItemNodeInstance;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkflowProcessInstance;

/**
 * Common methods for retrieving process instances and work items related to
 * workflow steps.
 * 
 * @author acmyers
 */
public class ProcessUtil
{

    /**
     * Regex to extra a work item id from a step id.
     */
    private static Pattern stepIdPattern = Pattern.compile( "^.+\\..+?\\.([^.]+)$" );

    private ProcessUtil()
    {
    }
    
    /**
     * Construct an execution id string, which is the process key, plus a dot, plus the
     * process instance numeric id.
     *
     * @param processInstance
     * @return the process instance id string
     */
    public static String getExecutionIdString( final WorkflowProcessInstance processInstance )
    {
        final String key = processInstance.getProcessId();
        return key + "." + processInstance.getId();
    }
    
    /**
     * Deconstruct a process instance id string, to get just the process instance numeric id.
     *
     * @param executionIdString
     * @return the process instance id string
     * @throws IllegalArgumentException if the execution id string doesn't conform to the proper format
     */
    public static long getProcessInstanceId( final String executionIdString )
    {
        final int lastDotIndex = executionIdString.lastIndexOf( '.' );
        if( lastDotIndex == -1 )
        {
            throw new IllegalArgumentException( "Execution id '" + executionIdString + "' does not contain a dot" );
        }
        
        final String numericPart = executionIdString.substring( lastDotIndex + 1 );
        long id;
        try
        {
            id = Long.parseLong( numericPart );
        }
        catch( final NumberFormatException e )
        {
            throw new IllegalArgumentException( "Execution id '" + executionIdString + "' does not end in a number", e );
        }
        return id;
    }

    /**
     * Generate a step id, based on a work item id.
     * 
     * @param execution
     * @param workItemId
     * @return step id
     */
    public static String getStepId( final WorkflowProcessInstance execution, final long workItemId )
    {
        final String key = execution.getProcessId();
        final long processInstanceId = execution.getId();
        return key + '.' + processInstanceId + '.' + workItemId;
    }

    /**
     * Extract the work item id from a step id.
     * 
     * @param stepId
     * @return work item id
     */
    public static long getWorkItemId( final String stepId )
    {
        final Matcher m = stepIdPattern.matcher( stepId );
        if ( !m.matches() )
        {
            throw new IllegalArgumentException( "Step id '" + stepId + "' does not match the expected pattern" );
        }

        final String numericPart = m.group( 1 );
        final long workItemId;
        try
        {
            workItemId = Long.parseLong( numericPart );
        }
        catch ( final NumberFormatException e )
        {
            throw new IllegalArgumentException( "Step id '" + stepId + "' does not end in a number", e );
        }
        return workItemId;
    }

    public static WorkItem getWorkItem( final WorkflowProcessInstance execution, final String stepId )
    {
        final WorkItem workItem = ( (WorkItemManager) ( (ProcessInstance) execution )
                .getKnowledgeRuntime().getWorkItemManager() ).getWorkItem( getWorkItemId( stepId ) );

        return workItem;
    }

    public static WorkItemNode getWorkItemNode( final WorkflowProcessInstance execution, final String stepId )
    {
        WorkItemNode node = null;

        for ( NodeInstance nodeInstance : execution.getNodeInstances() )
        {
            if ( nodeInstance instanceof WorkItemNodeInstance )
            {
                WorkItemNodeInstance workItemNode = (WorkItemNodeInstance) nodeInstance;
                if ( workItemNode.getWorkItem().getId() == getWorkItemId( stepId ) )
                {
                    node = (WorkItemNode) nodeInstance.getNode();
                    break;
                }
            }
        }

        return node;
    }
}
