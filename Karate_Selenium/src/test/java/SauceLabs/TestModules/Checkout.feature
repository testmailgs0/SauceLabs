Feature: Checkout Page Scenarios

  Background:
    * def SeleniumHelper = Java.type('SauceLabs.TestHelper.SeleniumHelper')

  @EnterUserInformation
  Scenario: Entering User Information
    Given driver 'https://www.saucedemo.com/checkout-step-one.html'
    And input("//input[@id='first-name']",'Sam')
    And input("//input[@id='last-name']",'Paw')
    And input("//input[@id='postal-code']",'1200078')
    Then click("//input[@id='continue']")
    Then match driver.url == 'https://www.saucedemo.com/checkout-step-two.html'

  @Cancel
  Scenario: Cancelling the order
    Given driver 'https://www.saucedemo.com/checkout-step-one.html'
    When click("//button[@id='continue-shopping']")
    Then match driver.url == 'https://www.saucedemo.com/inventory.html'

  @VerifyPaymentInformation
  Scenario: Verify Payment Information Of Product
    Given driver 'https://www.saucedemo.com/checkout-step-two.html'
    And assert SeleniumHelper.fnVerifyElementText(driver,"//div[@class='summary_value_label']","SauceCard #31337")

  @PlaceOrder
  Scenario: Place the order
    Given driver 'https://www.saucedemo.com/checkout-step-two.html'
    And click("//button[@name='finish']")