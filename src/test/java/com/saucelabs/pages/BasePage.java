package com.saucelabs.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
//import java.text.Collator;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.saucelabs.core.Hook;
import com.saucelabs.util.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {

	protected AndroidDriver<MobileElement> driver = Hook.getDriver();
	protected ExtentTest extentTest = Hook.getExtentTest();
	protected ExtentReports extentReport = Hook.getExtentReports();
	public final Logger logger = Logger.getLogger(BasePage.class);

	public BasePage() {

	}

	protected void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			logger.error("Erro ao executar wait(int seconds)", e);
		}
	}

	public String getTextElement(String text) {
		By element = By.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", text));
		waitElements(element, 2);
		return driver.findElement(element).getText();
	}


	public String erroMessage() {
		By erroLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]");
		waitElement(erroLocator, 2);
		MobileElement messageError = driver.findElement(erroLocator);
		String message = messageError.findElement(By.className("android.widget.TextView")).getText();
		return message;

	}


	protected void waitMilliseconds(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			logger.error("Erro ao executar wait(int milliseconds)", e);
		}
	}

	protected WebElement waitElement(By by, int timeOutInSeconds) {
		
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	protected WebElement waitElement(WebElement webElement, int timeOutInSeconds) {
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotInteractableException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		return element;
	}

	protected List<WebElement> waitElements(By by, int timeOutInSeconds) {
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		return element;
	}

	protected boolean waitNotPresent(By by, int timeOutInSeconds) {
		AndroidDriver<MobileElement> driver = Hook.getDriver();
		waitMilliseconds(500);
		Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		boolean isElementInvisible = false;
		try {
			isElementInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			return false;
		}
		return isElementInvisible;
	}

	protected boolean waitUntilElementHasValue(WebElement element, String text) {
		boolean isElementhasText = false;
		try {
			waitMilliseconds(500);
			Wait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(Duration.ofSeconds(1))
					.pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);
			isElementhasText = wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
			return isElementhasText;
		} catch (Exception e) {
			return isElementhasText;
		}
	}

	protected void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	protected boolean isElementDisplayed(By by) {
		boolean isElementPresent = false;
		boolean isElementDisplayed = false;
		isElementPresent = !driver.findElements(by).isEmpty();
		if (isElementPresent) {
			isElementDisplayed = driver.findElement(by).isDisplayed();
		}
		return isElementPresent && isElementDisplayed;
	}

	protected void waitLoading() {
		try {
			waitElement(By.id("loadingScreen"), 3);
		} catch (Exception e) {
		}
		waitNotPresent(By.id("loadingScreen"), 120);
	}

	private String saveScreenshotInRelatoriosPath() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int milliseconds = calendar.get(Calendar.MILLISECOND);
		String datahora = "" + day + month + year + "_" + hours + minutes + seconds + milliseconds;
		String screenShotName = datahora + ".png";
		File scrFile = null;
		try {
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("target/report/html/img/" + screenShotName));

		} catch (IOException e) {
			logger.error("Erro ao salvar screenshot.", e);
		}
		return screenShotName;
	}

	protected void log(String log) {
		String screenShotName = saveScreenshotInRelatoriosPath();
        extentTest.pass(log, MediaEntityBuilder.createScreenCaptureFromPath("img/" + screenShotName).build());
    }

	public void logPrintFail(String log) {
		String screenShotName = saveScreenshotInRelatoriosPath();
        extentTest.fail(log, MediaEntityBuilder.createScreenCaptureFromPath("img/" + screenShotName).build());
    }

	protected void logInfo(String log) {
		extentTest.info(log);
	}

	protected void logSkip(String log) {
		extentTest.skip(log);
	}

	protected void logFail(String log) {
		extentTest.fail(log);
	}

	protected void logError(String log) {
		extentTest.fail(log);
	}

	protected void logPass(String log) {
		extentTest.pass(log);
	}

	protected ExtentTest createChildStart(String strNomeTeste) {
		ExtentTest parentTest = Hook.getExtentTest();
		ExtentTest child = parentTest.createNode(strNomeTeste);
		return child;
	}

	protected void childLogFail(ExtentTest child, String log) {
		child.fail(log);
	}

	protected void childLogPass(ExtentTest child, String log) {
		child.pass(log);
	}

	protected void childLogInfo(ExtentTest child, String log) {
		child.info(log);
	}

	public void clearClipboard() {
		StringSelection selection = new StringSelection("");
		Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard2.setContents(selection, selection);
	}

	public String getClipboard() {
		String clipboardText = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			waitMilliseconds(500);
			clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			logger.error("Erro ao obter texto da area de transferencia", e);
		}
		return clipboardText;
	}




	public void aguardarDownloadArquivo() {
		String downloadFilepath = System.getProperty("user.dir") + "/target/temp";
		Utils.waitForFileExistsInPath(downloadFilepath, 10);
		waitMilliseconds(500);
	}


	public List<List<String>> csvToDataTable(String csv, String separator) {
		List<List<String>> dataTable = new ArrayList<List<String>>();
		List<String> lstLinhas = Arrays.asList(csv.split("\\r?\\n"));
		for (String linha : lstLinhas) {
			dataTable.add(Arrays.asList(linha.split(separator)));
		}
		return dataTable;
	}
	

	
	public void swipeFromBottomToUp(int swipeCount) {
		int counter = 0;
		while (counter < swipeCount) {
			swipeFromBottomToUp();
			counter ++;
		}
	}
	
	public void swipeFromBottomToUp() {
		new TouchAction(driver)
		.press(new PointOption<>().withCoordinates(100, 600))
		.waitAction(new WaitOptions().withDuration(Duration.ofMillis(200)))
		.moveTo(new PointOption<>().withCoordinates(100, 50))
		.release()
		.perform();
	}

	public void swipeFromUpToBottom() {
		new TouchAction(driver)
		.tap(new PointOption<>().withCoordinates(100, 50))
		.waitAction(new WaitOptions().withDuration(Duration.ofMillis(100)))
		.moveTo(new PointOption<>().withCoordinates(100, 200))
		.release()
		.perform();
	}

}