package org.jboss.deployOrder.servlet;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueueClientServlet extends HttpServlet {
	
	private static final long serialVersionUID = 8182131264740127488L;

	public QueueClientServlet() {
		System.out.println("Queue Client Servlet Constructed");
		sendSingal();
	}

	private void sendSingal() {
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

			System.out.println("Message been sent to testQueue");
			
		} catch (Exception e) {
			throw new IllegalStateException("Send message to queue error", e);
		} finally {
			// should close session
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sendSingal();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
