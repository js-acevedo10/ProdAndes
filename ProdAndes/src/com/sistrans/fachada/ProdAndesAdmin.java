package com.sistrans.fachada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.management.timer.Timer;

import com.sistrans.dao.ConsultaDAOUsuario;
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.EstaciondeProducto;
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
	
	public void lockTable(String tableName) {
		String q = "LOCK TABLE " + tableName + " IN SHARE UPDATE MODE";
		PreparedStatement a = null;
		try {
			dao.inicializar();
			a = dao.conexion.prepareStatement(q);
			a.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(a != null) {
				try {
					a.close();
				} catch (SQLException e) {
					e.printStackTrace();
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
		String query = "SELECT t.IDETAPA, count(*) as Cuenta FROM (SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInc+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFin+"','YYYY-MM-DD')) t group by t.IDETAPA order by count(*) DESC";
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
	
	public ArrayList<Usuario> consultarProveedor(String login, String tipoDoc, String numDoc, String nombre, String direccion, String nacionalidad, String email, String telefono, String ciudad, String departamento, String codPostal, String numReg, String personaNatural)
	{
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		String query="SELECT * FROM USUARIO WHERE ROL='PROVEEDOR'";
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
				String query2 = "SELECT * FROM PEDIDO WHERE IDCLIENTE='"+loginT+"' AND FECHARECIBIDO IS NULL";
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
				
				query2 = "SELECT dataTwo.ID, dataTwo.NOMBRE, dataTwo.TONELADAS FROM (SELECT * FROM MATERIAPRIMAPROVEEDOR WHERE IDPPROVEEDOR='"+loginT+"') dataOne LEFT JOIN MATERIAPRIMA dataTwo on dataOne.IDMATERIAPRIMA=dataTwo.ID";
				x = dao.conexion.prepareStatement(query2);
				c = x.executeQuery();
				while(c.next())
				{
					String nombreAA = c.getString(2);
					int toneladas = c.getInt(3);
					MateriaPrima zz = new MateriaPrima(nombreAA, toneladas);
					z.agregarMateriaPrima(zz);
				}
				
				query2 = "SELECT dataTwo.ID, dataTwo.NOMBRE, dataTwo.NUMINVENTARIO, dataTwo.UNIDADMEDIDA FROM (SELECT * FROM COMPONENTEPROVEEDOR WHERE IDPROVEEDOR='"+loginT+"') dataOne LEFT JOIN COMPONENTE dataTwo on dataOne.IDCOMPONENTE=dataTwo.ID";
				x = dao.conexion.prepareStatement(query2);
				c = x.executeQuery();
				while(c.next())
				{
					String nombreAA = c.getString(2);
					int numInventario = c.getInt(3);
					String unidadMedida = c.getString(4);
					Componente zz = new Componente(nombreAA, numInventario, unidadMedida);
					z.agregarComponente(zz);
				}
				
				query2 = "SELECT dataSix.ID, dataSix.NOMBRE, dataSix.COSTOVENTA, dataSix.NUMINVENTARIO FROM (SELECT dataFourth.IDPRODUCTO FROM (SELECT dataTwo.ID, dataTwo.NOMBRE, dataTwo.TONELADAS FROM (SELECT * FROM MATERIAPRIMAPROVEEDOR WHERE IDPPROVEEDOR='"+loginT+"') dataOne LEFT JOIN MATERIAPRIMA dataTwo on dataOne.IDMATERIAPRIMA=dataTwo.ID) dataThree LEFT JOIN PRODUCTOMATERIAPRIMA dataFourth on dataThree.ID=dataFourth.IDMATERIAPRIMA) dataFive LEFT JOIN PRODUCTO dataSix on dataFive.IDPRODUCTO=dataSix.ID";
				x = dao.conexion.prepareStatement(query2);
				c = x.executeQuery();
				while(c.next())
				{
					String nombreAA = c.getString("NOMBRE");
					int costoVenta = c.getInt("COSTOVENTA");
					int numInventarioT = c.getInt("NUMINVENTARIO");
					Producto zz = new Producto(nombreAA, costoVenta, numInventarioT);
					z.agregarProducto(zz);
				}
				
				query2 = "SELECT dataSix.ID, dataSix.NOMBRE, dataSix.COSTOVENTA, dataSix.NUMINVENTARIO FROM (SELECT dataFourth.IDPRODUCTO FROM (SELECT dataTwo.ID, dataTwo.NOMBRE, dataTwo.NUMINVENTARIO, dataTwo.UNIDADMEDIDA FROM (SELECT * FROM COMPONENTEPROVEEDOR WHERE IDPROVEEDOR='"+loginT+"') dataOne LEFT JOIN COMPONENTE dataTwo on dataOne.IDCOMPONENTE=dataTwo.ID) dataThree LEFT JOIN PRODUCTOCOMPONENTE dataFourth on dataThree.ID=dataFourth.IDCOMPONENTE) dataFive LEFT JOIN PRODUCTO dataSix on dataFive.IDPRODUCTO=dataSix.ID";
				x = dao.conexion.prepareStatement(query2);
				c = x.executeQuery();
				while(c.next())
				{
					String nombreAA = c.getString("NOMBRE");
					int costoVenta = c.getInt("COSTOVENTA");
					int numInventarioT = c.getInt("NUMINVENTARIO");
					Producto zz = new Producto(nombreAA, costoVenta, numInventarioT);
					z.agregarProducto(zz);
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

	public ArrayList<Pedido> getPedidos() {
		String query = "SELECT * FROM PEDIDO WHERE IDCLIENTE = 'alejoC'";
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		PreparedStatement a = null;
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
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
				pedidos.add(z);
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
	
	
	public ArrayList<EtapadeProduccion> etapaDeProduccion2 (String fechaInicial, String fechaFinal, int pagina)
	{
		ArrayList<EtapadeProduccion> resultado = new ArrayList<>();
		String query="CREATE INDEX index1 ON ETAPAOPERARIO(FECHAINICIAL, FECHAFINAL)";
		Timer timer = new Timer();
		PreparedStatement a = null;
		int minimo =0;
		int maximo =500;
		if(pagina!=0)
		{
			minimo = 500*pagina;
			maximo= minimo+500;
		}
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
			query="SELECT CODIGO, TIEMPOREALIZACION, IDCOMPONENTE, NUMCOMPONENTE, IDMATERIAPRIMA, NUMMATERIAPRIMA, IDPRODUCTO, NUMPRODUCTO  FROM ((SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInicial+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFinal+"','YYYY-MM-DD')) table1 LEFT JOIN ESTACIONDEPRODUCCION table2 on table1.IDETAPA=table2.IDETAPAPRODUCCION)";			
			a = dao.conexion.prepareStatement(query);
			timer.start();
			ResultSet b = a.executeQuery();
			timer.stop();
			System.out.println(timer);
			int i=0;
			while(b.next()&&i<maximo)
			{
				if (i>=minimo)
				{
					String codigo = b.getString(1);
					int tiempoRealizacion = Integer.parseInt(b.getString(2));
					EstaciondeProducto z = new EstaciondeProducto(codigo, "0", tiempoRealizacion);
					String idComponente = b.getString(3);
					if(!b.wasNull())
					{
						int numComponente = b.getInt(4);
						z.setIdComponente(idComponente);
						z.setNumComponente(numComponente);
					}
					String idMateriaPrima = b.getString(5);
					if(!b.wasNull())
					{
						int numMateriaPrima = b.getInt(6);
						z.setIdMateriaPrima(idMateriaPrima);
						z.setNumMateriaPrima(numMateriaPrima);
					}
					String idProductoA = b.getString(7);
					if(!b.wasNull())
					{
						int numProducto = b.getInt(8);
						z.setIdProducto(idProductoA);
					}
						
				}
				i=i+1;
			}
			
			query="DROP INDEX INDEX1";
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
	public ArrayList<EtapadeProduccion> etapaDeProduccion1 (String fechaInicial, String fechaFinal, int pagina, String idCom, String idMP, String idProd, int numProd, String tiempoReali)
	{
		ArrayList<EtapadeProduccion> resultado = new ArrayList<>();
		String query="CREATE INDEX index1 ON ETAPAOPERARIO(FECHAINICIAL, FECHAFINAL)";
		Timer timer = new Timer();
		PreparedStatement a = null;
		int minimo =0;
		int maximo =500;
		if(pagina!=0)
		{
			minimo = 500*pagina;
			maximo= minimo+500;
		}
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
			query="SELECT CODIGO, TIEMPOREALIZACION, IDCOMPONENTE, NUMCOMPONENTE, IDMATERIAPRIMA, NUMMATERIAPRIMA, IDPRODUCTO, NUMPRODUCTO  FROM ((SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInicial+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFinal+"','YYYY-MM-DD')) table1 LEFT JOIN (SELECT* FROM ESTACIONDEPRODUCCION ";	
			int numeroString = query.length();
			int numeroString2=0;
			int numeroString3=0;
			int numeroString4=0;
			if(idCom!=null&&idCom!="")
			{
				query=query+"WHERE ESTACIONDEPRODUCCION.IDCOMPONENTE='"+idCom+"'";
				numeroString2=query.length();
			}
			if(idMP!=null&&idMP!="")
			{
				if(numeroString<query.length())
				{
					query="SELECT CODIGO, TIEMPOREALIZACION, IDCOMPONENTE, NUMCOMPONENTE, IDMATERIAPRIMA, NUMMATERIAPRIMA, IDPRODUCTO, NUMPRODUCTO  FROM ((SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInicial+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFinal+"','YYYY-MM-DD')) table1 LEFT JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE (ESTACIONDEPRODUCCION.IDCOMPONENTE='"+idCom+"' OR ESTACIONDEPRODUCCION.IDMATERIAPRIMA='"+idMP+"')";	
					numeroString3=query.length();
				}
				else
				{
					query=query+"WHERE ESTACIONDEPRODUCCION.IDMATERIAPRIMA='"+idMP+"'";
					numeroString4=query.length();
				}
			}
			if(idProd!=null&&idProd!="")
			{
				if(numeroString3>0)
				{
					query="SELECT CODIGO, TIEMPOREALIZACION, IDCOMPONENTE, NUMCOMPONENTE, IDMATERIAPRIMA, NUMMATERIAPRIMA, IDPRODUCTO, NUMPRODUCTO  FROM ((SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInicial+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFinal+"','YYYY-MM-DD')) table1 LEFT JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE (ESTACIONDEPRODUCCION.IDCOMPONENTE='"+idCom+"' OR ESTACIONDEPRODUCCION.IDMATERIAPRIMA='"+idMP+"' OR ESTACIONDEPRODUCCION.IDPRODUCTO='"+idProd+"')";	

				}
				else if (numeroString2>0)
				{
					query="SELECT CODIGO, TIEMPOREALIZACION, IDCOMPONENTE, NUMCOMPONENTE, IDMATERIAPRIMA, NUMMATERIAPRIMA, IDPRODUCTO, NUMPRODUCTO  FROM ((SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInicial+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFinal+"','YYYY-MM-DD')) table1 LEFT JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE (ESTACIONDEPRODUCCION.IDCOMPONENTE='"+idCom+"' OR ESTACIONDEPRODUCCION.IDPRODUCTO='"+idProd+"')";	

				}
				else if(numeroString4>0)
				{
					query="SELECT CODIGO, TIEMPOREALIZACION, IDCOMPONENTE, NUMCOMPONENTE, IDMATERIAPRIMA, NUMMATERIAPRIMA, IDPRODUCTO, NUMPRODUCTO  FROM ((SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInicial+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFinal+"','YYYY-MM-DD')) table1 LEFT JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE (ESTACIONDEPRODUCCION.IDMATERIAPRIMA='"+idMP+"' OR ESTACIONDEPRODUCCION.IDPRODUCTO='"+idProd+"')";	

				}
				else
				{
					query="SELECT CODIGO, TIEMPOREALIZACION, IDCOMPONENTE, NUMCOMPONENTE, IDMATERIAPRIMA, NUMMATERIAPRIMA, IDPRODUCTO, NUMPRODUCTO  FROM ((SELECT* FROM ETAPAOPERARIO WHERE FECHAINICIAL>=to_date('"+fechaInicial+"','YYYY-MM-DD') and FECHAFINAL<=to_date('"+fechaFinal+"','YYYY-MM-DD')) table1 LEFT JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE ESTACIONDEPRODUCCION.IDPRODUCTO='"+idProd+"'";	

				}
			}
			if(numProd!=0)
			{
				if(numeroString==query.length())
				{
					query=query+"WHERE (ESTACIONDEPRODUCCION.NUMCOMPONENTE>="+numProd+" OR ESTACIONDEPRODUCCION.NUMMATERIAPRIMA>="+numProd+" OR ESTACIONDEPRODUCCION.NUMPRODUCTO>="+numProd+")";	

				}
				else
				{
					query=query+" AND (ESTACIONDEPRODUCCION.NUMCOMPONENTE>="+numProd+" OR ESTACIONDEPRODUCCION.NUMMATERIAPRIMA>="+numProd+" OR ESTACIONDEPRODUCCION.NUMPRODUCTO>="+numProd+")";	
				}
			}
			if(tiempoReali!=null&&tiempoReali!="")
			{
				if(numeroString==query.length())
				{
					query=query+"WHERE ESTACIONDEPRODUCCION.TIEMPOREALIZACION='"+tiempoReali+"'";
				}
				else
				{
					query=query+" AND ESTACIONDEPRODUCCION.TIEMPOREALIZACION='"+tiempoReali+"'";
				}
			}
			query = query+")table2 on table1.IDETAPA=table2.IDETAPAPRODUCCION)  WHERE CODIGO IS NOT NULL";
			a = dao.conexion.prepareStatement(query);
			timer.start();
			ResultSet b = a.executeQuery();
			timer.stop();
			System.out.println(timer.toString());
			int i=0;
			while(b.next()&&i<maximo)
			{
				if (i>=minimo)
				{
					String codigo = b.getString(1);
					int tiempoRealizacion = Integer.parseInt(b.getString(2));
					EstaciondeProducto z = new EstaciondeProducto(codigo, "0", tiempoRealizacion);
					String idComponente = b.getString(3);
					if(!b.wasNull())
					{
						int numComponente = b.getInt(4);
						z.setIdComponente(idComponente);
						z.setNumComponente(numComponente);
					}
					String idMateriaPrima = b.getString(5);
					if(!b.wasNull())
					{
						int numMateriaPrima = b.getInt(6);
						z.setIdMateriaPrima(idMateriaPrima);
						z.setNumMateriaPrima(numMateriaPrima);
					}
					String idProductoA = b.getString(7);
					if(!b.wasNull())
					{
						int numProducto = b.getInt(8);
						z.setIdProducto(idProductoA);
					}
											
				}
				i=i+1;
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
	public ArrayList<Pedido> pedido1 (String tipoMaterial, int costo, int pagina)
	{
		ArrayList<Pedido> resultado = new ArrayList<>();
		String query="CREATE INDEX index1 ON ETAPAOPERARIO(FECHAINICIAL, FECHAFINAL)";
		Timer timer = new Timer();
		PreparedStatement a = null;
		int minimo =0;
		int maximo =500;
		if(pagina!=0)
		{
			minimo = 500*pagina;
			maximo= minimo+500;
		}
		try 
		{
			dao.inicializar();	
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
			if(tipoMaterial=="PRODUCTO")
			{
				query="SELECT* FROM PEDIDO WHERE IDPRODUCTO IS NOT NULL AND COSTO>"+costo;
			}
			else if (tipoMaterial=="MATERIAPRIMA")
			{
				query="SELECT* FROM PEDIDO WHERE IDMATERIAPRIMA IS NOT NULL AND COSTO>"+costo;
			}
			else
			{
				query="SELECT* FROM PEDIDO WHERE IDCOMPONENTE IS NOT NULL AND COSTO>"+costo;
			}
			query ="";
			a = dao.conexion.prepareStatement(query);
			timer.start();
			ResultSet b = a.executeQuery();
			timer.stop();
			System.out.println(timer);
			int i=0;
			while(b.next()&&i<maximo)
			{
				if (i>=minimo)
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
				i=i+1;
			}
			
			query="DROP INDEX INDEX1";
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
	public ArrayList<Pedido> pedido2 (String tipoMaterial, int costo, int pagina, String idMaterial)
	{
		ArrayList<Pedido> resultado = new ArrayList<>();
		String query="CREATE INDEX index1 ON ETAPAOPERARIO(FECHAINICIAL, FECHAFINAL)";
		Timer timer = new Timer();
		PreparedStatement a = null;
		int minimo =0;
		int maximo =500;
		if(pagina!=0)
		{
			minimo = 500*pagina;
			maximo= minimo+500;
		}
		try 
		{
			dao.inicializar();	
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
			if(tipoMaterial=="PRODUCTO")
			{
				query="SELECT* FROM PEDIDO WHERE IDPRODUCTO IS NOT NULL AND IDPRODUCTO='"+idMaterial+" AND COSTO>"+costo;
			}
			else if (tipoMaterial=="MATERIAPRIMA")
			{
				query="SELECT* FROM (SELECT ID,IDCLIENTE,ESTADOPAGO,FECHACREACION,FECHARECIBIDO,DEADLINE,ANOTACIONES,IDPRODUCTO,NUMPRODUCTO,COSTO FROM (SELECT table2.IDPEDIDO FROM (SELECT* FROM ESTACIONDEPRODUCCION WHERE IDMATERIAPRIMA='"+idMaterial+"') table1 INNER JOIN ETAPAOPERARIO table2 ON table1.IDETAPAPRODUCCION=table2.IDETAPA)table3 LEFT JOIN (SELECT* FROM PEDIDO WHERE COSTO>"+costo+")table4 on table3.IDPEDIDO = table4.ID) UNION (SELECT ID,IDCLIENTE,ESTADOPAGO,FECHACREACION,FECHARECIBIDO,DEADLINE,ANOTACIONES,IDPRODUCTO,NUMPRODUCTO,COSTO  FROM PEDIDO WHERE IDMATERIAPRIMA='"+idMaterial+"')";
			}
			else
			{
				query="SELECT* FROM (SELECT ID,IDCLIENTE,ESTADOPAGO,FECHACREACION,FECHARECIBIDO,DEADLINE,ANOTACIONES,IDPRODUCTO,NUMPRODUCTO,COSTO FROM (SELECT table2.IDPEDIDO FROM (SELECT* FROM ESTACIONDEPRODUCCION WHERE IDCOMPONENTE='"+idMaterial+"') table1 INNER JOIN ETAPAOPERARIO table2 ON table1.IDETAPAPRODUCCION=table2.IDETAPA)table3 LEFT JOIN (SELECT* FROM PEDIDO WHERE COSTO>"+costo+")table4 on table3.IDPEDIDO = table4.ID) UNION (SELECT ID,IDCLIENTE,ESTADOPAGO,FECHACREACION,FECHARECIBIDO,DEADLINE,ANOTACIONES,IDPRODUCTO,NUMPRODUCTO,COSTO  FROM PEDIDO WHERE IDCOMPONENTE='"+idMaterial+"')";				
			}
			a = dao.conexion.prepareStatement(query);
			timer.start();
			ResultSet b = a.executeQuery();
			timer.stop();
			System.out.println(timer);
			int i=0;
			while(b.next()&&i<maximo)
			{
				if (i>=minimo)
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
				i=i+1;
			}
			
			query="DROP INDEX INDEX1";
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
