package com.syahnur.jsp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ServletUploadDataSet
 */
@WebServlet("/ServletUploadDataSet")
@MultipartConfig
public class ServletUploadDataSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUploadDataSet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datasetName = request.getParameter("datasetName").toString();
		int structureId = Integer.parseInt(request.getParameter("structureId"));
		String submitValue = request.getParameter("submit").toString();
		if (submitValue.equals("Upload CSV")) {
			InputStream is = null;
			Part filePart = request.getPart("dataset");
			
			if(filePart != null) {
				is = filePart.getInputStream();
			}
			String message = "";
			String url = "";
			boolean success = false;
			ConnectionManager cm = new ConnectionManager();
			String sql = "INSERT INTO `dataset` (structure_id, dataset_name, dataset_file) VALUES (?, ?, ?)";
			
			try {
				PreparedStatement ps = cm.getPreparedStatement(sql);
				ps.setInt(1, structureId);
				ps.setString(2, datasetName);
				ps.setBlob(3, is);
				int row = ps.executeUpdate();
				if(row > 0) {
					message = "File Uploaded to server";
					success = true;
					url = "/weka-tutorial/index.jsp";
				} else {
					message = "Fail to upload to server";
					success = false;
					url = "/weka-tutorial/views/structure/select.jsp";
				}	
				
				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				session.setAttribute("success", success);
				response.sendRedirect(url);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else if (submitValue.equals("Upload as CSV")) {
			
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
