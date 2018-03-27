package com.syahnur.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class ServletDataset
 */
@WebServlet("/ServletDataset")
public class ServletDataset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDataset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager cm = new ConnectionManager();
		String sql = "SELECT * FROM dataset";
		ResultSet myRs = null;
		String jsonString = "";
		boolean success = false;
		String message = "";
		try {
			PreparedStatement psmt = cm.getPreparedStatement(sql);
			myRs = psmt.executeQuery();
			if(!myRs.first()) {
				jsonString += "{ \"success\" : false, \"Message\" : \"No dataset found\"}";
			} else {
				jsonString ="{\"dataset\" : [";
				do {
					jsonString += "{\"id\" : "+myRs.getInt("dataset_id")+", \"name\" : \""+myRs.getString("dataset_name")+"\", \"structure_id\" : "+myRs.getInt("structure_id")+"},";
				} while(myRs.next());
				jsonString = jsonString.substring(0, jsonString.length() - 1);
				jsonString += "]}";
			}
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(jsonString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
