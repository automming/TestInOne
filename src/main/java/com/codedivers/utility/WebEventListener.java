package com.codedivers.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public abstract class WebEventListener implements WebDriverEventListener {

	@Override
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("Accepted alert!");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("Dismissed alert!");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] arg2) {
		System.out.println("Element value changed to: " + element.toString());
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : " + by.toString());
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("Refreshed page");
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to:'" + url + "'");
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Accepting alert...");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("Dismissing alert...");
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] arg2) {
		System.out.println("Value of the:" + element.toString() + " before any changes made");
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find Element By : " + by.toString());
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("Refreshing page...");
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	@Override
	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured: " + error);
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		System.out.println("Before running the script: " + script);
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		System.out.println("After running the script: " + script);
	}

}
