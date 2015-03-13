package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GerenteServlet
 */
@WebServlet("/gerente.html")
public class GerenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenteServlet() {
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
		PrintWriter out = response.getWriter();
		printHeader(response, out);
		
		String action = request.getParameter("submit");
		String[] uri = action.split("-", 2);
		String requestType = uri[0];
		String requestDetails = uri[1];
		
		if(requestType.equals("consult")) {
			realizarConsulta(requestDetails, out);			
		} else if(requestType.equals("register")) {
			realizarRegistro(requestDetails, out);			
		}
	}
	
	//WORLD METHODS
	
	public void realizarConsulta(String consulta, PrintWriter out) {
		
	}
	
	public void realizarRegistro(String registro, PrintWriter out) {
		
	}
	
	//HTML METHODS

	private void printHeader(HttpServletResponse response, PrintWriter out){
		out.println("<!DOCTYPE html><html><head></title></head>");
	}
}
