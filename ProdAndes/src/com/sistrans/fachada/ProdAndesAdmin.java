package com.sistrans.fachada;

import com.sistrans.dao.ConsultaDAO;

public class ProdAndesAdmin {

	private ConsultaDAO dao;
	
	//Singleton
	
	private static ProdAndesAdmin instancia;
	
	public static ProdAndesAdmin darInstancia()
	{
		if(instancia == null)
			instancia = new ProdAndesAdmin();
		return instancia;
	}
	
	private ProdAndesAdmin()
	{
		dao = new ConsultaDAO();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	//Metodos de casos de uso
	
}
