Feature: Managing Posts

  Scenario: Creating a new post
    Given I am on the post creation page
    When I enter the post details "Firstt Post", "This is the content of the first post.", ["Category1", "Category2"]
    Then I should see the post "Firstt Post" in the post list

  Scenario: Editing an existing post
#    Given I have an existing post "First Post" with content "This is the content of the first post." and tags ["Category1", "Category2"]
    When I update the post "Firstt Post" with new details "Updatedd Post", "This is the updated content.", ["Category3","Category3"]
    Then I should see the post "Updatedd Post" in the post list

  Scenario: Deleting a post
#    Given I have an existing post "Updated Post"
    When I delete the post "Updatedd Post"
    Then I should not see the post "Updatedd Post" in the post list

  Scenario: Searching for a post
    Given I have the following posts:
      | Title         | Content                          | Tags            |
      | First Post    | This is the content of the first post. | Category1, Category2 |
      | Second Post   | This is the content of the second post. | Category3      |
    Then I should see the post "Second Post" in the search results