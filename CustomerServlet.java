package com.FitnessCenter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		System.out.println("inside customer");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		if(name!=null)
		{
		request.setAttribute("loggedInName",name);
		request.getRequestDispatcher("/WEB-INF/views/customer.jsp").forward(request, response);
		}else
		{
		 response.sendRedirect("welcome");	
		}
		
	}
}
