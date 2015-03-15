package com.sistrans.fachada;

import com.sistrans.dao.ConsultaDAO;

public class ProdAndesUsuario {
	
	private ConsultaDAO dao;
	
	//Singleton
	
	private static ProdAndesUsuario instancia;
	
	public static ProdAndesUsuario darInstancia()
	{
		if(instancia == null)
			instancia = new ProdAndesUsuario();
		return instancia;
	}
	
	private ProdAndesUsuario()
	{
		dao = new ConsultaDAO();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	//Metodos de casos de uso
	
}
