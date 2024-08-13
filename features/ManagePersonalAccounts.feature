#Feature: Manage Personal Accounts
#
#  Background:
#    And The following users already exist:
#      | Username    | Password  | Role                  |
#      | admin       | admin123  | admin                 |
#      | user        | user123   | user                  |
#      | rawmaterial | raw123    | rawmaterial           |
#      | storeowner  | store123  | storeowner            |
#
#  Scenario Outline: Change Account Name
#    Given The user logs in as "<UserType>"
#    When The user navigates to the account settings page
#    And The user changes the account name to "<NewName>"
#    And Clicks on the save button
#    Then The user should see a success message "Your account name has been updated to <NewName>"
#    And The account name should be updated to "<NewName>"
#
#    Examples:
#      | UserType     | NewName         |
#      | user         | JohnDoe         |
#      | admin        | AdminUser       |
#      | rawmaterial  | RawMaterialUser |
#      | storeowner   | StoreOwnerUser  |
#
#  Scenario Outline: Change Password
#    Given The user logs in as "<UserType>"
#    When The user navigates to the account settings page
#    And The user changes the password from "<OldPassword>" to "<NewPassword>"
#    And Clicks on the save button
#    Then The user should see a success message "Your password has been successfully changed"
#    And The user should be able to log in with the new password "<NewPassword>"
#
#    Examples:
#      | UserType     | OldPassword | NewPassword      |
#      | user         | user123     | newpass123       |
#      | admin        | admin123    | adminpass        |
#      | rawmaterial  | raw123      | rawmaterialpass  |
#      | storeowner   | store123    | storeownerpass   |
#
#  Scenario: Attempt to Change Password with Incorrect Current Password
#    Given The user logs in as "admin"  # Use an existing user role for context
#    When The user navigates to the account settings page
#    And The user enters the incorrect current password "wrongpass"
#    And Attempts to change the password to "newpassword123"
#    Then The user should see an error message "Current password is incorrect"
#    And The password should not be updated
#
#  Scenario: Attempt to Change Name to an Already Taken Name
#    Given The following users already exist:
#      | Username | Name   |
#      | user1    | John   |
#    Given The user logs in as "user"  # Specify a user to perform this action
#    When The user navigates to the account settings page
#    And The user attempts to change the account name to "John"
#    Then The user should see an error message "This name is already taken"
#    And The account name should not be updated
