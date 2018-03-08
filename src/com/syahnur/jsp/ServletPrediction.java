package com.syahnur.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.classifiers.*;
import weka.core.*;

/**
 * Servlet implementation class ServletPrediction
 */
@WebServlet("/ServletPrediction")
	public class ServletPrediction extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ServletPrediction() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
		/**
		 * @throws IOException 
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			
			out.println("<html><head>");
			out.println("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js\" integrity=\"sha384-feJI7QwhOS+hwpX2zkaeJQjeiwlhOP+SdQDqhgvvo1DsjtiSQByFdThsxO669S2D\" crossorigin=\"anonymous\"></script>");
			out.println("</head><body>");
			out.println("<div class='container'><div class='jumbotron'><a href='index.jsp'><h1>Amazing Prediction Result</h1></a></div><div class='table-responsive'>");
			out.println("<table class='table table-striped table-hover' style='text-align: center'><tr><th>Student No</th><th>Actual Grade</th><th>Predicted Grade (J48)</th><th>J48 Matches</th><th>Predicted Grade (NB)</th><th>NB Matches</th></tr>");
			
			String trainSourceString = request.getParameter("train").toString();
			String testSourceString = "";
			if(!request.getParameter("test").isEmpty()) {
				testSourceString = request.getParameter("test").toString();
			} else {
				testSourceString = request.getParameter("test_file");
				CSVWriter cw = new CSVWriter(request);
		        cw.createCSV2(testSourceString);
			}
			
			if (!request.getParameter("full_path").isEmpty()) {
				testSourceString = request.getParameter("full_path").toString() + testSourceString;
				trainSourceString = request.getParameter("full_path").toString() + trainSourceString;
			}
			
			Prediction prediction = new Prediction();
			ArrayList<String> J48predictionList = new ArrayList<String>();
			ArrayList<String> NBpredictionList = new ArrayList<String>();
			ArrayList<String> actualList = new ArrayList<String>();
			try {
				J48predictionList = prediction.J48trainAndTest(trainSourceString, testSourceString);
				NBpredictionList = prediction.NBtrainAndTest(trainSourceString, testSourceString);
				actualList = prediction.actualGrade(testSourceString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int noOfMatchesJ48 = 0, noOfUnmatchesJ48 = 0;
			int noOfMatchesNB = 0, noOfUnmatchesNB = 0;
			for(int i = 0; i < J48predictionList.size(); i++) {
				String matchJ48, colourJ48, matchNB, colourNB;
				
				if (actualList.get(i).equals(J48predictionList.get(i))) {
					matchJ48 = "matched";
					noOfMatchesJ48++;
					colourJ48 = "green";
				} else { matchJ48 = "unmatched"; noOfUnmatchesJ48++; colourJ48 = "red";}
				
				if (actualList.get(i).equals(NBpredictionList.get(i))) {
					matchNB = "matched";
					noOfMatchesNB++;
					colourNB = "green";
				} else { matchNB = "unmatched"; noOfUnmatchesNB++; colourNB = "red";}
				int no = i + 1;
				out.println("<tr><td>"+ no +"</td><td>"+actualList.get(i)+"</td>");
				out.println("<td>"+J48predictionList.get(i)+"</td><td style='color: "+colourJ48+"'>"+matchJ48+"</td>");
				out.println("<td>"+NBpredictionList.get(i)+"</td><td style='color: "+colourNB+"'>"+matchNB+"</td>");
			}
			
			int totalJ48 = noOfMatchesJ48 + noOfUnmatchesJ48;
			double percentJ48 = (noOfMatchesJ48 * 100)/totalJ48;
			
			int totalNB = noOfMatchesNB + noOfUnmatchesNB;
			double percentNB = (noOfMatchesNB * 100)/totalNB;
			out.println("</table></div>");
			out.println("<hr>");
			out.println("Number of J48 matches: " + noOfMatchesJ48);
			out.println("<br>Number of J48 unmatches: " + noOfUnmatchesJ48);
			out.println("<br>% of J48 Matches: " + percentJ48 +"%");
			out.println("<hr>");
			out.println("Number of Naive Bayes matches: " + noOfMatchesNB);
			out.println("<br>Number of Naive Bayes unmatches: " + noOfUnmatchesNB);
			out.println("<br>% of Naive Bayes Matches: " + percentNB +"%");
			
			
			out.println("</div></body></html>");
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
	
	}
