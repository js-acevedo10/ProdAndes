package com.sistrans.mundo;
import java.util.Date;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SendMessage {
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String QUEUE_DESTINATION = "jms/queue/prodandesescribir";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://localhost:8080";
	
	public static void main(String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
		env.put(Context.SECURITY_PRINCIPAL, "juano");
		env.put(Context.SECURITY_CREDENTIALS, "123456");
		
	       InitialContext ctx = new InitialContext(env);                                                                       
	       Queue queue = (Queue) ctx.lookup(QUEUE_DESTINATION);                                  
	       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY);	                                                                       
	       QueueConnection queueConn = connFactory.createQueueConnection("juano","123456");                                                                 
	       QueueSession queueSession = queueConn.createQueueSession(false,Session.DUPS_OK_ACKNOWLEDGE);	                                                                          	       
	       QueueSender queueSender = queueSession.createSender(queue);
	       queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	       TextMessage message = queueSession.createTextMessage("msg" + new Date());
	       queueSender.send(message);
	       queueConn.close();
	}
}
