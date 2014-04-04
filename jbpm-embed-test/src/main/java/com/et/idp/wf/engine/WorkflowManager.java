// Copyright 2010 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine;

/**
 * Interface for workflow manager engine.
 *
 * @author mimartin
 */
public interface WorkflowManager extends ServiceWorkflowManager, LifecycleWorkflowManager, MessageWorkflowManager
{
    /**
     * Name of the workflow manager bean.
     */
    public static final String WORKFLOW_MANAGER = "workflowManager";
}
