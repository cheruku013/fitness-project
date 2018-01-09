package com.FitnessCenter;

import java.io.IOException;
import java.util.ArrayList;

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
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/adminUserDetails")
public class AdminUserDetails extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		if(sessionName!= null)
		{	
			
			ArrayList<ArrayList<String>> trainerRecords = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> customerRecords = new ArrayList<ArrayList<String>>();

			String loggedInName = null;
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select * from register_customer;");

				for(int x=0; rs.next();x++ ) 
				{
					ArrayList<String> custDetails = new ArrayList<String>();
					custDetails.add(0,rs.getString(1));
					custDetails.add(1,rs.getString(2));
					custDetails.add(2,rs.getString(3));
					custDetails.add(3,rs.getString(4));
					custDetails.add(4,rs.getString(5));
					custDetails.add(5,rs.getString(6));
					custDetails.add(6,rs.getString(7));
					custDetails.add(7,rs.getString(8));
					custDetails.add(8,rs.getString(9));
					custDetails.add(9,rs.getString(10));
					custDetails.add(10,rs.getString(11));
					custDetails.add(11,rs.getString(12));
					
					customerRecords.add(custDetails);
				}
				
				rs = stmt.executeQuery("select * from register_trainer;");
				for(int x=0; rs.next();x++ ) 
				{
					ArrayList<String> trDetails = new ArrayList<String>();
					trDetails.add(0,rs.getString(1));
					trDetails.add(1,rs.getString(2));
					trDetails.add(2,rs.getString(3));
					trDetails.add(3,rs.getString(4));
					trDetails.add(4,rs.getString(5));
					trDetails.add(5,rs.getString(6));
					trDetails.add(6,rs.getString(7));
					
					trainerRecords.add(trDetails);
				}
				
				//			HttpSession session = request.getSession();
				//			session.setAttribute("role","admin");
				//			session.setAttribute("name", loggedInName);
				
				
				connection.close();	
				request.setAttribute("loggedInName",sessionName);
				request.setAttribute("trainerRecords", trainerRecords);
				request.setAttribute("customerRecords", customerRecords);	



				request.getRequestDispatcher("/WEB-INF/views/adminUserDetails.jsp").forward(request, response);

			}catch (Exception e)
			{
				e.printStackTrace();

			}
		}

	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		if(sessionName!= null)
		{	
			
			ArrayList<ArrayList<String>> trainerRecords = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> customerRecords = new ArrayList<ArrayList<String>>();

			String loggedInName = null;
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select * from register_customer;");

				for(int x=0; rs.next();x++ ) 
				{
					ArrayList<String> custDetails = new ArrayList<String>();
					custDetails.add(0,rs.getString(1));
					custDetails.add(1,rs.getString(2));
					custDetails.add(2,rs.getString(3));
					custDetails.add(3,rs.getString(4));
					custDetails.add(4,rs.getString(5));
					custDetails.add(5,rs.getString(6));
					custDetails.add(6,rs.getString(7));
					custDetails.add(7,rs.getString(8));
					custDetails.add(8,rs.getString(9));
					custDetails.add(9,rs.getString(10));
					custDetails.add(10,rs.getString(11));
					custDetails.add(11,rs.getString(12));
					
					customerRecords.add(custDetails);
				}
				
				rs = stmt.executeQuery("select * from register_trainer;");
				for(int x=0; rs.next();x++ ) 
				{
					ArrayList<String> trDetails = new ArrayList<String>();
					trDetails.add(0,rs.getString(1));
					trDetails.add(1,rs.getString(2));
					trDetails.add(2,rs.getString(3));
					trDetails.add(3,rs.getString(4));
					trDetails.add(4,rs.getString(5));
					trDetails.add(5,rs.getString(6));
					trDetails.add(6,rs.getString(7));
					
					trainerRecords.add(trDetails);
				}
				
				//			HttpSession session = request.getSession();
				//			session.setAttribute("role","admin");
				//			session.setAttribute("name", loggedInName);
				
				

				request.setAttribute("loggedInName",sessionName);
				request.setAttribute("trainerRecords", trainerRecords);
				request.setAttribute("customerRecords", customerRecords);	



				request.getRequestDispatcher("/WEB-INF/views/adminUserDetails.jsp").forward(request, response);

			}catch (Exception e)
			{
				e.printStackTrace();

			}
		}

	}
}
