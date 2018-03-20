package com.datamining.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class DatasetInsertForm
 */
@WebServlet("/DatasetInsertForm")
public class DatasetInsertForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasetInsertForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String submitValue = request.getParameter("submitButton").toString();
		String datasetName = request.getParameter("datasetName").toString();
		String structureString = request.getParameter("structure_string").toString();
		int structureId = Integer.parseInt(request.getParameter("structure_id"));
		String url = "";
		String sql = "SELECT * FROM dataset WHERE dataset_name = ?";
		ConnectionManager cm = new ConnectionManager();
		PreparedStatement psmt = cm.getPreparedStatement(sql);
		ResultSet myRs = null;
		try {
			psmt.setString(1, datasetName);
			myRs = psmt.executeQuery();

			if (myRs.isBeforeFirst()) {
				session.setAttribute("success", false);
				session.setAttribute("message", datasetName + " is already exist! Choose different name!");
				response.sendRedirect("/weka-tutorial/DatasetCreate");
			} else {
				if (submitValue.equals("Manual Entry")) {
					url = "views/dataset/manual-entry.jsp";
					String tableContent = "";
					String tableHeader = "";
					String[] atts = structureString.split(",");
			         for (int i = 0; i < atts.length; i++) {
			              tableContent += "<td style='width:150px'>";
			              String att = atts[i].trim();
			              int indexOfBracket = att.indexOf('[');
			              if ( indexOfBracket < 0){//numeric or something else
			            	  tableHeader += "<th name='attributeName' class='attributeName' style='width:150px'>" + deCamelCasealize(att) + " <input type='hidden' name='attributeName' value='" + att + "'/></th>";
			            	  tableContent += "<input type='text' name='"+att+"' class='form-control'/>";
			              } else {//it's a nominal attribute
			                   String attName = att.substring(0, indexOfBracket);
			                   tableHeader += "<th name='attributeName' class='attributeName' style='width:150px'>" + deCamelCasealize(attName) + " <input type='hidden' name='attributeName' value='" + attName + "'/></th>";
			                   String[] nominalValues = att.substring(indexOfBracket+1, att.length()-1).split(";");
			                   tableContent += "<select class='form-control' name='"+attName+"'>";
			                   for (int j = 0; j < nominalValues.length; j++) {
			                	   tableContent += "<option>"+nominalValues[j]+"</option>";
			                   }
			                   tableContent += "</select>";
			              }
			         }
			         tableContent += "<td style='width:150px'><input type='button' value='Delete Row' class='btn btn-block btn-danger'/></td>";
			         tableHeader += "<th style='width:150px'>Option</th>";
			         request.setAttribute("tableHeader", tableHeader);
			         request.setAttribute("tableContent", tableContent);
				} else if (submitValue.equals("Upload CSV")) {
					url = "views/dataset/upload-csv.jsp";
				}
				RequestDispatcher rd = request.getRequestDispatcher(url);
				request.setAttribute("datasetName", datasetName);
				request.setAttribute("structureId", structureId);
				rd.forward(request, response);
			}
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
	
	public static String deCamelCasealize(String camelCasedString) {
	    if (camelCasedString == null || camelCasedString.isEmpty())
	        return camelCasedString;

	    StringBuilder result = new StringBuilder();
	    result.append(camelCasedString.charAt(0));
	    for (int i = 1; i < camelCasedString.length(); i++) {
	        if (Character.isUpperCase(camelCasedString.charAt(i)))
	        result.append(" ");
	        result.append(camelCasedString.charAt(i));
	    }
	    return result.toString();
	}

}
