package com.sistrans.fachada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.sistrans.dao.ConsultaDAOUsuario;
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.EtapadeProduccion;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Pedido;
import com.sistrans.mundo.Producto;
import com.sistrans.mundo.Usuario;

public class ProdAndesAdmin {

	private ConsultaDAOUsuario dao;
		
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

	public void encenderAutoCommit() {
		String query = "SET AUTOCOMMIT ON";
		PreparedStatement a = null;
		try {
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
		} catch(Exception e) {
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
	}
	
	public void apagarAutoCommit() {
		String query = "SET AUTOCOMMIT OFF";
		PreparedStatement a = null;
		try {
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
		} catch(Exception e) {
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
	}
	
	public void hacerCommit() {
		String query = "COMMIT";
		PreparedStatement a = null;
		try {
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(a != null) {
				try {
					a.close();
				} catch (Exception e) {
					try {
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					} catch (Exception f) {
						// TODO Auto-generated catch block
						f.printStackTrace();
					}
				}
			}
		}
	}
	
	public void hacerRollback() {
		String query = "ROLLBACK";
		PreparedStatement a = null;
		try {
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(a != null) {
				try {
					a.close();
				} catch (Exception e) {
					try {
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
					} catch (Exception f) {
						// TODO Auto-generated catch block
						f.printStackTrace();
					}
				}
			}
		}
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
				String toneladasT = b.getString("UNIDADMEDIDA");
				int numInventarioT = b.getInt("NUMINVENTARIO");

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
				String nombreT = b.getString("NOMBRE");
				int costoVenta = b.getInt("COSTOVENTA");
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
				String query2 = "SELECT dataTwo.nombre FROM (SELECT* FROM PRODUCTOMATERIAPRIMA WHERE PRODUCTOMATERIAPRIMA.IDMATERIAPRIMA ='"+query+"') dataOne LEFT JOIN PRODUCTO dataTwo ON dataTwo.ID = dataOne.IDPRODUCTO";
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
	
	public ArrayList<Usuario> operarioMasActivo(String idEtapa, String numOperaciones)
	{
		ArrayList<Usuario> resultado = new ArrayList<>();
		if(idEtapa!=null&&idEtapa!="")
		{
			String query="SELECT IDOPERARIO, count(*) FROM ETAPAOPERARIO  WHERE IDETAPA='"+idEtapa+"'group by IDOPERARIO order by count(*) DESC";
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(query);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String idoper = b.getString("IDOPERARIO");
					String query2="SELECT* FROM USUARIO WHERE USUARIO.LOGIN='"+idoper+"'";
					a = dao.conexion.prepareStatement(query2);
					ResultSet c = a.executeQuery();
			
					String login = c.getString("LOGIN");
					String clave = c.getString("CLAVE");
					String tipoDoc = c.getString("TIPODOC");
					int numDoc = c.getInt("NUMDOC");
					String nombre = c.getString("NOMBRE");
					String direccion = c.getString("DIRECCION");
					String nacionalidad = c.getString("NACIONALIDAD");
					String email = c.getString("EMAIL");
					int telefono = c.getInt("TELEFONO");
					String ciudad = c.getString("CIUDAD");
					String departamento = c.getString("DEPARTAMENTO");
					int codPostal = c.getInt("CODPOSTAL");
					
					Usuario z = new Usuario(login, clave, tipoDoc, numDoc, nombre, direccion, nacionalidad, email, telefono, ciudad, departamento, codPostal);
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
		}
		else
		{
			String query="SELECT* FROM USUARIO WHERE OPERACIONESHECHAS is not null AND OPERACIONESHECHAS>="+numOperaciones;
			PreparedStatement a = null;
			try 
			{
				dao.inicializar();
				a = dao.conexion.prepareStatement(query);
				ResultSet b = a.executeQuery();
				while(b.next())
				{
					String login = b.getString("LOGIN");
					String clave = b.getString("CLAVE");
					String tipoDoc = b.getString("TIPODOC");
					int numDoc = b.getInt("NUMDOC");
					String nombre = b.getString("NOMBRE");
					String direccion = b.getString("DIRECCION");
					String nacionalidad = b.getString("NACIONALIDAD");
					String email = b.getString("EMAIL");
					int telefono = b.getInt("TELEFONO");
					String ciudad = b.getString("CIUDAD");
					String departamento = b.getString("DEPARTAMENTO");
					int codPostal = b.getInt("CODPOSTAL");
					
					Usuario z = new Usuario(login, clave, tipoDoc, numDoc, nombre, direccion, nacionalidad, email, telefono, ciudad, departamento, codPostal);
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
		}
		return resultado;
	}
	
	public ArrayList<String> etapaMasActiva(String fechaInc, String fechaFin)
	{
		ArrayList<String> resultado=new ArrayList<>();
		String query = "SELECT t.IDETAPA, count(*) as Cuenta FROM (SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInc+"','MM-DD-YYYY') and FECHAFINAL<=to_date('"+fechaFin+"','MM-DD-YYYY')) t group by t.IDETAPA order by count(*) DESC";
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			int cuenta = 0;
			while(b.next())
			{
				String id = b.getString("IDETAPA");
				String numero = b.getString("CUENTA");
				String query2="SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION='"+id+"' AND NUMCOMPONENTE is not null";
				a = dao.conexion.prepareStatement(query2);
				ResultSet c = a.executeQuery();
				while(c.next())
				{
					int numeroActual = b.getInt("NUMCOMPONENTE");
					cuenta+=numeroActual;
				}
				query2="SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION='"+id+"' AND NUMMATERIAPRIMA is not null";
				a = dao.conexion.prepareStatement(query2);
				c = a.executeQuery();
				while(c.next())
				{
					int numeroActual = b.getInt("NUMMATERIAPRIMA");
					cuenta+=numeroActual;
				}
				query2="SELECT* FROM ESTACIONDEPRODUCCION WHERE IDETAPAPRODUCCION='"+id+"' AND NUMPRODUCTO is not null";
				a = dao.conexion.prepareStatement(query2);
				c = a.executeQuery();
				while(c.next())
				{
					int numeroActual = b.getInt("NUMPRODUCTO");
					cuenta+=numeroActual;
				}
				
				String adicion = "La etapa de id: "+id+" se a realizado "+numero+ " veces, a procesado "+cuenta+" componentes,materias primas y productos para realizar un total de "+numero+ " elementos";
				resultado.add(adicion);
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
	 
	public ArrayList<Pedido> consultarPedidos (String ID, String idCLiente, String estadoPago, String fechaCreacion, String fechaRecibido, String deadline, String idMateriaprima, Integer numMateriaPrima, String idComponente, Integer numComponente, String idProducto, Integer numProducto)
	{
		ArrayList<Pedido> resultado = new ArrayList<>();
		String query = "SELECT * FROM PEDIDO";
		if(ID!=null&&!ID.equals(""))
		{
			query = "SELECT * FROM PEDIDO WHERE ID='"+ID+"'";
		}
		if(idCLiente!=null&&!idCLiente.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND IDCLIENTE='"+idCLiente+"'";
			}
			else
			{
				query=query + " WHERE IDCLIENTE='"+idCLiente+"'";
			}
		}
		if(estadoPago!=null&&!estadoPago.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND ESTADOPAGO='"+estadoPago+"'";
			}
			else
			{
				query=query + " WHERE ESTADOPAGO='"+estadoPago+"'";
			}
		}
		if(fechaCreacion!=null&&!fechaCreacion.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND fechaCreacion='"+fechaCreacion+"'";
			}
			else
			{
				query=query + " WHERE fechaCreacion='"+fechaCreacion+"'";
			}
		}
		if(fechaRecibido!=null&&!fechaRecibido.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND fechaRecibido='"+fechaRecibido+"'";
			}
			else
			{
				query=query + " WHERE fechaRecibido='"+fechaRecibido+"'";
			}
		}
		if(deadline!=null&&!deadline.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND deadline='"+deadline+"'";
			}
			else
			{
				query=query + " WHERE deadline='"+deadline+"'";
			}
		}
		if(idMateriaprima!=null&&!idMateriaprima.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND idMateriaprima='"+idMateriaprima+"'";
			}
			else
			{
				query=query + " WHERE idMateriaprima='"+idMateriaprima+"'";
			}
		}
		if(numMateriaPrima!=null&&!numMateriaPrima.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND numMateriaPrima='"+numMateriaPrima+"'";
			}
			else
			{
				query=query + " WHERE numMateriaPrima='"+numMateriaPrima+"'";
			}
		}
		if(idComponente!=null&&!idComponente.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND idComponente='"+idComponente+"'";
			}
			else
			{
				query=query + " WHERE idComponente='"+idComponente+"'";
			}
		}
		if(numComponente!=null&&!numComponente.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND numComponente='"+numComponente+"'";
			}
			else
			{
				query=query + " WHERE numComponente='"+numComponente+"'";
			}
		}
		if(idProducto!=null&&!idProducto.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND idProducto='"+idProducto+"'";
			}
			else
			{
				query=query + " WHERE idProducto='"+idProducto+"'";
			}
		}
		if(numProducto!=null&&!numProducto.equals(""))
		{
			if(query.length()>20)
			{
				query = query +" AND numProducto='"+numProducto+"'";
			}
			else
			{
				query=query + " WHERE numProducto='"+numProducto+"'";
			}
		}
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			int x = 0;
			while(b.next())
			{
				String idT = b.getString("ID");
				String idClienteT = b.getString("IDCLIENTE");
				String estadoPagoT = b.getString("ESTADOPAGO");
				Date fechaCreacionT = b.getDate("FECHACREACION");
				Date fechaRecibidoT = null;
				if(b.getDate("FECHARECIBIDO")!=null)
				{
					fechaRecibidoT = b.getDate("FECHARECIBIDO");
				}
				Date deadlineT = b.getDate("DEADLINE");
				String anotacionesT ="";
				if(b.getString("ANOTACIONES")!=null&&b.getString("ANOTACIONES")!="")
				{
					anotacionesT = b.getString("ANOTACIONES");
				}
				
				String idMateriaPrimaT = "";
				
				if(b.getString("IDMateriaPrima")!=null)
				{
					idMateriaPrimaT = b.getString("IDMateriaPrima");
				}
				int numMateriaPrimaT = b.getInt("NUMMateriaPrima");
				
				String idComponenteT = "";
				
				if(b.getString("IDComponente")!=null)
				{
					idComponenteT = b.getString("IDComponente");
				}
				int numComponenteT = b.getInt("NUMComponente");
				
				String idPrductoT = "";
				
				if(b.getString("IDPRODUCTO")!=null)
				{
					idPrductoT = b.getString("IDPRODUCTO");
				}
				int numProductoT = b.getInt("NUMPRODUCTO");
				Pedido z = new Pedido(idT, idClienteT, estadoPagoT, fechaCreacionT, fechaRecibidoT, deadlineT, anotacionesT, idMateriaPrimaT, numMateriaPrimaT, idComponenteT, numComponenteT, idPrductoT, numProductoT);
				resultado.add(z);
			}
		} 
		catch (SQLException e) 
		{
			
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
	
	public ArrayList<Usuario> consultarClientes(String login, String tipoDoc, String numDoc, String nombre, String direccion, String nacionalidad, String email, String telefono, String ciudad, String departamento, String codPostal, String numReg, String personaNatural)
		{
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		String query="SELECT * FROM USUARIO WHERE ROL='CLIENTE'";
		if(login!=null&&!login.equals(""))
		{
			query = query +" AND login='"+login+"'";			
		}
		if(tipoDoc!=null&&!tipoDoc.equals(""))
		{
			query = query +" AND tipoDoc='"+tipoDoc+"'";			
		}
		if(numDoc!=null&&!numDoc.equals(""))
		{
			query = query +" AND numDoc='"+numDoc+"'";			
		}
		if(nombre!=null&&!nombre.equals(""))
		{
			query = query +" AND nombre='"+nombre+"'";			
		}
		if(direccion!=null&&!direccion.equals(""))
		{
			query = query +" AND direccion='"+direccion+"'";			
		}
		if(nacionalidad!=null&&!nacionalidad.equals(""))
		{
			query = query +" AND nacionalidad='"+nacionalidad+"'";			
		}
		if(email!=null&&!email.equals(""))
		{
			query = query +" AND email='"+email+"'";			
		}
		if(telefono!=null&&!telefono.equals(""))
		{
			query = query +" AND telefono='"+telefono+"'";			
		}
		if(ciudad!=null&&!ciudad.equals(""))
		{
			query = query +" AND ciudad='"+ciudad+"'";			
		}
		if(departamento!=null&&!departamento.equals(""))
		{
			query = query +" AND departamento='"+departamento+"'";			
		}
		if(codPostal!=null&&!codPostal.equals(""))
		{
			query = query +" AND codPostal='"+codPostal+"'";			
		}
		if(numReg!=null&&!numReg.equals(""))
		{
			query = query +" AND numReg='"+numReg+"'";			
		}
		if(personaNatural!=null&&!personaNatural.equals(""))
		{
			query = query +" AND personaNatural='"+personaNatural+"'";			
		}
		PreparedStatement a = null;
		PreparedStatement x = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			while(b.next())
			{
				String loginT = b.getString(1);
				String tipoDocT = b.getString(3);
				int numDocT = b.getInt(4);
				String nombreT= b.getString(5);
				String direccionT= b.getString(6);
				String nacionalidadT = b.getString(7);
				String emailT = b.getString(8);
				int telefonoT = b.getInt(9);
				String ciudadT = b.getString(10);
				String departamentoT= b.getString(11);
				int codPostalT = b.getInt(12);
				Usuario z = new Usuario(loginT, "0", tipoDocT, numDocT, nombreT, direccionT, nacionalidadT, emailT, telefonoT, ciudadT, departamentoT, codPostalT);
				String query2 = "SELECT * FROM PEDIDO WHERE IDCLIENTE='"+loginT+"'";
				x = dao.conexion.prepareStatement(query2);
				ResultSet c = x.executeQuery();
				while(c.next())
				{
					String idT = c.getString("ID");
					String idClienteT = c.getString("IDCLIENTE");
					String estadoPagoT = c.getString("ESTADOPAGO");
					Date fechaCreacionT = c.getDate("FECHACREACION");
					Date fechaRecibidoT = null;
					if(c.getDate("FECHARECIBIDO")!=null)
					{
						fechaRecibidoT = c.getDate("FECHARECIBIDO");
					}
					Date deadlineT = c.getDate("DEADLINE");
					String anotacionesT ="";
					if(c.getString("ANOTACIONES")!=null&&c.getString("ANOTACIONES")!="")
					{
						anotacionesT = c.getString("ANOTACIONES");
					}
					
					String idMateriaPrimaT = "";
					
					if(c.getString("IDMateriaPrima")!=null)
					{
						idMateriaPrimaT = c.getString("IDMateriaPrima");
					}
					int numMateriaPrimaT = c.getInt("NUMMateriaPrima");
					
					String idComponenteT = "";
					
					if(c.getString("IDComponente")!=null)
					{
						idComponenteT = c.getString("IDComponente");
					}
					int numComponenteT = c.getInt("NUMComponente");
					
					String idPrductoT = "";
					
					if(c.getString("IDPRODUCTO")!=null)
					{
						idPrductoT = c.getString("IDPRODUCTO");
					}
					int numProductoT = c.getInt("NUMPRODUCTO");
					Pedido zz = new Pedido(idT, idClienteT, estadoPagoT, fechaCreacionT, fechaRecibidoT, deadlineT, anotacionesT, idMateriaPrimaT, numMateriaPrimaT, idComponenteT, numComponenteT, idPrductoT, numProductoT);
					z.agregarPedido(zz);
				}
				
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}		
		return resultado;
		
	}

}
