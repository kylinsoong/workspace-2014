package com.et.idp.wf.engine.config;

import javax.persistence.Persistence;

import org.jbpm.services.task.identity.JAASUserGroupCallbackImpl;
import org.kie.api.runtime.manager.RegisterableItemsFactory;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;

/**
 * Factory to create an instance of RuntimeManager in the way it will be used
 * when running in the appserver. Process definitions will be loaded from kjars
 * on the classpath and the KieSession resolution strategy will be
 * per-process-instance.
 * 
 * @author mimartin
 */
public class PerProcessClasspathRuntimeManagerFactory {
	
	private RegisterableItemsFactory registerableItemsFactory;

	/**
	 * Construct the RuntimeManager instance.
	 * 
	 * @return the RuntimeManager
	 */
	public RuntimeManager getRuntimeManager() {
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newClasspathKmoduleDefaultBuilder();
		configureBuilder(builder);
		RuntimeEnvironment environment = builder.get();

		RuntimeManager manager = RuntimeManagerFactory.Factory.get().newPerProcessInstanceRuntimeManager(environment);
		return manager;
	}

	/**
	 * Configure the RuntimeEnvironmentBuilder before creating the
	 * RuntimeManager from it.
	 * 
	 * @param builder
	 */
	protected void configureBuilder(RuntimeEnvironmentBuilder builder) {
		builder.registerableItemsFactory(registerableItemsFactory);
		builder.persistence(true);
		builder.entityManagerFactory(Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa", null));
		builder.userGroupCallback(new JAASUserGroupCallbackImpl());
	}

	public void setRegisterableItemsFactory(RegisterableItemsFactory registerableItemsFactory) {
		this.registerableItemsFactory = registerableItemsFactory;
	}
}
