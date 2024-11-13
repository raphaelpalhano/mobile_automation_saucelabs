package com.saucelabs.steps;

import com.saucelabs.map.CheckoutMap;
import com.saucelabs.pages.CheckoutPage;
import com.saucelabs.pages.ProductsPanelPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;


public class CheckoutStep {

	CheckoutPage checkoutPage = new CheckoutPage();
	ProductsPanelPage productsPainelPage = new ProductsPanelPage();


	@When("I access cart I see the product {string}")
	public void moveToCart(String productName) {
		productsPainelPage.accesCart();
		String actualProductName = checkoutPage.productNameCheckout();
		assertEquals(productName, actualProductName);
	}

	@When("access checkout")
	public void moveToCheckout() {
		checkoutPage.startCheckout();
	}



	@When("fill form with {string} data")
	public void fillFormCheckout(String fillFormCondition) {
		checkoutPage.fillFormCheckout(fillFormCondition);
	}


	@When("I finish my checkout")
	public void finishCheckout() {
		checkoutPage.finishCheckout();
	}



}

