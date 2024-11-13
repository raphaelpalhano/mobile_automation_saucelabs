#language: en
Feature: Checkout saucelabs app

  As a User I want to make checkout of my products

  Background: Login saucelabs app
    Given I fill form with "valid" user
    When I signin
    Then I see the title "PRODUCTS"



  Scenario: As a User I want to buy a Sauce Labs Backpack
    Given I add the "Sauce Labs Backpack" to the cart
    When I access cart I see the product "Sauce Labs Backpack"
    And access checkout
    And fill form with "valid" data
    Then I finish my checkout
    And I see the title "THANK YOU FOR YOU ORDER"


  Scenario: As a User I buy a Sauce Labs Backpack with invalid data form
    Given I add the "Sauce Labs Backpack" to the cart
    When I access cart I see the product "Sauce Labs Backpack"
    And access checkout
    And fill form with "invalid_name" data
    Then I see the error message "First Name is required"


