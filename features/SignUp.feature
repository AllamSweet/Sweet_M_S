Feature: User Sign Up

  Background:
    Given User is on the login page
    And the following users already exist:
      | Username       |
      | existingAdmin  |
      | existingUser   |
      | existingOwner  |
      | existingSupplier |

  Scenario Outline: Successful sign up
    When the user clicks on the "sign_up" link
    And the user enters a new username "<NewUsername>" and password "<Password>" and confirms password "<ConfirmPassword>" and email "<Email>" and age "<Age>" and phone number "<PhoneNumber>"
    And clicks on the sign-up button
    Then the user should see a welcome message "Welcome, <NewUsername>"
    And the user should be redirected to the user dashboard

    Examples:
      | NewUsername  | Password  | ConfirmPassword | Email                | Age | PhoneNumber |
      | newUser1     | user123   | user123         | newuser1@example.com | 25  | 1234567890  |

  Scenario Outline: Sign up fails with various invalid inputs
    When the user clicks on the "sign_up" link
    When the user enters a new username "<NewUsername>" and password "<Password>" and confirms password "<ConfirmPassword>" and email "<Email>" and age "<Age>" and phone number "<PhoneNumber>"
    And clicks on the sign-up button
    Then the user should see an error message "<ErrorMessage>"

    Examples:
      | NewUsername         | Password    | ConfirmPassword | Email                     | Age | PhoneNumber | ErrorMessage                                      |
      | existingAdmin       | admin123    | admin123        | duplicateadmin@example.com | 30  | 1234567890  | Username already exists. Please choose another username. |
      | newYoungUser        | password123 | password123     | younguser@example.com      | 15  | 0987654321  | You must be at least 18 years old to sign up.             |
      | newInvalidPhoneUser | password123 | password123     | invalidphoneuser@example.com | 25 | abcde12345  | Invalid phone number format. Please enter a valid phone number. |

  Scenario Outline: Sign up fails with missing required fields
    When the user clicks on the "sign_up" link
    When the user enters a new username "<NewUsername>" and password "<Password>" and confirms password "<ConfirmPassword>" and email "<Email>" and age "<Age>" and phone number "<PhoneNumber>"
    And clicks on the sign-up button
    Then the user should see an error message "All fields are required."

    Examples:
      | NewUsername  | Password  | ConfirmPassword | Email              | Age | PhoneNumber |
      |              | user123   | user123         | newuser2@example.com | 25  | 1234567890  |
      | newUser2     |           | user123         | newuser2@example.com | 25  | 1234567890  |
      | newUser2     | user123   |                 |newuser2@example.com   | 25  | 1234567890  |
      | newUser2     | user123   | user123         |                    | 25  | 1234567890  |
      | newUser2     | user123   | user123         | newuser2@example.com |     | 1234567890  |
      | newUser2     | user123   | user123         | newuser2@example.com | 25  |            |
