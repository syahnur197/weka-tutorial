package com.syahnur.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ServletCreateDataSet
 */
@WebServlet("/ServletCreateDataSet")
public class ServletCreateDataSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreateDataSet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String structureString = request.getParameter("structureString");
		String submitButton = request.getParameter("submitButton");
		String datasetName = request.getParameter("datasetName");
		String message = "";
		int structureId = Integer.parseInt(request.getParameter("structureId"));

		request.setAttribute("structureId", structureId);
		request.setAttribute("datasetName", datasetName);
		
		ConnectionManager cm = new ConnectionManager();
		String where = "dataset_name = '"+datasetName+"'";
		boolean success = cm.get("dataset", "*", where);
		ResultSet rs = cm.getResult();
		
		try {
			if (rs.next()) {
				HttpSession session=request.getSession();  
				session.setAttribute("message", "The dataset name is already exist! Choose another name!");
				response.sendRedirect("/weka-tutorial/views/structure/select.jsp");
			} else {
				if(submitButton.equals("Manual Entry")) {
					String tableHeader = "";
					String tableContent = "";
					if(structureString.isEmpty()) {
						String[] attributeNames = request.getParameterValues("attributeName");
						int attCount = attributeNames.length;
						for(int i = 0; i < attCount; i++) {
							tableHeader += "<th class='attributeName' style='width:150px'>" + deCamelCasealize(attributeNames[i]) + "</th>";
							tableContent += "<td style='width:150px'>";
							int index = i+1;
							String attType = request.getParameter("attributeType_"+index).toString();
							if(attType.equals("Numeric")) {
								structureString += attributeNames[i]+",";
								tableContent += "<input type='text' name='"+attributeNames[i]+"' class='form-control'/>";
							} else if (attType.equals("Nominal")) {
								structureString += attributeNames[i]+"[";
								String[] options = request.getParameterValues("option_"+index);
								int optionCount = options.length;
								tableContent += "<select class='form-control' name='"+attributeNames[i]+"'>";
								for (int j = 0; j < optionCount; j++) {
									tableContent += "<option>"+options[j]+"</option>";
									structureString += options[j] + ";";
								}
								structureString = structureString.substring(0, structureString.length() - 1);
								structureString += "],";
								tableContent += "</select>";
							}
						}
						structureString = structureString.substring(0, structureString.length() - 1);
						tableContent += "<td style='width:150px'><input type='button' value='Delete Row' class='btn btn-block btn-danger'/></td>";
						tableHeader += "<th style='width:150px'>Option</th>";
					} else {
						String[] atts = structureString.split(",");
				         for (int i = 0; i < atts.length; i++) {
				              tableContent += "<td style='width:150px'>";
				              String att = atts[i].trim();
				              int indexOfBracket = att.indexOf('[');
				              if ( indexOfBracket < 0){//numeric or something else
				            	  tableHeader += "<th class='attributeName' style='width:150px'>" + deCamelCasealize(att) + "</th>";
				            	  tableContent += "<input type='text' name='"+att+"' class='form-control'/>";
				              }else{//it's a nominal attribute
				                   String attName = att.substring(0, indexOfBracket);
				                   tableHeader += "<th class='attributeName'style='width:150px'>" + deCamelCasealize(attName) + "</th>";
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
					}	
					
					request.setAttribute("tableHeader", tableHeader);
					request.setAttribute("tableContent", tableContent);
					request.setAttribute("structureString", structureString);
					
					RequestDispatcher rd = request.getRequestDispatcher("views/dataset/insert.jsp");
					rd.forward(request, response);
				} else if (submitButton.equals("Upload CSV")) {
					request.setAttribute("structureString", structureString);
					RequestDispatcher rd = request.getRequestDispatcher("views/dataset/upload.jsp");
					rd.forward(request, response);
				} else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println(submitButton);
				}
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
