package com.sistrans.fachada;

import com.sistrans.dao.ConsultaDAO;

public class ProdAndesOperario {

	private ConsultaDAO dao;
	
	//Singleton
	
	private static ProdAndesOperario instancia;
	
	public static ProdAndesOperario darInstancia()
	{
		if(instancia == null)
			instancia = new ProdAndesOperario();
		return instancia;
	}
	
	private ProdAndesOperario()
	{
		dao = new ConsultaDAO();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	//Metodos de casos de uso
	
}
