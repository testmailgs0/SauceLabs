Feature: Cart Page Scenarios

  Background:
    * def SeleniumHelper = Java.type('SwagLabs.TestHelper.SeleniumHelper')

  @RemoveProductFromCart
  Scenario: Removing Product From Cart
    Given driver 'https://www.saucedemo.com/cart.html'
    When click("//button[@name='remove-sauce-labs-backpack']")
    Then match driver.url == 'https://www.saucedemo.com/cart.html'

  @MoveToCheckoutPage
  Scenario: Move to Checkout page
    Given driver 'https://www.saucedemo.com/cart.html'
    When click("//button[@id='checkout']")
    Then match driver.url == 'https://www.saucedemo.com/checkout-step-one.html'

  @ContinueShopping
  Scenario: Moving back to product page
    Given driver 'https://www.saucedemo.com/cart.html'
    When click("//button[@id='continue-shopping']")
    Then match driver.url == 'https://www.saucedemo.com/inventory.html'

  @VerifyQuantity
  Scenario: Verify Quantity Of Product on cart page
    Given driver 'https://www.saucedemo.com/cart.html'
    And waitForUrl('https://www.saucedemo.com/cart.html')
    And def QtyValue = locate("//div[@class='cart_quantity']")
    Then match QtyValue.getText() == '1'

  @VerifyItemPrice
  Scenario: Verify Price Of Product on cart page
    Given driver 'https://www.saucedemo.com/cart.html'
    And def QtyValue = locate("//div[@class='inventory_item_price']")
    Then match QtyValue.getText() == '$9.99'