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

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM MATERIAPRIMA ";
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
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM COMPONENTE";
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
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ETAPAPRODUCCION";
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
			String existencias, String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM PRODUCTO ";
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

	public boolean registrarEjecucionEtapa(String id, String fi, String ft,
			String cod) {
		// TODO Auto-generated method stub
		return false;
	}	
}
