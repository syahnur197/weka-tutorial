package com.datamining.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.opencsv.CSVReader;
import com.syahnur.jsp.ConnectionManager;

/**
 * Servlet implementation class TaskView
 */
@WebServlet("/TaskView")
public class TaskView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int task_id;
		HttpSession session = request.getSession();
		if (request.getParameter("task_id") == null) {
			session.setAttribute("message", "No task id is provided!");
			session.setAttribute("success", false);
			response.sendRedirect("/weka-tutorial/index.jsp");
		} else {
			task_id = Integer.parseInt(request.getParameter("task_id"));
			ConnectionManager cm = new ConnectionManager();
			String sql = "SELECT * FROM `task` WHERE task_id = ? ";
			PreparedStatement psmt = null;
			FileOutputStream output = null;
			PrintWriter out = response.getWriter();
			byte[] buffer = new byte[4096];
			File file = new File("C:/Users/Syahnur197/workspace/weka-tutorial/WebContent/assets/files/taskData.csv");
			try {
				psmt = cm.getPreparedStatement(sql);
				psmt.setInt(1, task_id);
				ResultSet myRs = psmt.executeQuery();
				if(myRs.next()) {
					InputStream blob = myRs.getBinaryStream("task_file");
					output = new FileOutputStream(file);
					int b = 0;
					while (( b = blob.read(buffer)) != -1) {
						output.write(buffer, 0, b);
					}
					output.close();	
					
					CSVReader reader = new CSVReader(new FileReader(file));
					String [] nextLine;
					String tableString = "";
					int count = 0;
					int noOfMatchesJ48 = 0;
					int noOfUnmatchesJ48 = 0;
					int noOfMatchesNB = 0;
					int noOfUnmatchesNB = 0;
					String percentageString = "";
					while ((nextLine = reader.readNext()) != null) {
						String colourJ48 = "";
						String colourNB = "";
						if (nextLine[2].trim().toString().equals("matched")) {
							colourJ48 = "green";
							noOfMatchesJ48++;
						} else if (nextLine[2].trim().toString().equals("unmatched")) {
							colourJ48 = "red";
							noOfUnmatchesJ48++;
						}
						if (nextLine[4].trim().toString().equals("matched")) {
							colourNB = "green";
							noOfMatchesNB++;
						} else if (nextLine[4].trim().toString().equals("unmatched")) {
							colourNB = "red";
							noOfUnmatchesNB++;
						}
						tableString += "<tr><td>" + (++count)  + "</td>";
						tableString += "<td ondblclick='changeValue(" + count + ")'><span id='actualClass_" + count + "'>" + nextLine[0].trim() + "</span><input type='text' name='actualClass' id='editActualClass_" + count + "' style='display:none;' value='" + nextLine[0] + "' onchange='valueChanged("+count+");' onkeydown=\"if (event.keyCode == 13){ event.preventDefault();}\" class='form-control'></td>";
						tableString += "<td id='j48_" + count + "'>" + nextLine[1].trim() + "<input type='hidden' name='j48Class' value='" + nextLine[1].trim() + "' /></td><td style='color: " + colourJ48 + "' id='j48Color_" + count + "'>" + nextLine[2].trim() + "<input type='hidden' name='j48ClassMatch' value='" + nextLine[2].trim() + "' /></td>";
						tableString += "<td id='nb_" + count + "'>" + nextLine[3].trim() + "<input type='hidden' name='nbClass' value='" + nextLine[3].trim() + "' /></td><td style='color: " + colourNB + "; text-align: center;' id='nbColor_" + count + "'>" + nextLine[4].trim() + "<input type='hidden' name='nbClassMatch' value='" + nextLine[4].trim() + "' /></td>";
						tableString += "</tr>";
					}
					int totalJ48 = noOfMatchesJ48 + noOfUnmatchesJ48;
					double percentJ48 = (noOfMatchesJ48 * 100) / totalJ48;

					int totalNB = noOfMatchesNB + noOfUnmatchesNB;
					double percentNB = (noOfMatchesNB * 100) / totalNB;
					percentageString += "<hr>";
					percentageString += "<div class='row'>Number of J48 matches: \t <span id='j48Matches'>" + noOfMatchesJ48 + "</span></div>";
					percentageString += "<div class='row'>Number of J48 unmatches: \t <span id='j48Unmatches'>" + noOfUnmatchesJ48 + "</span></div>";
					percentageString += "<div class='row'>% of J48 Matches: \t <span id='j48Percent'>" + percentJ48 + "</span>%</div>";
					percentageString += "<button type=\"button\" class='btn btn-primary mx-1' onclick=\"drawJ48Chart();\">Show J48 Pie Chart</button>";
					percentageString += "<hr>";
					percentageString += "<div class='row'>Number of Naive Bayes matches: \t <span id='nbMatches'>" + noOfMatchesNB + "</span></div>";
					percentageString += "<div class='row'>Number of Naive Bayes unmatches: \t <span id='nbUnmatches'>" + noOfUnmatchesNB + "</span></div>";
					percentageString += "<div class='row'>% of Naive Bayes Matches: \t <span id='nbPercent'>" + percentNB + "</span>%</div>";
					percentageString += "<button type=\"button\" class='btn btn-primary mx-1' onclick=\"drawNBChart();\">Show NB Pie Chart</button>";

					request.setAttribute("percentageString", percentageString);
					reader.close();
		            request.setAttribute("tableString", tableString);
		            request.setAttribute("task_id", task_id);
					RequestDispatcher rd = request.getRequestDispatcher("/views/task/view.jsp");
					rd.forward(request, response);
				} else {
					session.setAttribute("message", "Task is Not Available!");
					session.setAttribute("success", false);
					response.sendRedirect("/weka-tutorial/index.jsp");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
