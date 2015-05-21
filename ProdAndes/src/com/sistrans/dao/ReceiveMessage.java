package com.sistrans.dao;

import java.util.Date;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ReceiveMessage implements MessageListener {

	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static String QUEUE_DESTINATION = "";	
	private static String PROVIDER_URL = "";
	private static JMSConsumer consumer;
	private ConnectionFactory connectionFactory;
	private Context namingContext;
	private Destination destination;
	private String user;
	private String pass;
	
	public ReceiveMessage(String user, String pass, String url, String cola) 
	{
		this.user = user;
		this.pass = pass;
		PROVIDER_URL = url;
		QUEUE_DESTINATION = cola;
		
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, PROVIDER_URL);
		env.put(Context.SECURITY_PRINCIPAL, this.user);
		env.put(Context.SECURITY_CREDENTIALS, this.pass);
		
		try {
			namingContext = new InitialContext(env);
			connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
			destination = (Destination) namingContext.lookup(QUEUE_DESTINATION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Recibir mensajes listo " + new Date());
	}
	
	public void startReceiving() {
		try (JMSContext context = connectionFactory.createContext(user, pass);) {
			consumer = context.createConsumer(destination);
			consumer.setMessageListener(this);
			System.out.println("Servicio asíncrono funcionando " + new Date());
			System.in.read();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("Mensaje recibido: " + msg.getText() + " " + new Date());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() throws Exception {
		consumer.close();
		namingContext.close();
		System.out.println("Servicio asíncrono detenido " + new Date());
	}
}