package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.StringLiteral;

@WebServlet(urlPatterns = "/adminUserBirthday")
public class AdminBirthdayUser extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		ArrayList<ArrayList<String>> customerRecords = new ArrayList<ArrayList<String>>();

		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",sessionName);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String strDate = formatter.format(date);
		
		
		Connection connection = null;
		java.sql.Statement stmt = null;
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
			stmt =connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from register_customer;");
			
			
			for(int x=0;rs.next();x++)
			{
				
				System.out.println("currDate:"+strDate+" bday:"+rs.getString(5));
				
				String currDateSplit[] = strDate.split("-");
				String dbDateSplit[] = rs.getString(5).split("-");
				if(currDateSplit[1].equals(dbDateSplit[1]) && currDateSplit[2].equals(dbDateSplit[2]))
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
				
			
				
			}
			
			connection.close();
			request.setAttribute("loggedInName",sessionName);
			request.setAttribute("customerRecords", customerRecords);	

			request.getRequestDispatcher("/WEB-INF/views/adminCustomerBirthday.jsp").forward(request, response);

		}catch (Exception e)
		{
			e.printStackTrace();			
		}
		
		
	}
}
