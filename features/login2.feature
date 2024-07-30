Feature: Login

  Background:
    Given  User is on the login page
    And The following users already exist:
      | Username    | Password  | Role                  |
      | admin       | admin123  | admin                 |
      | user        | user123   | user                  |
      | rawmaterial | raw123    | rawmaterial           |
      | storeowner  | store123  | storeowner            |

  Scenario Outline: Successful Login with different roles
    When The user enters username "<Username>" and password "<Password>" for "<UserType>"
    And Clicks on the login button
    Then The user should see a welcome message "Welcome, <UserType>"
    And the user should be redirected to the "<UserType>" dashboard

    Examples:
      | Username    | Password  | UserType              |
      | admin       | admin123  | admin                 |
      | user        | user123   | user                  |
      | rawmaterial | raw123    | rawmaterial           |
      | storeowner  | store123  | storeowner            |

  Scenario Outline: Login Fails with incorrect credentials
    When The user enters username "<Username>" and password "<Password>" for "<UserType>"
    And Clicks on the login button
    Then The user should see an error message "Invalid username or password"
    And The user should remain on the login page

    Examples:
      | Username   | Password      | UserType  |
      | admin      | wrongpassword | admin     |
      | wronguser  | admin123      | admin     |
      | wronguser  | wrongpassword | admin     |
