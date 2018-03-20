package com.syahnur.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class StructureData
 */
@WebServlet("/StructureData")
public class StructureData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StructureData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("structure_id") != null) {
			int structure_id = Integer.parseInt(request.getParameter("structure_id"));
			String sql = "SELECT * FROM structure WHERE structure_id = ? LIMIT 1";
			ConnectionManager cm = new ConnectionManager();
			PreparedStatement psmt = cm.getPreparedStatement(sql);
			ResultSet myRs = null;
			boolean success = false;
			String structureString = "";
			String structureName = "";
			String jsonString = "";
			try {
				psmt.setInt(1, structure_id);
				myRs = psmt.executeQuery();
				if (!myRs.isBeforeFirst()) {
					HttpSession session = request.getSession();
					session.setAttribute("message", "Structure ID is not exist!");
					session.setAttribute("success", false);
					response.sendRedirect("/weka-tutorial/index.jsp");
				} else {
					success = true;
					while(myRs.next()) {
						structureString = myRs.getString("structure_string");
						structureName = myRs.getString("structure_name");
						jsonString = "{\"structure_name\" : \"" + structureName + "\", \"structure_string\" : \"" + structureString + "\"}";
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				success = false;
			} 
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(jsonString);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("message", "No structure ID provided");
			session.setAttribute("success", false);
			response.sendRedirect("/weka-tutorial/index.jsp");
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
