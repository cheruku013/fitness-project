package com.FitnessCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/adminUserFeeDue")
public class AdminFeeDueUser extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		ArrayList<ArrayList<String>> unpaidCustRecords = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> allCustRecords = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> payTblCustRecords = new ArrayList<ArrayList<String>>();

		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",sessionName);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String curDate = formatter.format(date);

		Connection connection = null;
		java.sql.Statement stmt = null;
		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
			stmt =connection.createStatement();

			ResultSet rs = stmt.executeQuery("select * from register_customer;");


			for(int x=0;rs.next();x++)
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
				allCustRecords.add(custDetails);
			}
			rs = stmt.executeQuery("select * from payment_user;");
			for(int x=0;rs.next();x++)
			{
				ArrayList<String> custDetails = new ArrayList<String>();
				custDetails.add(0,rs.getString(1));
				custDetails.add(1,rs.getString(2));
				payTblCustRecords.add(custDetails);
			}

			for(int x=0; x<allCustRecords.size();x++)
			{
				ArrayList<String> custDetails = new ArrayList<String>();
				custDetails = allCustRecords.get(x);
				ArrayList<String> reqRecords = new ArrayList<String>();
				String cusTableId = custDetails.get(0);
				String cusTablePack = custDetails.get(11);
				reqRecords.add(0,custDetails.get(0));
				reqRecords.add(1,custDetails.get(2));
				reqRecords.add(2,custDetails.get(6));
				reqRecords.add(3,custDetails.get(7));
				reqRecords.add(4,custDetails.get(8));
				reqRecords.add(5,custDetails.get(11));

				int matchedStat =0;
				//System.out.println("cust IDS:"+custDetails.get(0));

				if(payTblCustRecords.size()>0)
				{
					String dbDate = null;
					String payTableId = null;
					//System.out.println("cpay rec size:"+payTblCustRecords.size());	
					for(int y=0; y<payTblCustRecords.size();y++)
					{

						ArrayList<String> payTbDetails = new ArrayList<String>();
						payTbDetails = payTblCustRecords.get(y);
						payTableId = payTbDetails.get(0);
						//System.out.println("payment IDS:"+payTbDetails.get(0));

						if(cusTableId.equals(payTableId))
						{
							matchedStat =1;
							dbDate = payTbDetails.get(1);
							System.out.println("matched cusTableId:"+cusTableId +"payTableId"+payTableId);

						}
					}

					if(matchedStat==1)
					{
						if(cusTablePack.equals("monthly"))
						{

							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=30)
							{
								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}

						}else if(cusTablePack.equals("quaterly"))
						{
							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=90)
							{

								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}

						}else if(cusTablePack.equals("halfyear"))
						{
							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=182)
							{

								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}
						}else if(custDetails.get(11).equals("yearly"))
						{
							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=365)
							{

								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}
						}

					}else
					{
						reqRecords.add(6,"NULL");
						unpaidCustRecords.add(reqRecords);	
					}
				}else
				{
					//System.out.println("cust table: "+custDetails.get(0));

					reqRecords.add(6,"NULL");
					unpaidCustRecords.add(reqRecords);
				}


			}


			connection.close();
			request.setAttribute("loggedInName",sessionName);
			request.setAttribute("customerRecords", unpaidCustRecords);	

			request.getRequestDispatcher("/WEB-INF/views/adminUserFee.jsp").forward(request, response);

		}catch (Exception e)
		{
			e.printStackTrace();			
		}
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		ArrayList<ArrayList<String>> unpaidCustRecords = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> allCustRecords = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> payTblCustRecords = new ArrayList<ArrayList<String>>();

		HttpSession session = request.getSession();
		String sessionName = (String) session.getAttribute("name");
		request.setAttribute("loggedInName",sessionName);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String curDate = formatter.format(date);

		Connection connection = null;
		java.sql.Statement stmt = null;
		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesscenterdb","root", "root");
			stmt =connection.createStatement();

			ResultSet rs = stmt.executeQuery("select * from register_customer;");


			for(int x=0;rs.next();x++)
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
				allCustRecords.add(custDetails);
			}
			rs = stmt.executeQuery("select * from payment_user;");
			for(int x=0;rs.next();x++)
			{
				ArrayList<String> custDetails = new ArrayList<String>();
				custDetails.add(0,rs.getString(1));
				custDetails.add(1,rs.getString(2));
				payTblCustRecords.add(custDetails);
			}

			for(int x=0; x<allCustRecords.size();x++)
			{
				ArrayList<String> custDetails = new ArrayList<String>();
				custDetails = allCustRecords.get(x);
				ArrayList<String> reqRecords = new ArrayList<String>();
				String cusTableId = custDetails.get(0);
				String cusTablePack = custDetails.get(11);
				reqRecords.add(0,custDetails.get(0));
				reqRecords.add(1,custDetails.get(2));
				reqRecords.add(2,custDetails.get(6));
				reqRecords.add(3,custDetails.get(7));
				reqRecords.add(4,custDetails.get(8));
				reqRecords.add(5,custDetails.get(11));

				int matchedStat =0;
				//System.out.println("cust IDS:"+custDetails.get(0));

				if(payTblCustRecords.size()>0)
				{
					String dbDate = null;
					String payTableId = null;
					//System.out.println("cpay rec size:"+payTblCustRecords.size());	
					for(int y=0; y<payTblCustRecords.size();y++)
					{

						ArrayList<String> payTbDetails = new ArrayList<String>();
						payTbDetails = payTblCustRecords.get(y);
						payTableId = payTbDetails.get(0);
						//System.out.println("payment IDS:"+payTbDetails.get(0));

						if(cusTableId.equals(payTableId))
						{
							matchedStat =1;
							dbDate = payTbDetails.get(1);
							System.out.println("matched cusTableId:"+cusTableId +"payTableId"+payTableId);

						}
					}

					if(matchedStat==1)
					{
						if(cusTablePack.equals("monthly"))
						{

							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=30)
							{
								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}

						}else if(cusTablePack.equals("quaterly"))
						{
							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=90)
							{

								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}

						}else if(cusTablePack.equals("halfyear"))
						{
							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=182)
							{

								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}
						}else if(custDetails.get(11).equals("yearly"))
						{
							long days =0;
							try {
								Date date1 = formatter.parse(curDate);
								Date date2 = formatter.parse(dbDate);
								long diff = date1.getTime() - date2.getTime();
								days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(days>=365)
							{

								reqRecords.add(6,dbDate);
								unpaidCustRecords.add(reqRecords);
								//payTblCustRecords.remove(y);
							}
						}

					}else
					{
						reqRecords.add(6,"NULL");
						unpaidCustRecords.add(reqRecords);	
					}
				}else
				{
					//System.out.println("cust table: "+custDetails.get(0));

					reqRecords.add(6,"NULL");
					unpaidCustRecords.add(reqRecords);
				}


			}


			connection.close();
			request.setAttribute("loggedInName",sessionName);
			request.setAttribute("customerRecords", unpaidCustRecords);	

			request.getRequestDispatcher("/WEB-INF/views/adminUserFee.jsp").forward(request, response);

		}catch (Exception e)
		{
			e.printStackTrace();			
		}
	}
	
}
