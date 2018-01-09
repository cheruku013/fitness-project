package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/adminDeleteCustomer")

public class AdminDeleteCustomer extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		String custId = request.getParameter("custID");

		Connection connection = null;
		java.sql.Statement stmt = null;
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
			

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			String deleteQuery = "delete from payment_user where custID_fk =?";
			PreparedStatement prepStmt = connection.prepareStatement(deleteQuery);
			prepStmt.setString(1,custId);				 
			prepStmt.executeUpdate();

			String deleteQuery1 = "delete from workoutroutine_user where custID_fk =?";
			PreparedStatement prepStmt1 = connection.prepareStatement(deleteQuery1);
			prepStmt1.setString(1,custId);				 
			prepStmt1.executeUpdate();
			
			String deleteQuery2 = "delete from customer_trainer_details where custID_fk =?";
			PreparedStatement prepStmt2 = connection.prepareStatement(deleteQuery2);
			prepStmt2.setString(1,custId);				 
			prepStmt2.executeUpdate();

			String deleteQuery3 = "delete from register_customer where custID_pk =?";
			PreparedStatement prepStmt3 = connection.prepareStatement(deleteQuery3);
			prepStmt3.setString(1,custId);				 
			prepStmt3.executeUpdate();

			
			connection.close();

			response.sendRedirect("adminUserDetails");


		}catch (Exception e)
		{
			e.printStackTrace();			
		}
	}
}
