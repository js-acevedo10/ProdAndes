package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

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
		PrintWriter out = response.getWriter();
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
			realizarConsultaOperMasActiv(out);
			break;
		case "pedidos":
			realizarConsultaPedidos(out);
			break;
		case "clientes":
			realizarConsultaClientes(out);
			break;
		case "proveedores":
			realizarConsultaProveedores(out);
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
		if(pedidos.size() == 0) {
			salida.println("                            <option value=\"none\" disabled selected>No hay pedidos disponibles</option>");
		} else {
			for(String pedido : pedidos) {
				System.out.println(pedido);
				String[] abc = pedido.split("&");
				String imp = abc[1];			
				salida.println("                            <option value=\"" + imp + "\">" + pedido.replaceAll("&", "") + "</option>");
			}
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
	
	private void realizarConsultaPedidos(PrintWriter out) {
		printHeaderPedidos(out);		
		printFooterPedidos(out);
	}
	
	private void printHeaderPedidos(PrintWriter salida) {
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
		salida.println("                <li><a href=\"../../index.html\">Cerrar Sesion</a></li>");
		salida.println("            </ul>");
		salida.println("          </div><!-- /.container-fluid -->");
		salida.println("        </nav>");
		salida.println("        <div class=\"container\">");
		salida.println("            <div class=\"jumbotron\" style=\"padding-top:10px; margin-bottom:-10px;\">");
		salida.println("               <h1>Consulta de Pedidos ProdAndes:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/consultar/pedido.html\" method=\"put\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"idPedido\">ID del Pedido:</label>");
		salida.println("                            <select class=\"form-control\" name=\"idPedido\">");	
		printIDPedidos(salida);
	}

	private void printIDPedidos(PrintWriter salida) {
		ArrayList<String> pedidos = ProdAndesGerente.darInstancia().darPedidos();
		if(pedidos == null || pedidos.size() == 0) {
			salida.println("                            <option value=\"none\" disabled>No hay pedidos disponibles</option>");
		} else {
			Collections.sort(pedidos);
			salida.println("                                <option value=\"\" selected>Ninguno</option>");
			for(String e : pedidos) {
				String idped = e.split("&")[1];
				salida.println("                            <option value=\"" + idped + "\">" + idped +"</option>");
			}	
		}
		salida.println("                            </select>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"idCliente\">ID del Cliente:</label>");
		salida.println("                            <select class=\"form-control\" name=\"idCliente\">");
		printIDClientes(salida);
	}

	private void printIDClientes(PrintWriter salida) {
		ArrayList<String> clientes = ProdAndesGerente.darInstancia().darClientes();
		if(clientes.size() == 0) {
			salida.println("                            <option value=\"none\" disabled>No hay clientes disponibles</option>");
		} else {
			Collections.sort(clientes);
			salida.println("                                <option value=\"\" selected>Ninguno</option>");
			for(String e : clientes) {
				salida.println("                            <option value=\"" + e + "\">" + e +"</option>");
			}	
		}
		salida.println("                            </select>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"estadoPago\">Estado del Pago:</label>");
		salida.println("                            <select class=\"form-control\" name=\"estadoPago\">");
		salida.println("                                <option value=\"\" selected>Ninguno</option>");
		salida.println("                                <option value=\"noPago\">Sin Cancelar</option>");
		salida.println("                                <option value=\"Pago\">Cancelado</option>");
		salida.println("                            </select>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"fechaCreacion\">Fecha de Creacion:</label>");
		salida.println("                            <input type=\"date\" class=\"form-control\" name=\"fechaCreacion\" placeholder=\"Fecha de Creación\">");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"fechaCreacion\">Fecha de Recibido:</label>");
		salida.println("                            <input type=\"date\" class=\"form-control\" name=\"fechaRecibido\" placeholder=\"Fecha Recibido\">");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"fechaCreacion\">Fecha de Entrega:</label>");
		salida.println("                            <input type=\"date\" class=\"form-control\" name=\"deadline\" placeholder=\"Fecha de Entrega\">");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"idMateriaPrima\">ID de Materia Prima:</label>");
		salida.println("                            <select class=\"form-control\" name=\"idMateriaPrima\">");
		printMaterias(salida);
	}

	private void printMaterias(PrintWriter salida) {
		ArrayList<String> mats = ProdAndesGerente.darInstancia().darMateriasPrimas();
		Collections.sort(mats);
		if(mats == null || mats.size() == 0) {
			salida.println("                            <option value=\"none\" disabled>No hay materias disponibles</option>");
		} else {
			salida.println("                                <option value=\"\" selected>Ninguno</option>");
			for(String e : mats) {
				salida.println("                            <option value=\"" + e.toLowerCase() + "\">" + e +"</option>");
			}	
		}
		salida.println("                            </select>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"idComponente\">ID del Componente:</label>");
		salida.println("                            <select class=\"form-control\" name=\"idComponente\">");
		printComponentes(salida);
	}

	private void printComponentes(PrintWriter salida) {
		ArrayList<String> comps = ProdAndesGerente.darInstancia().darComponentes();
		if(comps == null || comps.size() == 0) {
			salida.println("                            <option value=\"none\" disabled>No hay componentes disponibles</option>");
		} else {
			salida.println("                                <option value=\"\" selected>Ninguno</option>");
			for(String e : comps) {
				salida.println("                            <option value=\"" + e.toLowerCase() + "\">" + e +"</option>");
			}	
		}
		salida.println("                            </select>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"idProducto\">ID del Producto:</label>");
		salida.println("                            <select class=\"form-control\" name=\"idProducto\">");
		printProductos(salida);
	}

	private void printProductos(PrintWriter salida) {
		ArrayList<String> productos = ProdAndesGerente.darInstancia().darIDProductos();
		if(productos == null || productos.size() == 0) {
			salida.println("                            <option value=\"none\" disabled>No hay productos disponibles</option>");
		} else {
			salida.println("                                <option value=\"\" selected>Ninguno</option>");
			for(String e : productos) {
				salida.println("                            <option value=\"" + e.toLowerCase() + "\">" + e +"</option>");
			}	
		}
		salida.println("                            </select>");
		salida.println("                        </div>");
		salida.println("                        <br><br><br>");
		salida.println("                        <div class=\"col-md-12\" style=\"text-align:center;\">");
		salida.println("                        <br>");
		salida.println("                            <input class=\"btn btn-default btn-lg\" type=\"submit\" name=\"submit\" id=\"submit\" value=\"Filtrar Resultados\">");
		salida.println("                        </div>");
		salida.println("                    </div>");
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:black; padding-top:20px; margin-top:-10px;\">");
		salida.println("                <table class=\"table table-hover\">");
		salida.println("                    <thead>");
		salida.println("                        <tr>");
		salida.println("                            <th>ID</th>");
		salida.println("                            <th>ID Cliente</th>");
		salida.println("                            <th>Estado Pago</th>");
		salida.println("                            <th>Creacion</th>");
		salida.println("                            <th>Recibido</th>");
		salida.println("                            <th>Entrega</th>");
		salida.println("                            <th># Mat. Primas</th>");
		salida.println("                            <th># Componentes</th>");
		salida.println("                            <th># Productos</th>");
		salida.println("                        </tr>");
		salida.println("                    </thead>");
		salida.println("                    <tbody>");
		salida.println("                        <tr>");
		printTablaPedidos(salida);
	}

	private void printTablaPedidos(PrintWriter salida) {
		// TODO Auto-generated method stub
		
	}

	private void printFooterPedidos(PrintWriter salida) {
		salida.println("                        </tr>");
		salida.println("                    </tbody>");
		salida.println("                </table>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
	
	private void realizarConsultaClientes(PrintWriter out) {
		printHeaderClientes(out);
		printEtapasClientes(out);
		printFooterClientes(out);
	}
	
	private void printHeaderClientes(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void printEtapasClientes(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void printFooterClientes(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}
	
	private void realizarConsultaProveedores(PrintWriter out) {
		printHeaderProveedores(out);
		printEtapasProveedores(out);
		printFooterProveedores(out);
	}
	
	private void printHeaderProveedores(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void printEtapasProveedores(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void printFooterProveedores(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	public void realizarConsultaOperMasActiv(PrintWriter out) {
		printHeaderOperMasActivo(out);
		printEtapasOperMasActivo(out);
		printFooterOperMasActivo(out);
	}
	
	public void printHeaderOperMasActivo(PrintWriter salida) {
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
		salida.println("                    <a href=\"/ProdAndes/index.html\" class=\"navbar-brand\" id=\"navBarLink\">");
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
		salida.println("            	<h1>Registre la Entrega de un Pedido:</h1>");
		salida.println("                <form class=\"form\" action=\"/ProdAndes/consulta/operariomasactivo.html\" method=\"get\">");
		salida.println("                	<div class=\"form-group\">");
		salida.println("                		<label for=\"etapa\">ID de la etapa</label>");
		salida.println("                        <select name=\"etapa\" id=\"etapa\" class=\"form-control\" required>");
	}
	
	public void printEtapasOperMasActivo(PrintWriter salida) {
		ArrayList<String> etapas = ProdAndesGerente.darInstancia().darEtapas();
		if(etapas.size() == 0) {
			salida.println("                            <option value=\"none\" selected disabled>No hay etapas disponibles</option>");
		} else {
			for(String e : etapas) {
				salida.println("                            <option value=\"" + e.toLowerCase() + "\">" + e +"</option>");
			}	
		}
	}
	
	public void printFooterOperMasActivo(PrintWriter salida) {
		salida.println("                        </select>");
		salida.println("                    </div>");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <label for=\"number\">Numero de Operaciones</label>");
		salida.println("                        <input type=\"number\" class=\"form-control\" name=\"number\" placeholder=\"Numero de Operaciones\" required>");
		salida.println("                    </div>");
		salida.println("                    <button class=\"btn btn-default\" name=\"submit\" type=\"submit\" value=\"estacion\">Consultar</button>");
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
