// Copyright 2014 Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine;

import org.kie.api.runtime.KieSession;

/**
 * Creates or gets an appropriate instance of KieSession for a given process instance.
 * 
 * @author mimartin
 */
public interface KieSessionFactory
{
    /**
     * Creates or gets an appropriate instance of KieSession for a given process instance.
     * 
     * @param processInstanceId process instance id
     * @return the KieSession
     */
    public KieSession getSesionForProcessInstance( long processInstanceId );
    
    /**
     * Creates or gets an appropriate instance of KieSession for a new process instance.
     * 
     * @return the KieSession
     */
    public KieSession getSesionForNewProcessInstance();
}
