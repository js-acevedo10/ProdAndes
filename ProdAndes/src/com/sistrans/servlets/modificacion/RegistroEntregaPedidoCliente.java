package com.sistrans.servlets.modificacion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesGerente;

/**
 * Servlet implementation class RegistroEntregaPedidoCliente
 */
@WebServlet("/registro/entregapedidocliente.html")
public class RegistroEntregaPedidoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroEntregaPedidoCliente() {
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
	
	public void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String fechaFinal = request.getParameter("ff");
		
		boolean x = ProdAndesGerente.darInstancia().registrarEntregaPedido(id, fechaFinal);
		
		if(x) {
			response.sendRedirect("/ProdAndes/pages/gerente/success/entregacliente.html");
		} else {
			response.sendRedirect("/ProdAndes/pages/gerente/error/entregacliente.html");
		}
	}
}
