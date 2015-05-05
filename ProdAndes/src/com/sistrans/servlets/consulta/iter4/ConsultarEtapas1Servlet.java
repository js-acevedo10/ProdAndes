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
import com.sistrans.fachada.ProdAndesGerente;
import com.sistrans.mundo.EstaciondeProducto;
import com.sistrans.mundo.EtapadeProduccion;

/**
 * Servlet implementation class ConsultarEtapas1Servlet
 */
@WebServlet("/consulta/etapas1.html")
public class ConsultarEtapas1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int pag;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultarEtapas1Servlet() {
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
		System.out.println(action);
		if(action == null)
			action = "si";
		if(!action.equals("no")) {
			pag = Integer.parseInt(request.getParameter("p"));
			String fechIn = request.getParameter("fecha-inicio");
			String fechFin = request.getParameter("fecha-final");
			String idMat = request.getParameter("idMaterial");
			String tipoMat = request.getParameter("tipoMat");
			String idPedido = request.getParameter("idPedido");
			String cantidad = request.getParameter("cantidad");
			System.out.println("idmat " + idMat);
			System.out.println("tipoMat " + tipoMat);
			System.out.println("idpedido " + idPedido);
			System.out.println("cantidad " + cantidad);
			printTables(fechIn, fechFin, salida, idMat, tipoMat, idPedido, cantidad);
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
		salida.println("               <h1>Consultar ejecucion etapas 1:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/consulta/etapas1.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <div class=\"row\">");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("            <label for=\"fecha-inicio\">Fecha de Inicio:</label>");
		salida.println("            <input type=\"date\" class=\"form-control input-lg\" name=\"fecha-inicio\" required>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("            <label for=\"fecha-final\">Fecha Final:</label>");
		salida.println("            <input type=\"date\" class=\"form-control input-lg\" name=\"fecha-final\" required>");
		salida.println("                        </div>");
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"idMaterial\">Incluir ID del Material:</label>");
		salida.println("							<input type=\"text\" class=\"form-control input-lg\" name=\"idMaterial\" placeholder=\"Id del material\">");
		salida.println("						</div>");
				
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"idMaterial\">Incluir Tipo de Material:</label>");
		salida.println("							<select class=\"form-control input-lg\" name=\"tipoMat\">");
		salida.println("								<option value=\"null\" selected>Tipo de Materiales</option>");
		salida.println("								<option value=\"MATERIAPRIMA\">Materia Prima</option>");
		salida.println("								<option value=\"COMPONENTE\">Componente</option>");
		salida.println("								<option value=\"PRODUCTO\">Producto</option>");
		salida.println("							</select>");
		salida.println("						</div>");
		
		ArrayList<String> pedidos = ProdAndesGerente.darInstancia().darPedidos();
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"idPedido\">Incluir ID de Pedido:</label>");
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
		salida.println("							<label for=\"cantidad\">Incluir Cantidad:</label>");
		salida.println("							<input type=\"number\" class=\"form-control input-lg\" name=\"cantidad\">");
		salida.println("						</div>");
		salida.println("					<input type=\"text\" name=\"p\" value=\"0\" style=\"display:none;\"");
		salida.println("                        </div>");
		salida.println("                        <div class=\"row\">");
		salida.println("                        <div class=\"col-md-1\">");
		salida.println("            <br><button type=\"submit\" class=\"btn btn-default btn-lg\" id=\"search-input\" placeholder= \"Buscar\">Buscar y Filtrar</button>");
		salida.println("                        </div>");
		salida.println("                        </div>");
		salida.println("                    </div>");
		salida.println("                </form>");
		salida.println("            </div>");
	}

	private void printTables(String fechIn, String fechFin, PrintWriter salida, String idMat, String tipoMat, String idPedido, String cantidadS) {
		String idCom = null;
		String idMP = null;
		String idProd = null;
		if(tipoMat != null) {
			switch (tipoMat) {
			case "MATERIAPRIMA":
				idMP = idMat;
				break;
			case "COMPONENTE":
				idCom = idMat;
				break;
			case "PRODUCTO":
				idProd = idMat;
				break;
			default:
				break;
			}
		}
		int cantidad = 0;
		try {
			cantidad = Integer.parseInt(cantidadS);
		} catch (Exception e ) {
			
		}
		
		
		ArrayList<EstaciondeProducto> etapas = ProdAndesAdmin.darInstancia().etapaDeProduccion1(fechIn, fechFin, pag, idCom, idMP, idProd, cantidad, null);
			
		salida.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:black; padding-top:20px; margin-top:-10px;\">");
		
		if(etapas != null && etapas.size() != 0) {
			
			salida.println("                <table class=\"table table-hover\">");
			salida.println("                    <thead>");
			salida.println("                        <tr>");
			salida.println("                            <th>Codigo</th>");
			salida.println("                            <th>Tiempo Realizacion</th>");
			salida.println("                            <th>#Componente</th>");
			salida.println("                            <th>#Materia Prima</th>");
			salida.println("                            <th>#Producto</th>");
			salida.println("                        </tr>");
			salida.println("                    </thead>");
			salida.println("                    <tbody>");
			
			for(EstaciondeProducto et : etapas) {
				salida.println("                        <tr>");
				salida.println("							<td>" + et.getCodigo() + "</td>");
				salida.println("							<td>" + et.getTiempoRealizacion() + "</td>");
				salida.println("							<td>" + et.getNumComponente() + "</td>");
				salida.println("							<td>" + et.getNumMateriaPrima() + "</td>");
				salida.println("							<td>" + et.getNumProducto() + "</td>");
				salida.println("                        </tr>");
			}
			
			salida.println("                    </tbody>");
			salida.println("                </table>");
			
		} else {
			salida.println("						<h1>No hay</h1>");
		}	
		
		salida.println("            </div>");
		
		printFooterPag(salida, fechIn, fechFin, idMat, tipoMat, idPedido, cantidad, etapas.size());
	}
	
	private void printFooterPag(PrintWriter salida, String fin, String ffin, String idMaterial, String tipoMat, String idPedido, int cantidad, int tam) {
		pag += 1;
		salida.println("			<form action=\"/ProdAndes/consulta/pedidos2.html\" method=\"get\">");
		salida.println("				<input name=\"fecha-inicio\" value=\"" + fin + "\" style=\"display:none;\">");
		salida.println("				<input name=\"fecha-final\" value=\"" + ffin + "\" style=\"display:none;\">");
		salida.println("				<input name=\"idMaterial\" value=\"" + idMaterial + "\" style=\"display:none;\">");
		salida.println("				<input name=\"tipoMat\" value=\"" + tipoMat + "\" style=\"display:none;\">");
		salida.println("				<input name=\"idPedido\" value=\"" + idPedido + "\" style=\"display:none;\">");
		salida.println("				<input name=\"cantidad\" value=\"" + cantidad + "\" style=\"display:none;\">");
		salida.println("				<input name=\"p\" value=\"" + pag + "\" style=\"display:none;\">");
		if(tam == 500) {
			salida.println("				<button class=\"btn btn-default btn-lg\" type=\"submit\" name=\"submit\" value=\"si\">Siguiente Pagina</button>");
		} else {
			salida.println("				<button disabled class=\"btn btn-default btn-lg\" type=\"submit\" name=\"submit\" value=\"si\">Siguiente Pagina</button>");
		}
		salida.println("			</form>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}

	private void printFooter(PrintWriter salida) {
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
