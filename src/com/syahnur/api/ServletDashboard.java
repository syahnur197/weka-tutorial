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
 * Servlet implementation class ServletDashboard
 */
@WebServlet("/ServletDashboard")
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager cm = new ConnectionManager();
		String sql = "SELECT t.task_id, t.task_name, d1.dataset_id AS train_dataset_id, d1.dataset_name AS train_dataset_name, d2.dataset_id AS test_dataset_id, d2.dataset_name AS test_dataset_name FROM task t JOIN dataset d1 ON d1.dataset_id = t.train_dataset_id JOIN dataset d2 ON d2.dataset_id = t.test_dataset_id";
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
				jsonString ="{\"task\" : [";
				do {
					jsonString += "{\"id\" : "+myRs.getInt("task_id")+",\"name\" : \""+myRs.getString("task_name")+"\", \"train_id\" : "+myRs.getInt("train_dataset_id")+" ,\"train\" : \""+myRs.getString("train_dataset_name")+"\", \"test_id\" : "+myRs.getInt("test_dataset_id")+",\"test\" : \""+myRs.getString("test_dataset_name")+"\"},";
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
