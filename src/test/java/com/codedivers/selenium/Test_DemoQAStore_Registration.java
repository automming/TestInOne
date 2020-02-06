package com.codedivers.selenium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codedivers.pages.DemoQAStore_Home;
import com.codedivers.pages.DemoQAStore_Login;
import com.codedivers.pages.DemoQAStore_Registration;

public class Test_DemoQAStore_Registration extends TestBase {

	DemoQAStore_Home home;
	DemoQAStore_Login login;
	DemoQAStore_Registration registration;

	@Parameters({ "browser" })
	@BeforeMethod
	public void openTest(String browser) {
		selenium.setDriver(browser);
		home = new DemoQAStore_Home(selenium.driver);
		login = new DemoQAStore_Login(selenium.driver);
		registration = new DemoQAStore_Registration(selenium.driver);
	}

	@Test
	public void test() {
		home.launch();
		home.clickMyAccount();
		login.clickRegister();
		registration.addUsername("testusername");
		registration.addEmail("test@mail.com");
		registration.submit();
	}

	@AfterMethod
	public void closeTest() {
		if (selenium.driver != null)
			selenium.driver.close();
	}

}
