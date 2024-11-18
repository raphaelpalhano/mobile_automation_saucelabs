package com.saucelabs.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.saucelabs.pages.BasePage;
import com.saucelabs.util.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.sikuli.script.ImagePath;

public class Hook {

	private static AndroidDriver<MobileElement> driver;
	private static ExtentReports extentReport;
	private static Scenario scenario;
	private static ExtentTest extentTest;
	private static final Logger logger = Logger.getLogger(Hook.class);
	private static String activeAutomation;

	public Hook() {
		super();
	}


	@Before
	public void beforeCenario(Scenario scenario) {
	    Hook.scenario = scenario;

		Utils.setEnvironment();
		new File("target/report").mkdir();
		new File("target/report/pdf").mkdir();
		new File("target/temp").mkdir();
		Utils.deleteAllFilesInFolder("target/temp");

		// CREATE EXTENTREPORT
		new File("target/report/html").mkdir();
		new File("target/report/html/img").mkdir();
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/report/html/" + scenario.getName() + ".html");
		htmlReporter.config().setEncoding("ISO-8859-1");
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		Utils.addAllTestPropertiesToExtentReport(extentReport);
		extentReport.setSystemInfo("os.name", System.getProperty("os.name"));
	    
	    System.out.println("Thread ID - " + Thread.currentThread().getId());

		//START EXTENTTEST
		extentTest = extentReport.createTest("Cenario: " + scenario.getName(), scenario.getName());
		extentTest.assignCategory("feature:" + scenario.getId().replaceAll(";.*", ""));
		Collection<String> tags = scenario.getSourceTagNames();
		for (String tag : tags) {
			extentTest.assignCategory(tag);
		}
		
		System.out.println("Cenario: " + scenario.getName());
		openApplicationAndroid("/app/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

	}
	
	
	@After
	public void afterCenario() throws IOException{
    	if(scenario.isFailed()){
    		if (driver != null ) {
        		BasePage basePage = new BasePage();
        		basePage.logPrintFail("The test is failed");
        		
        		Throwable throwable = logError(scenario);
        		extentTest.fail(throwable);
        		String errorMessage = throwable.getMessage();
        		if (errorMessage != null) {
            		if (errorMessage.contains("FindFailed")) {
            			try {
                			String findFailedImage = Utils.substringRegexGroup1("FindFailed: (.*): ", errorMessage);
                			FileUtils.copyFile(new File(ImagePath.find(findFailedImage).getPath()), new File("target/report/html/img/" + findFailedImage));
                			extentTest.info("FindFailed: " + findFailedImage, MediaEntityBuilder.createScreenCaptureFromPath("img/" + findFailedImage).build());
						} catch (Exception e) {
							logger.log(Level.ERROR, "problema em encontrar imagem {hook}");
						}
    				}
				}
			}
    	}

		//FINISH EXTENT REPORT
    	extentReport.flush();
    	
		//FINISH DRIVER
    	if (driver != null) {
    		driver.quit();
    		driver = null;
		}
    	
	}
	
	private Throwable logError(Scenario scenario) {
		Field field = FieldUtils.getField(Scenario.class, "delegate", true);
		Method getError = null;
	    try {
	        final TestCaseState testCase = (TestCaseState) field.get(scenario);
	        if (getError == null) {
	            getError = MethodUtils.getMatchingMethod(testCase.getClass(), "getError");
	            getError.setAccessible(true);
	        }
	        return (Throwable) getError.invoke(testCase);
	    } catch (Exception e) {
	    	System.err.println("error receiving exception" + e);
	    }
	    return null;
	}
	
	public static AndroidDriver<MobileElement> getDriver() {
		return driver;
	}
	
	public static ExtentTest getExtentTest() {
		return extentTest;
	}
	
	public static Scenario getScenario() {
		return scenario;
	}
	

	public static ExtentReports getExtentReports(){
		return extentReport;
	}
	
	public static String getActiveAutomation(){
		return activeAutomation;
	}
	


	
	public static void openApplicationAndroid(String apk){
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if(apk.endsWith(".apk")){
			capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + apk);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			capabilities.setCapability("platformName", Platform.ANDROID);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.swaglabsmobileapp");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.swaglabsmobileapp.MainActivity");
			capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
			capabilities.setCapability("automationName", "uiautomator2");
		}else{
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "emulator-5554");
			capabilities.setCapability(CapabilityType.BROWSER_VERSION, "10");
		}

		try {
			driver = new AndroidDriver<MobileElement>(new URL(Utils.getTestProperty("device.url")), capabilities);

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
