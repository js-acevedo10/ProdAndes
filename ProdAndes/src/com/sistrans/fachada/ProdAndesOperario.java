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
		dao.inicializar();
	}
	public void encenderAutoCommit() {
		dao.inicializar();
		dao.encenderAutocommit();
	}
	
	public void apagarAutoCommit() {
		dao.inicializar();
		dao.apagarAutocommit();
	}
	
	public void hacerCommit() {
		dao.inicializar();
		dao.commit();
	}
	
	public void hacerRollback() {
		dao.inicializar();
		dao.rollback();
	}
	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existenciasMin, String existenciasMax, String estacion) {
		// TODO Auto-generated method stub
		String query="SELECT dataTwo.nombre, datatwo.toneladas FROM ";
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
				String id = b.getString(1);
				String nombreT = b.getString("NOMBRE");
				int toneladasT = b.getInt("TONELADAS");
				MateriaPrima z = new MateriaPrima(nombreT, toneladasT, id);
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
				String id = b.getString(1);
				String nombreT = b.getString("NOMBRE");
				int numInventarioT = b.getInt("NUMINVENTARIO");
				String toneladasT = b.getString("UNIDADMEDIDA");
				Componente z = new Componente(nombreT, numInventarioT, toneladasT, id);
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
				String id = b.getString(1);
				String nombreT = b.getString("NOMBRE");
				int costoVenta = b.getInt("COSTOVENTA");
				int numInventarioT = b.getInt("NUMINVENTARIO");
				Producto z = new Producto(nombreT, costoVenta, numInventarioT, id);
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

	public boolean registrarEjecucionEtapa(String id, String fi, String ft, String cod, String idPedido) {
		String idEtapa = id;
		String idOperairo = fi;
		String fechaFinal = ft;
		String fechaInicial = cod;
		PreparedStatement a = null;
		boolean flag = true;
		try 
		{
			apagarAutoCommit();
			String query = "SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION ='"+id+"' AND IDMATERIAPRIMA is not null";
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next()&&flag)
			{
				int numero = b.getInt("NUMMATERIAPRIMA");
				String idMP = b.getString("IDMATERIAPRIMA");
				String query2 = "SELECT* FROM MATERIAPRIMA WHERE ID='"+idMP+"'";
				a = dao.conexion.prepareStatement(query2);
				ResultSet c = a.executeQuery();
				while(c.next())
				{
					int numInventario = c.getInt("TONELADAS");
					if(numInventario<numero)
					{
						String msgEnviado = "P,18,MATERIAPRIMA,"+idMP+","+numInventario;
						dao.enviarMensaje(msgEnviado);
						String respuesta = "";
						try {
							respuesta = dao.recibirMensaje(msgEnviado);
						} catch(Exception e) {
							e.printStackTrace();
						}
						if(respuesta.equals("FALSE"))
						{
							flag=false;	
						}
						else
						{
							dao.enviarMensaje("P,U,MATERIAPRIMA,"+idMP+","+numInventario);
						}
					}
				}
			}
			query = "SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION ='"+id+"' AND IDCOMPONENTE is not null";
			a = dao.conexion.prepareStatement(query);
			b = a.executeQuery();
			while(b.next()&&flag)
			{
				int numero = b.getInt("NUMCOMPONENTE");
				String idMP = b.getString("IDCOMPONENTE");
				String query2 = "SELECT* FROM COMPONENTE WHERE ID='"+idMP+"'";
				a = dao.conexion.prepareStatement(query2);
				ResultSet c = a.executeQuery();
				while(c.next())
				{
					int numInventario = c.getInt("NUMINVENTARIO");
					if(numInventario<numero)
					{
						String msgEnviado = "P,18,COMPONENTE,"+idMP+","+numInventario;
						dao.enviarMensaje(msgEnviado);
						String respuesta = "";
						try {
							respuesta = dao.recibirMensaje(msgEnviado);
						} catch(Exception e) {
							e.printStackTrace();
						}
						if(respuesta.equals("FALSE"))
						{
							flag=false;	
						}
						else
						{
							dao.enviarMensaje("P,U,COMPONENTE,"+idMP+","+numInventario);
						}
					}
				}
			}
			query = "SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION ='"+id+"' AND IDPRODUCTO is not null";
			a = dao.conexion.prepareStatement(query);
			b = a.executeQuery();
			while(b.next()&&flag)
			{
				int numero = b.getInt("NUMPRODUCTO");
				String idMP = b.getString("IDPRODUCTO");
				String query2 = "SELECT* FROM PRODUCTO WHERE ID='"+idMP+"'";
				a = dao.conexion.prepareStatement(query2);
				ResultSet c = a.executeQuery();
				while(c.next())
				{
					int numInventario = c.getInt("NUMINVENTARIO");
					if(numInventario<numero)
					{
						String msgEnviado = "P,18,PRODUCTO,"+idMP+","+numInventario;
						dao.enviarMensaje(msgEnviado);
						String respuesta = "";
						try {
							respuesta = dao.recibirMensaje(msgEnviado);
						} catch(Exception e) {
							e.printStackTrace();
						}
						if(respuesta.equals("FALSE"))
						{
							flag=false;	
						}
						else
						{
							dao.enviarMensaje("P,U,PRODUCTO,"+idMP+","+numInventario);
						}
					}
				}
			}
			
			if (flag)
			{
				query = "SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION ='"+id+"' AND IDMATERIAPRIMA is not null";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				while(b.next())
				{
					int numero = b.getInt("NUMMATERIAPRIMA");
					String idMP = b.getString("IDMATERIAPRIMA");
					String queryMP="UPDATE MATERIAPRIMA SET TONELADAS=TONELADAS-"+numero+" WHERE ID='"+idMP+"'";
					a = dao.conexion.prepareStatement(queryMP);
					a.executeQuery();
				}
				query = "SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION ='"+id+"' AND IDCOMPONENTE is not null";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				while(b.next())
				{
					int numero = b.getInt("NUMCOMPONENTE");
					String idMP = b.getString("IDCOMPONENTE");
					String queryCP="UPDATE COMPONENTE SET NUMINVENTARIO=NUMINVENTARIO-"+numero+" WHERE ID='"+idMP+"'";
					a = dao.conexion.prepareStatement(queryCP);
					a.executeQuery();
				}
				query = "SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION ='"+id+"' AND IDPRODUCTO is not null";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				while(b.next())
				{
					int numero = b.getInt("NUMPRODUCTO");
					String idMP = b.getString("IDPRODUCTO");
					String queryPR="UPDATE PRODUCTO SET NUMINVENTARIO=NUMINVENTARIO-"+numero+" WHERE ID='"+idMP+"'";
					a = dao.conexion.prepareStatement(queryPR);
					a.executeQuery();
				}
				
				query = "UPDATE USUARIO SET OPERACIONESHECHAS=OPERACIONESHECHAS+1 WHERE LOGIN='"+fi+"'";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				
				query = "SELECT* FROM ETAPAOPERARIO WHERE IDETAPA='"+ id + "' AND IDOPERARIO='" + fi + "'";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				ArrayList<String> actuales = new ArrayList<>();
				while(b.next())
				{
					String actualesStr = b.getString(1);
					actuales.add(actualesStr);
				}
				if(actuales.isEmpty())
				{
					query = "INSERT INTO ETAPAOPERARIO (IDETAPA, IDOPERARIO, FECHAINICIAL, FECHAFINAL, IDPEDIDO) VALUES ('"+ id + "', '" + fi + "', to_date('"+ft+"','YYYY-MM-DD'), to_date('"+cod+"','YYYY-MM-DD'), '"+idPedido+"')";
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
				}
				else
				{
					query = "UPDATE ETAPAOPERARIO SET FECHAINICIAL=to_date('"+ft+"','YYYY-MM-DD'), FECHAFINAL=to_date('"+cod+"','YYYY-MM-DD') WHERE IDETAPA='"+ id + "' AND IDOPERARIO='" + fi + "'";
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
				}
				
				query = "SELECT* FROM(SELECT* FROM (SELECT* FROM ETAPAOPERARIO WHERE IDPEDIDO ='"+ idPedido + "') data1 LEFT JOIN ETAPAPRODUCCION data2 on data1.IDETAPA=data2.ID ORDER BY NUM DESC) WHERE ROWNUM=1";
				String idProducto="";
				int numeroSiguiente=0;
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				while(b.next())
				{
					idProducto=b.getString("IDPRODUCTO");
					numeroSiguiente=b.getInt("NUM")+1;
				}
				query="SELECT* FROM ETAPAPRODUCCION WHERE IDPRODUCTO='"+ idProducto + "' AND NUM='" + numeroSiguiente + "'";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				EtapadeProduccion nuevaEtapa=null;
				while(b.next())
				{
					nuevaEtapa = new EtapadeProduccion(b.getInt(2), b.getString(1));
				}
				if(nuevaEtapa!=null)
				{
					query="INSERT INTO ETAPAOPERARIO (IDETAPA, IDOPERARIO, IDPEDIDO) VALUES ('"+ nuevaEtapa.getNombre() + "', '" + fi + "', '"+idPedido+"')";
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
				}
				
			}
			else
			{
				dao.enviarMensaje("P,R");
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
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
		return flag;
	}	
}
