package com.sistrans.fachada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sistrans.dao.ConsultaDAOAdmin;
import com.sistrans.dao.ConsultaDAOUsuario;
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.EtapadeProduccion;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Producto;

public class ProdAndesAdmin {

	private ConsultaDAOUsuario dao;
	
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
		dao = new ConsultaDAOUsuario();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar();
	}

	public ArrayList consultarExistencias(String tipo,
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT dataTwo.nombre, datatwo.toneladas, datatwo.tipo FROM ";
		String queryExisTipo="";
		if(tipo!=null&&existencias!=null)
		{
			queryExisTipo =  "(SELECT* FROM MATERIAPRIMA WHERE MATERIAPRIMA.TIPO='eclipse.tipo'AND MATERIAPRIMA.TONELADAS>='eclipse.existencias') datatwo";
		}
		else if (tipo!=null)
		{
			queryExisTipo = "(SELECT* FROM MATERIAPRIMA WHERE MATERIAPRIMA.TIPO='eclipse.tipo') datatwo";
		}
		else if (existencias!=null)
		{
			queryExisTipo = "(SELECT* FROM MATERIAPRIMA WHERE MATERIAPRIMA.TONELADAS>='eclipse.existencias') datatwo";
		}
		else
		{
			queryExisTipo = "(SELECT* FROM MATERIAPRIMA) datatwo";
		}
		if (etapa!=null)
		{
			query = query + "((SELECT* FROM ESTACIONMATERIAPRIMA WHERE ESTACIONMATERIAPRIMA.IDESTACION='eclipse.estapa') dataone INNER JOIN" + queryExisTipo + " on dataone.IDMATERIAPRIMA=datatwo.ID)";
		}
		else
		{
			query = query + queryExisTipo;
		}
		if (fechaSol!=null&fechaEntreg!=null)
		{
			query = query + " INNER JOIN (PEDIDOMATERIAPRIMA dataFourth INNER JOIN (SELECT* FROM PEDIDO WHERE PEDIDO.FECHACREACION<=to_date('eclipse.fechasol','MM-DD-YYYY') AND PEDIDO.FECHARECIBIDO>=to_date('eclipse.fechaEntreg','MM-DD-YYYY')) datathree on dataFourth.IDPEDIDO=datathree.id)on dataFourth.IDMATERIAPRIMA = datatwo.ID";
		}
		else if(fechaSol !=null)
		{
			query = query + " INNER JOIN (PEDIDOMATERIAPRIMA dataFourth INNER JOIN (SELECT* FROM PEDIDO WHERE PEDIDO.FECHACREACION<=to_date('eclipse.fechasol','MM-DD-YYYY')) datathree on dataFourth.IDPEDIDO=datathree.id)on dataFourth.IDMATERIAPRIMA = datatwo.ID";
		}
		else if(fechaEntreg !=null)
		{
			query = query + " INNER JOIN (PEDIDOMATERIAPRIMA dataFourth INNER JOIN (SELECT* FROM PEDIDO WHERE PEDIDO.FECHARECIBIDO>=to_date('eclipse.fechaEntreg','MM-DD-YYYY')) datathree on dataFourth.IDPEDIDO=datathree.id)on dataFourth.IDMATERIAPRIMA = datatwo.ID";
		}
		query = query+";";
		ArrayList<MateriaPrima> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
<<<<<<< Updated upstream
				String nombreT = b.getString("nombre");
				int numInventarioT = b.getInt("numInventario");
				int toneladasT = b.getInt("toneladas");
				String tipoT = b.getString("tipo");
=======
				String nombreT = b.getString("NOMBRE");
				int toneladasT = b.getInt("TONELADAS");
				String tipoT = b.getString("TIPO");
>>>>>>> Stashed changes
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.Producto WHERE ";
		System.out.println(tipo);
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

	public ArrayList<String> informacionMaterial(String query, String tipo) {
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
			queer = "SELECT* FROM PRODUCTO WHERE PRODUCTO.ID ="+query;
			System.out.println(queer);
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
	
	//Metodos de casos de uso
	
}
