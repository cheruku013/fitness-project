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

@WebServlet(urlPatterns = "/customerProgress")
public class CustomerProgress extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String id = (String) session.getAttribute("id");
		request.setAttribute("loggedInName",name);
		if(name!=null)
		{
			ArrayList<ArrayList<String>> myCustomerRecords = new ArrayList<ArrayList<String>>();
			ArrayList<String> myCustIds = new ArrayList<String>();
			
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from workoutroutine_user where custID_fk='"+id+"';");
				while ( rs.next() ) 
				{
					ArrayList<String> custRecords = new ArrayList<String>();
					
					custRecords.add(0, rs.getString(2));
					custRecords.add(1, rs.getString(3));
					custRecords.add(2, rs.getString(4));
					custRecords.add(3, rs.getString(5));
					custRecords.add(4, rs.getString(6));
					myCustomerRecords.add(custRecords);
				}
				connection.close();	
				request.setAttribute("loggedInName",name);
				request.setAttribute("customerRecords", myCustomerRecords);	

				request.getRequestDispatcher("/WEB-INF/views/customerProgress.jsp").forward(request, response);


			}catch (Exception e)
			{
				e.printStackTrace();			
			}

		}else
		{
			response.sendRedirect("welcome");	
		}
	}
}
