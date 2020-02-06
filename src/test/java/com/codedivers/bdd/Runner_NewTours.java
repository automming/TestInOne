package com.codedivers.bdd;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "features/NewTours.feature", tags = "@NewTours", glue = "com.codedivers.bdd.stepdefination")

@Test
public class Runner_NewTours extends AbstractTestNGCucumberTests {

}
