package com.saucelabs.steps;

import com.saucelabs.pages.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.pt.Entao;

import static org.junit.Assert.assertEquals;

public class GlobalSteps {

    BasePage basePage = new BasePage();

    @Then("I see the title {string}")
    public void valid_title_product_painel(String title) {
        assertEquals(title, basePage.getTextElement(title));
    }

    @Entao("I see the error message {string}")
    public void validation_error_messages(String message) {
        assertEquals(message, basePage.erroMessage());
    }
}
