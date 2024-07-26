Feature: User Sign Up

  Background:
    Given the user is on the sign-up page
    And the following users already exist:
      | Username       | Role                  |
      | existingAdmin  | Admin                 |
      | existingUser   | User                  |
      | existingOwner  | Store Owner           |
      | existingSupplier | Raw Material Provider |



  Scenario Outline: Successful sign up with different roles
    When the user enters a new username "<NewUsername>"
    And the user enters a password "<Password>"
    And the user selects the role "<Role>"
    And clicks on the sign-up button
    Then the user should see a welcome message "Welcome, <NewUsername>"
    And the user should be redirected to the "<Role>" dashboard

    Examples:
      | NewUsername    | Password  | Role                  |
      | newAdmin       | admin123  | Admin                 |
      | newUser        | user123   | User                  |
      | newStoreOwner  | store123  | Store Owner           |
      | newSupplier    | raw123    | Raw Material Provider |



  Scenario: Sign up fails with duplicate username
    When the user enters a new username "existingAdmin"
    And the user enters a password "admin123"
    And the user selects the role "Admin"
    And clicks on the sign-up button
    Then the user should see an error message "Username already exists. Please choose another username."
