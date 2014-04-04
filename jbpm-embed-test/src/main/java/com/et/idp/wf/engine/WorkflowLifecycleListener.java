// Copyright 2010 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine;

import org.kie.api.event.process.DefaultProcessEventListener;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.runtime.process.WorkflowProcessInstance;

/**
 * Listens for the start and end of workflow instances and notifies the WorkflowManager.
 *
 * @author mimartin
 */
public class WorkflowLifecycleListener extends DefaultProcessEventListener
{
    private LifecycleWorkflowManager workflowManager;

    public void setWorkflowManager( final LifecycleWorkflowManager workflowManager )
    {
        this.workflowManager = workflowManager;
    }

    @Override
    public void beforeProcessStarted( final ProcessStartedEvent event )
    {
        workflowManager.notifyWorkflowStarted( (WorkflowProcessInstance) event.getProcessInstance() );
    }

    @Override
    public void beforeProcessCompleted( final ProcessCompletedEvent event )
    {
        workflowManager.notifyWorkflowFinished( (WorkflowProcessInstance) event.getProcessInstance() );
    }
}
