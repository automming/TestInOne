package com.codedivers.bdd.stepdefination;

import org.openqa.selenium.WebDriver;

import com.codedivers.pages.MercuryTours_Home;
import com.codedivers.pages.MercuryTours_Registration;
import com.codedivers.utility.SeleniumUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_MercuryTours {

	SeleniumUtil library;
	WebDriver driver;
	MercuryTours_Home home;
	MercuryTours_Registration registration;

	public Steps_MercuryTours() {
		library = SeleniumUtil.getInstance();
		library.setDriver("chrome");
		driver = library.driver;
		registration = new MercuryTours_Registration(driver);
	}

	@Given("^user opens NewTours application$")
	public void user_opens_NewTours() throws Throwable {
		home = new MercuryTours_Home(driver);
	}

	@When("^user clicks on registration$")
	public void user_clicks_on_registration_link() throws Throwable {
		home.clickRegister();
	}

	@And("^user fills registration details for Mercury Tours application$")
	public void user_fills_registration_details() throws Throwable {
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

	@Then("^user should completes Mercury Tours registration$")
	public void user_completes_registration() throws Throwable {
		driver.quit();
	}

}
