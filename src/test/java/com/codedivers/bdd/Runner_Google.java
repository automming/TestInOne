package com.codedivers.bdd;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "features/Google.feature"
		, tags = "@Google"
		, glue = "com.codedivers.bdd.stepdefination"
		)

@Test
public class Runner_Google extends AbstractTestNGCucumberTests {

}
