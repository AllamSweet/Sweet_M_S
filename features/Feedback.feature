Feature: Manage User Feedback

  Scenario: View user feedback
    When I navigate to the feedback page
    And I submit feedback with the following details:
      | title       | comment             | rating |
      | Recipe One  | Delicious recipe!   | 5      |
      | Recipe Two  | Too salty           | 3      |
    Then I should see a list of feedback with comments and ratings


  Scenario: Respond to user feedback
    When I respond to the feedback with:
      | title       | Response                |
      | Recipe One  | Thank you for your feedback! |
#    Then the feedback response should be recorded successfully

  Scenario: Delete user feedback
    When I delete the feedback with the comment "Too salty"
    Then the feedback should be removed from the system