package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.html")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
	
	public void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String action = request.getParameter("submit");
		String[] uri = action.split("-", 2);
		String requestType = uri[0];
		String requestDetails = uri[1];
		
		if(requestType.equals("consult")) {
			realizarConsulta(requestDetails, response);			
		} else if(requestType.equals("register")) {
			realizarRegistro(requestDetails, response);			
		} else if(requestType.equals("cancel")) {
			realizarCancelacion(requestDetails, response);
		}
	}
	
	//WORLD METHODS
	
	public void realizarConsulta(String consulta, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		switch (consulta) {
		case "exist-mat":
			response.sendRedirect("pages/user/search/existencias.html");
			break;
		case "mat-info":
			response.sendRedirect("pages/user/search/material.html");
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	public void realizarRegistro(String registro, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		switch (registro) {
		case "order":
			response.sendRedirect("pages/user/registro/pedido.html");
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	public void realizarCancelacion(String cancelacion, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		switch (cancelacion) {
			case "order-prod":
				break;
			default:
				response.sendRedirect("pages/error/404.html");
				break;
		}
	}
}
