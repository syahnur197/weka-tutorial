package com.syahnur.jsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

public class CSVWriter {
	
	private HttpServletRequest request;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public CSVWriter(HttpServletRequest request) {
		this.request = request;
	}
	
	public String createCSV(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		StringBuilder sb = new StringBuilder();
        sb.append("gender,"
        		+ "NationalITy,"
        		+ "PlaceofBirth,"
        		+ "StageID,"
        		+ "GradeID,"
        		+ "SectionID,"
        		+ "Topic,"
        		+ "Semester,"
        		+ "Relation,"
        		+ "raisedhands,"
        		+ "VisITedResources,"
        		+ "AnnouncementsView,"
        		+ "Discussion,"
        		+ "ParentAnsweringSurvey,"
        		+ "ParentschoolSatisfaction,"
        		+ "StudentAbsenceDays,"
        		+ "Class");
        sb.append("\n");
		sb.append(request.getParameter("gender").toString() + ",");
		sb.append(request.getParameter("NationalITy").toString() + ",");
		sb.append(request.getParameter("PlaceofBirth").toString() + ",");
		sb.append(request.getParameter("StageID").toString() + ",");
		sb.append(request.getParameter("GradeID").toString() + ",");
		sb.append(request.getParameter("SectionID").toString() + ",");
		sb.append(request.getParameter("Topic").toString() + ",");
		sb.append(request.getParameter("Semester").toString() + ",");
		sb.append(request.getParameter("Relation").toString() + ",");
		sb.append(request.getParameter("raisedhands") + ",");
		sb.append(request.getParameter("VisITedResources") + ",");
		sb.append(request.getParameter("AnnouncementsView") + ",");
		sb.append(request.getParameter("Discussion") + ",");
		sb.append(request.getParameter("ParentAnsweringSurvey") + ",");
		sb.append(request.getParameter("ParentschoolSatisfaction").toString() + ",");
		sb.append(request.getParameter("StudentAbsenceDays").toString() + ",");
		sb.append("?");
		pw.write(sb.toString());
        pw.close();
		return fileName;
	}
	
	public String createCSV2(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		StringBuilder sb = new StringBuilder();
        sb.append("gender,"
        		+ "NationalITy,"
        		+ "PlaceofBirth,"
        		+ "StageID,"
        		+ "GradeID,"
        		+ "SectionID,"
        		+ "Topic,"
        		+ "Semester,"
        		+ "Relation,"
        		+ "raisedhands,"
        		+ "VisITedResources,"
        		+ "AnnouncementsView,"
        		+ "Discussion,"
        		+ "ParentAnsweringSurvey,"
        		+ "ParentschoolSatisfaction,"
        		+ "StudentAbsenceDays,"
        		+ "Class");
        sb.append('\n');
        int count = request.getParameterValues("gender").length;
        String genders[] = request.getParameterValues("gender");
        String NationalITys[] = request.getParameterValues("NationalITy");
        String PlaceofBirths[] = request.getParameterValues("PlaceofBirth");
        String StageIDs[] = request.getParameterValues("StageID");
        String GradeIDs[] = request.getParameterValues("GradeID");
        String SectionIDs[] = request.getParameterValues("SectionID");
        String Topics[] = request.getParameterValues("Topic");
        String Semesters[] = request.getParameterValues("Semester");
        String Relations[] = request.getParameterValues("Relation");
        String raisedhandss[] = request.getParameterValues("raisedhands");
        String VisITedResourcess[] = request.getParameterValues("VisITedResources");
        String AnnouncementsViews[] = request.getParameterValues("AnnouncementsView");
        String Discussions[] = request.getParameterValues("Discussion");
        String ParentAnsweringSurveys[] = request.getParameterValues("ParentAnsweringSurvey");
        String ParentschoolSatisfactions[] = request.getParameterValues("ParentschoolSatisfaction");
        String StudentAbsenceDayss[] = request.getParameterValues("StudentAbsenceDays");
        String classs[] = request.getParameterValues("class");
        for(int i = 0; i < count; i++) {
        	sb.append(genders[i] + ",");
    		sb.append(NationalITys[i] + ",");
    		sb.append(PlaceofBirths[i] + ",");
    		sb.append(StageIDs[i] + ",");
    		sb.append(GradeIDs[i] + ",");
    		sb.append(SectionIDs[i] + ",");
    		sb.append(Topics[i] + ",");
    		sb.append(Semesters[i] + ",");
    		sb.append(Relations[i] + ",");
    		sb.append(raisedhandss[i] + ",");
    		sb.append(VisITedResourcess[i] + ",");
    		sb.append(AnnouncementsViews[i] + ",");
    		sb.append(Discussions[i] + ",");
    		sb.append(ParentAnsweringSurveys[i] + ",");
    		sb.append(ParentschoolSatisfactions[i] + ",");
    		sb.append(StudentAbsenceDayss[i] + ",");
    		sb.append(classs[i] + "\n");
        }
		
		pw.write(sb.toString());
        pw.close();
		return fileName;
	}

}
