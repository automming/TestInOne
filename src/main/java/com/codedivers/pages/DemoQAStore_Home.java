package com.codedivers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQAStore_Home {

	private WebDriver driver;

	@FindBy(className = "account_icon")
	private WebElement myAccount;

	public DemoQAStore_Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void launch() {
		driver.get("http://store.demoqa.com/");
	}

	public void clickMyAccount() {
		myAccount.click();
	}

}
