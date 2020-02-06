package com.codedivers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author akash.a.murumkar
 * @category Page Object Model
 */

public class Google_Home {

	private WebDriver driver;
	private By search = By.name("q");
	private By searchButton = By.xpath("(//*[@value='Google Search'])[1]");

	public Google_Home(WebDriver driver) {
		this.driver = driver;
	}

	public void launch() {
		driver.get("http://www.google.com/");
	}

	public void search(String query) {
		driver.findElement(search).sendKeys(query);
	}

	public void clickSearch() {
		driver.findElement(searchButton).click();
	}

}
