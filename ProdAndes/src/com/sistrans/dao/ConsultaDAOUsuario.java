package com.sistrans.dao;

import java.sql.*;

public class ConsultaDAOUsuario {
		
	//Consultas
	//Atributos
	
	public Connection conexion;
	
	private String usuario;
	
	private String clave;
	
	private String cadenaConexion;
	
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
}
