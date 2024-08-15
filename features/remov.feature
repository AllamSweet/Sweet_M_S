Feature: Remove Product from Sweet Management System
  Scenario : Successful addition of a new product
    When the user adds a product with the following details:
      | productName | price | quantity | description           |
      | cake        | 20.00 | 10       | Rich_chocolate_cake   |
      | cakel       | 15.00 | 5        | Classic_vanilla_treat |
    Then the product should be added successfully

  Scenario Outline: Successful remove of an existing product

    When the user removes the product "<productName>"
    Then the product "<productName>" should be removed successfully

    Examples:
      | productName |
      | cake        |
      | cakel       |
  Scenario Outline: Attempting to remove a non-existing product
    When the user tries to remove a non-existing product with the following details:
      | productName |
      | <productName> |
    Then the product should not be removed

    Examples:
      | productName |
      | pie         |
      | donut       |