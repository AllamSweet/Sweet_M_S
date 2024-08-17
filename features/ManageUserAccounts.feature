Feature: Manage user accounts

  Background:
    Given the admin is logged in

  Scenario: Add a new store owner account with valid details
    When the admin adds a store owner account with the following details:
      | Name          | Email              | Phone       | Role         |
      | Dopah     | john@example.com   | 1234567890  | Store Owner  |
    Then the store owner account should be added successfully

  Scenario: Attempt to add a user account with missing details
    When the admin adds a store owner account with the following missing details:
      | Name          | Email | Phone       | Role         |
      |               | john@example.com | 1234567890  | Store Owner  |



  Scenario: Update an existing store owner account with valid details
    When the admin updates the store owner account with the following details:
      | Name          | Email              | Phone       | Role         |
      | Dopah     | john@example.com   | 1234567890  | Store Owner  |
    Then the store owner account should be updated successfully

  Scenario: Delete an existing raw material supplier account
    Given the following raw material supplier account exists:
      | Name          | Email              | Phone       | Role                 |
      | Bomeh   | jane@example.com   | 0987654321  | Raw Material Supplier |
    When the admin deletes the raw material supplier account with name "Bomeh"
    Then the raw material supplier account should be deleted successfully