#language: en
Feature: Login saucelabs app

  As a User I want to signin

  Scenario: Valid user login
    Given I fill form with "valid" user
    When I signin
    Then I see the title "PRODUCTS"

  Scenario: Invalid user login
    Given I fill form with "invalid" user
    When I signin
    Then I see the error message "Username and password do not match any user in this service."


  Scenario: Locked user login
    Given I fill form with "locked_user" user
    When I signin
    Then I see the error message "Sorry, this user has been locked out."

