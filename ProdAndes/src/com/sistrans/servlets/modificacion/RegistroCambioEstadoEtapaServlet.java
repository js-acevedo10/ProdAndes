package com.sistrans.servlets.modificacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesGerente;
import com.sistrans.mundo.EtapadeProduccion;

/**
 * Servlet implementation class RegistroCambioEstadoEtapaServlet
 */
@WebServlet("/etapas/cambio.html")
public class RegistroCambioEstadoEtapaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroCambioEstadoEtapaServlet() {
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
		String accion = request.getParameter("submit");
		if(accion.equals("Activar")) {
			String idEtapa = request.getParameter("id");
			ProdAndesGerente.darInstancia().activarEtapaProduccion(idEtapa);
		} else if(accion.equals("Desactivar")) {
			String idEtapa = request.getParameter("id");
			ProdAndesGerente.darInstancia().desActivarEtapaProduccion(idEtapa);
		}
		ArrayList<EtapadeProduccion> etapas = ProdAndesGerente.darInstancia().darEtapasFull();
		
		printHeader(salida);
		printBody(salida, etapas);
		printFooter(salida);
	}

	private void printHeader(PrintWriter salida) {
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
		salida.println("            	<h1>Registrar cambio de estado en etapa</h1>");
	}

	private void printBody(PrintWriter salida, ArrayList<EtapadeProduccion> etapas) {
		salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
		if(etapas.size() == 0) {
			salida.println("            		<h5>No hay etapas registradas.</h5>");
		} else {
			salida.println("                <table class=\"table table-hover\">");
			salida.println("                    <thead>");
			salida.println("                        <tr>");
			salida.println("                            <th>ID de la Etapa</th>");
			salida.println("                            <th>Estado de la Etapa</th>");
			salida.println("                            <th>Ejecutar Accion</th>");
			salida.println("                        </tr>");
			salida.println("                    </thead>");
			salida.println("                    <tbody>");
			for(EtapadeProduccion e : etapas) {
				salida.println("                        <tr>");
				salida.println("							<td>" + e.getNum() + "</td>");
				if(e.getEstado()) {
					salida.println("							<td>Activa</td>");
					salida.println("							<td><button class=\"btn btn-danger\"><a style=\"text-decoration:none; color:black;\" href=\"/ProdAndes/etapas/cambio.html?submit=Desactivar&id=" + e.getNum() + "\">Desactivar</a></button></td>");
				} else {
					salida.println("							<td>Inactiva</td>");
					salida.println("							<td><button class=\"btn btn-success\"><a style=\"text-decoration:none; color:black;\" href=\"/ProdAndes/etapas/cambio.html?submit=Activar&id=" + e.getNum() + "\">Activar</a></button></td>");
				}
				salida.println("                        </tr>");
			}
			salida.println("                    </tbody>");
			salida.println("                </table>");
		}
		
		salida.println("				</div>");
	}

	private void printFooter(PrintWriter salida) {
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}

}
