package tpFinal.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletLogout extends ServletUtils {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setRequestResponse(request, response);
		invalidarSesion();
		redirectLogin();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setRequestResponse(request, response);
		invalidarSesion();
		redirectLogin();
	}
}
