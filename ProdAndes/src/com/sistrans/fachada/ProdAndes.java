package com.sistrans.fachada;

import com.sistrans.dao.ConsultaDAO;

public class ProdAndes {
	
	private ConsultaDAO dao;
	
	//Singleton
	
	private static ProdAndes instancia;
	
	public static ProdAndes darInstancia()
	{
		if(instancia == null)
			instancia = new ProdAndes();
		return instancia;
	}
	
	private ProdAndes()
	{
		dao = new ConsultaDAO();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	//Metodos de casos de uso
	
}
