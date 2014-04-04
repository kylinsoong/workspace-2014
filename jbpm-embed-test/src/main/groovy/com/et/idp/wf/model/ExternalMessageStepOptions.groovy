// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model

/**
 * A set of options and parameters pertaining to a specific action
 * within the Workflow Manager due to a received JMS message.
 *
 * @author mimartin
 */
class ExternalMessageStepOptions extends StepOptions
{
    String stepId

    String timestamp
}
