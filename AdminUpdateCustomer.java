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



@WebServlet(urlPatterns = "/adminUpdateCustomer")

public class AdminUpdateCustomer extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		
		String custId = request.getParameter("custID");
		Connection connection = null;
		java.sql.Statement stmt = null;

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("select * from register_customer where custID_pk ='"+custId+"';");
			
			rs.next();
						
			String custPwd = rs.getString(2);
			String custName = rs.getString(3);
			String custAge = String.valueOf(rs.getInt(4));
			String custDob = rs.getDate(5).toString();
			String custGender = rs.getString(6);
			String custAddr = rs.getString(7);
			String custMail = rs.getString(8);
			String custPhone = rs.getString(9);
			String custHeight = String.valueOf(rs.getFloat(10));
			String custWeight = String.valueOf(rs.getFloat(11));
			String custFee = rs.getString(12);
						
			connection.close();
			
			request.setAttribute("loggedInName",sessionName);
			request.setAttribute("custId",custId);
			request.setAttribute("custPwd",custPwd);
			request.setAttribute("custName",custName);
			request.setAttribute("custAge",custAge);
			request.setAttribute("custDob",custDob);
			request.setAttribute("custGender",custGender);
			request.setAttribute("custAddr",custAddr);
			request.setAttribute("custMail",custMail);
			request.setAttribute("custPhone",custPhone);
			request.setAttribute("custHeight",custHeight);
			request.setAttribute("custWeight",custWeight);
			request.setAttribute("custFee",custFee);
			
			request.getRequestDispatcher("/WEB-INF/views/adminCustomerUpdate.jsp").forward(request, response);

			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		//String sessionId = (String) session.getAttribute("id");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int age =Integer.parseInt(request.getParameter("age"));
		Date dob = null;
		try {
			dob = formatter.parse(request.getParameter("dob"));
			System.out.println("DOB:"+dob.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
		String gender = request.getParameter("gender");
		String address = request.getParameter("addr");
		String email = request.getParameter("email");
		String phone = request.getParameter("phno");
		float height = Float.parseFloat(request.getParameter("height"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		String fee = request.getParameter("fee");
		
		
		
		Connection connection = null;
		java.sql.Statement stmt = null;

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");

			String insertQuery = "UPDATE register_customer SET custPwd = ?,custName = ?,custAge = ?,custDOB = ?,custGender = ?,custAddr=?,custEmailId = ?,custPhone = ?,custHeight = ?,custWeight = ?,custFeeType = ?" + " WHERE custID_pk = ?;";
	        
			PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
	         prepStmt.setString(1, pwd);
	         prepStmt.setString(2, name);
	         prepStmt.setInt(3, age);
	         prepStmt.setDate(4, sqlDate);
	         prepStmt.setString(5, gender);
	         prepStmt.setString(6, address);
	         prepStmt.setString(7, email);
	         prepStmt.setString(8, phone);
	         prepStmt.setFloat(9, height);
	         prepStmt.setFloat(10, weight);
	         prepStmt.setString(11, fee);
	         prepStmt.setString(12, id);
	         
	         prepStmt.executeUpdate();
	         
	         prepStmt.close();
	         connection.close();
	         	         
	         response.sendRedirect("adminUserDetails");
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
