package com.codedivers.winium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test_Calc {

	WebDriver driver;
	WiniumDriverService service;
	DesktopOptions options;

	@BeforeTest
	public void setup() throws IOException {
		// Set Desktop options
		options = new DesktopOptions();
		options.setApplicationPath("C:\\Windows\\System32\\calc.exe");

		// Driver file path
		File file = new File("./drivers/Winium.Desktop.Driver.exe");

		// Build Winium Driver Service
		service = new WiniumDriverService.Builder().usingDriverExecutable(file).usingPort(9999).withVerbose(true)
				.withSilent(false).buildDesktopService();

		// Start Service
		service.start();
	}

	@BeforeMethod
	public void startDriver() {
		// Start Winium Driver
		driver = new WiniumDriver(service, options);
	}

	@Test
	public void test() {
		WebElement calc = driver.findElement(By.name("Calculator"));

		calc.findElement(By.id("num5Button")).click(); // Click 5
		waitForSeconds(2);
		calc.findElement(By.id("plusButton")).click(); // Click +
		waitForSeconds(2);
		calc.findElement(By.id("num2Button")).click(); // Click 2
		waitForSeconds(2);
		calc.findElement(By.id("equalButton")).click(); // Click =
		
	}

	@AfterMethod
	public void stopDriver() {
		// Stop Winium Driver
		driver.quit();
	}

	@AfterTest
	public void tearDown() {
		// Stop Winium Driver Service
		service.stop();
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
