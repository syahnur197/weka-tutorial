package com.syahnur.jsp;

import java.sql.*;
import java.util.HashMap;

import javax.sql.*;
public class Driver {

	public static void main(String[] args) {
		try {
			ConnectionManager cm = new ConnectionManager();
			
			// ResultSet rs = myStatement.executeQuery("SELECT * FROM `product_table`");
			HashMap<String,String> values = new HashMap<String,String>();
			values.put("structure_name", "StudentStructureNine");
			values.put("structure_string", "Gender[Male;Female],Nationality[Kuwait;SaudiArabia;Singapore;Jordan;USA;Lebanon;Iran;Venezuela;Egypt;Tunis;Morocco;Syria;Palestine;Iraq;Lybia],PlaceOfBirth[Kuwait;SaudiArabia;USA;Jordan;Lebanon;Iran;Venezuela;Egypt;Tunis;Morocco;Syria;Palestine;Iraq;Lybia],StageID[LowerLevel;MiddleSchool;HighSchool],GradeID[G-02;G-04;G-05;G-06;G-07;G-08;G-09;G-10;G-11;G-12],SectionID[A;B;C],Topic[IT;Math;Arabic;English;Quran;Science;French;Spanish;History;Biology;Chemistry;Geology],Semester[F;S],Relation[Father;Mum],RaisedHands,VisitedResources,AnnouncementsView,Discussion,ParentAnsweringSurvey[Yes;No],ParentschoolSatisfaction[Good;Bad],StudentAbsenceDays[Above-7;Under-7],Class[L;M;H]");
			boolean select = cm.get("structure", "*", "`structure_name`=\""+values.get("structure_name")+"\"");
			if (select) {
				System.out.println("INSERT FAIL: Structure name is already exist!");
			} else {
				select = cm.get("structure", "*", "`structure_string` LIKE \""+values.get("structure_string")+"\"");
				if (select) {
					System.out.println("INSERT FAIL: Structure is already exist!");
				} else {
					System.out.println(cm.getSQL());
					boolean success = cm.insert("structure", values);
					if(success) {
						System.out.println(cm.getInsertID());
						
					} else {
						System.out.println("Fail to execute query");
					}
				}
			}
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
