package com.sistrans.dao;

import java.sql.*;

import com.sistrans.fachada.ProdAndesAdmin;

public class ConsultaDAOUsuario {
		
	//Consultas
	//Atributos
	
	public Connection conexion;
	
	private String usuario;
	
	private String clave;
	
	private String cadenaConexion;
	
	private ProdAndesAdmin proAdmin;
	
	public ConsultaDAOUsuario()
	{
		
	}
	
	//Metodos
	
	public void inicializar()
	{
		try {			
			cadenaConexion = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";
			usuario = "ISIS2304171510";
			clave = "mmoefacet";
			final String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			establecer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void establecerConexion(String url, String usuario, String clave) throws SQLException
	{
		try
		{
			conexion = DriverManager.getConnection(url, usuario, clave);
		} catch(Exception e)
		{
			throw new SQLException("ERROR: ConsultaDAO obteniendo una conexin.");
		}
	}
	
	public void closeConnection(Connection connection) throws Exception
	{
		try
		{
			connection.close();
			connection = null;
		} catch(Exception e)
		{
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexión.");
		}
	}
	public void establecer() throws Exception
    {
    	establecerConexion(cadenaConexion, usuario, clave);
    }
	
	public void encenderAutocommit() {
		try {
			conexion.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void apagarAutocommit() {
		try {
			conexion.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commit() {
		try {
			conexion.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback() {
		try {
			conexion.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String enviarMensaje(String mensaje)
	{
		return mensaje;
		
	}
	public String recibirMensaje()
	{
		return cadenaConexion;
		//LLAME INTERPRETAR MENSAJE SI ES PREGUNTA
	}
	public String interpretarMensaje(String a)
	{
		PreparedStatement b1 = null;
		String[] mensaje = a.split(",");
		String requerimiento = mensaje[1];
		String query="";
		if(requerimiento.equals("18"))
		{
			String tipo = mensaje[2];
			String nombre = mensaje[3];
			String cantidad = mensaje[4];
			if(tipo.equals("MATERIAPRIMA"))
			{
				query="SELECT TONELADAS as NUM FROM "+tipo+" WHERE NOMBRE='"+nombre+"'";
			}
			else
			{
				query="SELECT NUMINVENTARIO as NUM FROM "+tipo+" WHERE NOMBRE='"+nombre+"'";
			}
			
		}
		else if(requerimiento.equals("19"))
		{
			
		}
		else if(requerimiento.equals("12"))
		{
			
		}
		else if(requerimiento.equals("13"))
		{
			return proAdmin.fusionMaterialesRespuesta(mensaje[2], mensaje[3], mensaje[4]);
		}
		else if(requerimiento.equals("U"))
		{
			String tipo = mensaje[2];
			String nombre = mensaje[3];
			String cantidad = mensaje[4];
			if(tipo.equals("MATERIAPRIMA"))
			{
				query="UPDATE "+tipo+"' SET TONELADAS=TONELADAS-"+cantidad+" WHERE NOMBRE='"+nombre+"'";
			}
			else
			{
				query="UPDATE "+tipo+"' SET NUMINVENTARIO=NUMINVENTARIO-"+cantidad+" WHERE NOMBRE='"+nombre+"'";
			}
		}	
		else if(requerimiento.equals("C"))
		{
			commit();
		}	
		else if(requerimiento.equals("R"))
		{
			rollback();
		}	
		else if(requerimiento.equals("A"))
		{
			
		}
		else if(requerimiento.equals("D"))
		{
			String tipo = mensaje[2];
			String nombre = mensaje[3];
			query="DELETE FROM "+tipo+"' WHERE NOMBRE='"+mensaje[3]+"'";
		}
		try 
		{
			inicializar();
			b1 = conexion.prepareStatement(query);
			ResultSet b = b1.executeQuery();
			while(b.next())
			{
				if(requerimiento.equals("18"))
				{
					int numero=b.getInt("NUM");
					int cantidad = Integer.parseInt(mensaje[4]);
					if(numero>cantidad)
					{
						return "R,TRUE";
					}
					else
					{
						return "R,FALSE";
					}
				}
			}
			if(requerimiento.equals("19"))
			{
				return "R,TRUE";
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if (a != null) 
			{
				try {
					b1.close();
				} catch (SQLException exception) {
					
					try {
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}		
		return "R,FALSE";		
	}
}
