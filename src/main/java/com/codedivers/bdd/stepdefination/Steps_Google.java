package com.codedivers.bdd.stepdefination;

import com.codedivers.pages.Google_Home;
import com.codedivers.utility.SeleniumUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_Google {

	SeleniumUtil library;
	Google_Home google;

	@Given("^user loads browser$")
	public void user_loads_the_browser() throws Throwable {
		library = SeleniumUtil.getInstance();
		library.setDriver("chrome");
		google = new Google_Home(library.driver);
	}

	@When("^user opens Google site$")
	public void user_opens_the_Google_site() throws Throwable {
		google.launch();
	}

	@And("^types \"([^\"]*)\" in search$")
	public void types_in_the_search(String arg1) throws Throwable {
		google.search("Selenium");
	}

	@Then("^user should view results$")
	public void user_should_view_the_results() throws Throwable {
		google.clickSearch();
		library.driver.quit();
	}

}
