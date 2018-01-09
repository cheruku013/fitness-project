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

@WebServlet(urlPatterns = "/adminCustomerReg")
public class AdminCustomerRegister  extends HttpServlet{

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		HttpSession session = request.getSession();
		String logName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",logName);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int age =Integer.parseInt(request.getParameter("age"));
		Date dob = null;
		try {
			
			dob = formatter.parse(request.getParameter("dob"));
			
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
		
		int duplicateIdStat = 0;
		Connection connection = null;
		java.sql.Statement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
	

			stmt =connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from register_customer;");
			while ( rs.next() ) 
			{
				String dbId = rs.getString(1).toLowerCase();
				
				if(dbId.equals(id.toLowerCase()))
				{
					duplicateIdStat = 1;
				}
			}
			
			
			if(duplicateIdStat == 1)
			{
				request.setAttribute("duplicateId","Customer Id already exist");
				request.getRequestDispatcher("/WEB-INF/views/adminUserCreate.jsp").forward(request, response);
			}else
			{
				/* to insert into user table */
				
		         String insertQuery = "insert into register_customer(custID_pk,custPwd,custName,custAge,custDOB,custGender,custAddr,custEmailId,custPhone,custHeight,custWeight,custFeeType)"
		               + " values(?,?,?,?,?,?,?,?,?,?,?,?)";
		         PreparedStatement prepStmt = connection.prepareStatement(insertQuery);
		        
		         prepStmt.setString(1, id);
		         prepStmt.setString(2, pwd);
		         prepStmt.setString(3, name);
		         prepStmt.setInt(4, age);
		         
		         prepStmt.setDate(5,sqlDate);
		         prepStmt.setString(6, gender);
		         prepStmt.setString(7, address);
		         prepStmt.setString(8, email);
		         prepStmt.setString(9, phone);
		         prepStmt.setFloat(10, height);
		         prepStmt.setFloat(11, weight);
		         prepStmt.setString(12, fee);
		         
		         prepStmt.executeUpdate();
		         
		        
		         System.out.println("registration success");
		         request.setAttribute("duplicateId",id+" Customer registration success");
		         request.getRequestDispatcher("/WEB-INF/views/adminUserCreate.jsp").forward(request, response);
			}
			
			stmt.close();
			connection.close();
			System.out.println("closed data base connection");			
		
		}catch (Exception e)
		{
			e.printStackTrace();			
		}
	}
}
