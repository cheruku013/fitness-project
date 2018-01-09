package com.FitnessCenter;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
		//request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		
		HttpSession session = request.getSession();
		//String role = (String) session.getAttribute("role");
		
		if(session.getAttribute("role") == null)
		{
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		}else if(session.getAttribute("role").equals("admin"))
		{
			response.sendRedirect("admin");
			
		}else if(session.getAttribute("role").equals("customer"))
		{
			response.sendRedirect("customer");
			
		}else if(session.getAttribute("role").equals("trainer"))
		{
			response.sendRedirect("trainer");
		}
	}

}
