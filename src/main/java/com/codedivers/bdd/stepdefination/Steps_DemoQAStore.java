package com.codedivers.bdd.stepdefination;

import org.openqa.selenium.WebDriver;

import com.codedivers.pages.DemoQAStore_Home;
import com.codedivers.pages.DemoQAStore_Login;
import com.codedivers.pages.DemoQAStore_Registration;
import com.codedivers.utility.SeleniumUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_DemoQAStore {

	SeleniumUtil library;
	WebDriver driver;
	DemoQAStore_Home home;
	DemoQAStore_Login login;
	DemoQAStore_Registration registration;

	public Steps_DemoQAStore() {
		library = SeleniumUtil.getInstance();
		library.setDriver("chrome");
		driver = library.driver;
		login = new DemoQAStore_Login(driver);
		registration = new DemoQAStore_Registration(driver);
	}

	@Given("^user opens DemoQA store application$")
	public void user_opens_DemoQA_store() throws Throwable {
		home = new DemoQAStore_Home(driver);
		home.launch();
		home.clickMyAccount();
	}

	@When("^user clicks registration link$")
	public void user_clicks_on_registration_link() throws Throwable {
		login.clickRegister();
	}

	@And("^user fills up registration details$")
	public void user_adds_registration_details() throws Throwable {
		registration.addUsername("username");
		registration.addEmail("username@email.com");
	}

	@Then("^user should complete DemoQA registration$")
	public void user_should_complete_registration() throws Throwable {
		registration.submit();
		driver.quit();
	}

}
