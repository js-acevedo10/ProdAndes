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
		String query="SELECT * FROM ProdAndes.materiaprima WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
		}
		else
		{
			query="SELECT * FROM ProdAndes.materiaprima";
		}
		return null;
	}

	public ArrayList<Componente> consultarExistenciasComp(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.componente WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
		}
		else
		{
			query="SELECT * FROM ProdAndes.componente";
		}
		return null;
	}

	public ArrayList<EtapadeProduccion> consultarExistenciasEtapa(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.Producto WHERE ";
		if(tipo!="")
		{
			query= "SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN PRODANDES.ETAPADEPRODUCCION DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
		}
		else
		{
			query="SELECT * FROM ProdAndes.EtapaDeProduccion";
		}
		return null;
	}

	public ArrayList<Producto> consultarExistenciasProd(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.producto WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
		}
		else
		{
			query="SELECT * FROM ProdAndes.producto";
		}
		return null;

	}

	public ArrayList<String> informacionMaterial(String query, String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean registrarPedido(String id1, String id2, String id3,
			String id32, String id4) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Metodos de casos de uso
	
}
