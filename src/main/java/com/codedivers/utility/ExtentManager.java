package com.codedivers.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private ExtentReports reports;
	private ExtentTest test;

	public ExtentManager(String filePath, String reportName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(false); // Disable Dash board view
		htmlReporter.config().setDocumentTitle("TestInOne - ExtentReports");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("dd/MMM/yyyy hh:mm:ss a");
		htmlReporter.config().setEncoding("UTF-8");

		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}

	public void initializeExtentTest(String testName, String description, String author, String category) {
		test = reports.createTest(testName, description);
		test.assignAuthor(author);
		test.assignCategory(category);
	}

	public void log(Status status, String details) {
		test.log(status, details);
	}

	public void createImageForLog(Status status, String details, String path) {
		try {
			test.log(status, details, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void finish() {
		reports.flush();
	}

	// Basic sample example
	public static void main(String[] args) {
		ExtentManager em = new ExtentManager("results/extent.html", "Report 1");
		em.initializeExtentTest("Test Name 1", "Test Description", "Akash A. Murumkar", "Regression");
		em.log(Status.PASS, "This step is passed");
		em.log(Status.INFO, "This step is an info");
		em.log(Status.FAIL, "This step is failed");
		em.createImageForLog(Status.FAIL, "details", "../images/Registration.png");
		em.finish();
	}
}