package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/customerUpdateRoutineInfo")
public class CustomerUpdateRoutineInfo extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String logName = (String) session.getAttribute("name");
		String logId = (String) session.getAttribute("id");
		request.setAttribute("loggedInName",logName);
		if(logName!=null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm");

			String wrkDate = request.getParameter("wrkDate");
			String inTime = request.getParameter("inTime");
			String outTime = request.getParameter("outTime");
			String height = request.getParameter("height");
			String weight = request.getParameter("weight");

			Date javaDate = null;
			Date javaInTime = null;
			Date javaOutTime = null;
			try {
				javaInTime=timeFormatter.parse(inTime);
				javaOutTime=timeFormatter.parse(outTime);
				javaDate = formatter.parse(wrkDate);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
			java.sql.Time sqlInTime = new java.sql.Time(javaInTime.getTime());
			java.sql.Time sqlOutTime = new java.sql.Time(javaOutTime.getTime());

			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				
				String insertQuery = "insert into workoutroutine_user values(?,?,?,?,?,?);";
		        
				PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
				
				 prepStmt.setString(1, logId);
				 prepStmt.setDate(2, sqlDate);
				 prepStmt.setTime(3,sqlInTime);
				 prepStmt.setTime(4, sqlOutTime);
				 prepStmt.setString(5, weight);
				 prepStmt.setString(6, height);
				 
				 prepStmt.executeUpdate();
				 
				 prepStmt.close();
		         connection.close();
		         
		         response.sendRedirect("welcome");
				
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
