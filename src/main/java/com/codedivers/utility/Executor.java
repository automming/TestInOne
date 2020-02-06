package com.codedivers.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.codedivers.selenium.pom.Element;
import com.codedivers.selenium.pom.TestCase;
import com.codedivers.selenium.pom.TestStep;

public class Executor {

	private SeleniumUtil selenium;
	private ExtentManager extent;
	private LogWorker logger;

	public Executor(SeleniumUtil seleniumUtil, ExtentManager extent) {
		this.selenium = seleniumUtil;
		this.extent = extent;
	}

	public boolean execute(TestCase testcase) {
		logger = new LogWorker(testcase.getName() + " [" + testcase.getBrowser() + "]");
		List<TestStep> steps = testcase.getSteps();
		for (int index = 0; index < steps.size(); index++) {
			TestStep step = steps.get(index);
			if (!step.getAction().equals("CAPTURESCREEN")) {
				boolean completed = executeTestStep(step);
				if (completed) {
					logger.info(completed + " | " + step.getDescription());
					extent.log(Status.PASS, step.getDescription());
				} else {
					return false;
				}
			} else if (step.getAction().equals("CAPTURESCREEN")) {
				extent.createImageForLog(Status.PASS, step.getDescription(),
						selenium.captureScreen(getFileName(step.getData())));
			}
		}
		return true;
	}

	private boolean executeTestStep(TestStep step) {
		boolean executed = false;
		String action = step.getAction();
		String data = step.getData();
		Element element = step.getElement();
		By by = null;
		if (element != null) {
			if ((element.getId() != "") && (element.getId() != null)) {
				by = selenium.getLocator("id", element.getId());
			} else if ((element.getXpath() != "") && (element.getXpath() != null)) {
				by = selenium.getLocator("xpath", element.getXpath());
			} else if ((element.getName() != "") && (element.getName() != null)) {
				by = selenium.getLocator("name", element.getName());
			} else if ((element.getClassName() != "") && (element.getClassName() != null)) {
				by = selenium.getLocator("classname", element.getClassName());
			} else if ((element.getTag() != "") && (element.getTag() != null)) {
				by = selenium.getLocator("tagname", element.getTag());
			}
		}
		try {
			selenium.perform(action, by, data);
			executed = true;
		} catch (Exception e) {
			logger.error(false + " | " + step.getDescription() + " | \n" + e.getMessage());
			extent.createImageForLog(Status.FAIL, step.getDescription() + " - " + e.getMessage(),
					selenium.captureScreen(getFileName("Error")));
		}
		return executed;
	}

	public String getFileName(String fileName) {
		SimpleDateFormat date = new SimpleDateFormat("dd_MMM_yyyy-hh_mm_ss");
		String time = date.format(Calendar.getInstance().getTime());
		return fileName + "-" + time;
	}

}
