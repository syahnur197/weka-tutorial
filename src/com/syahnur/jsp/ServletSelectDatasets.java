package com.syahnur.jsp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletViewDatasets
 */
@WebServlet("/ServletSelectDatasets")
public class ServletSelectDatasets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSelectDatasets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager cm = new ConnectionManager();
		boolean success = cm.get("dataset");
		ResultSet myRs = cm.getResult();
		String listString = "";
		try {
			do {
				listString += "<li class='list-group-item structure structure_"+myRs.getInt("structure_id")+"' >";
				listString += "<button class='btn btn-success selectBlobButton mx-1' value='"+myRs.getInt("dataset_id")+"'>Select</button>";
				listString += "<button class='btn btn-warning trainButton mx-1' value='"+myRs.getInt("structure_id")+"'>Train</button>";
				listString += "<button class='btn btn-warning testButton mx-1' value='"+myRs.getInt("structure_id")+"'>Test</button>";
				listString += "<span>"+myRs.getString("dataset_name")+"</span></li>";
			} while(myRs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listString", listString);
		RequestDispatcher rd = request.getRequestDispatcher("/views/dataset/select.jsp");
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
