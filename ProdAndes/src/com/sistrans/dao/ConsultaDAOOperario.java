package com.sistrans.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ConsultaDAOOperario {
	
	//Constantes
	
	private static final String ARCHIVO_CONEXION = "/../conexion.properties";
	
	//Consultas
	//Atributos
	
	public Connection conexion;
	
	private String usuario;
	
	private String clave;
	
	private String cadenaConexion;
	
	public ConsultaDAOOperario()
	{
		
	}
	
	//Metodos
	
	public void inicializar(String path)
	{
		try {
			File arch = new File(path + ARCHIVO_CONEXION);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			
			prop.load(in);
			in.close();
			
			usuario = prop.getProperty("usuario");
			clave = prop.getProperty("clave");
			final String driver = prop.getProperty("driver");
			Class.forName(driver);
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
}
