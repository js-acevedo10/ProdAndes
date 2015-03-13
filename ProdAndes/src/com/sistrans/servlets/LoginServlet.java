package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String username = request.getParameter("username");
		
		if(action.equals("login"))
		{
			if(username.contains("admin")) {
				response.sendRedirect("./pages/welcome/admin.html");
			} else if(username.contains("gerente")) {
				response.sendRedirect("./pages/welcome/gerente.html");
			} else if(username.contains("cliente")) {
				response.sendRedirect("./pages/welcome/user.html");
			} else if(username.contains("operario")) {
				response.sendRedirect("./pages/welcome/operario.html");
			} else if(username.contains("proveedor")) {
				response.sendRedirect("./pages/welcome/proveedor.html");
			} else {
				PrintWriter out = response.getWriter();
				out.println("Error, los usuarios temporales disponibles son:");
				out.println();
				out.println("			-admin");
				out.println("			-gerente");
				out.println("			-cliente");
				out.println("			-operario");
				out.println("			-proveedor");
				out.println();
				out.println("Recuerde que no es necesaria una contraseña.");
			}
		}
	}
}
