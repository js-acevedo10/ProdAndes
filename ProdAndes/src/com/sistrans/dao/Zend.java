package com.sistrans.dao;

public class Zend {

	public static void main(String[] args) {
		try {
			SendMessage sendMessage = new SendMessage("juano", "123456", "http-remoting://localhost:8080", "jms/queue/prodandesescribir");
			sendMessage.sendMessage("Este es el mensaje 0");
			ReceiveMessage receiveMessage = new ReceiveMessage("juano", "123456", "http-remoting://localhost:8080", "jms/queue/prodandesescribir");
			receiveMessage.startReceiving();
			for (int i = 0; i < 10; i++) {
				sendMessage.sendMessage("Este es el mensaje " + i);
			}
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
