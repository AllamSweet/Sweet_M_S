package argela;
import sweet_app.AppSWEET;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class login {

    AppSWEET app;
    private List<AppSWEET.User> currentpeople;
    List<Map<String, String>> users;
    public login() {
        app = AppSWEET.getInstance();
        users = new ArrayList<>();
    }

    @Given("the following people exist in login table:")
    public void the_following_people_exist_in_login_table(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name,pass,type;
        AppSWEET.User user;
        currentpeople = new ArrayList<>();

        for (Map<String, String> columns : rows) {
            name = columns.get("UserName");
            pass = columns.get("Password");
            type = columns.get("Type");
            user = new AppSWEET.User(name,"" ,pass, "","",0,type);
            currentpeople.add(user);
            try {
                app.addUser(name,"" ,pass, "","",0,type);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @When("the person who logged in system with the following valid info:")
    public void the_person_who_logged_in_system_with_the_following_valid_info(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
        String actualPage = app.logtosystem(loginDetails.get("UserName"), loginDetails.get("Password"));
        String expectedPage = app.getNextPage(loginDetails.get("UserName"));
        Assert.assertEquals(expectedPage, actualPage);
    }

    @When("the person who logged in system with the following invalid details:")
    public void the_person_who_logged_in_system_with_the_following_invalid_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
        String actualPage = app.logtosystem(loginDetails.get("UserName"), loginDetails.get("Password"));
        Assert.assertEquals("login_Page", actualPage);
    }



    @When("the person logged in with the following empty username details:")
    public void the_person_logged_in_with_the_following_empty_username_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
        String actualPage = app.logtosystem(loginDetails.get("UserName"), loginDetails.get("Password"));
        Assert.assertEquals("login_Page", actualPage);
    }

    @Then("the person should be still in login_page")
    public void the_person_should_be_still_in_login_page() {
        Assert.assertTrue(app.LoginPage());
    }

    @When("the person logged in with the following empty password details:")
    public void the_person_logged_in_with_the_following_empty_password_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
        String actualPage = app.logtosystem(loginDetails.get("UserName"), loginDetails.get("Password"));
        Assert.assertEquals("login_Page", actualPage);
    }

    @When("the person logged in with the following both empty details:")
    public void the_person_logged_in_with_the_following_both_empty_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
        String actualPage = app.logtosystem(loginDetails.get("UserName"), loginDetails.get("Password"));
        Assert.assertEquals("login_Page", actualPage);
    }



}
