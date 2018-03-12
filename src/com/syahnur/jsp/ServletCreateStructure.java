package com.syahnur.jsp;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ServletCreateStructure
 */
@WebServlet("/ServletCreateStructure")
public class ServletCreateStructure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreateStructure() {
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// ConnectionManager cm = new ConnectionManager();
		HashMap<String,String> values = new HashMap<String,String>();
		values.put("structure_name", structureName);
		values.put("structure_string", structureString);
		
		String url = "jdbc:mysql://localhost/weka_db";
		String username = "root";
		String password = "";
		
//		try {
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			Connection conn = DriverManager.getConnection(url,username,password);
//			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `structure` WHERE `structure_name` = ?");
//			stmt.setString(1, values.get("structure_name"));
//			ResultSet rs = stmt.executeQuery();
//			if(rs.next()) {
//				out.println(rs.getString("structure_string"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ConnectionManager cm = new ConnectionManager();
		boolean select = cm.get("structure", "*", "`structure_name` LIKE \""+values.get("structure_name")+"\"");
		if (select) {
			System.out.println("INSERT FAIL: Structure name is already exist!");
		} else {
			select = cm.get("structure", "*", "`structure_string` LIKE \""+values.get("structure_string")+"\"");
			if (select) {
				out.println("INSERT FAIL: Structure is already exist!");
			} else {
				boolean success = cm.insert("structure", values);
				if(success) {
					out.println(cm.getInsertID());
					
				} else {
					out.println("Fail to execute query");
				}
			}
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
