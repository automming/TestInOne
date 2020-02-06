package com.codedivers.bdd;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "features/DemoQAStore.feature"
		, tags = "@DemoQAStore"
		, glue = "com.codedivers.bdd.stepdefination"
		)

@Test
public class Runner_DemoQAStore extends AbstractTestNGCucumberTests {

}
