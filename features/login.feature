Feature: Login

  Background:
    Given the user is on the login page



  Scenario Outline: Successful login with different roles
    When the user enters username "<Username>"
    And the user enters password "<Password>"
    And clicks on the login button
    Then the user should be redirected to the "<Role>" dashboard

    Examples:
      | Username          | Password | Role                  |
      | admin             | admin123 | Admin                 |
      | user              | user123  | User                  |
      | rawmaterial       | raw123   | Raw Material Provider |
      | storeowner        | store123 | Store Owner           |



  Scenario: Login fails with wrong password
    When the user enters username "admin"
    And the user enters password "wrongpassword"
    And clicks on the login button
    Then the user should see an error message "Invalid username or password"

  Scenario: Login fails with wrong username
    When the user enters username "wronguser"
    And the user enters password "admin123"
    And clicks on the login button
    Then the user should see an error message "Invalid username or password"

  Scenario: Login fails with wrong username and password
    When the user enters username "wronguser"
    And the user enters password "wrongpassword"
    And clicks on the login button
    Then the user should see an error message "Invalid username or password"
