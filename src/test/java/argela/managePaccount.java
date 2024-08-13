//package argela;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import sweet_app.AppSWEET;
//
//import java.util.List;
//import java.util.Map;
//
//public class managePaccount {
//
//    private AppSWEET appSWEET = new AppSWEET();
//
//
//    @Given("The user logs in as {string}")
//    public void theUserLogsInAs(String userType) {
//        String[] credentials = appSWEET.getUsernameAndPasswordByUserType(userType);
//
//        if (credentials == null) {
//            Assert.fail("Invalid user type provided or user not found: " + userType);
//        }
//
//        // Set the username and password based on the retrieved credentials
//        appSWEET.setPassNAME(credentials[0], credentials[1]);
//        appSWEET.loginClicked();
//
//        Assert.assertEquals("Expected user to be logged in and redirected to their page.",
//                credentials[0], appSWEET.getRedirectedPage());
//    }
//
//    @When("The user navigates to the account settings page")
//    public void theUserNavigatesToTheAccountSettingsPage() throws IllegalAccessException {
//
//            appSWEET.clickLink("account_settings");
//    }
//
//    @When("The user changes the account name to {string}")
//    public void theUserChangesTheAccountNameTo(String newName) {
//        appSWEET.changeAccountName(newName);
//
//    }
//
//    @When("Clicks on the save button")
//    public void clicksOnTheSaveButton() {
//        appSWEET.saveChanges();
//    }
//
//    @Then("The user should see a success message {string}")
//    public void theUserShouldSeeASuccessMessage(String   expectedMessage) {
//        Assert.assertEquals("Success message should match expected message.",
//                expectedMessage, appSWEET.getMessage());
//    }
//
//    @Then("The account name should be updated to {string}")
//    public void theAccountNameShouldBeUpdatedTo(String expectedName) {
//        Assert.assertTrue("The account name should have been updated.", appSWEET.isAccountNameUpdated());
//        Assert.assertEquals("The account name should match the expected name.",
//                expectedName, appSWEET.getAccountName());
//    }
//
//    @When("The user changes the password from {string} to {string}")
//    public void theUserChangesThePasswordFromTo(String oldPassword, String newPassword) {
//        appSWEET.changePassword(oldPassword, newPassword);
//    }
//
//    @Then("The user should be able to log in with the new password {string}")
//    public void theUserShouldBeAbleToLogInWithTheNewPassword(String newPassword) {
//        appSWEET.setPassNAME(appSWEET.getAccountName(), newPassword);
//        appSWEET.loginClicked();
//        Assert.assertEquals("The user should be able to log in with the new password.",
//                appSWEET.getRedirectedPage(), appSWEET.getAccountName());
//    }
//
//    @Given("The user logs in as {string}  # Use an existing user role for context")
//    public void theUserLogsInAsUseAnExistingUserRoleForContext(String incorrectPassword) {
//        appSWEET.setPassNAME(appSWEET.getAccountName(), incorrectPassword);
//    }
//
//    @When("The user enters the incorrect current password {string}")
//    public void theUserEntersTheIncorrectCurrentPassword(String newPassword) {
//        appSWEET.changePassword(appSWEET.getAccountName(), newPassword);
//    }
//
//    @When("Attempts to change the password to {string}")
//    public void attemptsToChangeThePasswordTo(String newPassword) {
//        appSWEET.changePassword(appSWEET.getAccountName(), newPassword);
//    }
//
//    @Then("The password should not be updated")
//    public void thePasswordShouldNotBeUpdated() {
//        Assert.assertFalse("The password should not have been updated.", appSWEET.isPasswordUpdated());
//
//    }
//
//    @Given("The user logs in as {string}  # Specify a user to perform this action")
//    public void theUserLogsInAsSpecifyAUserToPerformThisAction(String userType) {
//
//
//        String[] credentials = appSWEET.getUsernameAndPasswordByUserType(userType);
//
//        if (credentials == null) {
//            Assert.fail("Invalid user type provided or user not found: " + userType);
//        }
//
//        // Set the username and password based on the retrieved credentials
//        appSWEET.setPassNAME(credentials[0], credentials[1]);
//
//        // Perform the login action
//        appSWEET.loginClicked();
//
//        // Validate the result of the login attempt
//        String expectedPage = credentials[0]; // Assuming the user should be redirected to a page with their username
//        String actualPage = appSWEET.getRedirectedPage();  // Replace this with how you currently get the redirected page
//
//        Assert.assertEquals("Expected user to be logged in and redirected to their page.", expectedPage, actualPage);
//    }
//
//    @When("The user attempts to change the account name to {string}")
//    public void theUserAttemptsToChangeTheAccountNameTo(String newName) {
//        appSWEET.changeAccountName(newName);
//    }
//
//    @Then("The account name should not be updated")
//    public void theAccountNameShouldNotBeUpdated() {
//        Assert.assertFalse("The account name should not have been updated.", appSWEET.isAccountNameUpdated());
//
//    }
//
//}
