// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model

/**
 * Initiate a workflow.
 *
 * @author mimartin
 */
class WorkflowInitiate extends StepOptions
{
    String moduloKey

    String workflowEnv

    String workflowEnvDetail

    String workflowKey

    String parentProcessId

    String parentExecutionId

    String parentStepId

    String parentStepName

    Map<String,String> inputs
}
