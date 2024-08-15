Feature: Identify Best-Selling Products in Sweet Management System

  Scenario: : Successful addition of a new product
    When the user adds a product with the following details:
      | ProductName | Price | Quantity | Description           |
      | cakell        | 20.00 | 10       | Rich_chocolate_cake    |
      | cakelll       | 15.00 | 5        | Classic_vanilla_treat  |
    Then the product should be added successfully

  Scenario: No products have been sold
    Then no product will be exist in the list

  Scenario: Add a sold product to sales list
    When the owner adds a sold product with the following details:
      | ProductName| QuantitySold | PricePerUnit | TotalRevenue | CostPercentage | DiscountPercentage |
      |cakell| 5          | 20.00        | 200.00       | 50.00           | 0.0                |
      |cakelll| 10           | 15.00        | 75.00        | 30.00          | 0.0                |
    Then the product sold should be added to the list


  Scenario: Identify best-selling products
    Given the following products have been sold:
      | ProductName| QuantitySold | PricePerUnit | TotalRevenue | CostPercentage |
      |cakell| 5         | 20.00        | 200.00       | 50.00           |
      |cakelll| 3         | 15.00        | 75.00        | 30.00          |

    Then the best-selling product should be
    |ProductName|QuantitySold|
    |cakell    |5      |

#  Scenario Outline: Successful remove of an existing product
#
#    When the user removes the product "<productName>"
#    Then the product "<productName>" should be removed successfully
#
#    Examples:
#      | productName |
#      | cakell        |
#      | cakelll       |
