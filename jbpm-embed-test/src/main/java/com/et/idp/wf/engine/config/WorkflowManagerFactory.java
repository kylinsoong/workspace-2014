// Copyright 2010 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import com.et.idp.wf.engine.KieSessionFactory;
import com.et.idp.wf.engine.WorkflowEngine;
import com.et.idp.wf.engine.WorkflowManager;

/**
 * Factory to create and configure the WorkflowManager.
 * 
 * @author mimartin
 */
public class WorkflowManagerFactory
{
    /**
     * Bean name for the KieSessionFactory.
     */
    public static final String KIE_SESSION_FACTORY_BEAN = "kieSessionFactory";

    private WorkflowEngine workflowEngine;

    private ApplicationContext appContext;

    /**
     * Create a new instance of the workflow manager and its dependencies.
     */
    public WorkflowManagerFactory( String[] contextFiles )
    {
        this( contextFiles, null );
    }

    /**
     * Create a new instance of the workflow manager and its dependencies. Use this for unit testing,
     * in order to supply a mock KieSessionFactory instance.
     */
    public WorkflowManagerFactory( String[] contextFiles, KieSessionFactory kieSessionFactory )
    {
        GenericApplicationContext parentContext = null;
        
        if( kieSessionFactory != null )
        {
            parentContext = new StaticApplicationContext();
            parentContext.getBeanFactory().registerSingleton( KIE_SESSION_FACTORY_BEAN, kieSessionFactory );
            parentContext.refresh();
        }
        appContext = new ClassPathXmlApplicationContext( contextFiles, parentContext );

        workflowEngine = (WorkflowEngine) appContext.getBean( WorkflowManager.WORKFLOW_MANAGER );
    }
    
    /**
     * Get the WorkflowManager.
     * 
     * @return
     */
    public WorkflowManager getWorkflowManager()
    {
        return workflowEngine;
    }
    
//    /**
//     * Get the CM service.
//     * 
//     * @return
//     */
//    public ConfigManagementService getConfigurationManagementService()
//    {
//        return (ConfigManagementService) appContext.getBean( ConfigManagementService.CM_SERVICE );
//    }
//    
    /**
     * Get the Spring application context.
     * 
     * @return
     */
    public ApplicationContext getApplicationContext()
    {
        return appContext;
    }
}
