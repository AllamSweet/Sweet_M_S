Feature: Dynamic Discount Management

  Scenario: : Successful addition of a new product
    When the user adds a product with the following details:
      | ProductName | Price | Quantity | Description           |
      | cake        | 20.00 | 10       | Rich_chocolate_cake    |
      | cakel       | 15.00 | 5        | Classic_vanilla_treat  |
    Then the product should be added successfully
  Scenario: Apply a discount to a product
    When the owner applies a discount of 10% to the product with name "cake"
    Then the product's price should be updated to 18.0

  Scenario: Attempt to apply an invalid discount
    When the owner attempts to apply an invalid discount of -10% to the product with name "cakel"
    Then the product's price should remain 15.0
