package com.syahnur.jsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;

/**
 * Servlet implementation class ServletGetBlob
 */
@WebServlet("/ServletGetBlob")
public class ServletGetBlob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetBlob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dataset_id = Integer.parseInt(request.getParameter("dataset_id"));
		ConnectionManager cm = new ConnectionManager();
		String sql = "SELECT * FROM `dataset` WHERE dataset_id = ?";
		PreparedStatement psmt = null;
		FileOutputStream output = null;
		byte[] buffer = new byte[4096];
		File file = new File("C:/Users/Syahnur197/workspace/weka-tutorial/WebContent/assets/files/data.csv");
		try {
			psmt = cm.getPreparedStatement(sql);
			psmt.setInt(1, dataset_id);
			ResultSet myRs = psmt.executeQuery();
			if(myRs.next()) {
				InputStream blob = myRs.getBinaryStream("dataset_file");
				output = new FileOutputStream(file);
				int b = 0;
				while (( b = blob.read(buffer)) != -1) {
					output.write(buffer, 0, b);
				}
				output.close();
			}
			
			CSVReader reader = new CSVReader(new FileReader(file));
            String [] nextLine;
            String[] header = reader.readNext();
            String tableString = "<di class='table-responsive'v><table class='table table-striped table-bordered table-hover'>";
            if (header != null) {
               tableString += "<tr>";
               for(int i = 0; i < header.length; i++) {
            	   tableString += "<th>"+header[i]+"</th>";
               }
               tableString += "</tr>";
               while ((nextLine = reader.readNext()) != null) {
            	 tableString += "<tr>";
                 for (int i = 0; i < nextLine.length; i++) {
                	 tableString += "<td>"+nextLine[i] + "</td>";
                 }
                 tableString += "</tr>";
               }
            }
            reader.close();
            tableString += "</table></div>";
            request.setAttribute("tableString", tableString);
			RequestDispatcher rd = request.getRequestDispatcher("/views/dataset/view.jsp");
			rd.forward(request, response);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
