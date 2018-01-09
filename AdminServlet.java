package com.FitnessCenter;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		System.out.println("inside admin");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",name);
		request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);
	}
}