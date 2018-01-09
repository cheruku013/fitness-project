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

@WebServlet(urlPatterns = "/customerWorkoutSchedule")
public class CustomerWorkoutType extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String id = (String) session.getAttribute("id");
		
		if(name!= null)
		{
			ArrayList<ArrayList<String>> workOutRecords = new ArrayList<ArrayList<String>>();
			
			Connection connection = null;
			java.sql.Statement stmt = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
				stmt =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = stmt.executeQuery("select trID_fk,dietType, workoutType from customer_trainer_details where custID_fk='"+id+"';");
				
				rs.next();
				ArrayList<String> wrkRec = new ArrayList<String>();
				wrkRec.add(0,rs.getString(1));
				wrkRec.add(1,rs.getString(2));
				wrkRec.add(1,rs.getString(3));
				workOutRecords.add(wrkRec);
				
				connection.close();	
				request.setAttribute("loggedInName",name);
				request.setAttribute("workOutRecords", workOutRecords);	

				request.getRequestDispatcher("/WEB-INF/views/customerWorkout.jsp").forward(request, response);

				
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
