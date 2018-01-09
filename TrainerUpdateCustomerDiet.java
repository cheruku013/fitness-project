package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/trainerUpdateCustomerDiet")
public class TrainerUpdateCustomerDiet extends HttpServlet{

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

				ResultSet rs = stmt.executeQuery("select * from customer_trainer_details where custID_fk='"+custID+"';");

				rs.next();

				String custDiet = rs.getString(3);
				String custWorkOut = rs.getString(4);

				request.setAttribute("loggedInName",name);
				request.setAttribute("custID",custID);
				request.setAttribute("custDiet",custDiet);
				request.setAttribute("custWorkOut",custWorkOut);
				stmt.close();
				connection.close();

				System.out.println("custDiet:"+custDiet+"' custWorkout:"+custWorkOut);
				request.getRequestDispatcher("/WEB-INF/views/trainerCustomerUpdateDiet.jsp").forward(request, response);
			}catch (Exception e)
			{
				e.printStackTrace();

			}
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
			try {

				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				String insertQuery = "UPDATE customer_trainer_details SET dietType = ?,workOutType = ? WHERE custID_fk = ?;";

				PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
				prepStmt.setString(1, diet);
				prepStmt.setString(2, workout);
				prepStmt.setString(3, id);
				prepStmt.execute();
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
