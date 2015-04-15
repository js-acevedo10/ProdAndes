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
import com.sistrans.mundo.Pedido;
import com.sistrans.mundo.Usuario;

/**
 * Servlet implementation class ConsultarClienteServlet
 */
@WebServlet("/consulta/cliente.html")
public class ConsultarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarClienteServlet() {
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

	private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter salida = response.getWriter();
		
		String id = request.getParameter("idCliente");
		ArrayList<Usuario> clientes = ProdAndesAdmin.darInstancia().consultarClientes(id, null, null, null, null, null, null, null, null, null, null, null, null);
		if(clientes == null || clientes.size() == 0) {
			response.sendRedirect("/ProdAndes/pages/error/404.html");
		} else {
			printClienteHeader(salida);
			printClienteInfo(salida, clientes);
			printClienteFooter(salida);
		}
	}

	private void printClienteHeader(PrintWriter salida) {
		salida.println("<!DOCTYPE html>");
		salida.println("<html>");
		salida.println("    <head>");
		salida.println("        <meta charset=\"utf-8\">");
		salida.println("        <title>Panel de Administrador ProdAndes</title>");
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
		salida.println("                <li><a href=\"/ProdAndes/index.html\">Cerrar Sesion</a></li>");
		salida.println("            </ul>");
		salida.println("          </div><!-- /.container-fluid -->");
		salida.println("        </nav>");
		salida.println("        <div class=\"container\">");
		salida.println("            <div class=\"jumbotron\">");
	}

	private void printClienteInfo(PrintWriter salida,
			ArrayList<Usuario> clientes) {
		Usuario user = clientes.get(0);
		if(user != null) {
			salida.println("            	<h1>Informacion del Cliente " + user.getLogin() + "</h1>");
			salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
			salida.println("            		<h3>Nombre: " + user.getNombre() + "</h3>");
			salida.println("            		<h3>Documento: " + user.getNumDoc() + "</h3>");
			salida.println("            		<h3>Email: " + user.getEmail() + "</h3>");
			salida.println("            		<h3>Direccion: " + user.getDireccion() + "</h3>");
			salida.println("            		<h3>Ciudad: " + user.getCiudad() + "</h3>");
			salida.println("				</div>");
			salida.println("            	<h3>Pedidos del Cliente</h3>");
			salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
			if(user.darPedidos().size() == 0) {
				salida.println("            		<h5>El cliente no tiene pedidos asociados</h5>");
			} else {
				salida.println("                <table class=\"table table-hover\">");
				salida.println("                    <thead>");
				salida.println("                        <tr>");
				salida.println("                            <th>ID</th>");
				salida.println("                            <th>Estado del Pago</th>");
				salida.println("                            <th># Componentes</th>");
				salida.println("                            <th># Materia Prima</th>");
				salida.println("                            <th># Producto</th>");
				salida.println("                            <th>Anotaciones</th>");
				salida.println("                        </tr>");
				salida.println("                    </thead>");
				salida.println("                    <tbody>");
				for(Pedido e : user.darPedidos()) {
					if(e.getId() != null) {
					salida.println("                        <tr>");
					salida.println("							<td>" + e.getId() + "</td>");
					salida.println("							<td>" + e.getEstadoPago() + "</td>");
					salida.println("							<td>" + e.getNumComponente() + "</td>");
					salida.println("							<td>" + e.getNumMateriaPrima() + "</td>");
					salida.println("							<td>" + e.getNumProducto() + "</td>");
					if(!e.getAnotaciones().equals("")) {
						salida.println("							<td><button><a onclick=\"alert('" + e.getAnotaciones() + "')\" style=\"text-decoration:none; color:black;\">Ver</a></button></td>");
					} else {
						salida.println("							<td><button disabled><a style=\"text-decoration:none; color:black;\">Ver</a></button></td>");
					}
					salida.println("                        </tr>");
					}
				}
				salida.println("                    </tbody>");
				salida.println("                </table>");
			}
		} else {
			
		}
		
	}

	private void printClienteFooter(PrintWriter salida) {
		// TODO Auto-generated method stub
		
	}

}
