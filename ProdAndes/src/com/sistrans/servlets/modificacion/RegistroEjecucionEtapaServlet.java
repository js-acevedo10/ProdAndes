package com.sistrans.servlets.modificacion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesOperario;

/**
 * Servlet implementation class RegistroEjecucionEtapaServlet
 */
@WebServlet("/ejecucion/etapa.html")
public class RegistroEjecucionEtapaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroEjecucionEtapaServlet() {
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

	private void procesarSolicitud(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		String id = request.getParameter("i");
		String fi = request.getParameter("fi");
		String ft = request.getParameter("ft");
		String cod = request.getParameter("cod");
		
		boolean x = ProdAndesOperario.darInstancia().registrarEjecucionEtapa(id,fi,ft,cod);
		
		if(x) {
			response.sendRedirect("/ProdAndes/pages/operario/success/etapa.html");
		} else {
			response.sendRedirect("/ProdAndes/pages/operario/error/etapa.html");
		}
	}
}
