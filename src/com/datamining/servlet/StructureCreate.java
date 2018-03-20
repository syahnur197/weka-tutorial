package com.datamining.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class StructureCreate
 */
@WebServlet("/StructureCreate")
public class StructureCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StructureCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String structureString = request.getParameter("structureString");
		String structureName = request.getParameter("structureName");
		if(structureString.isEmpty()) {
			String[] attributeNames = request.getParameterValues("attributeName");
			int attCount = attributeNames.length;
			for(int i = 0; i < attCount; i++) {
				int index = i+1;
				String attType = request.getParameter("attributeType_"+index).toString();
				if(attType.equals("Numeric")) {
					structureString += attributeNames[i]+",";
				} else if (attType.equals("Nominal")) {
					structureString += attributeNames[i]+"[";
					String[] options = request.getParameterValues("option_"+index);
					int optionCount = options.length;
					for (int j = 0; j < optionCount; j++) {
						structureString += options[j] + ";";
					}
					structureString = structureString.substring(0, structureString.length() - 1);
					structureString += "],";
				}
			}
			structureString = structureString.substring(0, structureString.length() - 1);
		}
		
		HashMap<String,String> values = new HashMap<String,String>();
		values.put("structure_name", structureName);
		values.put("structure_string", structureString);

		ConnectionManager cm = new ConnectionManager();
		boolean select = cm.get("structure", "*", "`structure_name` LIKE \""+values.get("structure_name")+"\"");
		boolean success = false;
		String message = "";
		if (select) {
			message = "INSERT FAIL: Structure name is already exist, use another name!";
		} else {
			select = cm.get("structure", "*", "`structure_string` LIKE \""+values.get("structure_string")+"\"");
			if (select) {
				message = "INSERT FAIL: Structure String is already exist in the database!";
			} else {
				success = cm.insert("structure", values);
				if(success) {
					message = "New structure inserted successfully!";
					success = true;
				} else {
					message = "INSERT FAIL: Fail to execute query!";
					success = false;
				}
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		session.setAttribute("success", success);
		response.sendRedirect("/weka-tutorial/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
