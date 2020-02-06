package com.codedivers.utility;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {

	public WebDriver driver;

	//private static SeleniumUtil instance = null;

	private SeleniumUtil() {
	}

	public static SeleniumUtil getInstance() {
		/*if (instance == null) {
			instance = new SeleniumUtil();
		}
		return instance;*/
		return new SeleniumUtil();
	}

	public void setDriver(String browser) {
		browser = browser.toLowerCase();
		if (browser.contains("chrome") || browser.equalsIgnoreCase("gc")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			// options.setHeadless(true); // For Headless Automation
			driver = new ChromeDriver(options);
		} else if (browser.contains("firefox") || browser.contains("ff")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("marionette", true);
			// options.setCapability(FirefoxDriver.BINARY, "C:\\Program Files (x86)\\Mozilla
			// Firefox\\firefox.exe");
			driver = new FirefoxDriver(options);
		} else if (browser.contains("ie") || browser.contains("internetexplorer")) {
			System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
			driver = new InternetExplorerDriver(options);
		} else if (browser.contains("edge")) {
			System.setProperty("webdriver.edge.driver", "./drivers/MicrosoftWebDriver.exe");
			EdgeOptions options = new EdgeOptions();
			driver = new EdgeDriver(options);
		} else if (browser.contains("safari") || browser.contains("apple")) {
			driver = new SafariDriver();
		} else if (browser.contains("opera")) {

		} else if (browser.contains("phantom")) {

		} else if (browser.contains("html")) {

		} else {
			throw new InvalidArgumentException("CSTMEXP: Invalid Browser Name.");
		}
		driver.manage().window().maximize(); // Maximize or Full Screen the browser
	}

	public void perform(String action, By by, String data) {
		switch (action.toUpperCase()) {
		case "CLICK":
			click(by);
			break;
		case "CLICKANDWAIT":
			clickAndWait(by, data);
			break;
		case "ENTER":
			input(by, data);
			break;
		case "CLEAR":
			clear(by);
			break;
		case "SUBMIT":
			submit(by);
			break;
		case "LAUNCH":
			goTo(data);
			break;
		case "SELECTBYINDEX":
			selectByIndex(by, data);
			break;
		case "SELECTBYVALUE":
			selectByValue(by, data);
			break;
		case "SELECTBYVISIBLETEXT":
			selectByVisibleText(by, data);
			break;
		case "NAVIGATE":
			navigateTo(data);
			break;
		case "NAVIGATEFORWARD":
			navigateForward();
			break;
		case "NAVIGATEBACK":
			navigateBack();
			break;
		case "SWITCHTOALERT":
			switchToAlert();
			break;
		case "SWITCHTOFRAMEELEMENT":
			switchToFrameElement(by);
			break;
		case "SWITCHTOFRAMEINDEX":
			switchToFrameIndex(data);
			break;
		case "SWITCHTOWINDOW":
			switchToWindow(data);
			break;
		case "DELETEALLCOOKIES":
			deleteAllCookies();
			break;
		case "REFRESH":
			refresh();
			break;
		case "VERIFYTEXTPRESENT":
			verifyTextPresent(by, data);
			break;
		case "VERIFYALERTTEXT":
			verifyAlertText(data);
			break;
		case "ACCEPTALERT":
			acceptAlert();
			break;
		case "DISMISSALERT":
			dismissAlert();
			break;
		case "CAPTURESCREEN":
			captureScreen(data);
			break;
		case "JAVASCRIPT":
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(data);
			break;
		default:
			throw new InvalidArgumentException("CSTMEXP: Invalid Action - " + action);
		}
	}

	public void setImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void setPageLoadTimeout(int seconds) {
		driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
	}

	public void staticWait(String seconds) {
		try {
			Thread.sleep(Integer.valueOf(seconds) * 1000);
		} catch (Exception e) {
			throw new IllegalArgumentException("CSTMEXP: Wrong wait time format.");
		}
	}

	public void waitTillElementIsVisible(WebElement element, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void click(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
	}

	public void clickAndWait(By by, String data) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
		staticWait(data);
	}

	public void input(By by, String data) {
		driver.findElement(by).sendKeys(data);
	}

	public void clear(By by) {
		driver.findElement(by).clear();
	}

	public void submit(By by) {
		driver.findElement(by).submit();
	}

	public void goTo(String url) {
		driver.get(url);
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void navigateForward() {
		driver.navigate().forward();
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void switchToFrameElement(By by) {
		driver.switchTo().frame(driver.findElement(by));
	}

	public void switchToFrameIndex(String index) {
		driver.switchTo().frame(Integer.parseInt(index));
	}

	public void switchToWindow(String windowTitle) {
		driver.switchTo().window(windowTitle);
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void switchToAlert() {
		driver.switchTo().alert();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void verifyTextPresent(By by, String expectedText) {
		String actualText = driver.findElement(by).getText();
		assertEquals(actualText, expectedText, "Expected text is correct");
	}

	public void verifyAlertText(String alertText) {
		String actualText = driver.switchTo().alert().getText();
		assertEquals(actualText, alertText, "Alert Text is correct");
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void selectByVisibleText(By by, String text) {
		new Select(driver.findElement(by)).selectByVisibleText(text);
	}

	public void selectByIndex(By by, String index) {
		new Select(driver.findElement(by)).selectByIndex(Integer.parseInt(index));
	}

	public void selectByValue(By by, String value) {
		new Select(driver.findElement(by)).selectByValue(value);
	}

	public void fullscreen() {
		driver.manage().window().fullscreen();
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public By getLocator(String locator, String locatorValue) {
		locator = locator.toLowerCase();
		if (locator.equals("id")) {
			return By.id(locatorValue);
		} else if (locator.equals("classname")) {
			return By.className(locatorValue);
		} else if (locator.equals("name")) {
			return By.name(locatorValue);
		} else if (locator.equals("xpath")) {
			return By.xpath(locatorValue);
		} else if (locator.equals("cssselector")) {
			return By.cssSelector(locatorValue);
		} else if (locator.equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);
		} else if (locator.equals("linktext")) {
			return By.linkText(locatorValue);
		} else if (locator.equals("tagname")) {
			return By.tagName(locatorValue);
		} else {
			throw new IllegalArgumentException("CSTMEXP: Invalid locator - " + locator);
		}
	}

	public String captureScreen(String imageName) {
		String time;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");

		time = date.format(cal.getTime());
		time = time.replaceAll(":", "-");
		time = time.replaceAll("/", "-");

		String filePath = System.getProperty("user.dir") + File.separator + "images" + File.separator + imageName + "-"
				+ time + ".png";

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath;
	}

	public void dragAndDropActions(WebDriver driver, String sourceXpath, String targetXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		WebElement target = driver.findElement(By.xpath(targetXpath));

		Actions builder = new Actions(driver);
		builder.clickAndHold(source).perform();
		builder.moveToElement(target, target.getLocation().x, target.getLocation().y).perform();
		builder.release(target).perform();
	}

	public void dragAndDropRobot(WebDriver driver, String sourceXpath, String targetXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		WebElement target = driver.findElement(By.xpath(targetXpath));

		try {
			Point c1 = source.getLocation();
			Point c2 = target.getLocation();
			System.out.println("SOURCE = X:" + c1.getX() + "--> Y:" + c1.getY());
			System.out.println("TARGET = X:" + c2.getX() + "--> Y:" + c2.getY());
			Robot robot = new Robot();
			robot.mouseMove(c1.getX(), c1.getY());
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseMove(c2.getX(), c2.getY());
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void dragAndDropJavaScript(WebDriver driver, String sourceId, String targetId) {
		try {
			String line = null;

			BufferedReader br = new BufferedReader(new FileReader("javascript\\drag.js"));
			StringBuffer buffer = new StringBuffer();

			while ((line = br.readLine()) != null)
				buffer.append(line);

			String javaScript = buffer.toString();

			String java_Script = javaScript + "$('#" + sourceId + "').simulateDragDrop({ dropTarget: '#" + targetId
					+ "'});";

			((JavascriptExecutor) driver).executeScript(java_Script);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void focusJavaScript(String sourceXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", source);
	}

	public void focusAndClick(String sourceXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		Locatable hoverItem = (Locatable) source;
		Mouse mouse = ((HasInputDevices) driver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates());
		mouse.click(hoverItem.getCoordinates());
	}

	public void openUrlNewWindow(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()"); // Open new tab

		int size = driver.getWindowHandles().size(); // Get number of tabs
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[size - 1]);
		navigateTo(url);// Navigate to URL
	}

	public void getPresentWorkingWindow() {
		int size = driver.getWindowHandles().size(); // Get number of tabs
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[size - 1]);
	}

	public void openNewTab() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()"); // Open new tab
		int size = driver.getWindowHandles().size(); // Get number of tabs
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[size - 1]);
	}

	public String[] getWindowTitles() {
		int size = driver.getWindowHandles().size();
		String[] titles = new String[size];
		for (int i = 0; i < size; i++) {
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[i]);
			titles[i] = driver.getTitle().trim();
		}
		return titles;
	}

	public void switchToWindowIndex(int i) {
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[i]);
	}

	public void focusWindow() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.focus()");
	}

	public void switchWindow(String[] titles, String currentTab, String switchToTab) {
		pressAltTabRobot();
		int switchToTabIndex = 0;
		int currentTabIndex = 0;
		for (int i = 0; i < titles.length; i++) {
			if (titles[i].contains(switchToTab)) {
				switchToTabIndex = i;
			}
			if (titles[i].contains(currentTab)) {
				currentTabIndex = i;
			}
		}
		System.out.println("Focus Tab Index for \"" + switchToTab + "\": " + switchToTabIndex);
		System.out.println("Current Tab Index for \"" + currentTab + "\": " + currentTabIndex);
		int press = 0;
		if (switchToTabIndex == currentTabIndex) {
			press = currentTabIndex;
			System.out.println("No focus");
		} else if (currentTabIndex > switchToTabIndex) {
			press = press + (titles.length - 1) - currentTabIndex;
			press = press + switchToTabIndex + 1;
			for (int j = 0; j < press; j++)
				pressCtrlTabRobot();
			System.out.println("Pressed Ctrl+TAB: " + press + " times");
		} else {
			press = switchToTabIndex - currentTabIndex;
			for (int k = 0; k < press; k++)
				pressCtrlTabRobot();
			System.out.println("Pressed Ctrl+TAB: " + press + " times");
		}
		System.out.println(driver.getTitle());
	}

	public void pressCtrlTabAction() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
	}

	public void pressCtrlTabRobot() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_TAB);

			r.keyRelease(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void pressAltTabRobot() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ALT);
			r.keyPress(KeyEvent.VK_TAB);

			r.keyRelease(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_ALT);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
