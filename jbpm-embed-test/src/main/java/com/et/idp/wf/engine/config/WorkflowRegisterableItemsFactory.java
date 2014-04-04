// Copyright 2014 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.engine.config;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import org.jbpm.runtime.manager.impl.SimpleRegisterableItemsFactory;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;

import com.et.idp.wf.engine.ServiceWorkflowManager;

/**
 * Custom implementation to allow additional direct configuration of items returned by getters.
 * 
 * @author mimartin
 */
public class WorkflowRegisterableItemsFactory extends SimpleRegisterableItemsFactory
{
    private List<ProcessEventListener> processEventListeners;
    
    private ServiceWorkflowManager workflowManager;

    public void setWorkflowManager( ServiceWorkflowManager workflowManager )
    {
        this.workflowManager = workflowManager;
    }

    /**
     * Set all the work item handlers, based on a map from handler name to implementation instance.
     * 
     * @param workItemHandlers
     */
    public void setWorkItemHandlers( Map<String, String> workItemHandlers )
    {
        for( Map.Entry<String, String> entry : workItemHandlers.entrySet() )
        {
            String name = entry.getKey();
            String className = entry.getValue();
            try
            {
                Class clazz = Class.forName( className );
                addWorkItemHandler( name, clazz );
            }
            catch( ClassNotFoundException e )
            {
                throw new RuntimeException( e );
            }
        }
    }

    /**
     * Set the process event listeners.
     * 
     * @param processEventListeners
     */
    public void setProcessEventListeners( List<ProcessEventListener> processEventListeners )
    {
        this.processEventListeners = processEventListeners;
    }

    /**
     * Always returns the same instances as configured with {@link #setProcessEventListeners}.
     * 
     * @see org.jbpm.runtime.manager.impl.SimpleRegisterableItemsFactory#getProcessEventListeners(org.kie.api.runtime.manager.RuntimeEngine)
     */
    @Override
    public List<ProcessEventListener> getProcessEventListeners( RuntimeEngine runtime )
    {
        return processEventListeners;
    }

    /**
     * Overridden so that exceptions are thrown instead of eaten.
     */
    @Override
    protected <T> T createInstance( Class<T> clazz, KieSession ksession )
    {
        T instance = null;
        
        if( (instance = create( getConstructor( clazz ) ) ) != null )
        {
            return instance;
        }
        if( (instance = create( getConstructor( clazz, KieSession.class ), ksession ) ) != null )
        {
            return instance;
        }
        if( (instance = create( getConstructor( clazz, KieSession.class, ServiceWorkflowManager.class ), ksession, workflowManager ) ) != null )
        {
            return instance;
        }
        
        throw new IllegalStateException( "Could not find a constructor for " + clazz );
    }

    private <T> Constructor<T> getConstructor( Class<T> clazz, Class... paramClasses ) throws InternalError
    {
        try
        {
            return clazz.getConstructor( paramClasses );
        }
        catch( NoSuchMethodException e )
        {
            // skip this constructor type
        }
        catch( RuntimeException e )
        {
            throw e;
        }
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
        return null;
    }

    private <T> T create( Constructor<T> constructor, Object... arguments )
    {
        try
        {
            if( constructor != null )
            {
                return constructor.newInstance( arguments );
            }
        }
        catch( RuntimeException e )
        {
            throw e;
        }
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
        return null;
    }
}