package com.codedivers.selenium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codedivers.pages.Google_Home;

public class Test_Google_Search extends TestBase {

	Google_Home home;

	@Parameters({ "browser" })
	@BeforeMethod
	public void openTest(String browser) {
		selenium.setDriver(browser);
		home = new Google_Home(selenium.driver);
	}

	@Test
	public void test() {
		home.launch();
		home.search("selenium");
		home.clickSearch();
	}

	@AfterMethod
	public void closeTest() {
		if (selenium.driver != null)
			selenium.driver.close();
	}

}
