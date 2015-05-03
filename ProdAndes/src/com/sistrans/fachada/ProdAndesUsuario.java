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
import com.sistrans.mundo.Pedido;
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

	public boolean registrarPedido(String id1, String id2, String id3,
			String id32, String id4) {
		// TODO Auto-generated method stub
		String idProducto = id1;
		String idCliente = id2;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreacion = new Date();
		String fechaCreacion = df.format(dateCreacion);
		String deadline = id3.replace("/", "-");
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
			apagarAutoCommit();
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
				String query3 ="INSERT INTO PEDIDO (ID, IDCLIENTE, ESTADOPAGO,FECHACREACION, DEADLINE, IDPRODUCTO, NUMPRODUCTO)VALUES ('"+id1+id2+fechaCreacion+"','"+id2+"','no pago',to_date('"+fechaCreacion+"','YYYY-MM-DD'),to_date('"+fechaEntrega+"','YYYY-MM-DD'), '"+id1+"', '"+id32+"')";
				a = dao.conexion.prepareStatement(query3);
				a.executeQuery();
				flag = true;
			}
			else if(fechaEntrega!=null&&dateCreacion.after(fechaEntrega))
			{
				hacerRollback();
				encenderAutoCommit();
				return false;
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
				Date xxx = c.getTime();
				String ti = df.format(dateCreacion);
				String tf = df.format(fechaEntrega);
				if(xxx.before(fechaEntrega))
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
						int rand = (int) (Math.random()*1000);
						flag = true;
						String query6 ="INSERT INTO PEDIDO (ID, IDCLIENTE, ESTADOPAGO,FECHACREACION, DEADLINE, IDPRODUCTO, NUMPRODUCTO)VALUES ('"+(id1+""+rand) +"','alejoC','no pago',to_date('"+ti+"','YYYY-MM-DD'),to_date('"+tf+"','YYYY-MM-DD'), '"+id1+"', '"+id32+"')";
						a = dao.conexion.prepareStatement(query6);
						a.executeQuery();
						
						query6="SELECT* FROM ETAPAPRODUCCION WHERE IDPRODUCTO='"+id1+"' AND NUM='1'";
						String idEtapa="";
						a = dao.conexion.prepareStatement(query6);
						b = a.executeQuery();
						while(b.next())
						{
							idEtapa = b.getString(1);
						}
						query6="INSERT INTO ETAPAOPERARIO (IDETAPA, IDPEDIDO) VALUES ('"+idEtapa+"','"+(id1+""+rand) +"')";
						a = dao.conexion.prepareStatement(query6);
						b = a.executeQuery();
						
						hacerCommit();
						encenderAutoCommit();
					}
					else
					{
						int rand = (int) (Math.random()*1000);
						flag = true;
						String query6 ="INSERT INTO PEDIDO (ID, IDCLIENTE, ESTADOPAGO,FECHACREACION, DEADLINE, ANOTACIONES, IDPRODUCTO, NUMPRODUCTO)VALUES ('"+(id1+""+rand)+"','','no pago',to_date('"+ti+"','YYYY-MM-DD'),to_date('"+tf+"','YYYY-MM-DD'), 'No se tienen los materiales necesarios', '"+id1+"', '"+id32+"')";
						a = dao.conexion.prepareStatement(query6);
						a.executeQuery();
						

						hacerCommit();
						encenderAutoCommit();
					}
				}
				
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			hacerRollback();
			encenderAutoCommit();
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
	
	public boolean cancelarPedido(String idPedido)
	{
		String query = "SELECT* FROM PEDIDO WHERE ID = '"+idPedido+"'";
		PreparedStatement a = null;
		try 
		{
			
			dao.inicializar();
			apagarAutoCommit();
			a = dao.conexion.prepareStatement(query);
			ResultSet b = a.executeQuery();
			ArrayList<String> infoPedido = new ArrayList<>();
			while(b.next())
			{
				infoPedido.add(b.getString(12));
			}
			query="SELECT* FROM ETAPAOPERARIO WHERE IDPEDIDO='"+idPedido+"' AND (FECHAINICIAL IS NULL OR FECHAFINAL IS NULL)";
			a = dao.conexion.prepareStatement(query);
			b = a.executeQuery();
			ArrayList<String> idOperarios = new ArrayList<>();
			while(b.next())
			{
				String idOperario=b.getString(2);
				String query2 ="DELETE FROM ETAPAOPERARIO WHERE IDPEDIDO='"+idPedido+"' AND IDETAPA='"+b.getString(1)+"' AND IDOPERARIO='"+idOperario+"'";
				a = dao.conexion.prepareStatement(query2);
				a.executeQuery();
				idOperarios.add(idOperario);
			}
			if(!idOperarios.isEmpty())
			{
				query = "SELECT* FROM ETAPAOPERARIO WHERE IDOPERARIO IS NULL";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				while(b.next()&&!idOperarios.isEmpty())
				{
					String query2 ="UPDATE ETAPAOPERARIO SET IDOPERARIO='"+idOperarios.get(0)+"' WHERE IDETAPA='"+b.getString(1)+"' AND IDPEDIDO='"+b.getString(5)+"'";
					a = dao.conexion.prepareStatement(query2);
					a.executeQuery();
					idOperarios.remove(0);
				}
				if(!idOperarios.isEmpty())
				{
					query="SELECT* FROM PEDIDO WHERE FECHARECIBIDO IS NULL";
					a = dao.conexion.prepareStatement(query);
					b = a.executeQuery();
					while(b.next())
					{
						query = "SELECT* FROM(SELECT* FROM (SELECT* FROM ETAPAOPERARIO WHERE IDPEDIDO ='"+ b.getString(1) + "') data1 LEFT JOIN ETAPAPRODUCCION data2 on data1.IDETAPA=data2.ID ORDER BY NUM DESC) WHERE ROWNUM=1";
						String idProducto="";
						int numeroSiguiente=0;
						a = dao.conexion.prepareStatement(query);
						ResultSet c = a.executeQuery();
						while(c.next())
						{
							idProducto=c.getString("IDPRODUCTO");
							numeroSiguiente=c.getInt("NUM")+1;
						}
						query="SELECT* FROM ETAPAPRODUCCION WHERE IDPRODUCTO='"+ idProducto + "' AND NUM='" + numeroSiguiente + "'";
						a = dao.conexion.prepareStatement(query);
						c = a.executeQuery();
						EtapadeProduccion nuevaEtapa=null;
						while(c.next())
						{
							nuevaEtapa = new EtapadeProduccion(c.getInt(2), c.getString(1));
						}
						if(nuevaEtapa!=null)
						{
							query="INSERT INTO ETAPAOPERARIO (IDETAPA, IDOPERARIO, IDPEDIDO) VALUES ('"+ nuevaEtapa.getNombre() + "', '" + idOperarios.get(0) + "', '"+idPedido+"')";
							a = dao.conexion.prepareStatement(query);
							idOperarios.remove(0);
						}
					}
				}
			}
			query="SELECT* FROM (SELECT* FROM (SELECT* FROM ETAPAOPERARIO WHERE IDPEDIDO='"+idPedido+"') data1 LEFT JOIN ETAPAPRODUCCION data2 on data1.IDETAPA=data2.ID ORDER BY NUM DESC) WHERE ROWNUM=1";
			a = dao.conexion.prepareStatement(query);
			b = a.executeQuery();
			int numeroDeEtapa =0;
			while(b.next())
			{
				numeroDeEtapa = b.getInt(7);
			}
			if(numeroDeEtapa==1||numeroDeEtapa==0)
			{
				query="DELETE FROM ETAPAOPERARIO WHERE IDPEDIDO='"+idPedido+"'";
				a = dao.conexion.prepareStatement(query);
				a.executeQuery();
			}
			else
			{
				query="SELECT* FROM PEDIDO WHERE IDPRODUCTO='"+infoPedido.get(0)+"'";
				a = dao.conexion.prepareStatement(query);
				b = a.executeQuery();
				while(b.next())
				{
					String idPedidoActual = b.getString(1);
					query="SELECT* FROM (SELECT* FROM (SELECT* FROM ETAPAOPERARIO WHERE IDPEDIDO='"+idPedidoActual+"') data1 LEFT JOIN ETAPAPRODUCCION data2 on data1.IDETAPA=data2.ID ORDER BY NUM DESC) WHERE ROWNUM=1";
					a = dao.conexion.prepareStatement(query);
					ResultSet c = a.executeQuery();
					int numeroEtapaActual=0;
					while(c.next())
					{
						numeroEtapaActual=c.getInt("NUM");
					}
					if(numeroEtapaActual==0||numeroEtapaActual==1)
					{
						query="UPDATE ETAPAOPERARIO SET IDPEDIDO='"+idPedidoActual+"' WHERE IDPEDIDO='"+idPedido+"'";
						a = dao.conexion.prepareStatement(query);
						a.executeQuery();
					}
					else if(numeroEtapaActual<numeroDeEtapa)
					{
						while(numeroEtapaActual<numeroDeEtapa)
						{
							query="SELECT* FROM (SELECT* FROM (SELECT* FROM ETAPAOPERARIO WHERE IDPEDIDO='"+idPedido+"') data1 LEFT JOIN ETAPAPRODUCCION data2 on data1.IDETAPA=data2.ID ORDER BY NUM DESC) WHERE ROWNUM=1";
							a = dao.conexion.prepareStatement(query);
							c = a.executeQuery();
							String idEtapaCambio="";
							while(c.next())
							{
								idEtapaCambio=b.getString(1);
							}
							query="UPDATE ETAPAOPERARIO SET IDPEDIDO='"+idPedidoActual+"' WHERE IDPEDIDO='"+idPedido+"' AND IDETAPA='"+idEtapaCambio+"'";
							a = dao.conexion.prepareStatement(query);
							a.executeQuery();
							numeroEtapaActual=numeroEtapaActual+1;
							numeroDeEtapa=numeroDeEtapa-1;
						}
					}
					query="DELETE FROM ETAPAOPERARIO WHERE IDPEDIDO='"+idPedido+"'";
					a = dao.conexion.prepareStatement(query);
					a.executeQuery();
				}
			}
			query = "DELETE FROM PEDIDO WHERE ID = '"+idPedido+"'";
			a = dao.conexion.prepareStatement(query);
			a.executeQuery();
			hacerCommit();
			encenderAutoCommit();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			hacerRollback();
			encenderAutoCommit();
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
	public ArrayList<Pedido> consultarPedidos (String idCliente)
	{
		ArrayList<Pedido> resultado = new ArrayList<>();
		String query = "SELECT FROM PEDIDO WHERE IDCLIENTE = '"+idCliente+"'";
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
				String idPrductoT = b.getString("IDPRODUCTO");
				int numProductoT = b.getInt("NUMPRODUCTO");
				Pedido z = new Pedido(idT, idClienteT, estadoPagoT, fechaCreacionT, fechaRecibidoT, deadlineT, anotacionesT, null, 0, null, 0, idPrductoT, numProductoT);
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
}
