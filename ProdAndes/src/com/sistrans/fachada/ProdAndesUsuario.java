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

public class ProdAndesUsuario {
	
	private ConsultaDAOUsuario dao;
	
	//Singleton
	
	private static ProdAndesUsuario instancia;
	
	public static ProdAndesUsuario darInstancia()
	{
		if(instancia == null)
			instancia = new ProdAndesUsuario();
		return instancia;
	}
	
	private ProdAndesUsuario()
	{
		dao = new ConsultaDAOUsuario();
	}
	
	public void inicializarRuta(String ruta)
	{
		dao.inicializar();
	}

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existencias) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.materiaprima WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
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
			String existencias) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM componente";
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
			String existencias) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM Producto";
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
			String existencias) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM producto";
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
		if(tipo=="materia-prima")
		{
			queer = "SELECT* FROM materiaprima";
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
			queer = "SELECT* FROM componente";
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
			queer = "SELECT* FROM producto";
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
			queer = "SELECT* FROM etapadeproduccion";
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

	public boolean registrarPedido(String id1, String id2, String id3,
			String id32, String id4) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO ProdAndes.Pedido (id, estadodepago, fechacreacion, deadline, fecharecibido)VALUES ('"+id1+"','"+id2+"','"+id3+"','"+id32+"','"+id4+"')";
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
