package org.jboss.deployOrder.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class JMSClient {

	public static void main(String[] args) throws Exception {
		
		String destinationName = "/queue/testQueue";

        InitialContext ic = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        
        try {
			ic = new InitialContext();

			factory = (ConnectionFactory) ic.lookup("/ConnectionFactory");
			Queue queue = (Queue) ic.lookup(destinationName);
			
			connection = factory.createConnection();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer producer = session.createProducer(queue);
			
			TextMessage message = session.createTextMessage("KYLIN");
			producer.send(message);
			
			System.out.println("Message been sent to JBoss Messaging Server");
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != connection) {
				connection.close();
			}
		}
	}

}
