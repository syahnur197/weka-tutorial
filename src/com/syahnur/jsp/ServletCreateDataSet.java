package com.syahnur.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String[] attributeNames = request.getParameterValues("attributeName");
		int attCount = attributeNames.length;
		
		String string = "{\"attribute\":[";
		for(int i = 0; i < attCount; i++) {
			int index = i+1;
			String type = request.getParameter("attributeType_"+index);
			string += " {\"name\" :\"" + attributeNames[i]+"\", \"type\": \""+type+"\", \"option\" :[";
			String[] option = request.getParameterValues("option_"+index);
			for(int j = 0; j < option.length; j++) {
				string += "\""+option[j]+"\",";
			}
			string = string.substring(0, string.length() - 1);
			string += "]},";
		}
		string = string.substring(0, string.length() - 1);
		string += "]}";
//		JSONObject data = null;
//		try {
//			data = new JSONObject(string);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		request.setAttribute("attribute", string);
		
		RequestDispatcher rd = request.getRequestDispatcher("createDataSetForm.jsp");
		rd.forward(request, response);
		
//		response.setContentType("application/json");
//		
//		PrintWriter out = response.getWriter();
//		
//		out.println(string);
		
//		for(int j = 0; j < attCount; i++) {
//			int index = j+1;
//			String attName = attributeNames[j];
//			String attType = request.getParameter("attributeType_"+index);
//			out.print("<td style='width:150px'>");
//			if(attType == "Numeric") { 
//				out.print("<input type='text' name='"+attName+"' class='form-control'/>");
//			} else if(attType == "Nominal")  {
//				String[] options = request.getParameterValues("option_"+index);
//				out.print("<select class='form-control' name='"+attName+"'>");
//				for(int k = 0; k < options.length; k++) {
//					out.print("<option>"+options[k]+"</option>");
//				}
//				out.print("</select>");
//			}
//			out.print("</td>");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
