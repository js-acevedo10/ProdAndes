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

public class ProdAndesGerente {

	private ConsultaDAOUsuario dao;
	
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
		dao = new ConsultaDAOUsuario();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar();
	}

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.materiaprima WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
				if (fechaSol!=""&&fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
				}
				else if(fechaSol !="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
				}
				else if(fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
				}
			}
			else if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
			if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
		}
		else 	if (fechaSol!=""&&fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.materiaprima dataFourth on dataThree.materiaprimaID = dataFourth.ID";
		}
		else if(fechaSol !="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.Componente dataFourth on dataThree.materiaprimaID = dataFourth.ID";
		}
		else if(fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.materiaprima dataFourth on dataThree.materiaprimaID = dataFourth.ID";
		}
		else
		{
			query="SELECT * FROM ProdAndes.materiaprima";
		}
		ArrayList<MateriaPrima> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
				String nombreT = b.getString("nombre");
				int numInventarioT = b.getInt("numInventario");
				int toneladasT = b.getInt("toneladas");
				String tipoT = b.getString("tipo");
				MateriaPrima z = new MateriaPrima(nombreT, toneladasT, tipoT);
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
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.componente WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
				if (fechaSol!=""&&fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
				}
				else if(fechaSol !="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
				}
				else if(fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
				}
			}
			else if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.componente WHERE tipo = '" + tipo + "') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
			if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.componente WHERE numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.componente WHERE numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.componente WHERE numInventario = '" + existencias +"') dataFourth on dataThree.componenteID = dataFourth.ID";
			}
		}
		else 	if (fechaSol!=""&&fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.componente dataFourth on dataThree.componenteID = dataFourth.ID";
		}
		else if(fechaSol !="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.Componente dataFourth on dataThree.componenteID = dataFourth.ID";
		}
		else if(fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidocomponente dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.componente dataFourth on dataThree.componenteID = dataFourth.ID";
		}
		else
		{
			query="SELECT * FROM ProdAndes.componente";
		}
		ArrayList<Componente> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
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
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		String query="SELECT * FROM ProdAndes.Producto WHERE ";
		if(tipo!="")
		{
			query= "SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN PRODANDES.ETAPADEPRODUCCION DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
			if (fechaSol!=""&&fechaEntreg!="")
			{
			 query="SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN (SELECT* FROM PRODANDES.ETAPADEPRODUCCION WHERE FECHAINICIAL= '"+ fechaSol + "' AND fechaFinal= '"+ fechaEntreg + "') DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
			}
			else if(fechaSol !="")
			{
				query="SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN (SELECT* FROM PRODANDES.ETAPADEPRODUCCION WHERE FECHAINICIAL= '"+ fechaSol +"') DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";

			}
			else if(fechaEntreg!="")
			{
				query="SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN (SELECT* FROM PRODANDES.ETAPADEPRODUCCION WHERE fechaFinal= '"+ fechaEntreg + "') DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
			}
		}
		else if (fechaSol!=""&&fechaEntreg!="")
		{
		 query="SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN (SELECT* FROM PRODANDES.ETAPADEPRODUCCION WHERE FECHAINICIAL= '"+ fechaSol + "' AND fechaFinal= '"+ fechaEntreg + "') DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";
		}
		else if(fechaSol !="")
		{
			query="SELECT* FROM ((SELECT* FROM PRODANDES.PRODUCTO WHERE tipo='" + tipo + "') DATAONE LEFT JOIN PRODANDES.ETAPAPRODUCTO DATATWO ON DATAONE.ID = DATATWO.PRODUCTOID) DATATHREE LEFT JOIN (SELECT* FROM PRODANDES.ETAPADEPRODUCCION WHERE FECHAINICIAL= '"+ fechaSol +"') DATAFOURTH ON DATATHREE.ETAPAID = DATAFOURTH.ID";

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
			dao.inicializar();
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
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		String query="SELECT * FROM ProdAndes.Producto WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
				if (fechaSol!=""&&fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.Producto WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.ProductoID = dataFourth.ID";
				}
				else if(fechaSol !="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.Producto WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.ProductoID = dataFourth.ID";
				}
				else if(fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.Producto WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.ProductoID = dataFourth.ID";
				}
			}
			else if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.Producto WHERE tipo = '" + tipo + "') dataFourth on dataThree.ProductoID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.Producto WHERE tipo = '" + tipo + "') dataFourth on dataThree.ProductoID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.Producto WHERE tipo = '" + tipo + "') dataFourth on dataThree.ProductoID = dataFourth.ID";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
			if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.Producto WHERE numInventario = '" + existencias +"') dataFourth on dataThree.ProductoID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.Producto WHERE numInventario = '" + existencias +"') dataFourth on dataThree.ProductoID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.Producto WHERE numInventario = '" + existencias +"') dataFourth on dataThree.ProductoID = dataFourth.ID";
			}
		}
		else 	if (fechaSol!=""&&fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.Producto dataFourth on dataThree.ProductoID = dataFourth.ID";
		}
		else if(fechaSol !="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.Componente dataFourth on dataThree.ProductoID = dataFourth.ID";
		}
		else if(fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.PedidoProducto dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.Producto dataFourth on dataThree.ProductoID = dataFourth.ID";
		}
		else
		{
			query="SELECT * FROM ProdAndes.Producto";
		}

		ArrayList<Producto> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
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

	public ArrayList<String> informacionMaterial(String query, String tipo) 
	{
		String queer="";
		if(tipo=="materia-prima")
		{
			queer = "SELECT* FROM ProdAndes.materiaprima";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String nombreT = b.getString("nombre");
					String toneladasT = b.getString("toneladas");
					resultado.add(nombreT);
					resultado.add(toneladasT);
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
		else if (tipo== "componente")
		{
			queer = "SELECT* FROM ProdAndes.componente";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String nombreT = b.getString("nombre");
					String numInventarioT = b.getString("numInventario");
					String toneladasT = b.getString("unidadMedida");
					String tipoT = b.getString("tipo");
					resultado.add(nombreT);
					resultado.add(numInventarioT);
					resultado.add(toneladasT);
					resultado.add(tipoT);
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
		else if(tipo == "producto")
		{
			queer = "SELECT* FROM ProdAndes.producto";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String nombreT = b.getString("nombre");
					String numInventarioT = b.getString("numInventario");
					String costoVenta = b.getString("costoVenta");
					String tipoT = b.getString("tipo");

					resultado.add(nombreT);
					resultado.add(numInventarioT);
					resultado.add(costoVenta);
					resultado.add(tipoT);
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
		else
		{
			queer = "SELECT* FROM ProdAndes.etapadeproduccion";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String nombreT = b.getString("id");
					String num = b.getString("num");
					String fechainicial = b.getString("fechaInicial");
					String fechaFinal = b.getString("fechaFinal");
					resultado.add(nombreT);
					resultado.add(num);
					resultado.add(fechainicial);
					resultado.add(fechaFinal);
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
	}

	public boolean registrarMateriaPrima(String nombre, String cantidad,
			String tonelada) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO ProdAndes.MateriaPrima (nombre, numInventario, toneladas, id, tipo)VALUES ('"+nombre+"','"+cantidad+"','"+tonelada+"','"+nombre+cantidad+tonelada+"','no tipo');";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
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
		return true;
	}

	public boolean registrarComponente(String nombre, String cantidad,
			String unidadMedida) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO ProdAndes.Componente (nombre, numInventario, unidadMedida, id, tipo)VALUES ('"+nombre+"','"+cantidad+"','"+unidadMedida+"','"+nombre+cantidad+unidadMedida+"','no tipo')";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
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
		return true;
	}

	public boolean registrarEntregaPedido(String id, String fechaFinal) {
		// TODO Auto-generated method stub
		String query = "UPDATE ProdAndes.Pedido SET fechaRecibido='"+fechaFinal+"' WHERE id='"+id+"'";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
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
		return true;
	}
	
	//Metodos de casos de uso
	
}
