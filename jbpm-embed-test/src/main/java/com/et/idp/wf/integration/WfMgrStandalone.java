package com.et.idp.wf.integration;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.et.idp.wf.engine.config.WorkflowManagerFactory;

/**
 * Runs workflow manager as a local, standalone process.
 * 
 * @author mimartin
 */
public class WfMgrStandalone {
	// Outline of dependencies:
	//
	// CM : mocked out to return info from a fixed list
	// FileNamer: mocked out to return info from a local sequence
	// Event Topic: mocked out to write messages to a file
	// Controller Queue: real (isolated) queue on JBoss
	// ETL Service Queues: real (isolated) queue on JBoss

	private static final Logger LOG = Logger.getLogger(WfMgrStandalone.class);

	private static ApplicationContext appContext;

	public static void main(final String[] args) throws Exception {
		try {
			// build the application
			final String[] contextFiles = { /* ContextUtil.ENV_CONTEXT_FILE, */
					ContextUtil.ENGINE_CONTEXT_FILE,
					ContextUtil.RUNTIME_MANAGER_CONTEXT_FILE,
					ContextUtil.NO_PERSISTENCE_RUNTIME_MANAGER_CONTEXT_FILE };

			WorkflowManagerFactory workflowManagerFactory = new WorkflowManagerFactory(contextFiles);
			appContext = workflowManagerFactory.getApplicationContext();

			// // start the JMS listener
			// final MessageListener messageListener = (MessageListener)
			// appContext.getBean( TxnAwareMessageListener.MESSAGE_LISTENER );
			// final ConfigManagementService cmService =
			// workflowManagerFactory.getConfigurationManagementService();
			// final JmsConnectInfo jmsConnectInfo =
			// cmService.getControllerQueue();
			//
			// LOG.info( "Starting to listen to queue..." );
			// new MessageListenerContainer( jmsConnectInfo, messageListener
			// ).run();
		} catch (final Exception e) {
			LOG.error("Error", e);
		}
		System.exit(0); // force exit so that Hibernate threads go away
	}
}
