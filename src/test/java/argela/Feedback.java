package argela;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_app.ContentManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class Feedback {
    ContentManagement  mf;
    public Feedback() {
        mf = ContentManagement.getInstance();

    }
    @When("I navigate to the feedback page")
    public void i_navigate_to_the_feedback_page() {
        mf.setNavagateToFeedbackPage(true);
        assertTrue(mf.isNavagateToFeedbackPage());
    }

    @When("I submit feedback with the following details:")
    public void i_submit_feedback_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> feedbackDetails = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> feedback : feedbackDetails) {
            String title = feedback.get("title");
            String comment = feedback.get("comment");
            int rating = Integer.parseInt(feedback.get("rating"));
            mf.addFeedback(title, comment, rating);
        }
    }

    @Then("I should see a list of feedback with comments and ratings")
    public void i_should_see_a_list_of_feedback_with_comments_and_ratings() {
        List<ContentManagement.Feedback> feedbackList = mf.getFeedbackList();
        assertNotNull("Feedback list should not be null", feedbackList);
        List<ContentManagement.Feedback> expectedFeedbackList = new ArrayList<>();
        expectedFeedbackList.add(new ContentManagement.Feedback("Recipe One", "Delicious recipe!", 5));
        expectedFeedbackList.add(new ContentManagement.Feedback("Recipe Two", "Too salty", 3));
        for (int i = 0; i < expectedFeedbackList.size(); i++) {
            ContentManagement.Feedback expectedFeedback = expectedFeedbackList.get(i);
            ContentManagement.Feedback actualFeedback = feedbackList.get(i);

            assertNotNull(actualFeedback);
            assertEquals(expectedFeedback.getRecipeTitle(), actualFeedback.getRecipeTitle());
            assertEquals(expectedFeedback.getComment(), actualFeedback.getComment());
            assertEquals(expectedFeedback.getRating(), actualFeedback.getRating());
        }
    }

    @When("I respond to the feedback with:")
    public void i_respond_to_the_feedback_with(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> responseDetails = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> responseDetail : responseDetails) {
            String title = responseDetail.get("title");
            String response = responseDetail.get("response");
            try{
                ContentManagement.getInstance().respondToFeedback(title, response);
                assertTrue(true);
            }
            catch (Exception ex){
                fail();
            }
        }
    }



    @When("I delete the feedback with the comment {string}")
    public void i_delete_the_feedback_with_the_comment(String comment) {
        ContentManagement contentManagement = ContentManagement.getInstance();
        try {
            contentManagement.deleteToFeedback(comment);
        } catch (Exception e) {

            fail();
        }
    }

    @Then("the feedback should be removed from the system")
    public void the_feedback_should_be_removed_from_the_system() {
        ContentManagement manageRecipeC = ContentManagement.getInstance();
        List<ContentManagement.Feedback> feedbackList = manageRecipeC.getFeedbackList();
        String commentToCheck = "Too salty";
        ContentManagement.Feedback feedbackToCheck = feedbackList.stream()
                .filter(feedback -> feedback.getComment().equals(commentToCheck))
                .findFirst()
                .orElse(null);
        assertNull("The feedback with comment '" + commentToCheck + "' was not removed.", feedbackToCheck);
    }
}