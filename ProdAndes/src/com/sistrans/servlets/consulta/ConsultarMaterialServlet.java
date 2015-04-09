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
		String role = request.getParameter("role");
		String tipo = request.getParameter("t");
		String query = request.getParameter("q");
		
		switch(role) {
			case "admin":
				printHeader(out, "admin");
				solicitudAdmin(tipo, query, out);
				printFooter(out);
				break;
			case "user":
				printHeader(out, "user");
				solicitudUsuario(tipo, query, out);
				printFooter(out);
				break;
			case "operario":
				printHeader(out, "operario");
				solicitudOperario(tipo, query, out);
				printFooter(out);
				break;
			case "gerente":
				printHeader(out, "gerente");
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
					if(detalles.size() > 1) {
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Existencias: " + detalles.get(1) + " toneladas." + "</h3>");
					out.println("<h3>Compone los productos: " + detalles.get(2).substring(0, detalles.get(2).length()-2) + ".</h3>");
					} else {
						out.println("<h1>No hay ninguna materia prima asociada al ID</h1>");
					}
					break;
				case "componente":
					if(detalles.size() > 2) {
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Existencias: " + detalles.get(1) +" " + detalles.get(2) + "</h3>");
					out.println("<h3>Compone los productos: " + detalles.get(3) + "</h3>");
					} else {
						out.println("<h1>No hay ningun componente asociado al ID</h1>");
					}
					break;
				case "producto":
					if(detalles.size() > 4) {
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>ID: " + detalles.get(1) + "</h3>");
					out.println("<h3>Costo de Venta: $" + detalles.get(2) + ".00</h3>");
					out.println("<h3>Existencias: " + detalles.get(3) + "</h3>");
					out.println("<h3>Materias primas involucradas: " + detalles.get(4) + "</h3>");
					out.println("<h3>Componentes involucrados: " + detalles.get(5) + "</h3>");
					} else {
						out.println("<h1>No hay ningun producto asociado al ID</h1>");
					}
					break;
				case "etapa-producto":
					if(detalles.size() > 1) {
					out.println("                <h1>ID: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Etapa Numero: " + detalles.get(1) + "</h3>");
					out.println("<h3>Nombre del producto en la etapa: " + detalles.get(2) + "</h3>");
					} else {
						out.println("<h1>No hay ninguna etapa de produccion asociada al ID</h1>");
					}
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
				if(detalles.size() > 1) {
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Existencias: " + detalles.get(1) + " toneladas." + "</h3>");
				out.println("<h3>Compone los productos: " + detalles.get(2).substring(0, detalles.get(2).length()-2) + ".</h3>");
				} else {
					out.println("<h1>No hay ningun material asociado al ID</h1>");
				}
				break;
			case "componente":
				if(detalles.size() > 2) {
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Existencias: " + detalles.get(1) +" " + detalles.get(2) + "</h3>");
				out.println("<h3>Compone los productos: " + detalles.get(3) + "</h3>");
				} else {
					out.println("<h1>No hay ningun material asociado al ID</h1>");
				}
				break;
			case "producto":
				if(detalles.size() > 2) {
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>ID: " + detalles.get(1) + "</h3>");
				out.println("<h3>Costo de Venta: " + detalles.get(2) + "</h3>");
				if(!detalles.get(3).equals("0")) {
					out.println("<h3>Aun hay existencias de este producto.</h3>");
				} else {
					out.println("<h3>Este producto se encuentra agotado.</h3>");
				}
				} else {
					out.println("<h1>No hay ningun material asociado al ID</h1>");
				}
				break;
			case "etapa-producto":
				if(detalles.size() > 1) {
				out.println("                <h1>ID: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Etapa Numero: " + detalles.get(1) + "</h3>");
				out.println("<h3>Nombre del producto en la etapa: " + detalles.get(2) + "</h3>");
				} else {
					out.println("<h1>No hay ningun material asociado al ID</h1>");
				}
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
				if(detalles.size() > 1) {
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Existencias: " + detalles.get(1) + " toneladas." + "</h3>");
				out.println("<h3>Compone los productos: " + detalles.get(2).substring(0, detalles.get(2).length()-2) + ".</h3>");
				} else {
					out.println("<h1>No hay ninguna materia prima asociada al ID</h1>");
				}
				break;
			case "componente":
				if(detalles.size() > 2) {
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Existencias: " + detalles.get(1) +" " + detalles.get(2) + "</h3>");
				out.println("<h3>Compone los productos: " + detalles.get(3) + "</h3>");
				} else {
					out.println("<h1>No hay ningun componente asociado al ID</h1>");
				}
				break;
			case "producto":
				if(detalles.size() > 4) {
				out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>ID: " + detalles.get(1) + "</h3>");
				out.println("<h3>Costo de Venta: $" + detalles.get(2) + ".00</h3>");
				out.println("<h3>Existencias: " + detalles.get(3) + "</h3>");
				out.println("<h3>Materias primas involucradas: " + detalles.get(4) + "</h3>");
				out.println("<h3>Componentes involucrados: " + detalles.get(5) + "</h3>");
				} else {
					out.println("<h1>No hay ningun producto asociado al ID</h1>");
				}
				break;
			case "etapa-producto":
				if(detalles.size() > 1) {
				out.println("                <h1>ID: "+ detalles.get(0) +"</h1>");
				out.println("            </div>");
				out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
				out.println("<h3>Etapa Numero: " + detalles.get(1) + "</h3>");
				out.println("<h3>Nombre del producto en la etapa: " + detalles.get(2) + "</h3>");
				} else {
					out.println("<h1>No hay ninguna etapa asociada al ID</h1>");
				}
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
				if(detalles.size() > 1) {
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Existencias: " + detalles.get(1) + " toneladas." + "</h3>");
					out.println("<h3>Compone los productos: " + detalles.get(2).substring(0, detalles.get(2).length()-2) + ".</h3>");
				} else {
					out.println("<h1>No hay ninguna materia prima asociada al ID</h1>");
				}				
				break;
			case "componente":
				if(detalles.size() > 2) {	
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Existencias: " + detalles.get(1) +" " + detalles.get(2) + "</h3>");
					out.println("<h3>Compone los productos: " + detalles.get(3) + "</h3>");
				} else {
					out.println("<h1>No hay ningun componente asociado al ID</h1>");
				}
				break;
			case "producto":
				if(detalles.size() > 4) {
					out.println("                <h1>Nombre: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>ID: " + detalles.get(1) + "</h3>");
					out.println("<h3>Costo de Venta: $" + detalles.get(2) + ".00</h3>");
					out.println("<h3>Existencias: " + detalles.get(3) + "</h3>");
					out.println("<h3>Materias primas involucradas: " + detalles.get(4) + "</h3>");
					out.println("<h3>Componentes involucrados: " + detalles.get(5) + "</h3>");
				} else {
					out.println("<h1>No hay ningun producto asociado al ID</h1>");
				}
				break;
			case "etapa-producto":
				if(detalles.size() > 1) {
					out.println("                <h1>ID: "+ detalles.get(0) +"</h1>");
					out.println("            </div>");
					out.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:BLACK;\">");
					out.println("<h3>Etapa Numero: " + detalles.get(1) + "</h3>");
					out.println("<h3>Nombre del producto en la etapa: " + detalles.get(2) + "</h3>");
				} else {
					out.println("<h1>No hay ninguna etapa de producto asociada al ID</h1>");
				}
				break;
			default:
				break;
		}
		} else  {
			out.println("ERROR FATAL");
		}
	}
	
	//METODOS HTML
	
	public void printHeader(PrintWriter salida, String role) {
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
		salida.println("                    <a href=\"/ProdAndes/pages/" + role + "/dashboard.html\" class=\"navbar-brand\" id=\"navBarLink\">");
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
	
	public void printFooter(PrintWriter salida) {
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}
}
