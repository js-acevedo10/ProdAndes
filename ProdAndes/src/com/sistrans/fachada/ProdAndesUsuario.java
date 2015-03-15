package com.sistrans.fachada;

import java.util.ArrayList;

import com.sistrans.dao.ConsultaDAOUsuario;
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.EtapadeProduccion;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Producto;

public class ProdAndesUsuario {
	
	private ConsultaDAOUsuario dao;
	
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
		dao = new ConsultaDAOUsuario();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Componente> consultarExistenciasComp(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<EtapadeProduccion> consultarExistenciasEtapa(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Producto> consultarExistenciasProd(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> informacionMaterial(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Metodos de casos de uso
	
}
