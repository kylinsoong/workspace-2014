package org.jboss.deployOrder.servlet;

public class Test {

	public static void main(String[] args) {
		try {
			Class cls = Thread.currentThread().getContextClassLoader().loadClass("org.jboss.deployOrder.servlet.QueueClientServlet");
			System.out.println("Loaded " + cls);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
