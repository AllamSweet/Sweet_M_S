Feature: Monitor Profits and Generate Financial Reports Across Multiple Stores
  Background:
    Given the admin is logged in

  Scenario: Record a sale and update profits
    Given the following product exists:

  |	Name|	Description|	Price	|Quantity|
  |	allam|	A very widget	|10.00|	100|
  |	allammm|	A tasty widget	|12.00|	200|

    When these products sold to some:

  |	 ProductName| QuantitySold | PricePerUnit | TotalRevenue | CostPercentage | DiscountPercentage |
 |		allam|	10	|15.00|  200.00                       |   50.0        |0.0                        |
|		allammm|	20	|20.00| 75.00                         |   30.0       |0.0                        |
    Then the total sales should be 0.0
    And the total profit should be 0.00
    And the financial report should include:

  |Total Sales|	Total Profit|
  |250.00     |	100.00|
  Scenario: Identify Best-Selling Products in Each Store
    Given the following sales data for stores:

 | Store|	ProductName|	QuantitySold|
  |Store  |allam    |50            |
  |Store2 	|allammm           |	40   |


    Then the best-selling products for each store should be:

  |Store	|Best-Selling Product|	QuantitySold|
 | Store1	|allam|	50|
  |Store2	|allammm|	40|
