package com.FitnessCenter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/customerUpdateRoutine")
public class CustomerUpdateRoutine extends HttpServlet{

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		if(name!=null)
		{
		request.setAttribute("loggedInName",name);
		request.getRequestDispatcher("/WEB-INF/views/customerUpdateRoutine.jsp").forward(request, response);
		}else
		{
		 response.sendRedirect("welcome");	
		}
		
	}
}
