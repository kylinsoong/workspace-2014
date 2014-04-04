// Copyright 2010 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.activity;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.runtime.process.WorkflowProcessInstance;

/**
 * Replaces ${tokens} in a string with corresponding process variable values
 * from the current workflow and puts the result into a process variable.
 * 
 * @author acmyers
 */
public class TokenReplacer extends CustomTask implements WorkItemHandler
{

    public TokenReplacer( KieSession ksession )
    {
        super( ksession );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeWorkItem( WorkItem workItem, WorkItemManager manager )
    {
        WorkflowProcessInstance processInstance = getProcessInstance( workItem );

        final String inputString = (String) workItem.getParameter( "config_inputString" );
        final String outputVariable = (String) workItem.getParameter( "config_outputVariable" );

//        final Map<String, Object> variables = VariableUpdate.getVariables( processInstance );
//        final StrSubstitutor sub = new StrSubstitutor( variables );
//        final String result = sub.replace( inputString );
//        processInstance.setVariable( outputVariable, result );

        manager.completeWorkItem( workItem.getId(), null );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void abortWorkItem( WorkItem workItem, WorkItemManager manager )
    {
        //throw new UnsupportedOperationException( "Not supported" );
    }
}
