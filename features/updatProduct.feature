Feature: update Product to Sweet Management System
  Scenario : Successful addition of a new product
    When the user adds a product with the following details:
      | productName | price | quantity | description           |
      | cake        | 20.00 | 10       | Rich_chocolate_cake   |
      | cakel       | 15.00 | 5        | Classic_vanilla_treat |
    Then the product should be added successfully

  Scenario Outline: Successful update of an existing product
    When the user updates a product with the following details:
      | productName | price | quantity | description |
      | <productName> | <price> | <quantity> | <description> |
    Then the product should be updated successfully
    Examples:
      | productName | price | quantity | description |
      | cake | 25.00 | 15 | Rich chocolate cake |
      | cakel | 18.00 | 8 | Updated vanilla treat |

  Scenario Outline: Attempting to update a non-existing product
    When the user updates a non-existing product with the following details:
      | productName | price | quantity | description |
      | <productName> | <price> | <quantity> | <description> |

    Examples:
      | productName | price | quantity | description |
      | pie | 20.00 | 10 | Fruity pie |
      | donut | 15.00 | 5 | Sweet donut |

  Scenario Outline: Attempting to update a product with invalid data
    When the user updates a product with the following invalid details:
      | productName | price | quantity | description |
      | <productName> | <price> | <quantity> | <description> |
#Then the product should not be updated
#And an error message should be displayed stating "Invalid product details"
    Examples:
      | productName | price | quantity | description |
      | cake | -10.00 | 10 | Invalid price |
      | cakel | 20.00 | -5 | Invalid quantity |
  Scenario Outline: Attempting to update a product with missing fields
    When the user updates a product with the following invalid details:
      | productName | NewPrice | quantity | description |
      | <productName> | <price> | <quantity> | <description> |
  #Then the product should not be updated
 # And an error message should be displayed stating "Missing product details"
    Examples:
      | productName | price | quantity | description |
      | cake |  | 15 | Rich chocolate cake |
      | cakel | 18.00 |  | Updated vanilla treat |

