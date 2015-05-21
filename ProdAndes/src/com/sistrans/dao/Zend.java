package com.sistrans.dao;

//ZEND JS
public class Zend {

	public static void main(String[] args) {
		try {
			SendMessage sendMessage = new SendMessage("juano", "123456", "http-remoting://localhost:8080", "jms/queue/prodandesleer");
			sendMessage.sendMessage("pruebaDesdeCosteño,22,3]pruebaDesdeChapa,11,44");
//			ReceiveMessage receiveMessage = new ReceiveMessage("juano", "123456", "http-remoting://localhost:8080", "jms/queue/prodandesescribir");
//			receiveMessage.startReceiving();
//			receiveMessage.closeConnection();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
