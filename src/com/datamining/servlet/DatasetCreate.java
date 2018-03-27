package com.datamining.servlet;

import java.io.IOException;
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
 * Servlet implementation class DatasetCreate
 */
@WebServlet("/DatasetCreate")
public class DatasetCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasetCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager cm = new ConnectionManager();
		Boolean success = cm.get("structure");
		ResultSet myRs = cm.getResult();
		String tableBody = "hello";
		if(success) {
			try {
				tableBody = "<tbody>";
				int count = 1;
				do {
					tableBody += "<tr>";
					tableBody += "<td>" + count + "</td>";
					tableBody += "<td style='color:black;'>" + myRs.getString("structure_name") + "</td>";
					tableBody += "<td><button type='button' class='btn btn-info btn-block' onclick='viewStructure("+myRs.getInt("structure_id")+")'>";
					tableBody += "View Structure";
					tableBody += "</button>";
					tableBody += "<button type='button' class='btn btn-primary btn-block' onclick='selectStructure("+myRs.getInt("structure_id")+")'>";
					tableBody += "Select Structure";
					tableBody += "</button></td>";
					tableBody += "</tr>";
					count++;
				} while (myRs.next());
				tableBody += "</tbody>";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			tableBody = "NO DATA";
		}
		
		
		request.setAttribute("tableBody", tableBody);
		
		RequestDispatcher rd = request.getRequestDispatcher("views/dataset/create.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
