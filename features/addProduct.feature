Feature: Add Product to Sweet Management System


  Scenario Outline: Successful addition of a new product
    When the user adds a product with the following details:
      | ProductName   | Price   | Quantity   | Description   |
      | <productName> | <price> | <quantity> | <description> |
    Then the product should be added successfully
    Examples:
      | productName | price | quantity | description           |
      | cake        | 20.00 | 10       | Rich_chocolate_cake   |
      | cakel       | 15.00 | 5        | Classic_vanilla_treat |


  Scenario Outline: Attempting to add a duplicate product
    When the user adds existing product with the following details:
      | ProductName   | Price   | Quantity   | Description   |
      | <productName> | <price> | <quantity> | <description> |


    Examples:
      | productName | price | quantity | description           |
      | cake       | 20.00 | 10       | Rich_chocolate_cake   |
      | cakel       | 15.00 | 5        | Classic_vanilla_treat |

  Scenario Outline: Attempting to add a product with missing fields
    When the user adds a product with the following missing details:
      | ProductName   | Price   | Quantity   | Description   |
      | <productName> | <price> | <quantity> | <description> |
    And  the following product should not be add to the list
    Examples:

      | productName    | price | quantity | description       |
      |                | 20.00 | 10       | Delicious_dessert |
      | Brownie        |       | 5        | Delicious_dessert |
      | Chocolate_Cake | 15.00 |          | Tasty_cake        |
      | Brownie        | 10.00 | 5        |                   |

  Scenario Outline: Attempting to add a product with invalid data
    When the user adds a product with the following invalid details:
      | ProductName   | Price   | Quantity   | Description   |
      | <productName> | <price> | <quantity> | <description> |
    And  the following product should not be add to the list
    Examples:

      | productName    | price | quantity | description       |
      | Brownie        | -5.00 | 5        | Delicious brownie |
      | Chocolate Cake | 15.00 | -1       | Tasty cake        |

