package org.jboss.exit.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class ServerExitSingleton {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerExitSingleton.class);

	@PostConstruct
	protected void startup()  {

		LOGGER.info("Server Exit Test will be initialized!");
		
		try {
			Thread.currentThread().sleep(1000 * 20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		LOGGER.info("Server Exit Test System will be exit");
		
		Runtime.getRuntime().exit(0);

	}

	@PreDestroy
	protected void destroy() {

		LOGGER.info("Server Exit Test will be removed!");

	}

}
