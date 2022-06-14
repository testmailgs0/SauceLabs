Feature: Product Page Scenarios

  Background:
    * def SeleniumHelper = Java.type('SwagLabs.TestHelper.SeleniumHelper')

  @SortHighToLow
  Scenario: Sorting products from highest to lowest
    Given driver 'https://www.saucedemo.com/inventory.html'
    When click("//button[@id='react-burger-menu-btn']")
    And select("//select", 'Price (high to low)')
    Then match driver.url == 'https://www.saucedemo.com/inventory.html'

  @SortLowToHigh
  Scenario: Sorting products from lowest to highest
    Given driver 'https://www.saucedemo.com/inventory.html'
    When click("//button[@id='react-burger-menu-btn']")
    And select("//select", 'Price (low to high)')
    Then match driver.url == 'https://www.saucedemo.com/inventory.html'

  @AddProductToCart
  Scenario: Adding selected product to cart
    Given driver 'https://www.saucedemo.com/inventory.html'
    And assert SeleniumHelper.fnSelectElementByText(driver,"//div[@class='inventory_item_name']","Sauce Labs Bike Light")

  @ViewCart
  Scenario: Moving user to cart
    Given driver 'https://www.saucedemo.com/inventory.html'
    And click("//a[@class='shopping_cart_link']")
    Then match driver.url == 'https://www.saucedemo.com/cart.html'


