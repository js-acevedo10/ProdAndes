package com.sistrans.servlets.consulta;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MaterialServlet
 */
@WebServlet("/consultar/material.html")
public class ConsultarMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarMaterialServlet() {
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
		PrintWriter out = response.getWriter();
		String role = request.getParameter("role");
		String tipo = request.getParameter("t");
		String existencias = request.getParameter("e");
		String etapa = "";
		String fechaSol = "";
		String fechaEntreg = "";
		
		switch(role) {
			case "admin":
				etapa = request.getParameter("s");
				fechaSol = request.getParameter("ds");
				fechaEntreg = request.getParameter("de");
				solicitudAdmin(role, tipo, existencias, etapa, fechaSol, fechaEntreg, out);
				break;
			case "user":
				solicitudUser(role, tipo, existencias, out);
				break;
			case "operario":
				etapa = request.getParameter("s");
				fechaEntreg = request.getParameter("de");
				solicitudOperario(role, tipo, existencias, fechaEntreg, out);
				break;
			case "gerente":
				etapa = request.getParameter("s");
				fechaSol = request.getParameter("ds");
				fechaEntreg = request.getParameter("de");
				solicitudGerente(role, tipo, existencias, etapa, fechaSol, fechaEntreg, out);
				break;
			default:
				response.sendRedirect("/ProdAndes/pages/error/404.html");
				break;
		}
	}

	private void solicitudAdmin(String role, String tipo, String existencias,
			String etapa, String fechaSol, String fechaEntreg, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void solicitudUser(String role, String tipo, String existencias,
			PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void solicitudOperario(String role, String tipo,
			String existencias, String fechaEntreg, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void solicitudGerente(String role, String tipo, String existencias,
			String etapa, String fechaSol, String fechaEntreg, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}
}
