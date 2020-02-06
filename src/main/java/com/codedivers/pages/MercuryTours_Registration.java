package com.codedivers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MercuryTours_Registration {

	@FindBy(css = "input[name='firstName']")
	private WebElement firstName;

	@FindBy(name = "lastName")
	private WebElement lastName;

	@FindBy(name = "phone")
	private WebElement phone;

	@FindBy(id = "userName")
	private WebElement userName;

	@FindBy(name = "address1")
	private WebElement address1;

	@FindBy(name = "address2")
	private WebElement address2;

	@FindBy(name = "city")
	private WebElement city;

	@FindBy(name = "state")
	private WebElement state;

	@FindBy(name = "postalCode")
	private WebElement postalCode;

	@FindBy(name = "country")
	private WebElement country;

	public MercuryTours_Registration(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void setFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}

	public void setPhone(String phone) {
		this.phone.sendKeys(phone);
	}

	public void setUserName(String userName) {
		this.userName.sendKeys(userName);
	}

	public void setAddress1(String address1) {
		this.address1.sendKeys(address1);
	}

	public void setAddress2(String address2) {
		this.address2.sendKeys(address2);
	}

	public void setCity(String city) {
		this.city.sendKeys(city);
	}

	public void setState(String state) {
		this.state.sendKeys(state);
	}

	public void setPostalCode(String postalCode) {
		this.postalCode.sendKeys(postalCode);
	}

	public void setCountry(String country) {
		new Select(this.country).selectByValue(country);
	}

}
