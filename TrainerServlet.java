package com.FitnessCenter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/trainer")
public class TrainerServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		System.out.println("inside trainer");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",name);
		request.getRequestDispatcher("/WEB-INF/views/trainer.jsp").forward(request, response);
	}
}