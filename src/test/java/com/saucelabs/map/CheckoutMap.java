package com.saucelabs.map;

import com.saucelabs.core.Hook;
import com.saucelabs.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class CheckoutMap extends BasePage {

	public CheckoutMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")
	protected MobileElement filterButton;

	@AndroidFindBy(accessibility = "test-Description")
	protected MobileElement containerDescription;



	@AndroidFindBy(accessibility = "test-CHECKOUT")
	protected MobileElement checkoutButton;


	@AndroidFindBy(accessibility = "test-First Name")
	protected MobileElement name;


	@AndroidFindBy(accessibility = "test-Last Name")
	protected MobileElement lastName;

	@AndroidFindBy(accessibility = "test-Zip/Postal Code")
	protected MobileElement zipCode;

	@AndroidFindBy(accessibility = "test-CONTINUE")
	protected MobileElement buttonContinue;

	@AndroidFindBy(accessibility = "test-FINISH")
	protected MobileElement finishButton;



}