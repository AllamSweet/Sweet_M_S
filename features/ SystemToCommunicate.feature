Feature: Owner Communication System
  As an owner I want to communicate with other owners and users to manage business operations effectively

  Scenario:
    Given the following users exist:

      | UserName | Password |Type|
      | argela | bobo |owner |
      | shalb | Allamshab |owner |
      | zeft | Allamzef |user |
  Scenario: Owner sends a message to another owner
    When the exist owner with name "argela" sends a message "Meeting at 5 PM" to exist owner with name "shalb"
    Then the message should be delivered to the owner with name "shalb"
    And the owner with name "shalb" should receive a notification

  Scenario: Owner sends a message to a user
#  The Given step appears to be commented out
# Given I am logged in as "Bob" with role "Owner"
    When the exist owner with name "shalb" sends a message "Welcome to our service!" to exist user with name "zeft"
    Then the message should be delivered to the user with name "zeft"
    And the user with name "zeft" should receive a notification