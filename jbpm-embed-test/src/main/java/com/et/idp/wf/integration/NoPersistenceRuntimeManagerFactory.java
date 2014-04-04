// Copyright 2014 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.integration;

import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;

import com.et.idp.wf.engine.config.PerProcessClasspathRuntimeManagerFactory;

/**
 * Sublcass to override and turn off persistence for the local, standalone instance of WorkflowEngine.
 * 
 * @author mimartin
 */
public class NoPersistenceRuntimeManagerFactory extends PerProcessClasspathRuntimeManagerFactory
{
    /**
     * Configure the RuntimeEnvironmentBuilder before creating the RuntimeManager from it.
     * 
     * @param builder
     */
    @Override
    protected void configureBuilder( RuntimeEnvironmentBuilder builder )
    {
        super.configureBuilder( builder );
        builder.persistence( false );
    }
}
