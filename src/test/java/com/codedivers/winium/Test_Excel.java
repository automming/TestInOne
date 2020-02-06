package com.codedivers.winium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test_Excel {

	WebDriver driver;
	WiniumDriverService service;
	DesktopOptions options;

	@BeforeTest
	public void setup() throws IOException {
		// Set Desktop options
		options = new DesktopOptions();
		options.setApplicationPath("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\EXCEL.exe");

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
		driver.findElement(By.id("AIOStartDocument")).click();

		driver.findElement(By.name("A1")).sendKeys("OTP");
		driver.findElement(By.name("B1")).sendKeys("569751");

		driver.findElement(By.id("FileTabButton")).click();

		driver.findElement(By.name("Save")).click();
		driver.findElement(By.name("Browse")).click();
		driver.findElement(By.id("1001")).sendKeys("C:\\Users\\akash.a.murumkar\\Desktop\\New Excel");
		driver.findElement(By.id("1")).click();

		driver.findElement(By.name("Close")).click();

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
