package com.sistrans.dao;

import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SendMessage {
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";	
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static String QUEUE_DESTINATION;
	private static String PROVIDER_URL;
	private InitialContext ctx;
	private Queue queue;
	private QueueConnectionFactory connFactory;
	private QueueConnection queueConn;
	private QueueSession queueSession;
	private QueueSender queueSender;
	String user, pass;
	
	public SendMessage(String user, String pass, String url, String queue) throws Exception {
		this.user = user;
		this.pass = pass;
		QUEUE_DESTINATION = queue;
		PROVIDER_URL = url;
		
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
		env.put(Context.SECURITY_PRINCIPAL, this.user);
		env.put(Context.SECURITY_CREDENTIALS, this.pass);
		
		ctx = new InitialContext(env);
		this.queue = (Queue) ctx.lookup(QUEUE_DESTINATION);
		connFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
		queueConn = connFactory.createQueueConnection(this.user, this.pass);
		queueSession = queueConn.createQueueSession(false,Session.DUPS_OK_ACKNOWLEDGE);
		queueSender = queueSession.createSender(this.queue);
		queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		System.out.println("Enviar mensajes listo.");
	}
	
	public void sendMessage(String message) {
		System.out.println("Por enviar..." + message);
		try {
			TextMessage msg = queueSession.createTextMessage(message);
			queueSender.send(msg);
			System.out.println("Mensaje enviado: " + msg.getText());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
