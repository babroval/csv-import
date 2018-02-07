package babroval.testtask.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exception.htm")
public class ExceptionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_INFO = "WEB-INF/pages/info.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

		request.setAttribute("error", throwable.getMessage());

		RequestDispatcher rd = request.getRequestDispatcher(VIEW_INFO);
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
