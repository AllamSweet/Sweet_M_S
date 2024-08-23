Feature: Content and recipes

  Scenario: Add a new recipe
    Given I am on the recipe creation page
    When I enter the recipe details "Ghassano",["Sugar","Flour","Chocolate"],"Mix and bake",["Dessert"]
    Then I should see the recipe "Ghassano" in the recipe list

    Scenario: Editing an existing recipe
      When I edite the recipe "Ghassano" with new details "krmosh",["Sugar","Flour","vanilla"],"Mix and bake",["Dessert"]
      Then I should see the recipe "krmosh" in the recipe list

      Scenario: delete recipe
        When delete the recipe "krmosh"
        Then i should not see the recipe "krmosh" in the recipe list



      Scenario: search for a recipe
        Given I have the following recipes
        |title|ingredients|instructions|tags|
        |Ghassano|Sugar,Flour,Chocolate|Mix and bake|Dessert|
        |  krmosh      |  Sugar,Flour,vanilla  |   Mix and bake|Dessert|
        Then I should see the recipe "krmosh" in the search results