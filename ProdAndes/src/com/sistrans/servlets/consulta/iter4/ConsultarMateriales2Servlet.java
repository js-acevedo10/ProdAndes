package com.sistrans.servlets.consulta.iter4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesAdmin;
import com.sistrans.mundo.Pedido;

/**
 * Servlet implementation class ConsultarMateriales2Servlet
 */
@WebServlet("/consulta/materiales2.html")
public class ConsultarMateriales2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int pag = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultarMateriales2Servlet() {
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
		if(action == null) {
			action = "si";
		}
		if(!action.equals("no")) {
			pag = Integer.parseInt(request.getParameter("p"));
			String id = request.getParameter("idMaterial");
			String tipo = request.getParameter("tipoMat");
			printTables(salida, id, tipo);
		} else {
			pag = 0;
			printFooter(salida);
		}
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
		salida.println("               <h1>Consultar Materiales 2:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/consulta/materiales2.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <div class=\"row\">");
		
		salida.println("						<div class=\"col-md-6\">");
		salida.println("							<label for=\"tipoMat\">Contiene tipo de Material:</label>");
		salida.println("							<select class=\"form-control input-lg\" name=\"tipoMat\" required>");
		salida.println("								<option value=\"MATERIAPRIMA\">Materia Prima</option>");
		salida.println("								<option value=\"COMPONENTE\">Componente</option>");
		salida.println("								<option value=\"PRODUCTO\">Producto</option>");
		salida.println("							</select>");
		salida.println("						</div>");
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"tipoMat\">ID del Material:</label>");
		salida.println("							<input type=\"text\" class=\"form-control input-lg\" name=\"idMaterial\" placeholder=\"ID del Material\" required>");
		salida.println("						</div>");
		salida.println("                        </div>");
		salida.println("					<input type=\"text\" name=\"p\" value=\"0\" style=\"display:none;\"");
		salida.println("                        <div class=\"row\">");
		salida.println("                        <div class=\"col-md-1\">");
		salida.println("            <br><button type=\"submit\" class=\"btn btn-default btn-lg\" id=\"search-input\" placeholder= \"Buscar\">Buscar y Filtrar</button>");
		salida.println("                        </div>");
		salida.println("                        </div>");
		salida.println("                    </div>");
		salida.println("                </form>");
		salida.println("            </div>");
	}

	private void printTables(PrintWriter salida, String id, String tipo) {	
		ArrayList<Pedido> etapas = ProdAndesAdmin.darInstancia().pedido2(tipo, pag, id);
			
		salida.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:black; padding-top:20px; margin-top:-10px;\">");
		
		if(etapas != null && etapas.size() != 0) {
			
			salida.println("                <table class=\"table table-hover\">");
			salida.println("                    <thead>");
			salida.println("                        <tr>");
			salida.println("                            <th>ID</th>");
			salida.println("                            <th>Estado Pago</th>");
			salida.println("                            <th>ID Cliente</th>");
			salida.println("                            <th>Fecha Creacion</th>");
			salida.println("                            <th>Deadline</th>");
			salida.println("                            <th>#Componente</th>");
			salida.println("                            <th>#Producto</th>");
			salida.println("                            <th>#Materia Prima</th>");
			salida.println("                            <th>Costo</th>");
			salida.println("                        </tr>");
			salida.println("                    </thead>");
			salida.println("                    <tbody>");
			
			for(Pedido et : etapas) {
				salida.println("                        <tr>");
				salida.println("							<td>" + et.getId() + "</td>");
				salida.println("							<td>" + et.getEstadoPago() + "</td>");
				salida.println("							<td>" + et.getIdCliente() + "</td>");
				salida.println("							<td>" + et.getFechaCreacion() + "</td>");
				salida.println("							<td>" + et.getDeadLine() + "</td>");
				salida.println("							<td>" + et.getNumComponente() + "</td>");
				salida.println("							<td>" + et.getNumProducto() + "</td>");
				salida.println("							<td>" + et.getNumMateriaPrima() + "</td>");
				salida.println("							<td>" + et.costo + "</td>");
				salida.println("                        </tr>");
			}
			
			salida.println("                    </tbody>");
			salida.println("                </table>");
			
		} else {
			salida.println("						<h1>No hay</h1>");
		}	
		
		salida.println("            </div>");
		
		printFooterPag(salida, tipo, id, etapas.size());
	}
	
	private void printFooterPag(PrintWriter salida, String tipoMat, String id, int tam) {
		pag += 1;
		salida.println("        </div>");
		salida.println("			<form action=\"/ProdAndes/consulta/pedidos2.html\" method=\"get\">");
		salida.println("				<input name=\"tipoMat\" value=\"" + tipoMat + "\" style=\"display:none;\">");
		salida.println("				<input name=\"idMaterial\" value=\"" + id + "\" style=\"display:none;\">");
		salida.println("				<input name=\"p\" value=\"" + pag + "\" style=\"display:none;\">");
		if(tam == 500) {
			salida.println("				<button class=\"btn btn-default btn-lg\" type=\"submit\" name=\"submit\" value=\"si\">Siguiente Pagina</button>");
		} else {
			salida.println("				<button disabled class=\"btn btn-default btn-lg\" type=\"submit\" name=\"submit\" value=\"si\">Siguiente Pagina</button>");
		}
		salida.println("			</form>");
		salida.println("    </body>");
		salida.println("</html>");
	}

	private void printFooter(PrintWriter salida) {
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
