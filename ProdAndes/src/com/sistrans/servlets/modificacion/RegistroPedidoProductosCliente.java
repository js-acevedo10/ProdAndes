package com.sistrans.servlets.modificacion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		boolean x = ProdAndesUsuario.darInstancia().registrarPedido(id1, id2, id3, id3, id4);
		
		if(x) {
			response.sendRedirect("/ProdAndes/pages/user/success/pedido.html");
		} else {
			response.sendRedirect("/ProdAndes/pages/user/error/pedido.html");
		}
	}

}
