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
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.EtapadeProduccion;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Producto;

/**
 * Servlet implementation class ExistenciaServletΩ
 */
@WebServlet("/consultar/existencias.html")
public class ConsultarExistenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarExistenciaServlet() {
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
		String existenciasMin = request.getParameter("eMin");
		String existenciasMax = request.getParameter("eMax");
		String estacion = request.getParameter("estacion");
		
		switch(role) {
			case "admin":
				printHeader(out, "admin");
				solicitudAdmin(role, tipo, existenciasMin, existenciasMax, estacion, out);
				printFooter(out);
				break;
			case "user":
				printHeader(out, "user");
				solicitudUser(role, tipo, out);
				printFooter(out);
				break;
			case "operario":
				printHeader(out, "operario");
				solicitudOperario(role, tipo, existenciasMin, existenciasMax, estacion, out);
				printFooter(out);
				break;
			case "gerente":
				printHeader(out, "gerente");
				solicitudGerente(role, tipo, existenciasMin, existenciasMax, estacion, out);
				printFooter(out);
				break;
			default:
				response.sendRedirect("/ProdAndes/pages/error/404.html");
				break;
		}
	}
	
	//LOGIC METHODS

	private void solicitudAdmin(String role, String tipo, String existenciasMin, String existenciasMax,
			String estacion, PrintWriter out) {		
		if(tipo.equals("materia-prima")) {
			ArrayList<MateriaPrima> items = ProdAndesAdmin.darInstancia().consultarExistenciasMatPrima(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {

			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "<th>Tonelada</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				int i = 0;
				for(MateriaPrima materia : items) {
					out.println("<tr>");
					out.println("<td>"+i+"</td>");
					out.println("<td>"+materia.getNombre()+"</td>");
					out.println("<td>"+materia.getTonelada()+"</td>");
					out.println("</tr>");
					i++;
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("componente")) {
			ArrayList<Componente> items = ProdAndesAdmin.darInstancia().consultarExistenciasComp(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "<th>UnidadMedida</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Componente comp : items) {
					out.println("<tr>");
					out.println("<td>" + comp.getNumInventario() + "</td>");
					out.println("<td>" + comp.getNombre() + "</td>");
					out.println("<td>" + comp.getUnidadMedida() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("etapa-producto")) {
			ArrayList<EtapadeProduccion> items = ProdAndesAdmin.darInstancia().consultarExistenciasEtapa(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Fecha Inicial</th>"
						+ "<th>Fecha Final</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(EtapadeProduccion stage : items) {
					out.println("<tr>");
					out.println("<td>" + stage.getNum() + "</td>");
					out.println("<td>" + stage.getNombre() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("producto")) {
			ArrayList<Producto> items = ProdAndesAdmin.darInstancia().consultarExistenciasProd(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>Nombre</th>"
						+ "<th>Costo</th>"
						+ "<th>Existencias</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Producto prod : items) {
					out.println("<tr>");
					out.println("<td>" + prod.getNombre() + "</td>");
					out.println("<td>" + prod.getCostoVenta() + "</td>");
					out.println("<td>" + prod.getnumInventario() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("all")) {
				
		} else {
			printError("No se encontró ningún material con estas características.", out);
		}
	}

	private void solicitudUser(String role, String tipo, PrintWriter out) {
		// TODO Auto-generated method stub
		if(tipo.equals("materia-prima")) {
			ArrayList<MateriaPrima> items = ProdAndesUsuario.darInstancia().consultarExistenciasMatPrima(tipo);
			if(items.size() == 0) {

			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				int i = 0;
				for(MateriaPrima materia : items) {
					out.println("<tr>");
					out.println("<td>"+i+ "</td>");
					out.println("<td>"+materia.getNombre()+"</td>");
					out.println("</tr>");
					i++;
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("componente")) {
			ArrayList<Componente> items = ProdAndesUsuario.darInstancia().consultarExistenciasComp(tipo);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "<th>UnidadMedida</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Componente comp : items) {
					out.println("<tr>");
					out.println("<td>" + comp.getNumInventario() + "</td>");
					out.println("<td>" + comp.getNombre() + "</td>");
					out.println("<td>" + comp.getUnidadMedida() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("etapa-producto")) {
			ArrayList<EtapadeProduccion> items = ProdAndesUsuario.darInstancia().consultarExistenciasEtapa(tipo);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(EtapadeProduccion stage : items) {
					out.println("<tr>");
					out.println("<td>" + stage.getNum() + "</td>");
					out.println("<td>" + stage.getNombre() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("producto")) {
			ArrayList<Producto> items = ProdAndesUsuario.darInstancia().consultarExistenciasProd(tipo);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>Nombre</th>"
						+ "<th>Costo</th>"
						+ "<th>Existencias</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Producto prod : items) {
					out.println("<tr>");
					out.println("<td>" + prod.getNombre() + "</td>");
					out.println("<td>" + prod.getCostoVenta() + "</td>");
					out.println("<td>" + prod.getnumInventario() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("all")) {
				
		} else {
			printError("No se encontró ningún material con estas características.", out);
		}
	}

	private void solicitudOperario(String role, String tipo,
			String existenciasMin, String existenciasMax, String estacion, PrintWriter out) {
		// TODO Auto-generated method stub
		if(tipo.equals("materia-prima")) {
			ArrayList<MateriaPrima> items = ProdAndesOperario.darInstancia().consultarExistenciasMatPrima(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {

			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "<th>Tonelada</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				int i = 0;
				for(MateriaPrima materia : items) {
					out.println("<tr>");
					out.println("<td>"+i+"</td>");
					out.println("<td>"+materia.getNombre()+"</td>");
					out.println("<td>"+materia.getTonelada()+"</td>");
					out.println("</tr>");
					i++;
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("componente")) {
			ArrayList<Componente> items = ProdAndesOperario.darInstancia().consultarExistenciasComp(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "<th>UnidadMedida</th>"
						+ "<th>Producto</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Componente comp : items) {
					out.println("<tr>");
					out.println("<td>" + comp.getNumInventario() + "</td>");
					out.println("<td>" + comp.getNombre() + "</td>");
					out.println("<td>" + comp.getUnidadMedida() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("etapa-producto")) {
			ArrayList<EtapadeProduccion> items = ProdAndesOperario.darInstancia().consultarExistenciasEtapa(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(EtapadeProduccion stage : items) {
					out.println("<tr>");
					out.println("<td>" + stage.getNum() + "</td>");
					out.println("<td>" + stage.getNombre()+ "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("producto")) {
			ArrayList<Producto> items = ProdAndesOperario.darInstancia().consultarExistenciasProd(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>Nombre</th>"
						+ "<th>Costo</th>"
						+ "<th>Existencias</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Producto prod : items) {
					out.println("<tr>");
					out.println("<td>" + prod.getNombre() + "</td>");
					out.println("<td>" + prod.getCostoVenta() + "</td>");
					out.println("<td>" + prod.getnumInventario() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("all")) {
				
		} else {
			printError("No se encontró ningún material con estas características.", out);
		}
	}

	private void solicitudGerente(String role, String tipo, String existenciasMin,
			String existenciasMax, String estacion, PrintWriter out) {	
		if(tipo.equals("materia-prima")) {
			ArrayList<MateriaPrima> items = ProdAndesGerente.darInstancia().consultarExistenciasMatPrima(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {

			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "<th>Tonelada</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				int i = 0;
				for(MateriaPrima materia : items) {
					out.println("<tr>");
					out.println("<td>"+i+"</td>");
					out.println("<td>"+materia.getNombre()+"</td>");
					out.println("<td>"+materia.getTonelada()+"</td>");
					out.println("</tr>");
					i++;
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("componente")) {
			ArrayList<Componente> items = ProdAndesGerente.darInstancia().consultarExistenciasComp(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "<th>UnidadMedida</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Componente comp : items) {
					out.println("<tr>");
					out.println("<td>" + comp.getNumInventario() + "</td>");
					out.println("<td>" + comp.getNombre() + "</td>");
					out.println("<td>" + comp.getUnidadMedida() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("etapa-producto")) {
			ArrayList<EtapadeProduccion> items = ProdAndesGerente.darInstancia().consultarExistenciasEtapa(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>ID</th>"
						+ "<th>Nombre</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(EtapadeProduccion stage : items) {
					out.println("<tr>");
					out.println("<td>" + stage.getNum() + "</td>");
					out.println("<td>" + stage.getNombre()+ "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("producto")) {
			ArrayList<Producto> items = ProdAndesGerente.darInstancia().consultarExistenciasProd(tipo, existenciasMin, existenciasMax, estacion);
			if(items.size() == 0) {
				
			} else {
				out.println("<thead>"
						+ "<tr>"
						+ "<th>Nombre</th>"
						+ "<th>Costo</th>"
						+ "<th>Existencias</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>");
				for(Producto prod : items) {
					out.println("<tr>");
					out.println("<td>" + prod.getNombre() + "</td>");
					out.println("<td>" + prod.getCostoVenta() + "</td>");
					out.println("<td>" + prod.getnumInventario() + "</td>");
					out.println("</tr>");
				}
			}
			out.println("</tbody>");
		} else if(tipo.equals("all")) {
				
		} else {
			printError("No se encontró ningún material con estas características.", out);
		}
	}
	
	public void printEstaciones(PrintWriter out) {
		ArrayList<String> estaciones = ProdAndesGerente.darInstancia().darEstaciones();
		System.out.println(estaciones.size());
		for(String x : estaciones) {
			out.println("								<option value=\""+ x.toLowerCase() +"\">" + x + "</option>");
		}
	}
	
	//HTML METHODS
	
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
		salida.println("                    <a href=\"\" class=\"navbar-brand\" onclick=\"window.history.back()\" id=\"navBarLink\">");
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
		salida.println("            <div class=\"jumbotron\" style=\"padding-top:10px; margin-bottom:-10px;\">");
		salida.println("               <h1>Existencias de ProdAndes:</h1>");
		salida.println("               <br>");
		salida.println("                <form class=\"form-horizontal\" action=\"/ProdAndes/consultar/existencias.html\" method=\"get\">");
		salida.println("                    <div class=\"form-group\">");
		salida.println("                        <div class=\"col-md-3\">");
		salida.println("                            <select class=\"form-control input-lg\" id=\"search-input\" name=\"t\" required>");
		salida.println("                                <option value=\"materia-prima\">Materia Prima</option>");
		salida.println("                                <option value=\"componente\">Componente</option>");
		salida.println("                                <option value=\"producto\">Producto</option>");
		salida.println("                            </select><br>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-3\">");
		salida.println("                    		<input type=\"number\" class=\"form-control input-lg\" name=\"eMin\" placeholder=\"Existencias minimas\">");
		salida.println("                    	</div>");
		salida.println("                    	<div class=\"col-md-3\">");
		salida.println("                    		<input type=\"number\" class=\"form-control input-lg\" name=\"eMax\" placeholder=\"Existencias maximas\">");
		salida.println("                    	</div>");
		salida.println("                    	<div class=\"col-md-3\">");
		salida.println("                            <select class=\"form-control input-lg\" id=\"search-input\" name=\"estacion\">");
		salida.println("                                <option value=\"\" selected disabled style=\"display: none\">Estacion</option>");
		
		printEstaciones(salida);
		
		salida.println("                            </select><br>");
		salida.println("                        </div>");
		salida.println("                        <div class=\"col-md-12\">");
		salida.println("                            <input class=\"btn btn-default btn-lg\" type=\"submit\" name=\"categoria\" id=\"submit\" value=\"Buscar\">");
		salida.println("                        </div>");
		salida.println("                        	<input type=\"text\" name=\"r\" value=\""+ role +"\" style=\"display:none\">");
		salida.println("                    </div>");
		salida.println("                </form>");
		salida.println("            </div>");
		salida.println("            <div class=\"jumbotron\" style=\"background-color:WHITE; color:black; padding-top:20px; margin-top:-10px;\">");
		salida.println("                <table class=\"table table-hover\">");


	}
	
	public void printFooter(PrintWriter salida) {
		salida.println("                </table>");
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");

	}
	
	public void printError(String msg, PrintWriter out) {
		out.println("ERROR FATAl");
	}
}
