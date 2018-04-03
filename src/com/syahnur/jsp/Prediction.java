package com.syahnur.jsp;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Prediction {

	public static void main(String[] args) throws Exception {
		DataSource trainSource = new DataSource("C:/Users/Syahnur197/Desktop/weka_data_set/students-grade.csv");
		Instances trainingDataSet = trainSource.getDataSet();
		trainingDataSet.setClassIndex(trainingDataSet.numAttributes() - 1);
		
		DataSource testSource = new DataSource("C:/Users/Syahnur197/Desktop/weka_data_set/students-grade-test.csv");
		Instances testingDataSet = testSource.getDataSet();
		testingDataSet.setClassIndex(testingDataSet.numAttributes() - 1);
		
		NaiveBayesUpdateable classifier = new NaiveBayesUpdateable();
		classifier.buildClassifier(trainingDataSet);
		
		float matched = 0;
		float unmatched = 0;
		
		for(int i = 0; i < testingDataSet.numInstances(); i++) {
			double index = classifier.classifyInstance(testingDataSet.instance(i));
			String predictedValue = trainingDataSet.classAttribute().value((int) index);
			String actualValue = testingDataSet.classAttribute().value((int) testingDataSet.instance(i).classValue());
			String match = "";
			if (predictedValue.equals(actualValue)) {
				match = "matched"; matched++;
			} else {
				match = "unmatched"; unmatched++;
			}
			System.out.println("No: " + i + " -> Actual Value: " + actualValue + " Predicted Value: " + predictedValue + " " + match);
		}
		float matchPct = (matched * 100)/(matched+unmatched);
		String matchPctString = Float.toString(matchPct);
		System.out.println("Accuracy: " + String.valueOf(matchPctString) + "%");
//		DataSource source = new DataSource("C:/Users/Syahnur197/Desktop/weka_data_set/students-grade.csv");
//		// DataSource source = new DataSource("C:/Users/Syahnur197/Desktop/students-grade-test.csv");
//		Instances train = source.getDataSet();
//		train.setClassIndex(train.numAttributes() - 1);
//		
//		DataSource testSource = new DataSource("C:/Users/Syahnur197/Desktop/weka_data_set/students-grade-test.csv");
//		// DataSource testSource = new DataSource("C:/Users/Syahnur197/Desktop/students-grade.csv");
//		Instances test = testSource.getDataSet();
//		test.setClassIndex(test.numAttributes() - 1);
//		
//		Instances unlabeled = new Instances(test);
//		
//		
//		String[] options = new String[1];
//		options[0] = "-U";
//		J48 tree = new J48();
//		tree.setOptions(options);
//		tree.buildClassifier(train);
//		
//		Evaluation eval = new Evaluation(train);
//		eval.crossValidateModel(tree, test, 10, new java.util.Random(1));
//		double percent = eval.pctCorrect();
//		System.out.println("Percentage of correct: " + percent + "%");
//		
//		for (int i = 0; i < unlabeled.numInstances(); i++) {
//			double clsLabel = tree.classifyInstance(unlabeled.instance(i));
//			Instance current = test.instance(i);
//			Attribute classAttribute = test.attribute(test.numAttributes() - 1);
//			String attValue = current.stringValue(classAttribute);
//			System.out.println(attValue);
//		   // predictionList.add(train.attribute(train.numAttributes() - 1).value((int) clsLabel));
//		}
		
//		Classifier cls = new J48();
//		cls.buildClassifier(train);
//		
//		Evaluation eval = new Evaluation(train);
//		eval.evaluateModel(cls, test);
//		for(int i=0; i<test.numInstances(); i++) {
//            
//            double index = cls.classifyInstance(test.instance(i));
//            Instance current = test.instance(i);
//            Attribute classAttribute = test.attribute(test.numAttributes() - 1);
//            String attValue = current.stringValue(classAttribute);
//            System.out.println(attValue);
//		}

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

		J48 cls = new J48();
		//tree.setOptions(options);
		cls.buildClassifier(train);
		
		
		
		// build a classifier
//		Classifier cls = new J48();
//		cls.buildClassifier(train);
		
		// make prediction
		Evaluation eval = new Evaluation(train);
		eval.evaluateModel(cls, test);
//		eval.crossValidateModel(cls, train, 10, new java.util.Random(1));

		for (int i = 0; i < eval.predictions().size(); i++) {
			double index = cls.classifyInstance(test.instance(i));
			predictionList.add(train.classAttribute().value((int) index));
//			System.out.println(index + " -> " + train.classAttribute().value((int) index));
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
		BayesNet cls = new BayesNet();
		cls.buildClassifier(train);
		
		for (int i = 0; i < test.numInstances(); i++) {
			double index = cls.classifyInstance(test.instance(i));
			predictionList.add(train.classAttribute().value((int) index));
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
	
	public Classifier buildClassifier(Instances dataset) throws Exception {
		J48 classifier = new J48();
		classifier.buildClassifier(dataset);
		return classifier;
	}
	
	public void evaluateClassifier() throws Exception {
		DataSource datasource = new DataSource("path/to/datasource.csv");
		Instances dataset = datasource.getDataSet();
		Classifier classifier = buildClassifier(dataset);
		
		Evaluation eval = new Evaluation(dataset);
		eval.crossValidateModel(classifier, dataset, 10, new java.util.Random(1));
		System.out.println("Percentage of Correct Prediction:" + eval.pctCorrect());
	}
	
	public void generatePrediction() throws Exception {
		DataSource trainSource = new DataSource("path/to/trainingSource.csv");
		Instances trainingDataSet = trainSource.getDataSet();
		trainingDataSet.setClassIndex(trainingDataSet.numAttributes() - 1);
		
		DataSource testSource = new DataSource("path/to/testingSource.csv");
		Instances testingDataSet = testSource.getDataSet();
		testingDataSet.setClassIndex(testingDataSet.numAttributes() - 1);
		
		J48 classifier = new J48();
		classifier.buildClassifier(trainingDataSet);

		float matched = 0;
		float unmatched = 0;
		for(int i = 0; i < testingDataSet.numInstances(); i++) {
			double index = classifier.classifyInstance(testingDataSet.instance(i));
			String predictedValue = trainingDataSet.classAttribute().value((int) index);
			String actualValue = testingDataSet.classAttribute().value((int) testingDataSet.instance(i).classValue());
			String match = "";
			if (predictedValue.equals(actualValue)) {
				match = "matched";
				matched++;
			} else {
				match = "unmatched";
				unmatched++;
			}
			System.out.println("No: " + i + " -> Actual Value: " + actualValue + " Predicted Value: " + predictedValue + " " + match);
		}
		float matchPct = (matched * 100)/(matched+unmatched);
		String matchPctString = Float.toString(matchPct);
		System.out.println(String.valueOf(matched));
	}

}
