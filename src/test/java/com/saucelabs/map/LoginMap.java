package com.saucelabs.map;

import com.saucelabs.core.Hook;
import com.saucelabs.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginMap extends BasePage {

	public LoginMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	

	@AndroidFindBy(accessibility = "test-Username")
	protected MobileElement username;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
	protected MobileElement password;
	
	
	@AndroidFindBy(accessibility = "test-LOGIN")
	protected MobileElement loginButton;



}