package com.sistrans.fachada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		ArrayList<MateriaPrima> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
				String nombreT = b.getString("nombre");
				int numInventarioT = b.getInt("numInventario");
				int toneladasT = b.getInt("toneladas");
				String tipoT = b.getString("tipo");
				MateriaPrima z = new MateriaPrima(nombreT, numInventarioT, toneladasT, tipoT);
				resultado.add(z);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if (a != null) 
			{
				try {
					a.close();
				} catch (SQLException exception) {
					
					try {
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}		
		return resultado;
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
		ArrayList<Componente> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
				String nombreT = b.getString("nombre");
				int numInventarioT = b.getInt("numInventario");
				String toneladasT = b.getString("unidadMedida");
				String tipoT = b.getString("tipo");
				Componente z = new Componente(nombreT, numInventarioT, toneladasT, tipoT);
				resultado.add(z);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if (a != null) 
			{
				try {
					a.close();
				} catch (SQLException exception) {
					
					try {
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}		
		return resultado;
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
		ArrayList<EtapadeProduccion> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
				String nombreT = b.getString("id");
				int num = b.getInt("num");
				String fechainicial = b.getString("fechaInicial");
				String fechaFinal = b.getString("fechaFinal");
				EtapadeProduccion z = new EtapadeProduccion(num, fechainicial, fechaFinal, nombreT);
				resultado.add(z);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if (a != null) 
			{
				try {
					a.close();
				} catch (SQLException exception) {
					
					try {
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}		
		return resultado;
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
		ArrayList<Producto> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
				String nombreT = b.getString("nombre");
				int numInventarioT = b.getInt("numInventario");
				int costoVenta = b.getInt("costoVenta");
				String tipoT = b.getString("tipo");
				Producto z = new Producto(nombreT, costoVenta, numInventarioT, tipoT);
				resultado.add(z);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if (a != null) 
			{
				try {
					a.close();
				} catch (SQLException exception) {
					
					try {
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}		
		return resultado;

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
