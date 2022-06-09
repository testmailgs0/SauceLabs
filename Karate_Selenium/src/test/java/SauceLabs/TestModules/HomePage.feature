Feature: Logging in to sauce demo website

  Background:

    @LoginToUI
  Scenario: Logging in to Sauce demo website with standard user
    Given driver 'https://www.saucedemo.com/'
    And input("//input[@id='user-name']",'standard_user')
    And input("//input[@id='password']",'secret_sauce')
    When click("//input[@id='login-button']")
    Then match driver.url == 'https://www.saucedemo.com/inventory.html'