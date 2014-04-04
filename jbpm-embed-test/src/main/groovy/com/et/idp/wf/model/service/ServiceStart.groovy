// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model.service

import com.et.idp.wf.model.ExternalMessageStepOptions

/**
 * Start work on a service.
 *
 * @author mimartin
 */
class ServiceStart extends ExternalMessageStepOptions
{
    String serviceName

    String serviceVersion

    String instanceId

    String machineName
}
