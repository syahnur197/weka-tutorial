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
import java.util.ArrayList;

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
 * Servlet implementation class DatasetSelect
 */
@WebServlet("/DatasetSelect")
public class DatasetSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasetSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager cm = new ConnectionManager();
		cm.get("dataset");
		ResultSet myRs = cm.getResult();
		String listString = "";
		try {
			do {
				listString += "<li>";
				listString += "<a href='/weka-tutorial/DatasetView?dataset_id="+myRs.getInt("dataset_id")+"' class='text-info'><i class=\"fa fa-check text-info\"></i> "+myRs.getString("dataset_name")+"</a></li>";
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
	
//	private void getStructureInput(String structureString) {
//		String[] atts = structureString.split(",");	
//		String tableString = "";
//		for (int j = 0; j < atts.length; j++) {
//			String att = atts[j].trim();
//			int indexOfBracket = att.indexOf('[');
//			if ( indexOfBracket < 0){
//				tableString = "<input type='text' name='"+att+"' class='form-control' style='display:none'/>";
//				structure.add(tableString);
//			} else {
//				String attName = att.substring(0, indexOfBracket);
//				String[] nominalValues = att.substring(indexOfBracket+1, att.length()-1).split(";");
//				tableString = "<select class='form-control' name='"+attName+"' style='display:none'>";
//				for (int k = 0; k < nominalValues.length; k++) {
//					tableString += "<option>"+nominalValues[k]+"</option>";
//				}
//				tableString += "</select>";
//
//				structure.add(tableString);
//			}
//		}
//	}
//	
//	private String getInput(int col) {
//		return structure.get(col).toString();
//	}
	
//	public static void main(String[] args) {
//		getStructureInput("Gender[Male;Female],Nationality[Kuwait;SaudiArabia;Jordan;USA;Lebanon;Iran;Venezuela;Egypt;Tunis;Morocco;Syria;Palestine;Iraq;Lybia],PlaceOfBirth[Kuwait;SaudiArabia;USA;Jordan;Lebanon;Iran;Venezuela;Egypt;Tunis;Morocco;Syria;Palestine;Iraq;Lybia],StageID[LowerLevel;MiddleSchool;HighSchool],GradeID[G-02;G-04;G-05;G-06;G-07;G-08;G-09;G-10;G-11;G-12],SectionID[A;B;C],Topic[IT;Math;Arabic;English;Quran;Science;French;Spanish;History;Biology;Chemistry;Geology],Semester[F;S],Relation[Father;Mum],RaisedHands,VisitedResources,AnnouncementsView,Discussion,ParentAnsweringSurvey[Yes;No],ParentschoolSatisfaction[Good;Bad],StudentAbsenceDays[Above-7;Under-7],Class[L;M;H]");
//		System.out.println(getInput(6));
//	}
	
	

}
