// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine;

import org.kie.api.runtime.process.WorkflowProcessInstance;

/**
 * Workflow manager interface for jbpm event listeners concerned with workflow lifecycle events.
 *
 * @author mimartin
 */
public interface LifecycleWorkflowManager
{
    /**
     * Called from jbpm event listener. A workflow instance has started.
     *
     * @param execution current execution
     */
    public void notifyWorkflowStarted( WorkflowProcessInstance execution );

    /**
     * Called from jbpm event listener. A workflow instance has finished.
     *
     * @param execution current execution
     */
    public void notifyWorkflowFinished( WorkflowProcessInstance execution );
}
