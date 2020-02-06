package com.codedivers.selenium;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codedivers.selenium.pom.TestCase;
import com.codedivers.utility.Executor;
import com.codedivers.utility.ExtentManager;
import com.codedivers.utility.PropertyLoader;
import com.codedivers.utility.ReadObjectRepository;
import com.codedivers.utility.ReadTestRepository;
import com.codedivers.utility.SeleniumUtil;

public class SELENIUM_Executor {

	String application, testRepositoryFilePath, objectRepositoryFilePath;
	SeleniumUtil selenium;
	ExtentManager extent;
	List<TestCase> testcases;

	@BeforeSuite
	public void initialize() {
		application = PropertyLoader.getInstance().getValue("application");
		selenium = SeleniumUtil.getInstance();
		testRepositoryFilePath = System.getProperty("user.dir") + "/inputs/" + application
				+ "/SELENIUM/TestRepository.xlsx";
		objectRepositoryFilePath = System.getProperty("user.dir") + "/inputs/" + application
				+ "/SELENIUM/ObjectRepository.xlsx";
		extent = new ExtentManager(getFileName("results/SeleniumReport") + ".html",
				"TestInOne (" + application + ") ExtentReports");
	}

	@BeforeTest
	public void setupTests() {
		ReadObjectRepository objectRepo = new ReadObjectRepository(objectRepositoryFilePath);
		ReadTestRepository testRepo = new ReadTestRepository(testRepositoryFilePath);
		testcases = testRepo.getTestCasesRepository(objectRepo.getObjectRepository());
	}

	@DataProvider(name = "provider")
	public Object[] data() {
		List<TestCase> cases = new ArrayList<TestCase>();
		for (int i = 0; i < testcases.size(); i++) {
			if (testcases.get(i).getExecute() == 1) {
				cases.add(testcases.get(i));
			}
		}
		Object[] data = new Object[cases.size()];
		for (int j = 0; j < cases.size(); j++) {
			data[j] = cases.get(j);
		}
		return data;
	}

	@BeforeMethod
	public synchronized void setupReporting() {
		System.out.println("Test Case Execution Started.");
	}

	@Test(dataProvider = "provider", enabled = true)
	public void main(TestCase testcase) {
		extent.initializeExtentTest(testcase.getId() + " [" + testcase.getBrowser() + "]", testcase.getName(), "Akash",
				"Regression");
		selenium.setDriver(testcase.getBrowser());
		selenium.setPageLoadTimeout(30);
		Executor executor = new Executor(selenium, extent);
		if (!executor.execute(testcase))
			Assert.fail("Failed - " + testcase.getName());
	}

	@AfterMethod
	public synchronized void populateReporting() {
		if (selenium.driver != null) {
			selenium.driver.quit();
			System.out.println("Browser Closed.");
		}
		extent.finish();
	}

	@AfterTest
	public void teardown() {
		if (selenium.driver != null) {
			selenium.driver.quit();
			System.out.println("Final Driver Closed.");
		}
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Suite Execution Completed.");
	}

	public String getFileName(String fileName) {
		SimpleDateFormat date = new SimpleDateFormat("dd_MMM_yyyy-hh_mm_ss");
		String time = date.format(Calendar.getInstance().getTime());
		return fileName + "-" + time;
	}

}
