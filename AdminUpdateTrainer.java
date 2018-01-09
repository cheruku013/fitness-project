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

@WebServlet(urlPatterns = "/adminUpdateTrainer")

public class AdminUpdateTrainer extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		String sessionId = (String) session.getAttribute("id");
		
		String trID = request.getParameter("trID");
		Connection connection = null;
		java.sql.Statement stmt = null;

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");

			stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("select * from register_trainer where trID_pk ='"+trID+"';");
			
			rs.next();
						
			String trPwd = rs.getString(2);
			String trName = rs.getString(3);
			String trAge = String.valueOf(rs.getInt(4));
			String trGender = rs.getString(5);
			String trEmailId = rs.getString(6);
			String trPhone = rs.getString(7);
			
						
			connection.close();
			
			request.setAttribute("loggedInName",sessionName);
			request.setAttribute("trID",trID);
			request.setAttribute("trPwd",trPwd);
			request.setAttribute("trName",trName);
			request.setAttribute("trAge",trAge);
			request.setAttribute("trGender",trGender);
			request.setAttribute("trEmailId",trEmailId);
			request.setAttribute("trPhone",trPhone);
			
			
			request.getRequestDispatcher("/WEB-INF/views/adminTrainerUpdate.jsp").forward(request, response);

			
			
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
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int age =Integer.parseInt(request.getParameter("age"));	
		String gender = request.getParameter("gender");		
		String email = request.getParameter("email");
		String phone = request.getParameter("phno");
				
		Connection connection = null;
		java.sql.Statement stmt = null;

		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");

			String insertQuery = "UPDATE register_trainer SET trPwd = ?,trName = ?,trAge = ?,trGender = ?,trEmailId = ?,trPhone = ?" + " WHERE trID_pk = ?;";
	        
			PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
	         prepStmt.setString(1, pwd);
	         prepStmt.setString(2, name);
	         prepStmt.setInt(3, age);
	         prepStmt.setString(4, gender);
	         prepStmt.setString(5, email);
	         prepStmt.setString(6, phone);
	         prepStmt.setString(7, id);
	         
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
