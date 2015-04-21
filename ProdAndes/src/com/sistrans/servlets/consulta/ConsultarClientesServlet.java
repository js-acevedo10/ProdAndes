package com.sistrans.servlets.consulta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesAdmin;
import com.sistrans.fachada.ProdAndesGerente;
import com.sistrans.mundo.Usuario;

/**
 * Servlet implementation class ConsultarClientesServlet
 */
@WebServlet("/consultar/clientes.html")
public class ConsultarClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarClientesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarSolicitud(request, response);
	}
	
	public void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter salida = response.getWriter();
		String idCliente = request.getParameter("idCliente");
		String tipoDoc = request.getParameter("tipoDoc");
		String numDoc = request.getParameter("numDoc");
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		String nacionalidad = request.getParameter("nacionalidad");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		String ciudad = request.getParameter("ciudad");
		String departamento = request.getParameter("departamento");
		String codPostal = request.getParameter("codPostal");
		String numReg = request.getParameter("numReg");
		
		ArrayList<Usuario> usersResponse = ProdAndesAdmin.darInstancia().consultarClientes(idCliente, tipoDoc, numDoc, nombre, direccion, nacionalidad, email, telefono, ciudad, departamento, codPostal, numReg, null);
		
		if(usersResponse == null) {
			response.sendRedirect("/ProdAndes/pages/error/404.html");
		} else {
			printHeaderClientes(salida);
			printTablaClientes(salida, usersResponse);
			printFooterClientes(salida);
		}
	}
	
	private void printHeaderClientes(PrintWriter salida) {
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
		salida.println("               <h1>Consulta de Clientes ProdAndes:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/consultar/cliente.html\" method=\"post\">");
		salida.println("                    <div class=\"form-group\">");
		
		//idCliente
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"idCliente\">ID del Cliente:</label>");
		salida.println("                           <input class=\"form-control\" type=\"text\" name=\"idCliente\">");
		salida.println("                        </div>");

		//tipoDoc
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"tipoDoc\">Tipo de Documento del Cliente:</label>");
		salida.println("                           <select class=\"form-control\" name=\"tipoDoc\">");
		salida.println("								<option value=\"\" selected>Ninguno</option> ");
		
		ArrayList<String> tiposDoc = ProdAndesGerente.darInstancia().darTiposDoc("CLIENTE");
		if(tiposDoc == null || tiposDoc.size() == 0) {
			salida.println("							<option value=\"\" selected disabled>No hay registrados</option> ");
		} else {
			for(String e : tiposDoc) {
				salida.println("							<option value=\"" + e + "\">" + e + "</option> ");
			}
		}
		salida.println("                        </select>");
		salida.println("                        </div>");
		
		//numDoc
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"numDoc\">Numero de Documento:</label>");
		salida.println("                           <input class=\"form-control\" type=\"text\" name=\"numDoc\">");
		salida.println("                        </div>");
		
		//nombre
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"nombre\">Nombre del Cliente:</label>");
		salida.println("                           <input class=\"form-control\" type=\"text\" name=\"nombre\">");
		salida.println("                        </div>");
		
		//direccion
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"direccion\">Direccion del Cliente:</label>");
		salida.println("                           <input class=\"form-control\" type=\"text\" name=\"direccion\">");
		salida.println("                        </div>");
		
		//nacionalidad
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"nacionalidad\">Nacionalidad del Cliente:</label>");
		salida.println("                           <select class=\"form-control\" name=\"nacionalidad\">");
		salida.println("								<option value=\"\" selected>Ninguno</option> ");
		
		ArrayList<String> nacionalidad = ProdAndesGerente.darInstancia().darNacionalidades();
		if(nacionalidad == null || nacionalidad.size() == 0) {
			salida.println("							<option value=\"\" selected disabled>No hay opciones</option> ");
		} else {
			for(String e : nacionalidad) {
				salida.println("							<option value=\"" + e + "\">" + e + "</option> ");
			}
		}
		salida.println("                        </select>");
		salida.println("                        </div>");
		
		//email
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"email\">Email del Cliente:</label>");
		salida.println("                           <input class=\"form-control\" type=\"email\" name=\"email\">");
		salida.println("                        </div>");
		
		//telefono
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"telefono\">Telefono del Cliente:</label>");
		salida.println("                           <input class=\"form-control\" type=\"number\" name=\"telefono\">");
		salida.println("                        </div>");
		
		//ciudad
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"ciudad\">Ciudad Origen del Cliente:</label>");
		salida.println("                           <select class=\"form-control\" name=\"ciudad\">");
		salida.println("								<option value=\"\" selected>Ninguno</option> ");
		
		ArrayList<String> ciudades = ProdAndesGerente.darInstancia().darCiudades();
		if(ciudades == null || ciudades.size() == 0) {
			salida.println("							<option value=\"\" selected disabled>No hay registrados</option> ");
		} else {
			for(String e : ciudades) {
				salida.println("							<option value=\"" + e + "\">" + e + "</option> ");
			}
		}
		salida.println("                        </select>");
		salida.println("                        </div>");
		
		//departamento
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"departamento\">Departamento Origen del Cliente:</label>");
		salida.println("                           <select class=\"form-control\" name=\"departamento\">");
		salida.println("								<option value=\"\" selected>Ninguno</option> ");
		
		ArrayList<String> departamentos = ProdAndesGerente.darInstancia().darDepartamentos();
		if(departamentos == null || departamentos.size() == 0) {
			salida.println("							<option value=\"\" selected disabled>No hay registrados</option> ");
		} else {
			for(String e : departamentos) {
				salida.println("							<option value=\"" + e + "\">" + e + "</option> ");
			}
		}
		salida.println("                        </select>");
		salida.println("                        </div>");
		
		//codPostal
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"codPostal\">Codigo Postal del Cliente:</label>");
		salida.println("                           <input class=\"form-control\" type=\"number\" name=\"codPostal\">");
		salida.println("                        </div>");
		
		//numReg
		salida.println("                        <div class=\"col-md-4\">");
		salida.println("                           <label for=\"numReg\">Numero de Registro del Cliente:</label>");
		salida.println("                           <input class=\"form-control\" type=\"number\" name=\"numReg\">");
		salida.println("                        </div>");
		
		//personaNatural

		salida.println("                        <div class=\"col-md-12\" style=\"text-align:center;\">");
		salida.println("                        <br>");
		salida.println("                            <input class=\"btn btn-default btn-lg\" type=\"submit\" name=\"submit\" id=\"submit\" value=\"Filtrar Resultados\">");
		salida.println("                        </div>");
		salida.println("                    </div>");
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:black; padding-top:20px; margin-top:-10px;\">");
		salida.println("                <table class=\"table table-hover\">");
	}

	private void printTablaClientes(PrintWriter salida, ArrayList<Usuario> usersResponse) {
		if(usersResponse.size() == 0) {
			salida.println("<h3>Lo setimos, ningun cliente cumple con las especificaciones.</h3>");
		} else {
			salida.println("                    <thead>");
			salida.println("                        <tr>");
			salida.println("                            <th>ID</th>");
			salida.println("                            <th>Nombre</th>");
			salida.println("                            <th>Num. Doc</th>");
			salida.println("                            <th>Direccion</th>");
			salida.println("                            <th>Ciudad</th>");
			salida.println("                            <th>Pais</th>");
			salida.println("                            <th>Email</th>");
			salida.println("                            <th>Telefono</th>");
			salida.println("                            <th>Cod.Postal</th>");
			salida.println("                            <th>Detalles</th>");
			salida.println("                        </tr>");
			salida.println("                    </thead>");
			salida.println("                    <tbody>");
			
			Collections.sort(usersResponse);
			for(Usuario e : usersResponse) {
				salida.println("                        <tr>");
				salida.println("							<td>" + e.getLogin() + "</td>");
				salida.println("							<td>" + e.getNombre() + "</td>");
				salida.println("							<td>" + e.getNumDoc() + "</td>");
				salida.println("							<td>" + e.getDireccion() + "</td>");
				salida.println("							<td>" + e.getCiudad() + "</td>");
				salida.println("							<td>" + e.getNacionalidad() + "</td>");
				salida.println("							<td>" + e.getEmail() + "</td>");
				salida.println("							<td>" + e.getTelefono() + "</td>");
				salida.println("							<td>" + e.getCodPostal() + "</td>");
				salida.println("							<td><button><a href=\"/ProdAndes/consulta/cliente.html?idCliente=" + e.getLogin() + "\" style=\"text-decoration:none; color:black;\">VER</a></button></td>");
				salida.println("                        </tr>");
			}
		}
	}

	private void printFooterClientes(PrintWriter salida) {
		salida.println("                    </tbody>");
		salida.println("                </table>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
	
}
