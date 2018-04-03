package com.datamining.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.syahnur.jsp.CSVWriter;
import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class DatasetUpload
 */
@WebServlet("/DatasetUpload")
@MultipartConfig
public class DatasetUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasetUpload() {
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

		String datasetName = request.getParameter("datasetName").toString();
		int structureId = Integer.parseInt(request.getParameter("structureId"));
		String submitValue = request.getParameter("submitButton").toString();
		InputStream is = null;
		String message = "";
		String url = "";
		boolean success = false;
		ConnectionManager cm = new ConnectionManager();
		String sql = "INSERT INTO `dataset` (structure_id, dataset_name, dataset_file) VALUES (?, ?, ?)";
		if (submitValue.equals("Upload CSV")) {
			Part filePart = request.getPart("dataset");		
			if(filePart != null) {
				is = filePart.getInputStream();
			}		
		} else if (submitValue.equals("Upload as CSV")) {
			CSVWriter cw = new CSVWriter(request);
			String uploadData = "D:/home/site/wwwroot/webapps/ROOT/assets/files/uploadData.csv";
			cw.createCSV3(uploadData);
			is = new FileInputStream(new File(uploadData));
		}
		
		try {
			PreparedStatement ps = cm.getPreparedStatement(sql);
			ps.setInt(1, structureId);
			ps.setString(2, datasetName);
			ps.setBlob(3, is);
			int row = ps.executeUpdate();
			if(row > 0) {
				message = "File Uploaded to server";
				success = true;
				url = "index.jsp";
			} else {
				message = "Fail to upload to server";
				success = false;
				url = "DatasetSelect";
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
	}

}
