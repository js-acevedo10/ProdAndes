package com.sistrans.dao;

import java.sql.*;

import com.sistrans.fachada.ProdAndesAdmin;
import com.sistrans.fachada.ProdAndesGerente;
import com.sistrans.fachada.ProdAndesUsuario;

public class ProdAndes3 {
	
	private ConsultaDAOUsuario dao;
	
	
	public void enviarMensajeRF18()
	{
		String msgEnviado = "P,18,MATERIAPRIMA,BSEWKEDBBX,760";
		System.out.println("Se esta enviando un mensaje que requiere 760 unidades de BSEWKEDBBX");
		System.out.println("Se espera que la respuesta sea R,TRUE");
		dao.enviarMensaje(msgEnviado);
		String respuesta = "";
		try {
			respuesta = dao.recibirMensaje(msgEnviado);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta1:  "+respuesta);
		msgEnviado = "P,18,MATERIAPRIMA,IWWFKJHTAR,600";
		System.out.println("Se esta enviando un mensaje que requiere 600 unidades de IWWFKJHTAR");
		System.out.println("Se espera que la respuesta sea R,FALSE");
		dao.enviarMensaje(msgEnviado);
		respuesta = "";
		try {
			respuesta = dao.recibirMensaje(msgEnviado);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta2:  "+respuesta);
	}
	public void enviarMensajeRF19()
	{
		String msgEnviado = "P,19,";
		System.out.println("Se esta enviando un mensaje que requiere un cambio de estado de la estacion 3");
		System.out.println("Se espera que la respuesta sea R,ACTIVO o R,ERROR");
		dao.enviarMensaje(msgEnviado);
		String respuesta = "";
		try {
			respuesta = dao.recibirMensaje(msgEnviado);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta1:  "+respuesta);
		msgEnviado = "P,19,463";
		System.out.println("Se esta enviando un mensaje que requiere un cambio de estado de la estacion 463");
		System.out.println("Se espera que la respuesta sea R,DESACTIVO o R,ERROR");
		dao.enviarMensaje(msgEnviado);
		respuesta = "";
		try {
			respuesta = dao.recibirMensaje(msgEnviado);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta2:  "+respuesta);
	}
	public void enviarMensajeRF12()
	{
		String msgEnviado = "P,12,01-01-1900,12-12-9999,1,1,1";
		System.out.println("Se esta enviando un mensaje que requiere estaciones");
		System.out.println("Se espera que la respuesta sean muchos objetos JSON");
		dao.enviarMensaje(msgEnviado);
		String respuesta = "";
		try {
			respuesta = dao.recibirMensaje(msgEnviado);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta1:  "+respuesta);
	}
	public void enviarMensajeRF13()
	{
		String msgEnviado = "P,13,MATERIAPRIMA,01-01-1999,12-12-9999";
		System.out.println("Se esta enviando un mensaje que requiere las materias primas mas usadas desde 01-01-1999 hasta 12-12-9999");
		System.out.println("Se espera que la respuesta sean muchos objetos JSON");
		dao.enviarMensaje(msgEnviado);
		String respuesta = "";
		try {
			respuesta = dao.recibirMensaje(msgEnviado);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta1:  "+respuesta);
		msgEnviado = "P,13,COMPONENTE,01-01-1999,12-12-9999";
		System.out.println("Se esta enviando un mensaje que requiere los componentes mas usadas desde 01-01-1999 hasta 12-12-9999");
		System.out.println("Se espera que la respuesta sea R,DESACTIVO o R,ERROR");
		dao.enviarMensaje(msgEnviado);
		respuesta = "";
		try {
			respuesta = dao.recibirMensaje(msgEnviado);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Respuesta2:  "+respuesta);
	}
	public String recibirMensaje(String f)
	{
		System.out.println("Interpretando: " + f);
		PreparedStatement a = null;
		String[] mensaje = f.split(",");

		if (!mensaje[0].equals("P")) {
			System.out.println("Como es respuesta no se tiene que hacer nada con el mensaje");
			System.out.println("Asi que se imprime el mensaje igual: "+f);
			return f;
		} else {
			String requerimiento = mensaje[1];
			if (requerimiento.equals("18"))
			{
				if(mensaje[2].equals("MATERIAPRIMA"))
				{
					if(Integer.parseInt(mensaje[4])>600)
					{
						System.out.println("La cantidad que se enviada es mayor a 600, por lo que no existen las unidades necesarias");
						return "R,FALSE";
					}
					else
					{
						System.out.println("La cantidad que se enviada es menor a 600, por lo que no existen las unidades necesarias");
						return "R,TRUE";
					}
				}
				else
				{
					if(Integer.parseInt(mensaje[4])>600)
					{
						System.out.println("La cantidad que se enviada es mayor a 600, por lo que no existen las unidades necesarias");
						return "R,FALSE";
					}
					else
					{
						System.out.println("La cantidad que se enviada es menor a 600, por lo que no existen las unidades necesarias");
						return "R,TRUE";
					}
				}
			}
			else if(requerimiento.equals("19"))
			{
				if(mensaje[3].equals("ETAPA1")||mensaje[3].equals("ETAPA2")||mensaje[3].equals("ETAPA3")||mensaje[3].equals("ETAPA4")||mensaje[3].equals("ETAPA6"))
				{
					System.out.println("La etapa es 1 o 2 o 3 o 4 o 6, asi que 'activa'");
					return "R,ACTIVO";
				}
				else
				{
					System.out.println("La etapa tiene un nombre al azar, asi que 'desactiva'");
					return "R,DESACTIVO";
				}
			}
			else if(requerimiento.equals("12"))
			{
				System.out.println("Se estan enviando 6 etapas con datos al azar");
				return "1,6,2,600,1,260,4,800]2,6,3,600,2,360,6,900]3,7,4,700,3,460,6,100]4,8,6,800,4,660,7,200]6,9,6,900,6,660,8,300";
			}
			else if(requerimiento.equals("13"))
			{
				
				if(mensaje[2].equals("MATERIAPRIMA"))
				{
					System.out.println("Se pidieron materias primas, y se enviaran 6 al azar");
					return "metal1,600,6]metal2,600,4]metal3,700,4]metal4,1000,2]metal6,60,1]";
				}
				else
				{
					System.out.println("Se pidieron materias primas, y se enviaran 6 al azar");
					return "metal1,600,6]metal2,600,5]metal3,700,5]metal4,1000,2]metal5,60,1]";
				}
			}
			else
			{
				System.out.println("Se pidio hacer un update, delete, add, commit o rollback, se respondera con R,TRUE");
				return "R,TRUE";
			}
		}
	}
	

}
