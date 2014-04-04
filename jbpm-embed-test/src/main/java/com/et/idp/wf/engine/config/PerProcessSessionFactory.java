package com.et.idp.wf.engine.config;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.Context;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;

import com.et.idp.wf.engine.KieSessionFactory;

/**
 * Create a new KieSession for each process instance, usign the RuntimeManager
 * and RuntimeEngine.
 * 
 * @author mimartin
 */
public class PerProcessSessionFactory implements KieSessionFactory {
	
	private RuntimeManager runtimeManager;

	public void setRuntimeManager(RuntimeManager runtimeManager) {
		this.runtimeManager = runtimeManager;
	}

	/**
	 * @see com.et.idp.wf.engine.KieSessionFactory#getSesionForProcessInstance(long)
	 */
	public KieSession getSesionForProcessInstance(long processInstanceId) {
		// find the RuntimeEngine for the given process instance
		Context<?> context = ProcessInstanceIdContext.get(processInstanceId);
		return getSession(context);
	}

	/**
	 * @see com.et.idp.wf.engine.KieSessionFactory#getSesionForNewProcessInstance()
	 */
	public KieSession getSesionForNewProcessInstance() {
		return getSession(ProcessInstanceIdContext.get());
	}

	/**
	 * Get the session for a given Context.
	 * 
	 * @param context
	 * @return the session
	 */
	private KieSession getSession(Context<?> context) {
		RuntimeEngine runtime = runtimeManager.getRuntimeEngine(context);

		return runtime.getKieSession();
	}
}
