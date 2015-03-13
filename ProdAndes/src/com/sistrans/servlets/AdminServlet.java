package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin.html")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		}
	}
	
	//WORLD METHODS
	
	public void realizarConsulta(String consulta, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		switch (consulta) {
		case "exist-mat":
			break;
		case "stage-more-mov":
			break;
		case "mat-info":
			break;
		case "oper-more-active":
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	public void realizarRegistro(String registro, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		switch (registro) {
		case "client":
			break;
		case "proveedor":
			break;
		case "operario":
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	//HTML METHODS

	private void printHeader(HttpServletResponse response, PrintWriter out){
		out.println("<!DOCTYPE html><html><head></title></head>");
	}
}
