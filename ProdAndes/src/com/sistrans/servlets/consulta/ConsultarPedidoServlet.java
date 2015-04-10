package com.sistrans.servlets.consulta;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsultarPedidoServlet
 */
@WebServlet("/consultar/pedido.html")
public class ConsultarPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPedido(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPedido(request, response);
	}

	private void procesarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String idPedido = request.getParameter("idPedido");
		String idCliente = request.getParameter("idCliente");
		String estadoPago = request.getParameter("estadoPago");
		String fechaCreacion = request.getParameter("fechaCreacion");
		String fechaRecibido = request.getParameter("fechaRecibido");
		String deadline = request.getParameter("deadline");
		String idMateriaPrima = request.getParameter("idMateriaPrima");
		String idComponente = request.getParameter("idComponente");
		String idProducto = request.getParameter("idProducto");
	}

}
