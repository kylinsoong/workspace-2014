// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model.service

import com.et.idp.wf.model.ExternalMessageStepOptions

/**
 * Report progress on a service.
 *
 * @author mimartin
 */
class ServiceProgress extends ExternalMessageStepOptions
{
    String serviceName

    String serviceVersion

    String serviceCategory

    String instanceId

    String machineName

    String statusDetail

    Map<String,String> outputs
}
