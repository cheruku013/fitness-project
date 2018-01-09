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

@WebServlet(urlPatterns = "/adminFeeUpdateCustomer")

public class AdminCustomerFeeUpdate extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");

		String custId = request.getParameter("custID");
		//System.out.println("inside fee update id:"+custId);

		request.setAttribute("loggedInName",sessionName);

		request.setAttribute("custId", custId);	

		request.getRequestDispatcher("/WEB-INF/views/adminUserFeeUpdate.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");

		String custId = request.getParameter("id");
		String custPay = request.getParameter("payDate");

		Date payDate = null;
		try {

			payDate = formatter.parse(custPay);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlPayDate = new java.sql.Date(payDate.getTime());

		Connection connection = null;
		java.sql.Statement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");

			stmt =connection.createStatement();

			int custIdExistFlag=0;

			ResultSet rs = stmt.executeQuery("select custID_fk from payment_user;");
			while ( rs.next() ) 
			{
				if(rs.getString(1).equals(custId)){
					custIdExistFlag = 1;
				}
			}

			if(custIdExistFlag == 1)
			{
				String insertQuery = "UPDATE payment_user SET lastPaidDate = ?" + " WHERE custID_fk = ?;";
				PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
				prepStmt.setDate(1, sqlPayDate);
				prepStmt.setString(2, custId);
				prepStmt.executeUpdate();
				prepStmt.close();
			}else
			{
				String insertQuery = "insert into payment_user(custID_fk,lastPaidDate) values(?,?)";
				PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
				prepStmt.setString(1,custId);
				prepStmt.setDate(2, sqlPayDate);
				prepStmt.executeUpdate();
				prepStmt.close();
			}
			connection.close();

			response.sendRedirect("adminUserFeeDue");

		}catch (Exception e)
		{
			e.printStackTrace();			
		}



	}

}
