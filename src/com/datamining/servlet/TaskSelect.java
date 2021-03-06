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

import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class TaskSelect
 */
@WebServlet("/TaskSelect")
public class TaskSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager cm = new ConnectionManager();
		cm.get("task");
		ResultSet myRs = cm.getResult();
		String listString = "";
		try {
			do {
				listString += "<li>";
				listString += "<a href='/weka-tutorial/TaskView?task_id="+myRs.getInt("task_id")+"' class='text-info'><i class=\"fa fa-check text-info\"></i> "+myRs.getString("task_name")+"</a></li>";
			} while(myRs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listString", listString);
		RequestDispatcher rd = request.getRequestDispatcher("/views/task/select.jsp");
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
