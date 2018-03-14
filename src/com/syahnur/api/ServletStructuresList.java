package com.syahnur.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class ServletStructuresList
 */
@WebServlet("/ServletStructuresList")
public class ServletStructuresList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletStructuresList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		        
		ConnectionManager cm = new ConnectionManager();
		Boolean success = cm.get("structure");
		ResultSet myRs = cm.getResult();
		
		String jsonString = "{ \"structure\" : [";
		try {
			do {
				jsonString += "{ \"ID\" : "+myRs.getInt("structure_id")+", \"name\" : \"" + myRs.getString("structure_name") +"\", \"string\" : \"" + myRs.getString("structure_string") +"\" },";
			} while (myRs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jsonString = jsonString.substring(0, jsonString.length() - 1);
		jsonString += "]}";
		
//		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
