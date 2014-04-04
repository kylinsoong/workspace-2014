// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine;

import com.et.idp.wf.model.service.ServiceInitiate;

/**
 * Workflow manager interface for ExternalServiceTask.
 *
 * @author mimartin
 */
public interface ServiceWorkflowManager
{
    /**
     * Initiate a service job by sending a message to the service. Also notify the event topic of this action.
     *
     * @param processInstanceId
     * @param options
     */
    public void initiateService( final long processInstanceId, final ServiceInitiate options );
}
