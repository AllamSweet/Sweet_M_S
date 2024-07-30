package argela;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import sweet_app.AppSWEET;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang. System.*;
public class login {


    AppSWEET APP;
public login(AppSWEET APP){


this.APP=APP;

}
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        APP.on_the_login_page=true;
        System.out.println("User is on the login page: " + APP.on_the_login_page);
        Assert.assertTrue(APP.on_the_login_page);
    }

    @Given("The following users already exist:")
    public void the_following_users_already_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>>users=dataTable.asMaps(String.class,String.class);
        APP.setValusers(users);
        System.out.println("Existing users set: " + users);
    }

    @When("The user enters username {string} and password {string} for {string}")
    public void the_user_enters_username_and_password_for(String name, String pass, String type) {
      APP.setPassNAME(name,pass);
        System.out.println("Entered username: " + name + ", password: " + pass + " for " + type);
    }

    @When("Clicks on the login button")
    public void clicks_on_the_login_button() {


            APP.loginClicked();
        System.out.println("Login button clicked");

    }

    @Then("The user should see a welcome message {string}")
    public void the_user_should_see_a_welcome_message(String message) {
        String actualMessage = APP.getMessage();
        System.out.println("Expected welcome message: " + message + ", Actual message: " + actualMessage);
        try {
            Assert.assertEquals("Welcome message should match", message, actualMessage);
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());
            throw e;
        }

    }

    @Then("The user should see an error message {string}")
    public void the_user_should_see_an_error_message(String message) {


        String actualMessage = APP.getMessage();
        Assert.assertEquals(message, actualMessage);


    }

    @Then("The user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {
        Assert.assertTrue(APP.on_the_login_page);
    }
    @Then("the user should be redirected to the {string} dashboard")
    public void the_user_should_be_redirected_to_the_dashboard(String type) {
        System.out.println("Expected page: " + type);
        String actualPage = APP.getRedirectedPage();
        System.out.println("Actual page: " + actualPage);
        Assert.assertEquals(type, actualPage);
    }


}
