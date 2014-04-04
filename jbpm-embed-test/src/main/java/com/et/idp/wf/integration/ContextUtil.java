// Copyright 2010 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.integration;

/**
 * Constants for working with Spring contexts.
 *
 * @author mimartin
 */
public class ContextUtil
{
    // Outline of dependencies:
    //
    // CM : mocked out to return info from a fixed list
    // FileNamer: mocked out to return info from a local sequence
    // Event Topic: mocked out to write messages to a file
    // Controller Queue: real (isolated) queue on JBoss
    // ETL Service Queues: real (isolated) queues on JBoss
    public static final String MOCK_ENV_CONTEXT_FILE = "mockEnvContext.xml";

    // Outline of dependencies:
    //
    // CM : real REST service
    // FileNamer: real REST service
    // Event Topic: mocked out to write messages to a file
    // Controller Queue: real queue on JBoss
    // ETL Service Queues: real queues on JBoss
    public static final String INTEGRATED_ENV_CONTEXT_FILE = "integratedEnvContext.xml";

    // Outline of dependencies:
    //
    // Everything the real deal
    public static final String REAL_ENV_CONTEXT_FILE = "envContext.xml";

    /**
     * Mock environment (services) Spring context file to use.
     */
    public static final String ENV_CONTEXT_FILE = MOCK_ENV_CONTEXT_FILE;

    /**
     * Main application Spring context file.
     */
    public static final String ENGINE_CONTEXT_FILE = "engineContext.xml";

    /**
     * Context file to create the jbpm RuntimeManager.
     */
    public static final String RUNTIME_MANAGER_CONTEXT_FILE = "runtimeManagerContext.xml";

    /**
     * Context file (override) to create the jbpm RuntimeManager without persistence.
     */
    public static final String NO_PERSISTENCE_RUNTIME_MANAGER_CONTEXT_FILE = "noPersistenceRuntimeManagerContext.xml";
}
