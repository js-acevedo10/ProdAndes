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
import com.sistrans.mundo.Componente;
import com.sistrans.mundo.MateriaPrima;
import com.sistrans.mundo.Pedido;
import com.sistrans.mundo.Producto;
import com.sistrans.mundo.Usuario;

/**
 * Servlet implementation class ConsultarProveedorServlet
 */
@WebServlet("/consulta/proveedor.html")
public class ConsultarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarProveedorServlet() {
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
		String loginProv = request.getParameter("provID");
		ArrayList<Usuario> prov = ProdAndesAdmin.darInstancia().consultarProveedor(loginProv, "", "", "", "", "", "", "", "", "", "", "", "");
		if(prov == null || prov.get(0) == null) {
			response.sendRedirect("/ProdAndes/pages/error/404.html");
		} else {
			printProvHeader(salida);
			printProvInfo(salida, prov);
			printProvFooter(salida);
		}
	}

	private void printProvHeader(PrintWriter salida) {
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

	private void printProvInfo(PrintWriter salida, ArrayList<Usuario> prov) {
		Usuario user = prov.get(0);
		salida.println("            	<h1>Informacion del Proveedor " + user.getLogin() + "</h1>");
		salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
		salida.println("            		<h3>Nombre: " + user.getNombre() + "</h3>");
		salida.println("            		<h3>Documento: " + user.getNumDoc() + "</h3>");
		salida.println("            		<h3>Email: " + user.getEmail() + "</h3>");
		salida.println("            		<h3>Direccion: " + user.getDireccion() + "</h3>");
		salida.println("            		<h3>Ciudad: " + user.getCiudad() + "</h3>");
		salida.println("				</div>");
		salida.println("            	<h3>Pedidos del Proveedor</h3>");
		salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
		if(user.darPedidos().size() == 0) {
			salida.println("            		<h5>El proveedor no tiene pedidos</h5>");
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
				}
				salida.println("                        </tr>");
				}
			}
			salida.println("                    </tbody>");
			salida.println("                </table>");
		}
		
		salida.println("				</div>");
		salida.println("            	<h3>Materias Primas del Proveedor</h3>");
		salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
		if(user.darMateriasPrimas().size() == 0) {
			salida.println("            		<h5>El proveedor no tiene materias primas</h5>");
		} else {
			salida.println("                <table class=\"table table-hover\">");
			salida.println("                    <thead>");
			salida.println("                        <tr>");
			salida.println("                            <th>ID</th>");
			salida.println("                            <th>Toneladas en Inventario</th>");
			salida.println("                        </tr>");
			salida.println("                    </thead>");
			salida.println("                    <tbody>");
			for(MateriaPrima e : user.darMateriasPrimas()) {
				if(e.getNombre() != null) {
				salida.println("                        <tr>");
				salida.println("							<td>" + e.getNombre() + "</td>");
				salida.println("							<td>" + e.getTonelada() + "</td>");
				salida.println("                        </tr>");
				}
			}
			salida.println("                    </tbody>");
			salida.println("                </table>");
		}
		
		salida.println("				</div>");
		salida.println("            	<h3>Componentes del Proveedor</h3>");
		salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
		if(user.darComponentes().size() == 0) {
			salida.println("            		<h5>El proveedor no tiene componentes</h5>");
		} else {
			salida.println("                <table class=\"table table-hover\">");
			salida.println("                    <thead>");
			salida.println("                        <tr>");
			salida.println("                            <th>ID</th>");
			salida.println("                            <th>Unidad de Medida</th>");
			salida.println("                            <th># en Inventario</th>");
			salida.println("                        </tr>");
			salida.println("                    </thead>");
			salida.println("                    <tbody>");
			for(Componente e : user.darComponentes()) {
				if(e.getNombre() != null) {
				salida.println("                        <tr>");
				salida.println("							<td>" + e.getNombre() + "</td>");
				salida.println("							<td>" + e.getUnidadMedida() + "</td>");
				salida.println("							<td>" + e.getNumInventario() + "</td>");
				salida.println("                        </tr>");
				}
			}
			salida.println("                    </tbody>");
			salida.println("                </table>");
		}
	
		salida.println("				</div>");
		salida.println("            	<h3>Productos dependientes del Proveedor</h3>");
		salida.println("				<div class=\"container\" style=\"background-color:WHITE; color:BLACK; border-radius:5px;\">");
		if(user.darproductos().size() == 0) {
			salida.println("            		<h5>El proveedor no tiene productos</h5>");
		} else {
			salida.println("                <table class=\"table table-hover\">");
			salida.println("                    <thead>");
			salida.println("                        <tr>");
			salida.println("                            <th>ID</th>");
			salida.println("                            <th>Costo Venta</th>");
			salida.println("                            <th># Mat. Prima</th>");
			salida.println("                            <th># Componentes</th>");
			salida.println("                        </tr>");
			salida.println("                    </thead>");
			salida.println("                    <tbody>");
			for(Producto e : user.darproductos()) {
				if(e.getNombre() != null) {
				salida.println("                        <tr>");
				salida.println("							<td>" + e.getNombre() + "</td>");
				salida.println("							<td>" + e.getCostoVenta() + "</td>");
				salida.println("							<td>" + e.materiaPrima.size() + "</td>");
				salida.println("							<td>" + e.componente.size() + "</td>");
				salida.println("                        </tr>");
				}
			}
			salida.println("                    </tbody>");
			salida.println("                </table>");
		}
		salida.println("				</div>");
	}

	private void printProvFooter(PrintWriter salida) {
		salida.println("            </div>");
		salida.println("        </div>");
		salida.println("    </body>");
		salida.println("</html>");
	}

}
