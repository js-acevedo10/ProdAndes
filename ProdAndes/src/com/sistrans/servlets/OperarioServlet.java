package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesAdmin;
import com.sistrans.fachada.ProdAndesGerente;

/**
 * Servlet implementation class OperarioServlet
 */
@WebServlet("/operario.html")
public class OperarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperarioServlet() {
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
			response.sendRedirect("pages/operario/search/existencias.html");
			break;
		case "mat-info":
			response.sendRedirect("pages/operario/search/material.html");
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	public void realizarRegistro(String registro, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		switch (registro) {
		case "exec-stage-prod":
			procesarRegistroEtapa(out);
			break;
		default:
			response.sendRedirect("pages/error/404.html");
			break;
		}
	}
	
	public void procesarRegistroEtapa(PrintWriter out) {
		printRegistroEtapaHeader(out);
		printRegistroEtapaOperarios(out);
		printRegistroEtapaEtapas(out);
		printRegistroEtapaFooter(out);
	}
	
	public void printRegistroEtapaHeader(PrintWriter salida) {
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
		salida.println("                    <a href=\"/ProdAndes/pages/operario/dashboard.html\" class=\"navbar-brand\" id=\"navBarLink\">");
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
		salida.println("            <h1>Registra la ejecucion de una etapa:</h1>");
		salida.println("                <form class=\"form\" action=\"/ProdAndes/ejecucion/etapa.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                    	<label for=\"idOper\">ID del operario</label>");
		salida.println("                    	<select name=\"idOper\" id=\"idOper\" class=\"form-control\" required>");
	}
	public void printRegistroEtapaOperarios(PrintWriter salida) {
		ArrayList<String> operarios = ProdAndesGerente.darInstancia().darOperarios();
		if(operarios.size() == 0) {
			salida.println("                        	<option value=\"none\" selected disabled>No hay operarios disponibles</option>");
		} else {
			for(String o : operarios) {
				salida.println("                        	<option value=\"" + o.toLowerCase() + "\">" + o + "</option>");
			}
		}
		salida.println("                    	</select>");
		salida.println("                    </div>");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                    	<label for=\"idEtapa\">ID de la etapa</label>");
		salida.println("                    	<select name=\"idEtapa\" id=\"idEtapa\" class=\"form-control\" required>");
	}
	public void printRegistroEtapaEtapas(PrintWriter salida) {
		ArrayList<String> etapas = ProdAndesGerente.darInstancia().darEtapas();
		if(etapas.size() == 0) {
			salida.println("                        	<option value=\"none\" selected disabled>No hay etapas disponibles</option>");
		} else {
			for(String o : etapas) {
				salida.println("                        	<option value=\"" + o.toLowerCase() + "\">" + o+ "</option>");
			}
		}
	}
	public void printRegistroEtapaFooter(PrintWriter salida) {
		salida.println("                    	</select>");
		salida.println("                    </div>");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <label for=\"fi\">Fecha Inicio</label>");
		salida.println("                        <input class=\"form-control\" type=\"date\" name=\"fi\" placeholder=\"Fecha de Inicio\" required>");
		salida.println("                    </div>");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <label for=\"ft\">Fecha de Terminacion</label>");
		salida.println("                        <input class=\"form-control\" type=\"date\" name=\"ft\" placeholder=\"Fecha de Terminación\" required>");
		salida.println("                    </div>");
		salida.println("                    <button class=\"btn btn-default\" name=\"submit\" type=\"submit\" value=\"estacion\">Registrar</button>");
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
