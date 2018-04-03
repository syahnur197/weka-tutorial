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
 * Servlet implementation class TaskSave
 */
@WebServlet("/TaskSave")
public class TaskSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskSave() {
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
		int trainDatasetId = Integer.parseInt(request.getParameter("trainDataset_id"));
		int testDatasetId = Integer.parseInt(request.getParameter("testDataset_id"));
		String taskName = request.getParameter("taskName");

		String message = "";
		boolean success = false;
		
		ConnectionManager cm = new ConnectionManager();
		PreparedStatement psmt = null;
		ResultSet myRs = null;
		String sql = "";
		try {
			sql = "SELECT * FROM task WHERE task_name = ?";
			psmt = cm.getPreparedStatement(sql);
			psmt.setString(1, taskName);
			myRs = psmt.executeQuery();
			if(myRs.isBeforeFirst()) {
				success = false;
				message = "Task Name is already used!";
			} else {
				InputStream is = null;
				
				CSVWriter cw = new CSVWriter(request);
				String uploadData = "D:/home/site/wwwroot/webapps/ROOT/assets/files/uploadTask.csv";
				cw.createCSV4(uploadData);
				is = new FileInputStream(new File(uploadData));
				
				try {
					sql = "INSERT INTO task (task_name, train_dataset_id, test_dataset_id, task_file) VALUES (?, ?, ?, ?)";
					psmt = cm.getPreparedStatement(sql);
					psmt.setString(1, taskName);
					psmt.setInt(2, trainDatasetId);
					psmt.setInt(3, testDatasetId);
					psmt.setBlob(4, is);
					int row = psmt.executeUpdate();
					if (row > 0) {
						message = taskName + " is saved successfully!";
						success = true;
					} else {
						message = "Fail to save " + taskName;
						success = false;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		session.setAttribute("success", success);
		response.sendRedirect("index.jsp");
		
	}

}
