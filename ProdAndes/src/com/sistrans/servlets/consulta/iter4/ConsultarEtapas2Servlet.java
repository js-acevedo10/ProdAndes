package com.sistrans.servlets.consulta.iter4;

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
 * Servlet implementation class ConsultarEtapas2Servlet
 */
@WebServlet("/consulta/etapas2.html")
public class ConsultarEtapas2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultarEtapas2Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	private void procesarSolicitud(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter salida = response.getWriter();
		String action = request.getParameter("submit");
		printHeader(salida);
		if(action != null && !action.equals("no")) {
			String idMat = request.getParameter("idMateria");
			String tipoMat = request.getParameter("tipoMateria");
			String idPedido = request.getParameter("idPedido");
			String cantidad = request.getParameter("cantidad");
			printTables(salida, idMat, tipoMat, idPedido, cantidad);
		}
		printFooter(salida);
	}

	private void printHeader(PrintWriter salida) {
		salida.println("<!DOCTYPE html>");
		salida.println("<html>");
		salida.println("    <head>");
		salida.println("        <meta charset=\"utf-8\">");
		salida.println("        <title>Panel de Gerente ProdAndes</title>");
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
		salida.println("                    <a href=\"\" class=\"navbar-brand\" onclick=\"window.history.back()\" id=\"navBarLink\">");
		salida.println("                        <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>Volver al Dashboard");
		salida.println("                    </a>");
		salida.println("                 </li>");
		salida.println("            </ul>");
		salida.println("            <ul class=\"nav navbar-nav navbar-right\">");
		salida.println("                <li><a href=\"/ProdAndes/index.html\">Cerrar Sesion</a></li>");
		salida.println("            </ul>");
		salida.println("          </div><!-- /.container-fluid -->");
		salida.println("        </nav>");
		salida.println("        <div class=\"container\" id=\"search\">");
		salida.println("            <div class=\"jumbotron\" style=\"padding-bottom:15px\">");
		salida.println("               <h1>Consultar ejecucion etapas 2:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/consulta/etapas2.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <div class=\"row\">");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("            <label for=\"fecha-inicio\">Fecha de Inicio:</label>");
		salida.println("            <input type=\"date\" class=\"form-control input-lg\" id=\"fecha-inicio\" required>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("            <label for=\"fecha-final\">Fecha Final:</label>");
		salida.println("            <input type=\"date\" class=\"form-control input-lg\" id=\"fecha-final\" required>");
		salida.println("                        </div>");
		
		ArrayList<String> materiales = ProdAndesGerente.darInstancia().darTipoMaterias();
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"idMaterial\">Excluir ID del Material:</label>");
		salida.println("							<select class=\"form-control input-lg\" name=\"idMateria\">");
		salida.println("								<option value=\"null\" selected>ID Materia Prima</option>");
		if(materiales != null) {
			for(String e : materiales) {
				salida.println("								<option value=\"" + e + "\">" + e + "</option>");
			}
		} else {
			salida.println("								<option value=\"null\" selected disabled>No hay Materias Primas</option>");
		}
		salida.println("							</select>");
		salida.println("						</div>");
				
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"idMaterial\">Excluir Tipo de Material:</label>");
		salida.println("							<select class=\"form-control input-lg\" name=\"tipoMat\">");
		salida.println("								<option value=\"null\" selected>Tipo de Materiales</option>");
		salida.println("								<option value=\"Materia Prima\">Materia Prima</option>");
		salida.println("								<option value=\"Componente\">Componente</option>");
		salida.println("								<option value=\"Producto\">Producto</option>");
		salida.println("							</select>");
		salida.println("						</div>");
		
		ArrayList<String> pedidos = ProdAndesGerente.darInstancia().darTipoMaterias();
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"idPedido\">Excluir ID de Pedido:</label>");
		salida.println("							<select class=\"form-control input-lg\" name=\"idPedido\">");
		salida.println("								<option value=\"null\" selected>ID de Pedidos</option>");
		if(pedidos != null) {
			for(String e : pedidos) {
				salida.println("								<option value=\"" + e + "\">" + e + "</option>");
			}
		} else {
			salida.println("								<option value=\"null\" selected disabled>No hay Pedidos</option>");
		}
		salida.println("							</select>");
		salida.println("						</div>");
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"cantidad\">Excluir Cantidad:</label>");
		salida.println("							<input type=\"number\" class=\"form-control input-lg\" name=\"cantidad\" placeholder=\"Cantidad\">");
		salida.println("						</div>");
	}

	private void printTables(PrintWriter salida, String idMat, String tipoMat, String idPedido, String cantidad) {
		
	}

	private void printFooter(PrintWriter salida) {
		salida.println("                        </div>");
		salida.println("                        <div class=\"row\">");
		salida.println("                        <div class=\"col-md-1\">");
		salida.println("            <br><button type=\"submit\" class=\"btn btn-default btn-lg\" id=\"search-input\" placeholder= \"Buscar\">Buscar y Filtrar</button>");
		salida.println("                        </div>");
		salida.println("                        </div>");
		salida.println("                    </div>");
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
