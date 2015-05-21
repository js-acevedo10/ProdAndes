package com.sistrans.dao;

import java.util.Date;

public class zend {

	public static void main(String[] args) {
		try {
			SendMessage sendMessage = new SendMessage("juano", "123456", "http-remoting://localhost:8080", "jms/queue/prodandesescribir");
			sendMessage.sendMessage("Este es el mensaje 0 " + new Date());
			ReceiveMessage receiveMessage = new ReceiveMessage("juano", "123456", "http-remoting://localhost:8080", "jms/queue/prodandesescribir");
			receiveMessage.startReceiving();
			receiveMessage.closeConnection();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
