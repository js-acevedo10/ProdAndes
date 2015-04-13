package com.sistrans.servlets.modificacion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesAdmin;
import com.sistrans.fachada.ProdAndesUsuario;

/**
 * Servlet implementation class RegistroPedidoProductosCliente
 */
@WebServlet("/registro/pedido/usuario.html")
public class RegistroPedidoProductosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroPedidoProductosCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesarSolicitud(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesarSolicitud(request, response);
	}

	private void procesarSolicitud(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id1 = request.getParameter("prod1");
		String id2 = request.getParameter("prod2");
		String id3 = request.getParameter("prod3");
		String id4 = request.getParameter("prod4");
		String fecha = request.getParameter("ff");
		String c1 = request.getParameter("c1");
		String c2 = request.getParameter("c2");
		String c3 = request.getParameter("c3");
		String c4 = request.getParameter("c4");
		int g = 4;
		if(id2 == null || id2.equals("")) g = 1;
		else if(id3 == null || id3.equals("")) g = 2;
		else if(id4 == null || id4.equals("")) g = 3;
		
		boolean x = true;
		ProdAndesAdmin.darInstancia().apagarAutoCommit();
		ProdAndesAdmin.darInstancia().lockTable("PEDIDO");
		ProdAndesAdmin.darInstancia().lockTable("PRODUCTO");
		for(int i = 0; i < g; i++) {
			if(i == 1) {
				id1 = id2;
				c1 = c2;
			}
			if(i == 2) {
				id1 = id3;
				c1 = c3;
			}
			if(i == 3) {
				id1 = id4;
				c1 = c4;
			}
			x = ProdAndesUsuario.darInstancia().registrarPedido(id1, id2, fecha, c1, id4);
			if(!x) break;
		}
		
		if(x) {
			response.sendRedirect("/ProdAndes/pages/user/success/pedido.html");
			ProdAndesAdmin.darInstancia().hacerCommit();
		} else {
			response.sendRedirect("/ProdAndes/pages/user/error/pedido.html");
			ProdAndesAdmin.darInstancia().hacerRollback();
		}
		ProdAndesAdmin.darInstancia().encenderAutoCommit();
	}

}
