package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/customerFeeUpdate")
public class CustomerFeeUpdate extends HttpServlet{

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String id = (String) session.getAttribute("id");
		request.setAttribute("loggedInName",name);
		if(name!=null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String payDate = request.getParameter("paidDate");
			
			Date javaDate = null;
			try {
				
				javaDate = formatter.parse(payDate);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date paidSqlDate = new java.sql.Date(javaDate.getTime());
			//System.out.println("PaidSqlDate:"+paidSqlDate.toString());
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				
				int custIdExistFlag=0;
				stmt = connection.createStatement();
				
				ResultSet rs = stmt.executeQuery("select custID_fk from payment_user;");
				while ( rs.next() ) 
				{
					if(rs.getString(1).equals(id)){
						custIdExistFlag = 1;
					}
				}
				
				
				if(custIdExistFlag == 1)
				{
					String insertQuery = "UPDATE payment_user SET lastPaidDate = ?" + " WHERE custID_fk = ?;";
					PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
					prepStmt.setDate(1, paidSqlDate);
					prepStmt.setString(2, id);
					prepStmt.executeUpdate();
					prepStmt.close();
				}else
				{
					String insertQuery = "insert into payment_user(custID_fk,lastPaidDate) values(?,?)";
					PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
					prepStmt.setString(1,id);
					prepStmt.setDate(2, paidSqlDate);
					prepStmt.executeUpdate();
					prepStmt.close();
				}
				 
		         connection.close();
		         
		         response.sendRedirect("welcome");
				
				
			}catch (Exception e)
			{
				e.printStackTrace();			
			}
			//request.getRequestDispatcher("/WEB-INF/views/customerFeePayment.jsp").forward(request, response);
		}else
		{
			response.sendRedirect("welcome");	
		}

	}

}
