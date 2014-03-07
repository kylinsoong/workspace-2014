package org.jboss.jmx.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


public class RemotingJMXClient {

	public static void main(String[] args) throws IOException {
		
//		String host = "10.66.218.46";
		String host = "10.66.192.93";
        int port = 9999;  // management-native port
        String urlString ="service:jmx:remoting-jmx://" + host + ":" + port;
        System.out.println("\n\n\t****  urlString: "+urlString);;
        JMXServiceURL serviceURL = new JMXServiceURL(urlString);
        
        Map map = new HashMap();
        String[] credentials = new String[] { "admin1", "password1!1" };
        map.put("jmx.remote.credentials", credentials);
        
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, map);
        MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

        //Invoke on the JBoss AS MBean server
        int count = connection.getMBeanCount();
        System.out.println(count);
        jmxConnector.close();
	}

}
