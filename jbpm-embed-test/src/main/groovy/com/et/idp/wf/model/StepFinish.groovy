// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model

/**
 * Finish work on a workflow step.
 *
 * @author mimartin
 */
class StepFinish extends ExternalMessageStepOptions
{
    String statusDetail

    Map<String,String> outputs
}
