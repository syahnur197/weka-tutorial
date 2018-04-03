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

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class TaskCreate
 */
@WebServlet("/TaskCreate")
public class TaskCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager cm = new ConnectionManager();
		ResultSet myRs = null;
		try {
			boolean success = cm.get("dataset");
			if (success) {
				myRs = cm.getResult();
				String list = "";				
				do {
					list += "<li><a href='#' onclick='selectTrain(this, " + myRs.getInt("structure_id") + ", " + myRs.getInt("dataset_id") + ")'><i class='fa fa-check text-info'></i><span class='train_dataset_name'>" + myRs.getString("dataset_name") + "</span></a></li>";
				} while (myRs.next());
				request.setAttribute("list", list);
				RequestDispatcher rd = request.getRequestDispatcher("views/task/create.jsp");
				rd.forward(request, response);
			} else {
				// 
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

}
