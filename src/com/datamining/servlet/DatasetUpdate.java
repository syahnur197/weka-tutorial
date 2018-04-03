package com.datamining.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
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
 * Servlet implementation class DatasetUpdate
 */
@WebServlet("/DatasetUpdate")
public class DatasetUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasetUpdate() {
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
		int dataset_id = Integer.parseInt(request.getParameter("dataset_id"));
		InputStream is = null;
		String message = "";
		String url = "";
		boolean success = false;
		ConnectionManager cm = new ConnectionManager();
		String sql = "UPDATE `dataset` SET `dataset_file` = ? WHERE `dataset_id` = ?";
		CSVWriter cw = new CSVWriter(request);
		String updateData = "D:/home/site/wwwroot/webapps/ROOT/assets/files/updateData.csv";
		cw.createCSV3(updateData);
		is = new FileInputStream(new File(updateData));
		try {
			PreparedStatement ps = cm.getPreparedStatement(sql);
			ps.setBlob(1, is);
			ps.setInt(2, dataset_id);
			int row = ps.executeUpdate();
			if(row > 0) {
				message = "Dataset is updated";
				success = true;
				url = "index.jsp";
			} else {
				message = "Fail to update dataset";
				success = false;
				url = "ServletSelectDatasets";
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
