package com.sistrans.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistrans.dao.ConsultaDAOUsuario;
import com.sistrans.dao.ReceiveMessage;
import com.sistrans.dao.SendMessage;
import com.sistrans.dao.Zizas;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER = "juano";
	private static final String PASS = "123456";
	private static final String URL = "http-remoting://localhost:8080";
	private static final String QUEUE = "jms/queue/prodandesleer";
	public Zizas zz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

	public void procesarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		String action = request.getParameter("submit");
		String username = request.getParameter("username");
		
		if(action.equals("login"))
		{
			if(username.contains("admin")) {
				response.sendRedirect("./pages/admin/dashboard.html");
			} else if(username.contains("gerente")) {				
				response.sendRedirect("./pages/gerente/dashboard.html");
				prender();
			} else if(username.contains("cliente")) {
				response.sendRedirect("./pages/user/dashboard.html");
			} else if(username.contains("operario")) {
				response.sendRedirect("./pages/operario/dashboard.html");
			} else if(username.contains("proveedor")) {
				response.sendRedirect("./pages/welcome/proveedor.html");
			} else {
				PrintWriter out = response.getWriter();
				out.println("Error, los usuarios temporales disponibles son:");
				out.println();
				out.println("			-admin");
				out.println("			-gerente");
				out.println("			-cliente");
				out.println("			-operario");
				out.println();
				out.println("Recuerde que no es necesaria una contraseña.");
			}
		}
	}

	private void prender() {
		ReceiveMessage receiver = new ReceiveMessage(USER, PASS, URL, QUEUE);
		System.out.println("Listening...");
		while(true)
		 {
			zz = receiver.startReceiving();
			System.out.println(zz.newMessage);
			while(zz.newMessage) {
				System.out.println("Nuevo mensaje");
				String received = zz.darMensaje();
				ConsultaDAOUsuario dao = new ConsultaDAOUsuario();
				dao.inicializar();
				String x = dao.interpretarMensaje(received);
				if(x.equals(received)) {
					
				} else {
					System.out.println("Enviar Respuesta...");
					SendMessage sender = null;
					try{
						sender = new SendMessage(USER, PASS, URL, QUEUE);
					} catch(Exception e) {
						
					}
					sender.sendMessage(x);
					sender.closeSender();
				}
			}
			try {
				Thread.sleep(1000);
				receiver.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	}
}
