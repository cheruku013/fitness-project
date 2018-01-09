package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")

public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String role  = request.getParameter("role");

		int validIdStat = 0;
		Connection connection = null;
		java.sql.Statement stmt = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");

			stmt =connection.createStatement();

			if(role.equals("admin"))
			{
				String dbId=null;
				String dbPwd=null;
				String dbName = null;
				ResultSet rs = stmt.executeQuery("select * from admin_table;");
				while ( rs.next() ) 
				{
					dbId = rs.getString(1).toLowerCase();
				    dbPwd = rs.getString(2).toLowerCase();
					dbName = rs.getString(3).toLowerCase();
					if(dbId.equals(id.toLowerCase()) && dbPwd.equals(pwd.toLowerCase()))
					{
						validIdStat = 1;	
						break;
					}
				}
				if(validIdStat ==1)
				{
					HttpSession session = request.getSession();
					session.setAttribute("role","admin");
					session.setAttribute("name", dbName);
					session.setAttribute("id", dbId);
					response.sendRedirect("admin");
					System.out.println("valid admin");
				}else
				{
					request.setAttribute("invalid","Admin ID  or Password invalid please try again");
					request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				}
				
			}else if(role.equals("customer"))
			{
				String dbId = null;
				String dbPwd = null;
				String dbName = null;
				ResultSet rs = stmt.executeQuery("select * from register_customer;");
				while ( rs.next() ) 
				{
					dbId = rs.getString(1).toLowerCase();
					dbPwd = rs.getString(2).toLowerCase();
					dbName = rs.getString(3).toLowerCase();

					if(dbId.equals(id.toLowerCase()) && dbPwd.equals(pwd.toLowerCase()))
					{
						validIdStat = 1;		
						break;
					}
				}
				if(validIdStat == 1)
				{
					HttpSession session = request.getSession();
					session.setAttribute("role","customer");
					session.setAttribute("name", dbName);
					session.setAttribute("id", dbId);
					response.sendRedirect("customer");
					System.out.println("valid customer");
				}else
				{
					request.setAttribute("invalid","Customer ID  or Password invalid please try again");
					request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				}
				
			}else if(role.equals("trainer"))
			{
				System.out.println("beforetrainer");
				System.out.println("inside trainer");
				ResultSet rs = stmt.executeQuery("select * from register_trainer;");
				String dbId = null;
				String dbPwd = null;
				String dbName =null;

				while ( rs.next() ) 
				{
					

					if(rs.getString(1).toLowerCase().equals(id.toLowerCase()) &&  rs.getString(2).toLowerCase().equals(pwd.toLowerCase()))
					{	dbId = rs.getString(1).toLowerCase();
						dbPwd = rs.getString(2).toLowerCase();
						dbName = rs.getString(3).toLowerCase();
						validIdStat = 1;
					}
				}

				if(validIdStat==1)
				{
					HttpSession session = request.getSession();
					session.setAttribute("role","trainer");
					session.setAttribute("name", dbName);
					session.setAttribute("id", dbId);
					System.out.println("valid trainer");
					response.sendRedirect("trainer");

					System.out.println("valid trainer");

				}else
				{
					request.setAttribute("invalid","Trainer ID  or Password invalid please try again");
					request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

				}
			}
		}catch (Exception e)
		{
			e.printStackTrace();			
		}



	}	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{

		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

	}
}
