package com.sistrans.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ConsultaDAOGerente {
	
	//Constantes
	
	private static final String ARCHIVO_CONEXION = "/../conexion.properties";
	
	//Consultas
	//Atributos
	
	public Connection conexion;
	
	private String usuario;
	
	private String clave;
	
	private String cadenaConexion;
	
	
	private DataSource ds2;
	private Connection conn2;
	private InitialContext context;
	
	/**
	 * Fabrica de conexiones para el envio de mensajes a la cola
	 */
	
	private ConnectionFactory cf;
	
	/**
	 * Conexion a la cola de mensajes
	 */
	
	private javax.jms.Connection comm;
	
	/**
	 * Cola definida para la recepcion de mensajes.
	 */
	
	private Queue colaDefinida;
	
	public ConsultaDAOGerente()
	{
		
	}
	
	//Metodos
	
	public void inicializar()
	{
		try {			
			ds2 = (DataSource) context.lookup("java:XAChie2");
			cf = (ConnectionFactory) context.lookup("java:JmsXA");
			colaDefinida = (Queue) context.lookup("queue/WebApp2");
			
			
			
			
			
			
//			cadenaConexion = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";
//			
//			usuario = "ISIS2304171510";
//			clave = "mmoefacet";
//			final String driver = "oracle.jdbc.driver.OracleDriver";
//			Class.forName(driver);
//			establecer();
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
}
