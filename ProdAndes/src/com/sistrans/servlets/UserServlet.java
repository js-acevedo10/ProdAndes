package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesGerente;

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
			pedido(out);
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	public void realizarCancelacion(String cancelacion, HttpServletResponse response) throws IOException {
		switch (cancelacion) {
			case "order-prod":
				break;
			default:
				response.sendRedirect("pages/error/404.html");
				break;
		}
	}
	
	public void pedido(PrintWriter out) {
		printPedidoHeader(out);
		printProductos(out, 0);
		printProductos(out, 1);
		printProductos(out, 2);
		printProductos(out, 3);
		printPedidoFooter(out);
	}
	
	public void printPedidoHeader(PrintWriter salida) {
		salida.println("<!DOCTYPE html>");
		salida.println("<html>");
		salida.println("    <head>");
		salida.println("        <meta charset=\"utf-8\">");
		salida.println("        <title>Panel ProdAndes</title>");
		salida.println("        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
		salida.println("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>");
		salida.println("        <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js\"></script>");
		salida.println("        <link rel=\"stylesheet\" href=\"/ProdAndes/style/bootstrap.min.css\">");
		salida.println("        <link type=\"text/css\" href=\"/ProdAndes/style/custom-bootstrap-override.css\" rel=\"stylesheet\">");
		salida.println("        <!--[if lt IE 9]>");
		salida.println("          <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>");
		salida.println("          <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>");
		salida.println("        <![endif]-->");
		salida.println("    </head>");
		salida.println("    <body>");
		salida.println("        <nav class=\"navbar navbar-default navbar-fixed-top\">");
		salida.println("          <div class=\"container-fluid\">");
		salida.println("            <!-- Brand and toggle get grouped for better mobile display -->");
		salida.println("            <ul class=\"nav navbar-nav navbar-left\">");
		salida.println("                 <li>");
		salida.println("                    <a href=\"/ProdAndes/pages/user/dashboard.html\" class=\"navbar-brand\" id=\"navBarLink\">");
		salida.println("                        <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>Volver al Dashboard");
		salida.println("                    </a>");
		salida.println("                 </li>");
		salida.println("            </ul>");
		salida.println("            <ul class=\"nav navbar-nav navbar-right\">");
		salida.println("                <li><a href=\"/ProdAndes/index.html\">Cerrar Sesion</a></li>");
		salida.println("            </ul>");
		salida.println("          </div><!-- /.container-fluid -->");
		salida.println("        </nav>");
		salida.println("        <div class=\"container\">");
		salida.println("            <div class=\"jumbotron\">");
		salida.println("				<h1>Realiza un pedido:</h1>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/registro/pedido/usuario.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
	}
	
	public void printProductos(PrintWriter salida, int indice) {
		ArrayList<String> prods = ProdAndesGerente.darInstancia().darProductos();
		if(prods.size() == 0) {
			salida.println("						<div class=\"col-md-9\">");
			salida.println("                        	<select class=\"form-control input-lg\" id=\"search-input\" name=\"none\">");
			salida.println("                                	<option value=\"\" selected disabled style=\"display: none\">No hay productos disponibles</option>");
			salida.println("                        	</select><br>");
			salida.println("						</div>");
			salida.println("						<div class=\"col-md-3\">");
			salida.println("							<input type=\"number\" class=\"form-control input-lg\" name=\"none\" placeholder=\"None\">");
			salida.println("                    	</div>");
		} else {
			String required = "";
			if(indice == 0) required = "required";
			salida.println("						<div class=\"col-md-9\">");
			salida.println("                        	<select class=\"form-control input-lg\" id=\"search-input\" name=\"prod" + (indice+1) + "\"" + required + ">");
			salida.println("                                	<option value=\"\" selected disabled style=\"display: none\">Selecciona el Producto " + (indice+1) + "</option>");
			for(int i = 0; i < 100; i++) {
				String prod = prods.get(i);
				salida.println("                                <option value=\"" + prod.toLowerCase().replaceAll(" ", "") +  "\">" + prod + "</option>");
			}
			salida.println("                        	</select><br>");
			salida.println("						</div>");
			salida.println("						<div class=\"col-md-3\">");
			salida.println("							<input type=\"number\" class=\"form-control input-lg\" name=\"c"+(indice+1)+"\" placeholder=\"Cantidad\"" +required + ">");
			salida.println("                    	</div>");
		}
		
	}
	
	public void printPedidoFooter(PrintWriter salida) {
		salida.println("						<div class=\"col-md-12\">");
		salida.println("                        	<label for=\"ff\">Fecha de entrega</label>");
		salida.println("                        	<input type=\"date\" id=\"ff\" class=\"form-control\" name=\"ff\" placeholder=\"Fecha de Entrega\"><br>");
		salida.println("                    		<button class=\"btn btn-default btn-lg\" name=\"submit\" type=\"submit\" value=\"producto\">Pedir</button>");
		salida.println("                    	</div>");
		salida.println("                    </div>");	
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
