package com.codedivers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQAStore_Login {

	@FindBy(xpath = "//a[text()='Register']")
	private WebElement register;

	public DemoQAStore_Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickRegister() {
		register.click();
	}

}
