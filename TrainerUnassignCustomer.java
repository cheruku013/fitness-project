package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/trainerUnassignCustomer")
public class TrainerUnassignCustomer extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		
		String trId = (String) session.getAttribute("id");
		
		if(name!= null)
		{
			String custID = request.getParameter("custID");
			
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				stmt.executeUpdate("delete from customer_trainer_details where custID_fk='"+custID+"';");
				stmt.close();
				connection.close();
				response.sendRedirect("trainerMyCustomerDetails");

			}catch (Exception e)
			{
				e.printStackTrace();

			}
		}
	}
	
}
