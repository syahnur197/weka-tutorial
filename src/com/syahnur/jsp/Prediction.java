package com.syahnur.jsp;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Prediction {

	public static void main(String[] args) throws Exception {
		// DataSource source = new DataSource("C:/Users/Syahnur197/Desktop/students-grade.csv");
		DataSource source = new DataSource("C:/Users/Syahnur197/Desktop/students-grade-test.csv");
		Instances train = source.getDataSet();
		train.setClassIndex(train.numAttributes() - 1);
		
		// DataSource testSource = new DataSource("C:/Users/Syahnur197/Desktop/students-grade-test.csv");
		DataSource testSource = new DataSource("C:/Users/Syahnur197/Desktop/students-grade.csv");
		Instances test = testSource.getDataSet();
		test.setClassIndex(test.numAttributes() - 1);
		
		Classifier cls = new J48();
		cls.buildClassifier(train);
		
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(cls, test);
		for(int i=0; i<test.numInstances(); i++) {
            
            double index = cls.classifyInstance(test.instance(i));
            Instance current = test.instance(i);
            Attribute classAttribute = test.attribute(test.numAttributes() - 1);
            String attValue = current.stringValue(classAttribute);
            System.out.println(attValue);
		}

	}
	
	
	public  ArrayList<String> J48trainAndTest(String trainString, String testString) throws Exception {
		ArrayList<String> predictionList = new ArrayList<String>();
		
		DataSource trainSource = new DataSource(trainString);
		DataSource testSource = new DataSource(testString);
		
		// get instances

		Instances train = trainSource.getDataSet();
		Instances test = testSource.getDataSet();
		
		// set the class
		train.setClassIndex(train.numAttributes() - 1);
		test.setClassIndex(test.numAttributes() - 1);
		
		// build a classifier
		Classifier cls = new J48();
		cls.buildClassifier(train);
		
		// make prediction
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(cls, test);
		for (int i = 0; i < test.numInstances(); i++) {
			double index = cls.classifyInstance(test.instance(i));
			predictionList.add(train.attribute(train.numAttributes() - 1).value((int) index));
		}
		return predictionList;
	}
	
	public  ArrayList<String> NBtrainAndTest(String trainString, String testString) throws Exception {
		ArrayList<String> predictionList = new ArrayList();
		
		DataSource trainSource = new DataSource(trainString);
		DataSource testSource = new DataSource(testString);
		
		// get instances

		Instances train = trainSource.getDataSet();
		Instances test = testSource.getDataSet();
		
		// set the class
		train.setClassIndex(train.numAttributes() - 1);
		test.setClassIndex(test.numAttributes() - 1);
		
		// build a classifier
		Classifier cls = new NaiveBayes();
		cls.buildClassifier(train);
		
		// make prediction
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(cls, test);
		for (int i = 0; i < test.numInstances(); i++) {
			double index = cls.classifyInstance(test.instance(i));
			predictionList.add(train.attribute(train.numAttributes() - 1).value((int) index));
		}
		return predictionList;
	}
	
	public ArrayList<String> actualGrade(String testString) throws Exception {
		ArrayList<String> actualList = new ArrayList();
		DataSource testSource = new DataSource(testString);
		Instances test = testSource.getDataSet();
		for(int i = 0; i < test.numInstances(); i ++) {
			Instance current = test.instance(i);
            Attribute classAttribute = test.attribute(test.numAttributes() - 1);
            String attValue = current.stringValue(classAttribute);
			actualList.add(attValue);
		}
		return actualList;
	}
	
	public String predictInstance(Map<String, String> instanceMap, String trainString) throws Exception {
		String prediction = "WAHH JAVA";
		int noOfAttributes = instanceMap.size() + 1;
		Instance instance = new DenseInstance(noOfAttributes);
		
		PrintWriter pw = new PrintWriter(new File("C:/Users/Syahnur197/Desktop/test.csv"));
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
		for (String key : instanceMap.keySet()) {
			sb.append(instanceMap.get(key));
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		pw.write(sb.toString());
        pw.close();
		return prediction;
	}

}
