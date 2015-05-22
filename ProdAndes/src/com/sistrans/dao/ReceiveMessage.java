package com.sistrans.dao;

import java.util.ArrayList;
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
	public boolean newMessageHere;
	public String lastMessage;
	public ArrayList<String> msgArray;
	public Zizas zz;
	public boolean test;
	
	public ReceiveMessage(String user, String pass, String url, String cola) 
	{
		test = false;
		zz = new Zizas();
		msgArray = new ArrayList<String>();
		lastMessage = "";
		newMessageHere = false;
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
	
	public Zizas startReceiving() {
		try (JMSContext context = connectionFactory.createContext(user, pass);) {
			consumer = context.createConsumer(destination);
			consumer.setMessageListener(this);
			System.in.read();
			return zz;
		} catch(Exception e) {
		}
		return zz;
	}
	
	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("Mensaje recibido: " + msg.getText());
			newMessageHere = true;
			lastMessage = msg.getText();
			if(test) {
				ProdAndes3 p3 = new ProdAndes3(lastMessage);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() throws Exception {
		consumer.close();
		namingContext.close();
	}

	public String getLastMessage() {
		newMessageHere = false;
		lastMessage = "";
		return lastMessage;
	}
}