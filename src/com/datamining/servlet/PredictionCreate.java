package com.datamining.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syahnur.jsp.ConnectionManager;
import com.syahnur.jsp.Prediction;

/**
 * Servlet implementation class PredictionCreate
 */
@WebServlet("/PredictionCreate")
public class PredictionCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PredictionCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("message", "403: Forbidden Access");
		session.setAttribute("success", false);
		response.sendRedirect("/weka-tutorial/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String tableString = "",
		percentageString = "";
		int trainDataset_id = Integer.parseInt(request.getParameter("trainingData"));
		int testDataset_id = Integer.parseInt(request.getParameter("testingData"));
		request.setAttribute("trainDataset_id", trainDataset_id);
		request.setAttribute("testDataset_id", testDataset_id);
		ConnectionManager cm = new ConnectionManager();
		String sql = "SELECT * FROM `dataset` WHERE dataset_id = ?";
		PreparedStatement psmt = null;
		FileOutputStream output = null;
		byte[] buffer = new byte[4096];
		String trainFileString = "C:/Users/Syahnur197/workspace/weka-tutorial/WebContent/assets/files/trainData.csv";
		String testFileString = "C:/Users/Syahnur197/workspace/weka-tutorial/WebContent/assets/files/testData.csv";
		File trainFile = new File(trainFileString);
		File testFile = new File(testFileString);
		try {
			psmt = cm.getPreparedStatement(sql);
			psmt.setInt(1, trainDataset_id);
			ResultSet trainRs = psmt.executeQuery();
			if(trainRs.next()) {
				InputStream blob = trainRs.getBinaryStream("dataset_file");
				output = new FileOutputStream(trainFile);
				int b = 0;
				while (( b = blob.read(buffer)) != -1) {
					output.write(buffer, 0, b);
				}
				output.close();
			}
			
			psmt.setInt(1, testDataset_id);
			ResultSet testRs = psmt.executeQuery();
			if(testRs.next()) {
				InputStream blob = testRs.getBinaryStream("dataset_file");
				output = new FileOutputStream(testFile);
				int b = 0;
				while (( b = blob.read(buffer)) != -1) {
					output.write(buffer, 0, b);
				}
				output.close();
			}
			Prediction prediction = new Prediction();
			ArrayList < String > J48predictionList = new ArrayList < String > ();
			ArrayList < String > NBpredictionList = new ArrayList < String > ();
			ArrayList < String > actualList = new ArrayList < String > ();
			try {
				J48predictionList = prediction.J48trainAndTest(trainFileString, testFileString);
				NBpredictionList = prediction.NBtrainAndTest(trainFileString, testFileString);
				actualList = prediction.actualGrade(testFileString);
			} catch(Exception e) {
				e.printStackTrace();
			}
			int noOfMatchesJ48 = 0,
			noOfUnmatchesJ48 = 0;
			int noOfMatchesNB = 0,
			noOfUnmatchesNB = 0;
			for (int i = 0; i < J48predictionList.size(); i++) {
				String matchJ48,
				colourJ48,
				matchNB,
				colourNB;

				if (actualList.get(i).equals(J48predictionList.get(i))) {
					matchJ48 = "matched";
					noOfMatchesJ48++;
					colourJ48 = "green";
				} else {
					matchJ48 = "unmatched";
					noOfUnmatchesJ48++;
					colourJ48 = "red";
				}

				if (actualList.get(i).equals(NBpredictionList.get(i))) {
					matchNB = "matched";
					noOfMatchesNB++;
					colourNB = "green";
				} else {
					matchNB = "unmatched";
					noOfUnmatchesNB++;
					colourNB = "red";
				}
				int no = i + 1;
				tableString += "<tr><td>" + no + "</td><td ondblclick='changeValue(" + i + ")'><span id='actualClass_" + i + "'>" + actualList.get(i) + "</span><input type='text' name='actualClass' id='editActualClass_" + i + "' style='display:none;' value='" + actualList.get(i) + "' onchange='valueChanged("+i+")' onkeydown=\"if (event.keyCode == 13){ return false }\" class='form-control'></td>";
				tableString += "<td id='j48_" + i + "'>" + J48predictionList.get(i) + "<input type='hidden' name='j48Class' value='" + J48predictionList.get(i) + "' /></td><td style='color: " + colourJ48 + "' id='j48Color_" + i + "'>" + matchJ48 + "<input type='hidden' name='j48ClassMatch' value='" + matchJ48 + "' /></td>";
				tableString += "<td id='nb_" + i + "'>" + NBpredictionList.get(i) + "<input type='hidden' name='nbClass' value='" + NBpredictionList.get(i) + "' /></td><td style='color: " + colourNB + "; text-align: center;' id='nbColor_" + i + "'>" + matchNB + "<input type='hidden' name='nbClassMatch' value='" + matchNB + "' /></td>";
			}

			int totalJ48 = noOfMatchesJ48 + noOfUnmatchesJ48;
			double percentJ48 = (noOfMatchesJ48 * 100) / totalJ48;

			int totalNB = noOfMatchesNB + noOfUnmatchesNB;
			double percentNB = (noOfMatchesNB * 100) / totalNB;
			percentageString += "<hr>";
			percentageString += "<div class='row'>Number of J48 matches: \t <span id='j48Matches'>" + noOfMatchesJ48 + "</span></div>";
			percentageString += "<div class='row'>Number of J48 unmatches: \t <span id='j48Unmatches'>" + noOfUnmatchesJ48 + "</span></div>";
			percentageString += "<div class='row'>% of J48 Matches: \t <span id='j48Percent'>" + percentJ48 + "</span>%</div>";
			percentageString += "<hr>";
			percentageString += "<div class='row'>Number of Naive Bayes matches: \t <span id='nbMatches'>" + noOfMatchesNB + "</span></div>";
			percentageString += "<div class='row'>Number of Naive Bayes unmatches: \t <span id='nbUnmatches'>" + noOfUnmatchesNB + "</span></div>";
			percentageString += "<div class='row'>% of Naive Bayes Matches: \t <span id='nbPercent'>" + percentNB + "</span>%</div>";

			request.setAttribute("tableString", tableString);
			request.setAttribute("percentageString", percentageString);
			RequestDispatcher rd = request.getRequestDispatcher("views/prediction/page.jsp");
			rd.forward(request, response);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
