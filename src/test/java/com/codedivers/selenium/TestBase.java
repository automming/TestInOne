package com.codedivers.selenium;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.codedivers.utility.SeleniumUtil;

public class TestBase {

	SeleniumUtil selenium;

	@BeforeClass
	public void setup() {
		selenium = SeleniumUtil.getInstance();
	}

	@AfterClass
	public void tearDown() {
		if (selenium.driver != null)
			selenium.driver.quit();
	}

}
