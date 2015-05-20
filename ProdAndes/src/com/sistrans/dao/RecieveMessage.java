package com.sistrans.dao;

import java.util.Date;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RecieveMessage implements MessageListener {

	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String QUEUE_DESTINATION = "jms/queue/prodandesescribir";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
	
	public static void main(String[] args) throws NamingException, JMSException {
		System.out.println("LEER");
		Context namingContext = null;
		JMSContext context = null;
		
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, PROVIDER_URL);
		env.put(Context.SECURITY_PRINCIPAL, "juano");
		env.put(Context.SECURITY_CREDENTIALS, "123456");
		
		try {
			namingContext = new InitialContext(env);
	        
	        // Use JNDI to look up the connection factory and queue
	        ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
	        Destination destination = (Destination) namingContext.lookup(QUEUE_DESTINATION);
	        
	        // Create a JMS context to use to create consumers
	        context = connectionFactory.createContext("juano", "123456"); // again, don't do this in production
	        
	        // Read a message.  If nothing is there, this will return null
	        JMSConsumer consumer = context.createConsumer(destination);
	        String text = consumer.receiveBodyNoWait(String.class);
	        System.out.println("Received message: " + text );
		} finally {
			if (namingContext != null) {
        		namingContext.close();
        	}
        	if (context != null) {
        		context.close();
        	}
		}
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("Incoming messages: " + ((TextMessage)message).getText());
		} catch(JMSException e) {
			e.printStackTrace();
		}
	}
}
