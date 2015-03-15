package com.sistrans.fachada;

import com.sistrans.dao.ConsultaDAO;

public class ProdAndesGerente {

	private ConsultaDAO dao;
	
	//Singleton
	
	private static ProdAndesGerente instancia;
	
	public static ProdAndesGerente darInstancia()
	{
		if(instancia == null)
			instancia = new ProdAndesGerente();
		return instancia;
	}
	
	private ProdAndesGerente()
	{
		dao = new ConsultaDAO();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	//Metodos de casos de uso
	
}
