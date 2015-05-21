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
import com.sistrans.mundo.Producto;

/**
 * Servlet implementation class RegistroPedidoProductosFusionServlet
 */
@WebServlet("registro/fusion/pedidoProductos.html")
public class RegistroPedidoProductosFusionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroPedidoProductosFusionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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

		if(request.getParameter("prod1") != null) {
			String prod2 = "", prod3 = "", cantidad2 = "", cantidad3 = "";
			String prod1 = request.getParameter("prod1");
			String cantidad1 = request.getParameter("cantidad1");
			if(request.getParameter("prod2") != null)
			{
				prod2 = request.getParameter("prod2");
				cantidad2 = request.getParameter("cantidad2");
			}
			if(request.getParameter("prod3") != null)
			{
				prod3 = request.getParameter("prod3");
				cantidad3 = request.getParameter("cantidad3");
			}
			
//			String id = ProdAndesAdmin.darInstancia().fusionPedido(prod1, cantidad1, prod2, cantidad2, prod3, cantidad3);
//			
//			if( id == null || id.equals("error") || id.equals("0")) {
//				salida.println("<h1>ERROR</h1>");
//			} else {
//				salida.println("<h1>Su pedido se realizo con exito, este es el numero de guia: " + id + "</h1>");
//			}
			
		} else {
			printHeader(salida);
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
		salida.println("               <h1>Hacer un Pedido Fusion:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/registro/fusion/pedidoProductos.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <div class=\"row\">");
		
		salida.println("						<div class=\"col-md-8\">");
		salida.println("							<label for=\"prod1\">ID Producto 1</label>");
		salida.println("							<input type=\"text\" class=\"form-control input-lg\" name=\"prod1\" placeholder=\"ID Producto 1\" required>");
		salida.println("						</div>");
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"cantidad1\">Cantidad</label>");
		salida.println("							<input type=\"number\" class=\"form-control input-lg\" name=\"cantidad1\" placeholder=\"Cantidad\" required>");
		salida.println("						</div>");
		
		salida.println("						<div class=\"col-md-8\">");
		salida.println("							<label for=\"prod2\">ID Producto 2</label>");
		salida.println("							<input type=\"text\" class=\"form-control input-lg\" name=\"prod2\" placeholder=\"ID Producto 2\">");
		salida.println("						</div>");
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"cantidad2\">Cantidad</label>");
		salida.println("							<input type=\"number\" class=\"form-control input-lg\" name=\"cantidad2\" placeholder=\"Cantidad\">");
		salida.println("						</div>");
		
		salida.println("						<div class=\"col-md-8\">");
		salida.println("							<label for=\"prod3\">ID Producto 3</label>");
		salida.println("							<input type=\"text\" class=\"form-control input-lg\" name=\"prod3\" placeholder=\"ID Producto 3\">");
		salida.println("						</div>");
		salida.println("						<div class=\"col-md-4\">");
		salida.println("							<label for=\"cantidad3\">Cantidad</label>");
		salida.println("							<input type=\"number\" class=\"form-control input-lg\" name=\"cantidad3\" placeholder=\"Cantidad\">");
		salida.println("						</div><br><br>");
		
		salida.println("                        <div class=\"col-md-12\">");
		salida.println("            				<button type=\"submit\" class=\"btn btn-default btn-lg\" id=\"search-input\" placeholder= \"Buscar\">Buscar y Filtrar</button>");
		salida.println("                        </div>");
		salida.println("                        </div>");
		salida.println("                    </div>");
		salida.println("                </form>");
	}

	private void printFooter(PrintWriter salida) {
		salida.println("        </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
