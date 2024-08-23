package argela;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import  sweet_app.ContentManagement;

import static org.junit.Assert.*;
public class ContentAndRecipes {
    ContentManagement  mr;
    private List<ContentManagement.Recipe> searchResults = new ArrayList<>();
    public ContentAndRecipes() {
        mr = ContentManagement.getInstance();

    }

    @Given("I am on the recipe creation page")
    public void i_am_on_the_recipe_creation_page() {

        mr.setNavagateToRecipePage(true);
        assertTrue(mr.isNavagateToRecipePage());
    }

    @When("I enter the recipe details {string},[{string},{string},{string}],{string},[{string}]")
    public void i_enter_the_recipe_details(String title, String ingredientsString, String preparation, String servings, String cookingTime, String tagsString) {
        List<String> ingredients = Arrays.asList(ingredientsString.split(", "));
        List<String> tags = Arrays.asList(tagsString.split(", "));
        try {
            mr.addRecipe(title, ingredientsString, preparation, servings, cookingTime, tags);
            assertTrue(true);
        }catch (Exception ex){
            fail();
        }

    }


    @Then("I should see the recipe {string} in the recipe list")
    public void i_should_see_the_recipe_in_the_recipe_list(String title) {
        ContentManagement.Recipe recipe=mr.getRecipe(title);
        assertNotNull("Recipe with title " + title + " should be in the recipe list.", recipe);
    }


    @When("I edite the recipe {string} with new details {string},[{string},{string},{string}],{string},[{string}]")
    public void i_edite_the_recipe_with_new_details(String title,String newTitle, String ingredientsString, String preparation, String servings, String cookingTime, String tagsString) {
        List<String> ingredients = Arrays.asList(ingredientsString.split(", "));
        List<String> tags = Arrays.asList(tagsString.split(", "));
        try {

            mr.updateRecipe(title, newTitle, ingredients, preparation, servings, cookingTime, tags);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Failed to update recipe: " + ex.getMessage());
        }

    }


    @When("delete the recipe {string}")
    public void delete_the_recipe(String title) {
        try {
            mr.deleteRecipe(title);
            assertTrue(true);
        }catch (Exception ex){
            fail();
        }
    }

    @Then("i should not see the recipe {string} in the recipe list")
    public void i_should_not_see_the_recipe_in_the_recipe_list(String title) {
        ContentManagement.Recipe recipe=mr.getRecipe(title);
        assertNull(recipe);
    }

    @Given("I have the following recipes")
    public void i_have_the_following_recipes(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> recipesList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> recipeMap : recipesList) {
            String title = recipeMap.get("title");
            String ingredients = recipeMap.get("ingredients");
            String preparation = recipeMap.get("instructions");
            String servings = recipeMap.get("Servings");
            String cookingTime = recipeMap.get("Cooking Time");
            String tags = recipeMap.get("tags");
            List<String> ingredientsList = List.of(ingredients.split(", "));
            List<String> tagsList = List.of(tags.split(", "));
            try{
                mr.addRecipe(title, ingredients, preparation, servings, cookingTime, tagsList);
                assertTrue(true);
            }catch (Exception ex){
                fail();
            }
        }
    }

    @Then("I should see the recipe {string} in the search results")
    public void i_should_see_the_recipe_in_the_search_results(String recipeTitle) {
        searchResults = mr.searchRecipes(recipeTitle);
        boolean recipeFound = searchResults.stream()
                .anyMatch(recipe -> recipe.getTitle().equals(recipeTitle));
        assertTrue("Recipe with title '" + recipeTitle + "' was not found in the search results.", recipeFound);
    }


}
