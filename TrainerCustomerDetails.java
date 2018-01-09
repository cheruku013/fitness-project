package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/trainerCustomerDetails")
public class TrainerCustomerDetails extends HttpServlet{

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String trId = (String) session.getAttribute("id");
		
		if(name!= null)
		{	
			ArrayList<ArrayList<String>> unassignedRecords = new ArrayList<ArrayList<String>>();
			ArrayList<String> assignedIds = new ArrayList<String>();
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select custID_fk from customer_trainer_details;");
				
				for(int x=0; rs.next();x++ ) 
				{
					assignedIds.add(x,rs.getString(1));					
				}
				
				rs = stmt.executeQuery("select * from register_customer;");
				while(rs.next()) 
				{
					if(!assignedIds.contains(rs.getString(1)))
					{
						ArrayList<String> custDetails = new ArrayList<String>();
						custDetails.add(0,rs.getString(1));
						custDetails.add(1,rs.getString(3));
						custDetails.add(2,rs.getString(4));
						custDetails.add(3,rs.getString(5));
						custDetails.add(4,rs.getString(6));
						custDetails.add(5,rs.getString(7));
						custDetails.add(6,rs.getString(8));
						custDetails.add(7,rs.getString(9));
						custDetails.add(8,rs.getString(10));
						custDetails.add(9,rs.getString(11));
						
						unassignedRecords.add(custDetails);
					}					
				}
				connection.close();	
				request.setAttribute("loggedInName",name);
				request.setAttribute("customerRecords", unassignedRecords);	

				request.getRequestDispatcher("/WEB-INF/views/trainerCustomerDetails.jsp").forward(request, response);

				
				
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
		String name = (String) session.getAttribute("name");
		if(name!= null)
		{	
			ArrayList<ArrayList<String>> unassignedRecords = new ArrayList<ArrayList<String>>();
			ArrayList<String> assignedIds = new ArrayList<String>();
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select custID_fk from customer_trainer_details;");
				for(int x=0; rs.next();x++ ) 
				{
					assignedIds.add(x,rs.getString(1));					
				}
				
				rs = stmt.executeQuery("select * from register_customer;");
				while(rs.next()) 
				{
					if(!assignedIds.contains(rs.getString(1)))
					{
						ArrayList<String> custDetails = new ArrayList<String>();
						custDetails.add(0,rs.getString(1));
						custDetails.add(1,rs.getString(3));
						custDetails.add(2,rs.getString(4));
						custDetails.add(3,rs.getString(5));
						custDetails.add(4,rs.getString(6));
						custDetails.add(5,rs.getString(7));
						custDetails.add(6,rs.getString(8));
						custDetails.add(7,rs.getString(9));
						custDetails.add(8,rs.getString(10));
						custDetails.add(9,rs.getString(11));
						
						unassignedRecords.add(custDetails);
					}					
				}
				connection.close();	
				request.setAttribute("loggedInName",name);
				request.setAttribute("customerRecords", unassignedRecords);	

				request.getRequestDispatcher("/WEB-INF/views/trainerCustomerDetails.jsp").forward(request, response);

				
				
			}catch (Exception e)
			{
				e.printStackTrace();

			}
			
			
		}
		
	}
}
