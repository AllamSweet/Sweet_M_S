Feature: Monitor Sales and Profits


  Scenario: : Successful addition of a new product
    When the user adds a product with the following details:
      | ProductName | Price | Quantity | Description           |
      | cake3       | 20.00 | 10       | Rich_chocolate_cake    |
      | cakel2       | 15.00 | 5        | Classic_vanilla_treat  |
    Then the product should be added successfully

  Scenario: Add a sold product to sales list
    When the owner adds a sold product with the following details:
      | ProductName| QuantitySold | PricePerUnit | TotalRevenue | CostPercentage | DiscountPercentage |
      |cake3| 10           | 20.00        | 200.00       | 50.0           | 0.0                |
      |cakel2| 5            | 15.00        | 75.00        | 30.0           | 0.0                |
    Then the product sold should be added to the list

  Scenario: Cannot add a non-existing sold product to the list
    When the owner cannot add a sold product that does not exist:
      | ProductName | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage | DiscountPercentage |
      | Bahta       | 20           | 19.99        | 299.85       | 30.0           | 0.0                |
    Then the product sold should not be added to the list

  Scenario: View overall sales and profits
    Given the following products have been sold:
      | ProductName | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage |
      | cake3       | 10           | 20.00        | 200.00       | 50.0           |
#      |cakel| 5            | 15.00        | 75.00        | 30.0           |
    Then the total sales revenue should be 345.0

  Scenario: Attempt to view sales and profits with no sales
    Given the following products have not been sold:
      | ProductName | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage |
      |   cakel2   | 17            | 15.00        | 75.00        | 30.0           |
    Then the total revenue should be 345.0
    And the profit should be 181.5

