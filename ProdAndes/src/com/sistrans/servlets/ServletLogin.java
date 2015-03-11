package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndes;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet
(
	urlPatterns="/loginProdAndes.html"
)
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
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
		
		switch (action) {
		case "login":
			
			printHeader(response, out);
			out.println("<body><h1>Loggeado</h1></body>");
			
			break;
		case "logout":
			
			break;
		default:
			printHeader(response, out);
			out.println("<h1>Error</h1>");
			break;
		}
		
	}

	private void printHeader(HttpServletResponse response, PrintWriter out){
		out.println("<!DOCTYPE html><html><head></title></head>");
	}
}
