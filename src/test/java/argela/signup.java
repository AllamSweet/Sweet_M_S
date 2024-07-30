
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
public class signup {


    AppSWEET APP;
    public signup(AppSWEET APP){


        this.APP=APP;

    }

    @Given("the following users already exist:")
    public void the_following_users_already_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>>user=dataTable.asMaps(String.class,String.class);
        APP.setValusers(user);
    }
    @When("the user clicks on the {string} link")
    public void the_user_clicks_on_the_link(String signUp) throws IllegalAccessException {
     APP.clickLink(signUp);
    }

    @When("the user enters a new username {string} and password {string} and confirms password {string} and email {string} and age {string} and phone number {string}")
    public void the_user_enters_a_new_username_and_password_and_confirms_password_and_email_and_age_and_phone_number(String name, String Password, String ConfirmPassword, String Email, String age, String PhoneNumber) {
       APP.fillSignUp( name,  Password,  ConfirmPassword,  Email,  age,  PhoneNumber);
    }

    @When("clicks on the sign-up button")
    public void clicks_on_the_sign_up_button() {
       APP.signUpClicked();
    }

    @Then("the user should see a welcome message {string}")
    public void the_user_should_see_a_welcome_message(String expectmessage) {
        String acmessage=APP.getMessage();
        Assert.assertEquals(expectmessage,acmessage);
    }

    @Then("the user should be redirected to the user dashboard")
    public void the_user_should_be_redirected_to_the_user_dashboard() {
        String acpage=APP.getRedirectedPage();
        Assert.assertEquals("user",acpage);
    }

    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String emessage) {
        String actmessage=APP.getMessage();
        Assert.assertEquals(emessage,actmessage);
    }

}
