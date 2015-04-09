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
import com.sistrans.mundo.Usuario;

/**
 * Servlet implementation class OperarioMasActivoServlet
 */
@WebServlet("/consulta/operariomasactivo.html")
public class ConsultarOperarioMasActivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarOperarioMasActivoServlet() {
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
	
	public void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String etapa = request.getParameter("etapa");
		String numOp = request.getParameter("number");
		
		ArrayList<Usuario> opers = ProdAndesAdmin.darInstancia().operarioMasActivo(etapa, numOp);
		int i = 1;
		if(opers.size() == 0) {
			out.println("<h1>No hay operarios en esta etapa</h1>");
		} else {
			for(Usuario user : opers) {
				out.println( (i) +") " + user.getNombre());
				i++;
			}
		}
		
	}
}
