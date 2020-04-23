package com.mywebsite;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessInfo
 */
@WebServlet("/ProcessInfo")
public class ProcessInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/DisplayInfo.jsp";
		String usersName = request.getParameter("name");
		request.setAttribute("usersName", usersName);
		getServletContext()
		.getRequestDispatcher(url)
		.forward(request, response);
	}

	
	protected void updateDB(String fName, String lName, String phone) {
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/test1";
			String user = "dbadmin";
			String pw = "javatut1";
			// Used to issue queries to the DB
	        con = DriverManager.getConnection(url, user, pw);
	        
	        // Sends queries to the DB for results
	        Statement s = con.createStatement();
	        
	        // Add a new entry
	        String query = "INSERT INTO CUSTOMER " + 
	        "(first_name, last_name, phone, cust_id) " + 
	        "VALUES ('" + fName + "', '" + lName + "', '" +
	        phone + "', NULL)";
	        
	        // Execute the Query
	        s.executeUpdate(query);
	        
	        // Close DB connection
	        con.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
