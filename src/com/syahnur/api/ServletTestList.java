package com.syahnur.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class ServletTestList
 */
@WebServlet("/ServletTestList")
public class ServletTestList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("structure_id") == null && request.getParameter("dataset_id") == null) {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println("{\"message\" : \"You're fucked\"}");
		} else {
			ConnectionManager cm = new ConnectionManager();
			ResultSet myRs = null;
			String sql = "SELECT * FROM dataset WHERE structure_id = ? AND NOT dataset_id = ?";
			PreparedStatement psmt = cm.getPreparedStatement(sql);
			try {
				psmt.setInt(1, Integer.parseInt(request.getParameter("structure_id")));
				psmt.setInt(2, Integer.parseInt(request.getParameter("dataset_id")));
				myRs = psmt.executeQuery();
				if (myRs != null) {
					String list = "{ \"dataset\" : [";				
					while(myRs.next()) {
						list += "{ \"id\" : \"" + myRs.getInt("dataset_id") + "\", \"name\" : \"" + myRs.getString("dataset_name") + "\"},";
					}
					
					list = list.substring(0, list.length() - 1);
					list += "]}";
					
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.println(list);
				} else {
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.println("{\"message\" : \"You're fucked\"}"); 
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
