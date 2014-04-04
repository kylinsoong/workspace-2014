// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model.service

import com.et.idp.wf.model.StepFinish

/**
 * Finish work on a service.
 *
 * @author mimartin
 */
class ServiceFinish extends StepFinish
{
    public ServiceFinish()
    {
    }

    /**
     * Construct as a copy of another options object.
     *
     * @param options
     */
    public ServiceFinish( Object options )
    {
        copyProperties( options )
    }

    String serviceName

    String serviceVersion

    String serviceCategory

    String instanceId

    String machineName

    String status

    String resultCode
}
