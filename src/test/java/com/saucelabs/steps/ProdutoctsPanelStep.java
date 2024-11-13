package com.saucelabs.steps;

import com.saucelabs.pages.ProductsPanelPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;


public class ProdutoctsPanelStep {

	ProductsPanelPage productsPainelPage = new ProductsPanelPage();




	@Given("I sort items by {string}")
	public void sort_items_by(String sortSelect) {
		productsPainelPage.openFilterSort();
		productsPainelPage.sortItemsBy(sortSelect);
	}


	@Then("I see the first item {int} with title {string}")
	public void itemsFiltred(int item, String productTitle) {
		String actualTitleProduct = productsPainelPage.getTitleItemProduct(item, productTitle);
		assertEquals(productTitle, actualTitleProduct);
	}

	@Given("I add the {string} to the cart")
	public void addProductToCart(String titleProduct) {
		productsPainelPage.addProductToCart(titleProduct);
	}

	@When("I remove the {string} to the cart")
	public void removeProductToTheCart(String titleProduct) {
		productsPainelPage.removeProductToCart(titleProduct);
	}


	@Then("I see number {string} in cart")
	public void validateNumberProductCart(String expectedNumber) {
		String actualProductNumber = productsPainelPage.valueCart();
		assertEquals(expectedNumber, actualProductNumber);
	}


}

