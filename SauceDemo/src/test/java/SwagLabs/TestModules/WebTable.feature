Feature: Reading Data From Web Table

  Background:
    * def SeleniumHelper = Java.type('SwagLabs.TestHelper.SeleniumHelper')

  @ValidatingWebTableFromExcel
  Scenario Outline: Reading data from web table
    Given driver '<Url>'
    And def uiData = SeleniumHelper.fnGetDataFromWebTable(driver,'<Url>','<TableXpath>','<THeader>','<Trows>','<Tcell>')
    And print uiData
    Examples:
      | Url                                                | TableXpath | THeader | Trows | Tcell |
      | https://demo.guru99.com/test/web-table-element.php | table      | th      | tr    | td    |