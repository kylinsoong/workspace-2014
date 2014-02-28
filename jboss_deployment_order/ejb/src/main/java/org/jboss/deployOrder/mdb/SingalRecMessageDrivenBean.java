package org.jboss.deployOrder.mdb;

import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue"),
		@ActivationConfigProperty(propertyName = "DLQMaxResent", propertyValue = "10") })
public class SingalRecMessageDrivenBean implements MessageListener {
	
	public SingalRecMessageDrivenBean() {
		System.out.println("Singal Receive Message Driven Bean Constructed");
	}

	
	public void onMessage(Message message) {
		
		try {
			Class cls = Thread.currentThread().getContextClassLoader().loadClass("org.jboss.deployOrder.servlet.QueueClientServlet");
			System.out.println("Loaded " + cls);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Load Class Error", e);
		}
		
		try {
			TextMessage tm = (TextMessage)message;
			String text = tm.getText();
			System.out.println("Received Message: " + text);
		} catch (Exception e) {
			throw new IllegalStateException("Receive Message Error", e);
		}
		
	}

}
