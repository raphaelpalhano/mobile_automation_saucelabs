#language: en
Feature: Products Panel saucelabs app

  As a User I want to verify my products painel with sort filter.

  Background: Login saucelabs app
    Given I fill form with "valid" user
    When I signin
    Then I see the title "PRODUCTS"


  Scenario: I filter produtos by Name (A-Z)
    Given I sort items by "Name (A to Z)"
    Then I see the first item 1 with title "Sauce Labs Backpack"


  Scenario: I filter produtos by Name (Z to A)
    Given I sort items by "Name (Z to A)"
    Then I see the first item 2 with title "Sauce Labs Onesie"

  Scenario: I filter produtos by Price (low to high)
    Given I sort items by "Price (low to high)"
    Then I see the first item 1 with title "Sauce Labs Onesie"


  Scenario: I filter produtos by Price (high to low)
    Given I sort items by "Price (high to low)"
    Then I see the first item 1 with title "Sauce Labs Fleece Jacket"


  Scenario: User Add product to the cart
    Given I add the "Sauce Labs Backpack" to the cart
    Then I see number "1" in cart


  Scenario: I add and remove product cart
    Given I add the "Sauce Labs Backpack" to the cart
    Then I remove the "Sauce Labs Backpack" to the cart



