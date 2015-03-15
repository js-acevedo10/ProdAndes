package com.sistrans.servlets.modificacion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.fachada.ProdAndesGerente;

/**
 * Servlet implementation class RegistroLlegadaMaterial
 */
@WebServlet("/registro/llegadamaterial.html")
public class RegistroLlegadaMaterial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroLlegadaMaterial() {
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

	private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String tipo = request.getParameter("t");
		String nombre = request.getParameter("n");
		String cantidad = request.getParameter("c");
		
		
		switch(tipo) {
			case "materia-prima":
				String tonelada = request.getParameter("u");
				registrarMateriaPrima(nombre, cantidad, tonelada, response);
				break;
			case "componente":
				String unidadMedida = request.getParameter("u");
				registrarComponente(nombre, cantidad, unidadMedida, response);
				break;
			default:
				break;
		}
	}

	private void registrarMateriaPrima(String nombre, String cantidad,
			String tonelada, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		boolean reg = ProdAndesGerente.darInstancia().registrarMateriaPrima(nombre, cantidad, tonelada);
		if(reg) {
			resp.sendRedirect("/ProdAndes/pages/gerente/success/registromateriaprima.html");
		} else {
			resp.sendRedirect("/ProdAndes/pages/gerente/error/registromateriaprima.html");
		}
	}

	private void registrarComponente(String nombre, String cantidad,
			String unidadMedida, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		boolean reg = ProdAndesGerente.darInstancia().registrarComponente(nombre, cantidad, unidadMedida);
		if(reg) {
			resp.sendRedirect("/ProdAndes/pages/gerente/success/registrocomponente.html");
		} else {
			resp.sendRedirect("/ProdAndes/pages/gerente/error/registrocomponente.html");
		}
	}
}
