package com.saucelabs.steps;

import static org.junit.Assert.assertEquals;


import com.saucelabs.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.Entao;


public class LoginStep {

	LoginPage loginPage = new LoginPage();

	@Given("I fill form with {string} user")
	public void fill_username_and_password(String userCondition) {
		loginPage.fillFormLogin(userCondition);
	}


	@When("I signin")
	public void signin_click_button() {
		loginPage.signin();
	}





}
