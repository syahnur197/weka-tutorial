package com.datamining.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syahnur.jsp.CSVWriter;
import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class TaskUpdate
 */
@WebServlet("/TaskUpdate")
public class TaskUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("message", "403: Forbidden Access");
		session.setAttribute("success", false);
		response.sendRedirect("index.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		String message = "";
		boolean success = false;
		
		ConnectionManager cm = new ConnectionManager();
		PreparedStatement psmt = null;
		ResultSet myRs = null;
		String sql = "";
		
		try {
			sql = "SELECT * FROM task WHERE task_id = ?";
			psmt = cm.getPreparedStatement(sql);
			psmt.setInt(1, task_id);
			myRs = psmt.executeQuery();
			
			if(!myRs.first()) {
				message = "No task found!";
				success = false;
				
				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				session.setAttribute("success", success);
				response.sendRedirect("index.jsp");	
			} else {
				String taskName = myRs.getString("task_name");
				InputStream is = null;
				CSVWriter cw = new CSVWriter(request);
				String uploadData = "D:/home/site/wwwroot/webapps/ROOT/assets/files/uploadTask.csv";
				cw.createCSV4(uploadData);
				is = new FileInputStream(new File(uploadData));
				
				try {
					sql = "UPDATE task SET task_file = ? WHERE task_id = ?";
					psmt = cm.getPreparedStatement(sql);
					psmt.setBlob(1, is);
					psmt.setInt(2, task_id);
					
					int row = psmt.executeUpdate();
					
					if (row > 0) {
						message = taskName + " is updated successfully!";
						success = true;
					} else {
						message = "Fail to update " + taskName;
						success = false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				session.setAttribute("success", success);
				response.sendRedirect("TaskView?task_id="+task_id);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}




























