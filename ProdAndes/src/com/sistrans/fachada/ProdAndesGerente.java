package com.sistrans.fachada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sistrans.dao.ConsultaDAOUsuario;
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.EstaciondeProducto;
import com.sistrans.mundo.EtapadeProduccion;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Producto;
import com.sistrans.mundo.Usuario;

public class ProdAndesGerente {

	private ConsultaDAOUsuario dao;
		
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
						e.printStackTrace();
					}
				}
			}

		}		
		return resultado;
	}

	public ArrayList<Componente> consultarExistenciasComp(String tipo,
			String existenciasMin, String existenciasMax, String estacion) {
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
				String query2 = "SELECT dataTwo.nombre FROM (SELECT* FROM PRODUCTOMATERIAPRIMA WHERE PRODUCTOMATERIAPRIMA.IDMATERIAPRIMA = '"+query+"') dataOne LEFT JOIN PRODUCTO dataTwo ON dataTwo.ID = dataOne.IDPRODUCTO";
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
				if(nombresProductos.length() > 2)
				{
					nombresProductos = nombresProductos.substring(0, nombresProductos.length()-2) + ".";
				}
				resultado.add(nombresProductos);
			} 
			catch (SQLException e) 
			{
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
				if(mat.length() > 2)
				{
					mat = mat.substring(0, mat.length()-2) + ".";
				}
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
				if(comp.length() > 2)
				{
					comp = comp.substring(0, comp.length()-2) + ".";
				}
				resultado.add(comp);
			} 
			catch (SQLException e) 
			{
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
				if(nombresProductos.length() > 2)
				{
					nombresProductos = nombresProductos.substring(0, nombresProductos.length()-2) + ".";
				}
				resultado.add(nombresProductos);
			} 
			catch (SQLException e) 
			{
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
	public boolean registrarMateriaProducto(String idMateria, String idProducto)
	{
		String query = "INSERT INTO PRODUCTOMATERIAPRIMA (IDPRODUCTO, IDMATERIAPRIMA) VALUES ('"+ idProducto + "', '" + idMateria + "')";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
		} 
		catch (SQLException e) 
		{
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
						e.printStackTrace();
					}
				}
			}

		}		
		return true;
	}
	public boolean registrarComponenteProducto(String idComponente, String idProducto)
	{
		String query = "INSERT INTO PRODUCTOCOMPONENTE (IDPRODUCTO, IDCOMPONENTE) VALUES ('"+ idProducto + "', '" + idComponente + "')";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
		} 
		catch (SQLException e) 
		{
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

	public boolean registrarEntregaPedido(String id, String fechaFinal) {
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
	
	public ArrayList<String> darOperarios() {
		String query = "SELECT USUARIO.LOGIN FROM USUARIO WHERE USUARIO.ROL = '" + "OPERARIO" + "'";
		PreparedStatement a = null;
		ArrayList<String> operarios = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {				
				operarios.add(b.getString("LOGIN"));
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
		return operarios;
	}
	
	public ArrayList<String> darEtapas() {
		String query = "SELECT E.ID FROM ETAPAPRODUCCION E";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("ID"));
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
		return etapas;
	}

	public ArrayList<String> darClientes() {
		String query = "SELECT E.LOGIN FROM USUARIO E WHERE E.ROL = 'CLIENTE'";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("LOGIN"));
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
		return etapas;
	}

	public ArrayList<String> darMateriasPrimas() {
		String query = "SELECT E.ID FROM MATERIAPRIMA E";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("ID"));
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
		return etapas;
	}

	public ArrayList<String> darComponentes() {
		String query = "SELECT E.ID FROM COMPONENTE E";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("ID"));
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
		return etapas;
	}

	public ArrayList<String> darIDProductos() {
		String query = "SELECT E.ID FROM PRODUCTO E";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("ID"));
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
						e.printStackTrace();
					}
				}
			}			
		}
		return etapas;
	}

	public ArrayList<String> darTiposDoc(String rol) {
		String query = "SELECT DISTINCT E.TIPODOC FROM USUARIO E WHERE E.ROL = '" + rol + "'";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("TIPODOC"));
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
						e.printStackTrace();
					}
				}
			}			
		}
		return etapas;
	}

	public ArrayList<String> darNacionalidades() {
		String query = "SELECT DISTINCT E.NACIONALIDAD FROM USUARIO E WHERE E.ROL = 'CLIENTE'";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("NACIONALIDAD"));
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
						e.printStackTrace();
					}
				}
			}			
		}
		return etapas;
	}

	public ArrayList<String> darCiudades() {
		String query = "SELECT DISTINCT E.CIUDAD FROM USUARIO E WHERE E.ROL = 'CLIENTE'";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("CIUDAD"));
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
						e.printStackTrace();
					}
				}
			}			
		}
		return etapas;
	}

	public ArrayList<String> darDepartamentos() {
		String query = "SELECT DISTINCT E.DEPARTAMENTO FROM USUARIO E WHERE E.ROL = 'CLIENTE'";
		PreparedStatement a = null;
		ArrayList<String> etapas = new ArrayList<String>();
		try
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()) {
				etapas.add(b.getString("DEPARTAMENTO"));
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
						e.printStackTrace();
					}
				}
			}			
		}
		return etapas;		
	}
	
	public boolean desActivarEtapaProduccion(String idEstacion)
	{
		String query = "SELECT * FROM ETAPAPRODUCCION WHERE ID='"+idEstacion+"'";
		PreparedStatement a = null;
		try
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			String idProducto ="";
			while(b.next()) 
			{
				idProducto=b.getString(4);
			}
			if(!idProducto.equals(""))
			{
				query="SELECT * FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION='"+idEstacion+"'"; 
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				ArrayList<EstaciondeProducto> estacionesEtapa= new ArrayList<>();
				while(b.next())
				{
					String codigo = b.getString(1);
					String etapaProduccion = b.getString(2);
					int tiempoRealizacion = Integer.parseInt(b.getString(3));
					EstaciondeProducto z = new EstaciondeProducto(codigo, etapaProduccion, tiempoRealizacion);
					String idComponente = b.getString(4);
					if(!b.wasNull())
					{
						int numComponente = b.getInt(5);
						z.setIdComponente(idComponente);
						z.setNumComponente(numComponente);
					}
					String idMateriaPrima = b.getString(6);
					if(!b.wasNull())
					{
						int numMateriaPrima = b.getInt(7);
						z.setIdMateriaPrima(idMateriaPrima);
						z.setNumMateriaPrima(numMateriaPrima);
					}
					String idProductoA = b.getString(8);
					if(!b.wasNull())
					{
						int numProducto = b.getInt(9);
						z.setIdProducto(idProductoA);
						z.setNumProducto(numProducto);
					}
					
					estacionesEtapa.add(z);
				}
								
				query="SELECT * FROM ETAPAPRODUCCION WHERE IDPRODUCTO='"+idProducto+"' AND ID!='"+idEstacion+"'"; 
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				ArrayList<EtapadeProduccion> etapasDeProduccion = new ArrayList<>();
				while(b.next())
				{
					String id = b.getString(1);
					int num = b.getInt(2);
					EtapadeProduccion z = new EtapadeProduccion(num, id);
					etapasDeProduccion.add(z);
				}
				ArrayList<Integer> cuenta = new ArrayList<>();
				for(int i=0;i<etapasDeProduccion.size();i++)
				{
					query="SELECT * FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION='"+etapasDeProduccion.get(i).getNombre()+"'"; 
					int contador =0;
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
					while(b.next())
					{
						contador++;
					}
					cuenta.add(contador);
				}
				int promedio=0;
				int suma=0;
				for(int i=0; i<cuenta.size();i++)
				{
					suma+=cuenta.get(i);
				}
				promedio = suma/cuenta.size();
				while(!estacionesEtapa.isEmpty())
				{
					int menor=999999999;
					int indice=0;
					for (int i=0;i<cuenta.size();i++)
					{
						if(menor==999999999||menor>cuenta.get(i))
						{
							menor=cuenta.get(i);
							indice=i;
						}							
					}
					query = "UPDATE ESTACIONDEPRODUCCION SET IDETAPAPRODUCCION='" +etapasDeProduccion.get(indice)+ "' WHERE CODIGO='"+estacionesEtapa.get(0)+"'";
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
					estacionesEtapa.remove(0);
					int nuevoNumero= cuenta.get(indice)+1;
					cuenta.set(indice, nuevoNumero);
				}
			}
			else
			{
				return false;
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
						e.printStackTrace();
					}
				}
			}			
		}
		return true;
	}
	
	
	public boolean activarEtapaProduccion(String idEstacion)
	{
		String query = "SELECT * FROM ETAPAPRODUCCION WHERE ID='"+idEstacion+"'";
		PreparedStatement a = null;
		try
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			String idProducto ="";
			while(b.next()) 
			{
				idProducto=b.getString(4);
			}
			if(!idProducto.equals(""))
			{							
				query="SELECT * FROM ETAPAPRODUCCION WHERE IDPRODUCTO='"+idProducto+"' AND ID!='"+idEstacion+"'"; 
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				ArrayList<EtapadeProduccion> etapasDeProduccion = new ArrayList<>();
				while(b.next())
				{
					String id = b.getString(1);
					int num = b.getInt(2);
					EtapadeProduccion z = new EtapadeProduccion(num, id);
					etapasDeProduccion.add(z);
				}
				ArrayList<Integer> cuenta = new ArrayList<>();
				for(int i=0;i<etapasDeProduccion.size();i++)
				{
					query="SELECT * FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION='"+etapasDeProduccion.get(i).getNombre()+"'"; 
					int contador =0;
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
					while(b.next())
					{
						contador++;
					}
					cuenta.add(contador);
				}
				int promedio=0;
				int suma=0;
				for(int i=0; i<cuenta.size();i++)
				{
					suma+=cuenta.get(i);
				}
				promedio = suma/(cuenta.size()+1);
				ArrayList<EstaciondeProducto> estacionesEtapa = new ArrayList<>();
				while(estacionesEtapa.size()<promedio)
				{
					int mayor=0;
					int indice=0;
					for (int i=0;i<cuenta.size();i++)
					{
						if(mayor<cuenta.get(i))
						{
							mayor=cuenta.get(i);
							indice=i;
						}							
					}
					
					query="SELECT * FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION='"+etapasDeProduccion.get(indice)+"'"; 
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
					EstaciondeProducto estacionEtapaActual= null;
					while(b.next()&&estacionEtapaActual==null)
					{
						String codigo = b.getString(1);
						String etapaProduccion = b.getString(2);
						int tiempoRealizacion = Integer.parseInt(b.getString(3));
						EstaciondeProducto z = new EstaciondeProducto(codigo, etapaProduccion, tiempoRealizacion);
						String idComponente = b.getString(4);
						if(!b.wasNull())
						{
							int numComponente = b.getInt(5);
							z.setIdComponente(idComponente);
							z.setNumComponente(numComponente);
						}
						String idMateriaPrima = b.getString(6);
						if(!b.wasNull())
						{
							int numMateriaPrima = b.getInt(7);
							z.setIdMateriaPrima(idMateriaPrima);
							z.setNumMateriaPrima(numMateriaPrima);
						}
						String idProductoA = b.getString(8);
						if(!b.wasNull())
						{
							int numProducto = b.getInt(9);
							z.setIdProducto(idProductoA);
							z.setNumProducto(numProducto);
						}
						
						estacionEtapaActual=z;
					}
					
					query = "UPDATE ESTACIONDEPRODUCCION SET IDETAPAPRODUCCION='" +idEstacion+ "' WHERE CODIGO='"+estacionEtapaActual.getCodigo()+"'";
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
					estacionesEtapa.add(estacionEtapaActual);
					int nuevoNumero= cuenta.get(indice)-1;
					cuenta.set(indice, nuevoNumero);
				}
			}
			else
			{
				return false;
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
						e.printStackTrace();
					}
				}
			}			
		}
		return true;
	}

	public Usuario consultarProveedor(String loginProv) {
		Usuario resp = null;
		String query = "SELECT * FROM USUARIO WHERE ID = '" + loginProv + "'";
		PreparedStatement a = null;
		try {
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet infoUser = a.executeQuery();
			while(infoUser.next()) {
				String login = infoUser.getString("LOGIN");
				String clave = infoUser.getString("CLAVE");
				String tipoDoc = infoUser.getString("TIPODOC");
				int numDoc = infoUser.getInt("NUMDOC");
				String nombre = infoUser.getString("NOMBRE");
				String direccion = infoUser.getString("DIRECCION");
				String nacionalidad = infoUser.getString("NACIONALIDAD");
				String email = infoUser.getString("EMAIL");
				int telefono = infoUser.getInt("TELEFONO");
				String ciudad = infoUser.getString("CIUDAD");
				String departamento = infoUser.getString("DEPARTAMENTO");
				int codPostal = infoUser.getInt("CODPOSTAL");
				resp = new Usuario(login, clave, tipoDoc, numDoc, nombre, direccion, nacionalidad, email, telefono, ciudad, departamento, codPostal);
			}
			String queryMat = "SELECT ";
		} catch (SQLException e) {
			
		}
		return resp;
	}
	
	
}
