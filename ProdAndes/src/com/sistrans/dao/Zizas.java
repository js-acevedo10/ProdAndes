package com.sistrans.dao;

public class Zizas {
	
	public boolean newMessage;
	public String message;
	
	public Zizas() {
		newMessage = false;
		message = "";
	}
	
	public String darMensaje() {
		newMessage = false;
		return message;
	}

}
