@TestExecutor
Feature: Sauce Demo Work Flow Scenrios

  Background:
    * configure driver = { type: 'chrome',showDriverLog: true }
  # * configure driverTarget = { docker: 'justinribeiro/chrome-headless', showDriverLog: true }
  # * configure driverTarget = { docker: 'ptrthomas/karate-chrome', showDriverLog: true }
  # * configure driver = { type: 'chromedriver', showDriverLog: true }
  # * configure driver = { type: 'geckodriver', showDriverLog: true }
  # * configure driver = { type: 'safaridriver', showDriverLog: true }
  # * configure driver = { type: 'iedriver', showDriverLog: true, httpConfig: { readTimeout: 120000 } }

  Scenario: Logging to UI & Adding product to cart and placing order.
    Given driver 'https://www.saucedemo.com'
    And fullscreen()
    * call read('../TestModules/HomePage.feature@LoginToUI') { driver: '#(driver)' }
    * call read('../TestModules/Product.feature@AddProductToCart') {driver: '#(driver)'}
    * call read('../TestModules/Product.feature@ViewCart') {driver: '#(driver)'}
    * call read('../TestModules/Cart.feature@VerifyQuantity') {driver: '#(driver)'}
    * call read('../TestModules/Cart.feature@VerifyItemPrice') {driver: '#(driver)'}
    * call read('../TestModules/Cart.feature@MoveToCheckoutPage') {driver: '#(driver)'}
    * call read('../TestModules/Checkout.feature@EnterUserInformation') {driver: '#(driver)'}
    * call read('../TestModules/Checkout.feature@VerifyPaymentInformation') {driver: '#(driver)'}
    * call read('../TestModules/Checkout.feature@PlaceOrder') {driver: '#(driver)'}
    * call read('../TestModules/WebTable.feature@ValidatingWebTableFromExcel') {driver: '#(driver)'}
