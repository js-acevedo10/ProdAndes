package com.sistrans.fachada;

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
		dao.inicializar(ruta);
	}

	public ArrayList<MateriaPrima> consultarExistenciasMatPrima(String tipo,
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		// TODO Auto-generated method stub
		String query="SELECT * FROM ProdAndes.materiaprima WHERE ";
		if(tipo!="")
		{
			query =  query + "tipo = '" + tipo + "'" ; 
			if(existencias!="")
			{
				query = query + " AND numInventario = '" + existencias +"'";
				if (fechaSol!=""&&fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
				}
				else if(fechaSol !="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
				}
				else if(fechaEntreg!="")
				{
					query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "' AND numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
				}
			}
			else if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN  (SELECT* FROM ProdAndes.materiaprima WHERE tipo = '" + tipo + "') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
		}
		else if (existencias !="")
		{
			query = query + " numInventario = '" + existencias +"'";
			if (fechaSol!=""&&fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaSol !="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
			else if(fechaEntreg!="")
			{
				query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN (SELECT* FROM ProdAndes.materiaprima WHERE numInventario = '" + existencias +"') dataFourth on dataThree.materiaprimaID = dataFourth.ID";
			}
		}
		else 	if (fechaSol!=""&&fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "' AND fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.materiaprima dataFourth on dataThree.materiaprimaID = dataFourth.ID";
		}
		else if(fechaSol !="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechacreacion= '"+ fechaSol + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.Componente dataFourth on dataThree.materiaprimaID = dataFourth.ID";
		}
		else if(fechaEntreg!="")
		{
			query = "SELECT* FROM ((SELECT* FROM ProdAndes.Pedido WHERE fechaRecibido= '"+ fechaEntreg + "') dataOne LEFT JOIN ProdAndes.Pedidomateriaprima dataTwo on dataOne.id = dataTwo.pedidoID) dataThree LEFT JOIN ProdAndes.materiaprima dataFourth on dataThree.materiaprimaID = dataFourth.ID";
		}
		else
		{
			query="SELECT * FROM ProdAndes.materiaprima";
		}
		return null;
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
		return null;
	}

	public ArrayList<EtapadeProduccion> consultarExistenciasEtapa(String tipo,
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
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
		return null;
	}

	public ArrayList<Producto> consultarExistenciasProd(String tipo,
			String existencias, String etapa, String fechaSol,
			String fechaEntreg) {
		String query="SELECT * FROM ProdAndes.Producto WHERE ";
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
		return null;
	}

	public ArrayList<String> informacionMaterial(String query, String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean registrarMateriaPrima(String nombre, String cantidad,
			String tonelada) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO ProdAndes.MateriaPrima (nombre, numInventario, toneladas, id, tipo)VALUES ('"+nombre+"','"+cantidad+"','"+tonelada+"','"+nombre+cantidad+tonelada+"','no tipo');";
		return false;
	}

	public boolean registrarComponente(String nombre, String cantidad,
			String unidadMedida) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO ProdAndes.Componente (nombre, numInventario, unidadMedida, id, tipo)VALUES ('"+nombre+"','"+cantidad+"','"+unidadMedida+"','"+nombre+cantidad+unidadMedida+"','no tipo');";
		return false;
	}

	public boolean registrarEntregaPedido(String id, String fechaFinal) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Metodos de casos de uso
	
}
