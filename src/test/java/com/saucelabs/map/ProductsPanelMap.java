package com.saucelabs.map;

import com.saucelabs.core.Hook;
import com.saucelabs.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class ProductsPanelMap extends BasePage {

	public ProductsPanelMap() {
		PageFactory.initElements(new AppiumFieldDecorator(Hook.getDriver()), this);
	}
	


	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")
	protected MobileElement filterButton;



	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"Selector container\"]/android.view.ViewGroup")
	protected MobileElement containerFilters;

	protected MobileElement containerItem(int item) {
		By element = By.xpath(String.format("(//android.view.ViewGroup[@content-desc=\"test-Item\"])[%d]", item));
		waitElement(element, 1);
		return driver.findElement(element);
	}

	protected MobileElement addCartUsingTitle(String titleItem) {
		By element = By.xpath(String.format("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"%s\"]/ancestor::android.view.ViewGroup[@content-desc=\"test-Item\"]//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]", titleItem));
		return driver.findElement(element);
	}

	protected MobileElement removeCartUsingTitle(String titleItem) {
		By element = By.xpath(String.format("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"%s\"]/ancestor::android.view.ViewGroup[@content-desc=\"test-Item\"]//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]", titleItem));
		waitElements(element, 1);
		return driver.findElement(element);
	}

	protected MobileElement containerItemProduct(int item, String titleItemProduct) {
		By element = By.xpath(String.format("(//android.view.ViewGroup[@content-desc=\"test-Item\"])[%d]", item));
		waitElement(element, 1);
		return driver.findElement(element).findElement(By.xpath(String.format("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"%s\"]", titleItemProduct)));
	}


	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup")
	protected MobileElement containerCart;


}