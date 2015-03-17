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
	
	public void realizarConsulta(String consulta, HttpServletResponse response) throws IOException {
		switch (consulta) {
		case "exist-mat":
			response.sendRedirect("pages/gerente/search/existencias.html");
			break;
		case "stage-more-mov":
			break;
		case "mat-info":
			response.sendRedirect("pages/gerente/search/material.html");
			break;
		case "oper-more-active":
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	public void realizarRegistro(String registro, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		switch (registro) {
		case "order-placed":
			procesarEntrega(out);
			break;
		case "catalog-matprima":
			break;
		case "new-mat":
			response.sendRedirect("pages/gerente/registro/registromaterial.html");
			break;
		case "component":
			break;
		case "prod-place":
			break;
		case "prod-stage":
			break;
		case "new-prod":
			break;
		case "matprima-order":
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}

	private void procesarEntrega(PrintWriter out) {
		printEntregaHeader(out);
		printPedidos(out);
		printEntregaFooter(out);
	}
	
	public void printEntregaHeader(PrintWriter salida) {
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
		salida.println("                    <a href=\"/ProdAndes/pages/gerente/dashboard.html\" class=\"navbar-brand\" id=\"navBarLink\">");
		salida.println("                        <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>Volver al Dashboard");
		salida.println("                    </a>");
		salida.println("                 </li>");
		salida.println("            </ul>");
		salida.println("            <ul class=\"nav navbar-nav navbar-right\">");
		salida.println("                <li><a href=\"/ProdAndes/index.html\">Cerrar Sesión</a></li>");
		salida.println("            </ul>");
		salida.println("          </div><!-- /.container-fluid -->");
		salida.println("        </nav>");
		salida.println("        <div class=\"container\">");
		salida.println("            <div class=\"jumbotron\">");
		salida.println("            	<h1>Registre la Entrega de un Pedido:</h1>");
		salida.println("                <form class=\"form\" action=\"/ProdAndes/registro/entregapedidocliente.html\" method=\"get\">");
		salida.println("                <div class=\"form-group\">");
		salida.println("                		<label for=\"pedido\">Pedido entregado:</label>");
		salida.println("                        <select name=\"pedido\" id=\"pedido\" class=\"form-control\" required>");
	}
	
	public void printPedidos(PrintWriter salida) {
		ArrayList<String> pedidos = ProdAndesGerente.darInstancia().darPedidos();
		
		for(String pedido : pedidos) {
			String[] abc = pedido.split("*");
			String imp = abc[1];			
			salida.println("                            <option value=\"" + imp + "\">" + pedido.replaceAll("*", "") + "</option>");
		}
	}
	
	public void printEntregaFooter(PrintWriter salida) {
		salida.println("                        </select>");
		salida.println("                    </div>");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <label for=\"ff\">Fecha de entrega</label>");
		salida.println("                        <input type=\"date\" class=\"form-control\" name=\"ff\" placeholder=\"Fecha de Entrega\" required>");
		salida.println("                    </div>");
		salida.println("                    <button class=\"btn btn-default\" name=\"submit\" type=\"submit\" value=\"estacion\">Registrar Entrega</button>");
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
