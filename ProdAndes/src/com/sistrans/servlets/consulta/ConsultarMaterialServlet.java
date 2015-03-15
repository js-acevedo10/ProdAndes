package com.sistrans.servlets.consulta;

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
import com.sistrans.fachada.ProdAndesOperario;
import com.sistrans.fachada.ProdAndesUsuario;

/**
 * Servlet implementation class MaterialServlet
 */
@WebServlet("/consultar/material.html")
public class ConsultarMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarMaterialServlet() {
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
	
	public void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String role = request.getParameter("r");
		String tipo = request.getParameter("t");
		String query = request.getParameter("q");
		
		switch(role) {
			case "admin":
				printHeader(out);
				solicitudAdmin(tipo, query, out);
				printFooter(out);
				break;
			case "user":
				printHeader(out);
				solicitudUsuario(tipo, query, out);
				printFooter(out);
				break;
			case "operario":
				printHeader(out);
				solicitudOperario(tipo, query, out);
				printFooter(out);
				break;
			case "gerente":
				printHeader(out);
				solicitudGerente(tipo, query, out);
				printFooter(out);
				break;
			default:
				response.sendRedirect("/ProdAndes/pages/error/404.html");
				break;
		}
	}

	private void solicitudAdmin(String tipo, String query, PrintWriter out) {
		// TODO Auto-generated method stub
		ArrayList<String> detalles = ProdAndesAdmin.darInstancia().informacionMaterial(query, tipo);
		if(detalles.size() != 0) {
			switch(tipo) {
				case "materia-prima":
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Toneladas: " + detalles.get(1) + "</h3>");
					out.println("<h3>Materia Prima: " + detalles.get(2) + "</h3>");
					break;
				case "componente":
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Numero: " + detalles.get(2) + "</h3>");
					out.println("<h3>Unidad de Medida: " + detalles.get(3) + "</h3>");
					out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
					break;
				case "producto":
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Costo de Venta: " + detalles.get(1) + "</h3>");
					out.println("<h3>Materia Prima: " + detalles.get(2) + "</h3>");
					out.println("<h3>Componente: " + detalles.get(3) + "</h3>");
					out.println("<h3>Etapa de Produccion: " + detalles.get(4) + "</h3>");
					break;
				case "etapa-producto":
					out.println("                <h1>Num: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Fecha Inicial: " + detalles.get(1) + "</h3>");
					out.println("<h3>Fecha Final: " + detalles.get(2) + "</h3>");
					out.println("<h3>Estacion de Produccion: " + detalles.get(3) + "</h3>");
					out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
					break;
				default:
					break;
			}
		} else  {
			out.println("ERROR FATAL");
		}
	}

	private void solicitudUsuario(String tipo, String query, PrintWriter out) {
		// TODO Auto-generated method stub
		ArrayList<String> detalles = ProdAndesUsuario.darInstancia().informacionMaterial(query, tipo);
		if(detalles.size() != 0) {
			switch(tipo) {
			case "materia-prima":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Toneladas: " + detalles.get(1) + "</h3>");
				break;
			case "componente":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Numero: " + detalles.get(2) + "</h3>");
				out.println("<h3>Unidad de Medida: " + detalles.get(3) + "</h3>");
				out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
				break;
			case "producto":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Costo de Venta: " + detalles.get(1) + "</h3>");
				break;
			case "etapa-producto":
				out.println("                <h1>Num: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Fecha Inicial: " + detalles.get(1) + "</h3>");
				out.println("<h3>Fecha Final: " + detalles.get(2) + "</h3>");
				out.println("<h3>Estacion de Produccion: " + detalles.get(3) + "</h3>");
				out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
				break;
			default:
				break;
		}
		} else  {
			out.println("ERROR FATAL");
		}
	}

	private void solicitudOperario(String tipo, String query, PrintWriter out) {
		// TODO Auto-generated method stub
		ArrayList<String> detalles = ProdAndesOperario.darInstancia().informacionMaterial(query, tipo);
		if(detalles.size() != 0) {
			switch(tipo) {
			case "materia-prima":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Toneladas: " + detalles.get(1) + "</h3>");
				out.println("<h3>Materia Prima: " + detalles.get(2) + "</h3>");
				break;
			case "componente":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Numero: " + detalles.get(2) + "</h3>");
				out.println("<h3>Unidad de Medida: " + detalles.get(3) + "</h3>");
				out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
				break;
			case "producto":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Costo de Venta: " + detalles.get(1) + "</h3>");
				out.println("<h3>Materia Prima: " + detalles.get(2) + "</h3>");
				out.println("<h3>Componente: " + detalles.get(3) + "</h3>");
				out.println("<h3>Etapa de Produccion: " + detalles.get(4) + "</h3>");
				break;
			case "etapa-producto":
				out.println("                <h1>Num: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Fecha Inicial: " + detalles.get(1) + "</h3>");
				out.println("<h3>Fecha Final: " + detalles.get(2) + "</h3>");
				out.println("<h3>Estacion de Produccion: " + detalles.get(3) + "</h3>");
				out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
				break;
			default:
				break;
		}
		} else  {
			out.println("ERROR FATAL");
		}
	}

	private void solicitudGerente(String tipo, String query, PrintWriter out) {
		// TODO Auto-generated method stub
		ArrayList<String> detalles = ProdAndesGerente.darInstancia().informacionMaterial(query, tipo);
		if(detalles.size() != 0) {
			switch(tipo) {
			case "materia-prima":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Toneladas: " + detalles.get(1) + "</h3>");
				out.println("<h3>Materia Prima: " + detalles.get(2) + "</h3>");
				break;
			case "componente":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Costo de Venta: " + detalles.get(1) + "</h3>");
				out.println("<h3>Materia Prima: " + detalles.get(2) + "</h3>");
				out.println("<h3>Componente: " + detalles.get(3) + "</h3>");
				out.println("<h3>Etapa de Produccion: " + detalles.get(4) + "</h3>");
				break;
			case "producto":
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Numero: " + detalles.get(2) + "</h3>");
				out.println("<h3>Unidad de Medida: " + detalles.get(3) + "</h3>");
				out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
				break;
			case "etapa-producto":
				out.println("                <h1>Num: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Fecha Inicial: " + detalles.get(1) + "</h3>");
				out.println("<h3>Fecha Final: " + detalles.get(2) + "</h3>");
				out.println("<h3>Estacion de Produccion: " + detalles.get(3) + "</h3>");
				out.println("<h3>Producto: " + detalles.get(4) + "</h3>");
				break;
			default:
				break;
		}
		} else  {
			out.println("ERROR FATAL");
		}
	}
	
	//METODOS HTML
	
	public void printHeader(PrintWriter salida) {
		salida.println("<!DOCTYPE html>");
		salida.println("<html>");
		salida.println("    <head>");
		salida.println("        <meta charset=\"utf-8\">");
		salida.println("        <title>Panel de Administrador ProdAndes</title>");
		salida.println("        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
		salida.println("        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>");
		salida.println("        <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js\"></script>");
		salida.println("        <link rel=\"stylesheet\" href=\"../../style/bootstrap.min.css\">");
		salida.println("        <link type=\"text/css\" href=\"../../style/custom-bootstrap-override.css\" rel=\"stylesheet\">");
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
		salida.println("                <li><a href=\"../../index.html\">Cerrar Sesión</a></li>");
		salida.println("            </ul>");
		salida.println("          </div><!-- /.container-fluid -->");
		salida.println("        </nav>");
		salida.println("        <div class=\"container\">");
		salida.println("            <div class=\"jumbotron\">");
	}
	
	public void printFooter(PrintWriter salida) {
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
