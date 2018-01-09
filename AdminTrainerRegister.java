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
@WebServlet(urlPatterns = "/adminTrainerReg")
public class AdminTrainerRegister  extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
		HttpSession session = request.getSession();
		String LogName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",LogName);
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender =request.getParameter("gender");
		String email = request.getParameter("email");
		String phno = request.getParameter("phno");
		
		int duplicateIdStat = 0;
		Connection connection = null;
		java.sql.Statement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
			System.out.println("Opened database successfully");

			stmt =connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from register_trainer;");
			while ( rs.next() ) 
			{
				String dbId = rs.getString(1).toLowerCase();
				System.out.println("ID:"+dbId);
				if(dbId.equals(id.toLowerCase()))
				{
					duplicateIdStat = 1;
				}
			}
			
			
			if(duplicateIdStat == 1)
			{
				request.setAttribute("duplicateId","Trainer Id already exist");
				request.getRequestDispatcher("/WEB-INF/views/adminUserCreate.jsp").forward(request, response);
			}else
			{
				/* to insert into user table */
				
		         String insertQuery = "insert into register_trainer(trID_pk,trPwd,trName,trAge,trGender,trEmailId,trPhone)"
		               + " values(?,?,?,?,?,?,?)";
		         PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
		        
		         prepStmt.setString(1, id);
		         prepStmt.setString(2, pwd);
		         prepStmt.setString(3, name);
		         prepStmt.setInt(4, age);
		         prepStmt.setString(5,gender);
		         prepStmt.setString(6, email);
		         prepStmt.setString(7, phno);
		         prepStmt.executeUpdate();
		         
		        
		         System.out.println("registration success");
		         request.setAttribute("duplicateId",id+" Trainer registration success.");
		         request.getRequestDispatcher("/WEB-INF/views/adminUserCreate.jsp").forward(request, response);
			}
			
			stmt.close();
			connection.close();
			System.out.println("closed data base connection");			
		
		} catch (Exception e)
		{
			e.printStackTrace();			
		}
		
		
	}
}
