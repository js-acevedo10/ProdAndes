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
			String existenciasMin, String existenciasMax, String estacion) {
		// TODO Auto-generated method stub
		String query="SELECT datatwo.nombre, datatwo.toneladas FROM ";
		String queryExisTipo="";
		if ((existenciasMin!=null&&existenciasMin!="")&&(existenciasMax!=null&&existenciasMax!=""))
		{
			queryExisTipo = "(SELECT* FROM MATERIAPRIMA WHERE MATERIAPRIMA.TONELADAS>='"+existenciasMin+"' AND MATERIAPRIMA.TONELADAS<='"+existenciasMax+"') datatwo";
		}
		else if (existenciasMin!=null&&existenciasMin!="")
		{
			queryExisTipo = "(SELECT* FROM MATERIAPRIMA WHERE MATERIAPRIMA.TONELADAS>='"+existenciasMin+"') datatwo";
		}
		else if (existenciasMax!=null&&existenciasMax!="")
		{
			queryExisTipo = "(SELECT* FROM MATERIAPRIMA WHERE MATERIAPRIMA.TONELADAS<='"+existenciasMax+"') datatwo";
		}
		else
		{
			queryExisTipo = "(SELECT* FROM MATERIAPRIMA) datatwo";
		}
		if (estacion!=null&&estacion!="")
		{
			query = query + "((SELECT* FROM ESTACIONDEPRODUCCION WHERE ESTACIONDEPRODUCCION.IDMATERIAPRIMA is not null AND ESTACIONDEPRODUCCION.CODIGO='"+estacion+"') dataone INNER JOIN " + queryExisTipo + " on dataone.IDMATERIAPRIMA=datatwo.ID)";
		}
		else
		{
			query = query + queryExisTipo;
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
				String nombreT = b.getString("NOMBRE");
				int toneladasT = b.getInt("TONELADAS");
				MateriaPrima z = new MateriaPrima(nombreT, toneladasT);
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
			String existenciasMin, String existenciasMax, String estacion) {
		// TODO Auto-generated method stub
		String query="SELECT dataTwo.nombre, datatwo.NUMINVENTARIO, datatwo.UNIDADMEDIDA FROM ";
		String queryExisTipo="";
		if ((existenciasMin!=null&&existenciasMin!="")&&(existenciasMax!=null&&existenciasMax!=""))
		{
			queryExisTipo = "(SELECT* FROM COMPONENTE WHERE COMPONENTE.NUMINVENTARIO>="+existenciasMin+" AND COMPONENTE.NUMINVENTARIO<="+existenciasMax+") datatwo";
		}
		else if (existenciasMin!=null&&existenciasMin!="")
		{
			queryExisTipo = "(SELECT* FROM COMPONENTE WHERE COMPONENTE.NUMINVENTARIO>="+existenciasMin+") datatwo";
		}
		else if (existenciasMax!=null&&existenciasMax!="")
		{
			queryExisTipo = "(SELECT* FROM COMPONENTE WHERE COMPONENTE.NUMINVENTARIO<="+existenciasMax+") datatwo";
		}
		else
		{
			queryExisTipo = "(SELECT* FROM COMPONENTE) datatwo";
		}
		if (estacion!=null&&estacion!="")
		{
			query = query + "((SELECT* FROM ESTACIONDEPRODUCCION WHERE ESTACIONDEPRODUCCION.IDCOMPONENTE is not null AND ESTACIONDEPRODUCCION.CODIGO='"+estacion+"') dataone INNER JOIN " + queryExisTipo + " on dataone.IDCOMPONENTE=datatwo.ID)";
		}
		else
		{
			query = query + queryExisTipo;
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
				String nombreT = b.getString("NOMBRE");
				int numInventarioT = b.getInt("NUMINVENTARIO");
				String toneladasT = b.getString("UNIDADMEDIDA");
				Componente z = new Componente(nombreT, numInventarioT, toneladasT);
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
			String existenciasMin, String existenciasMax, String estacion) {
		String query="SELECT dataTwo.ID, datatwo.NUM FROM ";
		String queryExisTipo="(SELECT ESTACIONDEPRODUCCION.IDETAPAPRODUCCION FROM ESTACIONDEPRODUCCION WHERE ";
		if((estacion==null||estacion=="")&&(existenciasMax==null||existenciasMax=="")&&(existenciasMin==null||existenciasMin==""))
		{
			queryExisTipo="(SELECT ESTACIONDEPRODUCCION.IDETAPAPRODUCCION FROM ESTACIONDEPRODUCCION) dataOne";
		}
		if (estacion!=null&&estacion!=""&&(existenciasMin!=null&&existenciasMin!="")||(existenciasMax!=null&&existenciasMax!=""))
		{
			queryExisTipo = queryExisTipo+" ESTACIONDEPRODUCCION.CODIGO='"+estacion+"' AND";
		}
		else if(estacion!=null&&estacion!="")
		{
			queryExisTipo = queryExisTipo+" ESTACIONDEPRODUCCION.CODIGO='"+estacion+"'";
		}
		
		if ((existenciasMin!=null&&existenciasMin!="")&&(existenciasMax!=null&&existenciasMax!=""))
		{
			queryExisTipo = " (ESTACIONDEPRODUCCION.NUMCOMPONENTE>="+existenciasMin+" AND ESTACIONDEPRODUCCION.NUMCOMPONENTE<="+existenciasMax+")OR(ESTACIONDEPRODUCCION.NUMMATERIAPRIMA>="+existenciasMin+" AND ESTACIONDEPRODUCCION.NUMMATERIAPRIMA<="+existenciasMax+")OR(ESTACIONDEPRODUCCION.NUMPRODUCTO>="+existenciasMin+" AND ESTACIONDEPRODUCCION.NUMPRODUCTO<="+existenciasMax+")) dataOne";
		}
		else if (existenciasMin!=null&&existenciasMin!="")
		{
			queryExisTipo = " (ESTACIONDEPRODUCCION.NUMCOMPONENTE>="+existenciasMin+")OR(ESTACIONDEPRODUCCION.NUMMATERIAPRIMA>="+existenciasMin+")OR(ESTACIONDEPRODUCCION.NUMPRODUCTO>="+existenciasMin+") dataOne";
		}
		else if (existenciasMax!=null&&existenciasMax!="")
		{
			queryExisTipo = " (ESTACIONDEPRODUCCION.NUMCOMPONENTE<="+existenciasMax+")OR(ESTACIONDEPRODUCCION.NUMMATERIAPRIMA<="+existenciasMax+")OR(ESTACIONDEPRODUCCION.NUMPRODUCTO<="+existenciasMax+") dataOne";
		}
		query = query + queryExisTipo + " INNER JOIN ETAPAPRODUCCION dataTwo on dataOne.IDETAPAPRODUCCION=dataTwo.ID";
		ArrayList<EtapadeProduccion> resultado = new ArrayList<>();
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
				String nombreT = b.getString("ID");
				int num = b.getInt("NUM");
				EtapadeProduccion z = new EtapadeProduccion(num, nombreT);
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
			String existenciasMin, String existenciasMax, String estacion) {
		String query="SELECT datatwo.NOMBRE, datatwo.NUMINVENTARIO, datatwo.COSTOVENTA FROM ";
		String queryExisTipo="";
		if (existenciasMin!= null&&existenciasMax!= null&&existenciasMin!=""&&existenciasMax!="")
		{
			queryExisTipo = "(SELECT* FROM PRODUCTO WHERE PRODUCTO.NUMINVENTARIO>="+existenciasMin+" AND PRODUCTO.NUMINVENTARIO<="+existenciasMax+") datatwo";
		}
		else if (existenciasMin!= null && existenciasMin!="")
		{
			queryExisTipo = "(SELECT* FROM PRODUCTO WHERE PRODUCTO.NUMINVENTARIO>="+existenciasMin+") datatwo";
		}
		else if (existenciasMax != null && existenciasMax!="")
		{
			queryExisTipo = "(SELECT* FROM PRODUCTO WHERE PRODUCTO.NUMINVENTARIO<="+existenciasMax+") datatwo";
		}
		else
		{
			queryExisTipo = "(SELECT* FROM PRODUCTO) datatwo";
		}
		if (estacion != null && estacion!="")
		{
			query = query + "((SELECT* FROM ESTACIONDEPRODUCCION WHERE ESTACIONDEPRODUCCION.IDPRODUCTO is not null AND ESTACIONDEPRODUCCION.CODIGO='"+estacion+"') dataone INNER JOIN " + queryExisTipo + " on dataone.IDPRODUCTO=datatwo.ID)";
		}
		else
		{
			query = query + queryExisTipo;
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
				String nombreT = b.getString("NOMBRE");
				int costoVenta = Integer.parseInt(b.getString("COSTOVENTA"));
				int numInventarioT = b.getInt("NUMINVENTARIO");
				Producto z = new Producto(nombreT, costoVenta, numInventarioT);
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
		if(tipo.equals("materia-prima"))
		{
			queer = "SELECT* FROM MATERIAPRIMA WHERE MATERIAPRIMA.ID='"+query+"'";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String nombreT = b.getString("NOMBRE");
					String toneladasT = b.getString("TONELADAS");
					resultado.add(nombreT);
					resultado.add(toneladasT);
				}
				String query2 = "SELECT dataTwo.nombre FROM (SELECT* FROM PRODUCTOMATERIAPRIMA WHERE PRODUCTOMATERIAPRIMA.IDMATERIAPRIMA = 'PAPACRIOLLA32') dataOne LEFT JOIN PRODUCTO dataTwo ON dataTwo.ID = dataOne.IDPRODUCTO";
				a = dao.conexion.prepareStatement(query2);
				b = a.executeQuery();
				String nombresProductos ="";
				while(b.next())
				{
					String nombreT = b.getString("NOMBRE");
					nombresProductos = nombresProductos+nombreT+", ";
					
				}
				resultado.add(nombresProductos);
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
		else if (tipo.equals("componente"))
		{
			queer = "SELECT* FROM COMPONENTE WHERE COMPONENTE.ID='"+query+"'";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String nombreT = b.getString("NOMBRE");
					String existencias = b.getString("NUMINVENTARIO");
					String toneladasT = b.getString("UNIDADMEDIDA");
					resultado.add(nombreT);
					resultado.add(existencias);
					resultado.add(toneladasT);
				}
				String query2 = "SELECT dataTwo.nombre FROM (SELECT* FROM PRODUCTOCOMPONENTE WHERE PRODUCTOCOMPONENTE.IDCOMPONENTE = '"+query+"') dataOne LEFT JOIN PRODUCTO dataTwo ON dataTwo.ID=dataOne.IDPRODUCTO";
				a = dao.conexion.prepareStatement(query2);
				b = a.executeQuery();
				String nombresProductos ="";
				while(b.next())
				{
					String nombreT = b.getString("NOMBRE");
					nombresProductos = nombresProductos+nombreT+", ";
					
				}
				nombresProductos = nombresProductos.substring(0, nombresProductos.length()-2) + ".";
				resultado.add(nombresProductos);
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
		else if(tipo.equals("producto"))
		{
			queer = "SELECT* FROM PRODUCTO WHERE ID = '" + query + "'";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String nombreT = b.getString("NOMBRE");
					String id = b.getString("ID");
					String costoVenta = b.getString("COSTOVENTA");
					String exist = b.getString("NUMINVENTARIO");

					resultado.add(nombreT);
					resultado.add(id);
					resultado.add(costoVenta);
					resultado.add(exist);
				}
				queer = "SELECT M.nombre FROM PRODUCTO P JOIN PRODUCTOMATERIAPRIMA W ON P.ID = '" + query + "' AND P.ID = W.IDPRODUCTO JOIN MATERIAPRIMA M ON W.IDMATERIAPRIMA = M.ID";
				a = dao.conexion.prepareStatement(queer);
				b = a.executeQuery();
				String mat = "";
				while(b.next())
				{
					String nomMat = b.getString("NOMBRE");
					mat += nomMat + ", ";
				}
				mat = mat.substring(0, mat.length()-2) + ".";
				resultado.add(mat);
				
				queer = "SELECT C.nombre FROM PRODUCTO P JOIN PRODUCTOCOMPONENTE W ON P.ID = '" + query + "' AND P.ID = W.IDPRODUCTO JOIN COMPONENTE C ON W.IDCOMPONENTE = C.ID";
				a = dao.conexion.prepareStatement(queer);
				b = a.executeQuery();
				String comp = "";
				while(b.next())
				{
					String nomComp = b.getString("NOMBRE");
					comp += nomComp + ", ";
				}
				comp = comp.substring(0, comp.length()-2) + ".";
				resultado.add(comp);
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
			queer = "SELECT* FROM etapaproduccion WHERE ETAPAPRODUCCION.ID = '" + query +"'";
			ArrayList<String> resultado = new ArrayList<>();
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(queer);
				ResultSet b = a.executeQuery();
				String idprod = "";
				while(b.next())
				{
					String nombreT = b.getString("id");
					String num = b.getString("num");
					idprod = b.getString("idproducto");
					System.out.println(idprod);
					resultado.add(nombreT);
					resultado.add(num);
				}
				String query2 = "SELECT P.nombre FROM ETAPAPRODUCCION E JOIN PRODUCTO P ON E.IDPRODUCTO = P.ID AND P.ID = '" + idprod + "'";
				a = dao.conexion.prepareStatement(query2);
				b = a.executeQuery();
				String nombresProductos ="";
				while(b.next())
				{
					String nombreT = b.getString("nombre");
					nombresProductos = nombresProductos+nombreT+", ";
				}
				nombresProductos = nombresProductos.substring(0, nombresProductos.length()-2) + ".";
				resultado.add(nombresProductos);
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

	public boolean registrarMateriaPrima(String id, String nombre,
			String cantidad) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO MATERIAPRIMA (ID, NOMBRE, TONELADAS) VALUES ('"+ id + "', '" + nombre + "'," + cantidad + ")";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
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
					exception.printStackTrace();
					return false;
				}
			}

		}		
		return true;
	}

	public boolean registrarComponente(String nombre, String cantidad,
			String unidadMedida) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO COMPONENTE (ID, NOMBRE, NUMINVENTARIO, UNIDADMEDIDA)VALUES ('"+nombre+cantidad+"', '"+nombre+"','"+cantidad+"', '"+unidadMedida+"')";
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
		String query = "UPDATE PEDIDO SET fechaRecibido=to_date('"+fechaFinal+"','MM-DD-YYYY'), estadoPago ='pago' WHERE ID='"+id+"'";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			String query2 = "SELECT* FROM PEDIDO WHERE ID='"+id+"' AND IDMATERIAPRIMA is not null";
			a = dao.conexion.prepareStatement(query2);
			b = a.executeQuery();
			while(b.next())
			{
				int numero = b.getInt("NUMMATERIAPRIMA");
				String idMP = b.getString("IDMATERIAPRIMA");
				String queryMP="UPDATE MATERIAPRIMA SET TONELADAS=TONELADAS-"+numero+" WHERE ID='"+idMP+"'";
				a = dao.conexion.prepareStatement(queryMP);
				a.executeQuery();
			}
			String query3 = "SELECT* FROM PEDIDO WHERE ID='"+id+"' AND IDCOMPONENTE is not null";
			a = dao.conexion.prepareStatement(query3);
			b = a.executeQuery();
			while(b.next())
			{
				int numero = b.getInt("NUMCOMPONENTE");
				String idMP = b.getString("IDCOMPONENTE");
				String queryCP="UPDATE COMPONENTE SET NUMINVENTARIO=NUMINVENTARIO-"+numero+" WHERE ID='"+idMP+"'";
				a = dao.conexion.prepareStatement(queryCP);
				a.executeQuery();
			}
			String query4 = "SELECT* FROM PEDIDO WHERE ID='"+id+"' AND IDPRODUCTO is not null";
			a = dao.conexion.prepareStatement(query4);
			b = a.executeQuery();
			while(b.next())
			{
				int numero = b.getInt("NUMPRODUCTO");
				String idMP = b.getString("IDPRODUCTO");
				String queryPR="UPDATE PRODUCTO SET NUMINVENTARIO=NUMINVENTARIO-"+numero+" WHERE ID='"+idMP+"'";
				a = dao.conexion.prepareStatement(queryPR);
				a.executeQuery();
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
		return true;
	}

	public ArrayList<String> darEstaciones() {
		String query = "SELECT E.CODIGO ESTACION FROM ESTACIONDEPRODUCCION E";
		PreparedStatement a = null;
		ArrayList<String> estaciones = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				estaciones.add(b.getString("ESTACION"));
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
		return estaciones;
	}

	public ArrayList<String> darProductos() {
		String query = "SELECT E.NOMBRE FROM PRODUCTO E";
		PreparedStatement a = null;
		ArrayList<String> productos = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				productos.add(b.getString("NOMBRE"));
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
		return productos;
	}
	
	public ArrayList<String> darPedidos() {
		String query = "SELECT E.ID, C.NOMBRE FROM PEDIDO E JOIN USUARIO C ON E.ID = C.LOGIN AND E.FECHARECIBIDO IS NULL";
		PreparedStatement a = null;
		ArrayList<String> pedidos = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				String x = "Pedido con id.&" + b.getString("ID") + "& del cliente " + b.getString("NOMBRE");
				pedidos.add(x);
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
		return pedidos;
	}
}
