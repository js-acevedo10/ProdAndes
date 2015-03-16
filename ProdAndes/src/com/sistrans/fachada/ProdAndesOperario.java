package com.sistrans.fachada;

import java.util.ArrayList;

import com.sistrans.dao.ConsultaDAOUsuario;
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.EtapadeProduccion;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Producto;

public class ProdAndesOperario {

	private ConsultaDAOUsuario dao;
	
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
		dao = new ConsultaDAOUsuario();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.materiaprima WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
				if(fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
				}
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
			if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
		}
		else
		{
			query="SELECT * FROM ProdAndes.materiaprima";
		}
		return null;
	}

	public ArrayList<Componente> consultarExistenciasComp(String tipo,
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.componente WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
				if(fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
				}
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
			if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.componente WHERE numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
		}
		else
		{
			query="SELECT * FROM ProdAndes.componente";
		}
		return null;
	}

	public ArrayList<EtapadeProduccion> consultarExistenciasEtapa(String tipo,
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.Producto WHERE ";
		if(tipo!="")
		{
			query= "SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN PRODANDES.ETAPADEPRODUCCION DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
			if(fechaEntreg!="")
			{
				query="SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN (SELECT* FROM PRODANDES.ETAPADEPRODUCCION WHERE fechaFinal= '"+ fechaEntreg + "') DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
			}
		}
		else if(fechaEntreg!="")
		{
			query="SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN (SELECT* FROM PRODANDES.ETAPADEPRODUCCION WHERE fechaFinal= '"+ fechaEntreg + "') DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
		}
		else
		{
			query="SELECT * FROM ProdAndes.EtapaDeProduccion";
		}
		return null;
	}

	public ArrayList<Producto> consultarExistenciasProd(String tipo,
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.producto WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
				if(fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidoproducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.producto WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.productoID = dataFourth.ID";
				}
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidoproducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.producto WHERE tipo = '" + tipo + "') dataFourth on dataThree.productoID = dataFourth.ID";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
			if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidoproducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.producto WHERE numInventario = '" + existencias +"') dataFourth on dataThree.productoID = dataFourth.ID";
			}
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

	public boolean registrarEjecucionEtapa(String id, String fi, String ft,
			String cod) {
		// TODO Auto-generated method stub
		return false;
	}	
}
