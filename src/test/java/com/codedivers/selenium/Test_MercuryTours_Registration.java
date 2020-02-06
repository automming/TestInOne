package com.codedivers.selenium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codedivers.pages.MercuryTours_Home;
import com.codedivers.pages.MercuryTours_Registration;

public class Test_MercuryTours_Registration extends TestBase {

	MercuryTours_Home home;
	MercuryTours_Registration registration;

	@Parameters({ "browser" })
	@BeforeMethod
	public void openTest(String browser) {
		selenium.setDriver(browser);
		home = new MercuryTours_Home(selenium.driver);
		registration = new MercuryTours_Registration(selenium.driver);
	}

	@Test
	public void test() {
		home.clickRegister();
		registration.setFirstName("FirstName");
		registration.setLastName("LastName");
		registration.setPhone("123456789");
		registration.setUserName("email@address.com");
		registration.setAddress1("Address Line 1");
		registration.setAddress2("Address Line 2");
		registration.setCity("City");
		registration.setState("State");
		registration.setPostalCode("560056");
		registration.setCountry("92");
	}

	@AfterMethod
	public void closeTest() {
		if (selenium.driver != null)
			selenium.driver.close();
	}

}
