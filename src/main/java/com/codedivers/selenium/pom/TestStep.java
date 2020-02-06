package com.codedivers.selenium.pom;

public class TestStep {

	String description, action, data;
	Element element;

	public TestStep(String decription, String action, Element element, String data) {
		this.description = decription;
		this.action = action;
		this.element = element;
		this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
