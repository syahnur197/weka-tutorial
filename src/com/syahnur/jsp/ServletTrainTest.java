package com.syahnur.jsp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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

import com.opencsv.CSVReader;

/**
 * Servlet implementation class ServletTrainTest
 */
@WebServlet("/ServletTrainTest")
public class ServletTrainTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTrainTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String tableString = "",
		percentageString = "";
		int trainDataset_id = Integer.parseInt(request.getParameter("trainDataset_id"));
		int testDataset_id = Integer.parseInt(request.getParameter("testDataset_id"));
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
				tableString += "<tr><td>" + no + "</td><td ondblclick='changeValue(" + i + ")'><span id='actualClass_" + i + "'>" + actualList.get(i) + "</span><input type='text' id='editActualClass_" + i + "' style='display:none;' value='" + actualList.get(i) + "'></td>";
				tableString += "<td id='j48_" + i + "'>" + J48predictionList.get(i) + "</td><td style='color: " + colourJ48 + "' id='j48Color_" + i + "'>" + matchJ48 + "</td>";
				tableString += "<td id='nb_" + i + "'>" + NBpredictionList.get(i) + "</td><td style='color: " + colourNB + "' id='nbColor_" + i + "'>" + matchNB + "</td>";
			}

			int totalJ48 = noOfMatchesJ48 + noOfUnmatchesJ48;
			// double percentJ48 = (noOfMatchesJ48 * 100) / totalJ48;

			int totalNB = noOfMatchesNB + noOfUnmatchesNB;
			// double percentNB = (noOfMatchesNB * 100) / totalNB;
			percentageString += "<hr>";
			percentageString += "Number of J48 matches: <span id='j48Matches'>" + noOfMatchesJ48 + "</span>";
			percentageString += "<br>Number of J48 unmatches: <span id='j48Unmatches'>" + noOfUnmatchesJ48 + "</span>";
			// percentageString += "<br>% of J48 Matches: <span id='j48Percent'>" + percentJ48 + "</span>%";
			percentageString += "<hr>";
			percentageString += "Number of Naive Bayes matches: <span id='nbMatches'>" + noOfMatchesNB + "</span>";
			percentageString += "<br>Number of Naive Bayes unmatches: <span id='nbUnmatches'>" + noOfUnmatchesNB + "</span>";
			// percentageString += "<br>% of Naive Bayes Matches: <span id='nbPercent'>" + percentNB + "</span>%";

			request.setAttribute("tableString", tableString);
			request.setAttribute("percentageString", percentageString);
			RequestDispatcher rd = request.getRequestDispatcher("views/prediction/page.jsp");
			rd.forward(request, response);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
