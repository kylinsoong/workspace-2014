// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model.service

import com.et.idp.wf.model.StepOptions

/**
 * Initiate work on a service.
 *
 * @author mimartin
 */
class ServiceInitiate extends StepOptions
{
    long workItemId
    
    String serviceName

    String serviceCategory

    boolean includeFilePrefix

    boolean includeSystemParameters

    Map<String,String> inputs

    Map<String,String> addlHeaders
}
