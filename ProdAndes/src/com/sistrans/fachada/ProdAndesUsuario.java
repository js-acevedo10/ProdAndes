package com.sistrans.fachada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM MATERIAPRIMA";
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

	public ArrayList<Componente> consultarExistenciasComp(String tipo) {
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

	public ArrayList<EtapadeProduccion> consultarExistenciasEtapa(String tipo) {
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

	public ArrayList<Producto> consultarExistenciasProd(String tipo) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM PRODUCTO";
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

	public boolean registrarPedido(String id1, String id2, String id3,
			String id32, String id4) {
		// TODO Auto-generated method stub
		String idProducto = id1;
		String idCliente = id2;
		DateFormat df = new SimpleDateFormat("MM-DD-YYYY");
		Date dateCreacion = new Date();
		String fechaCreacion = df.format(dateCreacion);
		String deadline = id3;
		int cantidadRequerida = Integer.parseInt(id32);
		String query ="SELECT* FROM PRODUCTO WHERE PRODUCTO.ID='"+idProducto+"'";
		PreparedStatement a = null;
		boolean flag = false;
		Date fechaEntrega=null;
		try {
			fechaEntrega = df.parse(deadline);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			dao.inicializar();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			int numInventarioT = 0;
			while(b.next())
			{
				numInventarioT = b.getInt("NUMINVENTARIO");
			}
			if(numInventarioT>cantidadRequerida)
			{
				String query3 ="INSERT INTO PEDIDO (ID, IDCLIENTE, ESTADOPAGO,FECHACREACION, DEADLINE, IDPRODUCTO, NUMPRODUCTO)VALUES ('"+id1+id2+fechaCreacion+"','"+id2+"','no pago',to_date('"+fechaCreacion+"','MM-DD-YYYY'),to_date('"+deadline+"','MM-DD-YYYY'), '"+id1+"', '"+id32+"')";
				a = dao.conexion.prepareStatement(query3);
				a.executeQuery();
				flag = true;
			}
			else if(fechaEntrega!=null&&dateCreacion.after(fechaEntrega))
			{
				
			}
			else
			{
				int numRequerido = cantidadRequerida-numInventarioT;
				String query2 = "SELECT dataTwo.TIEMPOREALIZACION, dataTwo.CODIGO FROM (SELECT* FROM ETAPAPRODUCCION WHERE ETAPAPRODUCCION.IDPRODUCTO ='"+idProducto+"') dataOne INNER JOIN ESTACIONDEPRODUCCION dataTwo on dataOne.ID = dataTwo.IDETAPAPRODUCCION";
				a = dao.conexion.prepareStatement(query2);
				b = a.executeQuery();
				int dias =0;
				while(b.next())
				{
					dias+=b.getInt("TIEMPOREALIZACION");					
				}
				Calendar c = Calendar.getInstance();
				c.setTime(dateCreacion);
				c.add(Calendar.DATE, dias);
				if(c.before(fechaEntrega))
				{
					boolean flag2 = true;
					String query3 = "SELECT dataRight.NOMBRE, dataRight.TONELADAS, dataLeft.NUMMATERIAPRIMA FROM (SELECT dataTwo.IDMATERIAPRIMA, dataTwo.NUMMATERIAPRIMA FROM (SELECT* FROM ETAPAPRODUCCION WHERE ETAPAPRODUCCION.IDPRODUCTO ='"+idProducto+"') dataOne INNER JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE ESTACIONDEPRODUCCION.IDMATERIAPRIMA is not null) dataTwo on dataOne.ID = dataTwo.IDETAPAPRODUCCION) dataLeft INNER JOIN MATERIAPRIMA dataRight on dataLeft.IDMATERIAPRIMA=dataRight.ID";
					a = dao.conexion.prepareStatement(query3);
					b = a.executeQuery();
					while(b.next()&&flag2)
					{
						int disponibles=b.getInt("TONELADAS");
						int requeridas=b.getInt("NUMMATERIAPRIMA");
						if(requeridas>disponibles)
						{
							flag2=false;
						}
					}
					String query4 = "SELECT dataRight.NOMBRE, dataRight.NUMINVENTARIO, dataLeft.NUMCOMPONENTE FROM (SELECT dataTwo.IDCOMPONENTE, dataTwo.NUMCOMPONENTE FROM (SELECT* FROM ETAPAPRODUCCION WHERE ETAPAPRODUCCION.IDPRODUCTO ='"+idProducto+"') dataOne INNER JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE ESTACIONDEPRODUCCION.IDCOMPONENTE is not null) dataTwo on dataOne.ID = dataTwo.IDETAPAPRODUCCION) dataLeft INNER JOIN COMPONENTE dataRight on dataLeft.IDCOMPONENTE=dataRight.ID";
					a = dao.conexion.prepareStatement(query4);
					b = a.executeQuery();
					while(b.next()&&flag2)
					{
						int disponibles=b.getInt("NUMINVENTARIO");
						int requeridas=b.getInt("NUMCOMPONENTE");
						if(requeridas>disponibles)
						{
							flag2=false;
						}
					}
					String query5 = "SELECT dataRight.NOMBRE, dataRight.NUMINVENTARIO, dataLeft.NUMPRODUCTO FROM (SELECT dataTwo.IDPRODUCTO, dataTwo.NUMPRODUCTO FROM (SELECT* FROM ETAPAPRODUCCION WHERE ETAPAPRODUCCION.IDPRODUCTO ='"+idProducto+"') dataOne INNER JOIN (SELECT* FROM ESTACIONDEPRODUCCION WHERE ESTACIONDEPRODUCCION.IDPRODUCTO is not null) dataTwo on dataOne.ID = dataTwo.IDETAPAPRODUCCION) dataLeft INNER JOIN PRODUCTO dataRight on dataLeft.IDPRODUCTO=dataRight.ID";
					a = dao.conexion.prepareStatement(query5);
					b = a.executeQuery();
					while(b.next()&&flag2)
					{
						int disponibles=b.getInt("NUMINVENTARIO");
						int requeridas=b.getInt("NUMPRODUCTO");
						if(requeridas>disponibles)
						{
							flag2=false;
						}
					}
					if(flag2)
					{
						flag = true;
						String query6 ="INSERT INTO PEDIDO (ID, IDCLIENTE, ESTADOPAGO,FECHACREACION, DEADLINE, IDPRODUCTO, NUMPRODUCTO)VALUES ('"+id1+"','3','no pago',to_date('"+fechaCreacion+"','MM-DD-YYYY'),to_date('"+deadline+"','MM-DD-YYYY'), '"+id1+"', '"+id32+"')";
						a = dao.conexion.prepareStatement(query6);
						a.executeQuery();
					}
					else
					{
						flag = true;
						String query6 ="INSERT INTO PEDIDO (ID, IDCLIENTE, ESTADOPAGO,FECHACREACION, DEADLINE, ANOTACIONES, IDPRODUCTO, NUMPRODUCTO)VALUES ('"+id1+"','3','no pago',to_date('"+fechaCreacion+"','MM-DD-YYYY'),to_date('"+deadline+"','MM-DD-YYYY'), 'No se tienen los materiales necesarios', '"+id1+"', '"+id32+"')";
						a = dao.conexion.prepareStatement(query6);
						a.executeQuery();
					}
				}
				
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
		return flag;
	}
	
	//Metodos de casos de uso
	
}
