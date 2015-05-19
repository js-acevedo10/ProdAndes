package com.sistrans.dao;

import java.util.Date;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SendMessage {
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String QUEUE_DESTINATION = "jms/queue/prodandesescribir";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "//localhost:8080";
	
	public static void main(String[] args) throws NamingException {
		Context namingContext = null;
		JMSContext context = null;
		
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
		env.put(Context.SECURITY_PRINCIPAL, "juan");
		env.put(Context.SECURITY_CREDENTIALS, "jsacbn");
		
		try {
			namingContext = new InitialContext(env);
			//JNDI
			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
			Destination destination = (Destination) namingContext.lookup(QUEUE_DESTINATION);
			//JMS
//			context = connectionFactory.createContext("juan", "jsacbn");
			//Create Producer
			context.createProducer().send(destination, "Este es un mensaje de prueba " + new Date());
			System.out.println("Mensaje Eviado a la cola");
		} finally {
			if(namingContext != null) {
				namingContext.close();
			}
			if(context != null) {
				context.close();
			}
		}
	}
}
