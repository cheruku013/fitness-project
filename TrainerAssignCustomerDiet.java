package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/trainerCustomerDiet")

public class TrainerAssignCustomerDiet extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");

		String custID = request.getParameter("custID");
		if(name!= null)
		{
			request.setAttribute("loggedInName",name);
			request.setAttribute("custID",custID);

			request.getRequestDispatcher("/WEB-INF/views/trainerCustomerAssignDiet.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String trId = (String) session.getAttribute("id");

		if(name!= null)
		{
			String id = request.getParameter("id");
			String diet = request.getParameter("diet");
			String workout = request.getParameter("workout");

			Connection connection = null;
			java.sql.Statement stmt = null;

			try
			{
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");

				System.out.println("assign trainer session id:"+trId);
				
				String insertQuery = "insert into customer_trainer_details() values(?,?,?,?)";

				PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
				
				prepStmt.setString(1,trId);
				prepStmt.setString(2,id);
				prepStmt.setString(3,diet);
				prepStmt.setString(4,workout);

				prepStmt.executeUpdate();

				prepStmt.close();
				connection.close();

				response.sendRedirect("trainerCustomerDetails");

			}catch(Exception e)
			{
				e.printStackTrace();
			}

		}

	}
}
