package com.codedivers.selenium.pom;

import java.util.List;

public class TestCase {

	String id, name, browser;
	List<TestStep> steps;
	int execute;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestStep> getSteps() {
		return steps;
	}

	public void setSteps(List<TestStep> steps) {
		this.steps = steps;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public int getExecute() {
		return execute;
	}

	public void setExecute(int execute) {
		this.execute = execute;
	}

}
