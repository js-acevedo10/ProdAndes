package com.sistrans.servlets.iter5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesAdmin;
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Pedido;
import com.sistrans.mundo.Producto;

/**
 * Servlet implementation class ConsultaMaterialesFusionServlet
 */
@WebServlet("/consulta/fusion/materiales.html")
public class ConsultaMaterialesFusionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaMaterialesFusionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    int pag = 0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
			String tipo = request.getParameter("tipoMat");
			String fechaIn = request.getParameter("fechaIn");
			String fechaFin = request.getParameter("fechaFin");
			printTables(salida, tipo, fechaIn, fechaFin);
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
		salida.println("               <h1>Consultar Materiales Fusion:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/consulta/fusion/materiales.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <div class=\"row\">");
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"tipoMat\">Tipo de Material:</label>");
		salida.println("							<select class=\"form-control input-lg\" name=\"tipoMat\" required>");
		salida.println("								<option value=\"MATERIAPRIMA\">Materia Prima</option>");
		salida.println("								<option value=\"COMPONENTE\">Componente</option>");
		salida.println("								<option value=\"PRODUCTO\">Producto</option>");
		salida.println("							</select>");
		salida.println("						</div>");
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"fechaIn\">Fecha Inicial:</label>");
		salida.println("							<input type=\"date\" class=\"form-control input-lg\" name=\"fechaIn\" placeholder=\"Fecha De Inicio\" required>");
		salida.println("						</div>");
		
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"fechaFin\">Fecha Final:</label>");
		salida.println("							<input type=\"date\" class=\"form-control input-lg\" name=\"fechaFin\" placeholder=\"Fecha Final\" required>");
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
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	private void printTables(PrintWriter salida, String tipo, String fechaIn, String fechaFin) {
		// TODO Auto-generated method stub
		ArrayList respuesta = null;
		
		salida.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:black; padding-top:20px; margin-top:-10px;\">");
		
			if(tipo.equals("MATERIAPRIMA")) {
				ArrayList<MateriaPrima> etapas = ProdAndesAdmin.darInstancia().fusionMateriales(tipo, fechaIn, fechaFin);
				if( etapas != null && etapas.size() != 0) {
					respuesta = etapas;
					salida.println("                <table class=\"table table-hover\">");
					salida.println("                    <thead>");
					salida.println("                        <tr>");
					salida.println("                            <th>ID</th>");
					salida.println("                            <th>Nombre</th>");
					salida.println("                            <th>Toneladas</th>");
					salida.println("                        </tr>");
					salida.println("                    </thead>");
					salida.println("                    <tbody>");
					
					for(MateriaPrima et : etapas) {
						salida.println("                        <tr>");
						salida.println("							<td>" + et.getId() + "</td>");
						salida.println("							<td>" + et.getNombre() + "</td>");
						salida.println("							<td>" + et.getTonelada() + "</td>");
						salida.println("                        </tr>");
					}
					
					salida.println("                    </tbody>");
					salida.println("                </table>");
				} else {
					respuesta = new ArrayList<String>();
					salida.println("<h1>No hay Materiales para mostrar.</h1>");
				}
			} else if(tipo.equals("COMPONENTE")) {
				ArrayList<Componente> etapas = ProdAndesAdmin.darInstancia().fusionMateriales(tipo, fechaIn, fechaFin);
				if( etapas != null && etapas.size() != 0) {
					respuesta = etapas;
					salida.println("                <table class=\"table table-hover\">");
					salida.println("                    <thead>");
					salida.println("                        <tr>");
					salida.println("                            <th>ID</th>");
					salida.println("                            <th>Nombre</th>");
					salida.println("                            <th># Inventario</th>");
					salida.println("                            <th>Unidad Medida</th>");
					salida.println("                        </tr>");
					salida.println("                    </thead>");
					salida.println("                    <tbody>");
					
					for(Componente et : etapas) {
						salida.println("                        <tr>");
						salida.println("							<td>" + et.getId() + "</td>");
						salida.println("							<td>" + et.getNombre() + "</td>");
						salida.println("							<td>" + et.getNumInventario() + "</td>");
						salida.println("							<td>" + et.getUnidadMedida() + "</td>");
						salida.println("                        </tr>");
					}
					
					salida.println("                    </tbody>");
					salida.println("                </table>");
				} else {
					respuesta = new ArrayList<String>();
					salida.println("<h1>No hay Materiales para mostrar.</h1>");
				}
			} else if(tipo.equals("PRODUCTO")) {
				ArrayList<Producto> etapas = ProdAndesAdmin.darInstancia().fusionMateriales(tipo, fechaIn, fechaFin);
				if( etapas != null && etapas.size() != 0) {
					respuesta = etapas;
					salida.println("                <table class=\"table table-hover\">");
					salida.println("                    <thead>");
					salida.println("                        <tr>");
					salida.println("                            <th>ID</th>");
					salida.println("                            <th>Nombre</th>");
					salida.println("                            <th>Costo Venta</th>");
					salida.println("                            <th># Inventario</th>");
					salida.println("                        </tr>");
					salida.println("                    </thead>");
					salida.println("                    <tbody>");
					
					for(Producto et : etapas) {
						salida.println("                        <tr>");
						salida.println("							<td>" + et.getId() + "</td>");
						salida.println("							<td>" + et.getNombre() + "</td>");
						salida.println("							<td>" + et.getCostoVenta() + "</td>");
						salida.println("							<td>" + et.getnumInventario() + "</td>");
						salida.println("                        </tr>");
					}
					
					salida.println("                    </tbody>");
					salida.println("                </table>");
				} else {
					respuesta = new ArrayList<String>();
					salida.println("<h1>No hay Materiales para mostrar.</h1>");
				}
			}
		salida.println("            </div>");		
		printFooterPag(salida, tipo, fechaIn, fechaFin, respuesta.size());
	}

	private void printFooterPag(PrintWriter salida, String tipoMat, String fechaIn, String fechaFin, int tam) {
		pag += 1;
		salida.println("			<form action=\"/ProdAndes/consulta/fusion/materiales.html\" method=\"get\">");
		salida.println("				<input name=\"tipoMat\" value=\"" + tipoMat + "\" style=\"display:none;\">");
		salida.println("				<input name=\"fechaIn\" value=\"" + fechaIn + "\" style=\"display:none;\">");
		salida.println("				<input name=\"fechaFin\" value=\"" + fechaFin + "\" style=\"display:none;\">");
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
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
