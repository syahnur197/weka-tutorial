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
import java.util.ArrayList;
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
		String sql = "SELECT `dataset`.*, `structure`.`structure_string` FROM `dataset` JOIN `structure` ON `dataset`.`structure_id` = `structure`.`structure_id` WHERE dataset_id = ? ";
		String structureString = "";
		PreparedStatement psmt = null;
		FileOutputStream output = null;
		PrintWriter out = response.getWriter();
		byte[] buffer = new byte[4096];
		File file = new File("C:/Users/Syahnur197/workspace/weka-tutorial/WebContent/assets/files/data.csv");
		try {
			psmt = cm.getPreparedStatement(sql);
			psmt.setInt(1, dataset_id);
			ResultSet myRs = psmt.executeQuery();
			if(myRs.next()) {
				InputStream blob = myRs.getBinaryStream("dataset_file");
				structureString = myRs.getString("structure_string");
				output = new FileOutputStream(file);
				int b = 0;
				while (( b = blob.read(buffer)) != -1) {
					output.write(buffer, 0, b);
				}
				output.close();
			}
			
			String[] atts = structureString.split(",");			
			
			CSVReader reader = new CSVReader(new FileReader(file));
			// BufferedReader reader = new BufferedReader(new FileReader(file));
            String [] nextLine;
            String[] header = reader.readNext();
            // String[] header = reader.readLine();
            String tableString = "";
              
            if (header != null) {
               tableString += "<tr>";
               for(int i = 0; i < header.length; i++) {
            	   tableString += "<th style='width:150px'>"+header[i]+" <input type='hidden' name='attributeName' value='" + header[i] + "'/></th>";
               }
               tableString += "</tr>";
               while ((nextLine = reader.readNext()) != null) {
            	 tableString += "<tr>";
                 for (int i = 0; i < nextLine.length; i++) {
                	 tableString += "<td style='width:150px'><span class='valueCell'>"+nextLine[i] + "</span> ";
                	 for (int j = 0; j < atts.length; j++) {
         				String att = atts[j].trim();
         				int indexOfBracket = att.indexOf('[');
         				if ( indexOfBracket < 0 && j == i){
         					tableString += "<input type='text' name='"+att+"' class='form-control' style='display:none' value='" + nextLine[i] + "'/>";
         				} else if (j == i) {
         					String attName = att.substring(0, indexOfBracket);
         					String[] nominalValues = att.substring(indexOfBracket+1, att.length()-1).split(";");
         					tableString += "<select class='form-control' name='"+attName+"' style='display:none'>";
         					for (int k = 0; k < nominalValues.length; k++) {
         						String chosen = "";
         						if (nominalValues[k].equals(nextLine[i])) {
         							chosen = "selected";
         						}
         						tableString += "<option " + chosen + ">"+nominalValues[k]+"</option>";
         					}
         					tableString += "</select>";
         				}
         			}
                	 tableString += "</td>";
                 }
                 tableString += "</tr>";
               }
            }
            reader.close();
            request.setAttribute("tableString", tableString);
            request.setAttribute("dataset_id", dataset_id);
			RequestDispatcher rd = request.getRequestDispatcher("/views/dataset/old-view.jsp");
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
