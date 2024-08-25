package argela;
import sweet_app.Communication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CommunicationClass {
    Communication com;
    List<Map<String, String>> usersandowners;
    private List<Communication.User> currentUsers;

    public CommunicationClass() {
        com = Communication.getInstance();

    }
    @Given("the following users exist:")
    public void the_following_users_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        String uname, type,password;
        Communication.User user;
        currentUsers = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            uname = columns.get("UserName");
            password = columns.get("Password");
            type = columns.get("Type");
            user = new Communication.User(uname, type);
            currentUsers.add(user);
            try {
                com.addUser(user);
                assertTrue(true);
            } catch (Exception ex) {
                fail();
            }
        }
    }

    @When("the exist owner with name {string} sends a message {string} to exist owner with name {string}")
    public void the_exist_owner_with_name_sends_a_message_to_exist_owner_with_name(String string, String string2, String string3) {
        try {
            com.sendMessage(string, string3, string2);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Then("the message should be delivered to the owner with name {string}")
    public void the_message_should_be_delivered_to_the_owner_with_name(String string) {
        Communication.User owner = com.getUserByName(string);
        List<String> in = owner.getInbox();
        boolean messageDelivered = in.stream().anyMatch(msg -> msg.contains("Meeting at 5 PM"));
        assertTrue("Message was not delivered to the owner with Name " + string, messageDelivered);
    }

    @Then("the owner with name {string} should receive a notification")
    public void the_owner_with_name_should_receive_a_notification(String string) {
        Communication.User user = com.getUserByName(string);
        Assert.assertNotNull("User with Name " + string + " does not exist.", user);
        List<String> notifications = user.getNotification();
        assertTrue("No notifications were received by the owner with Name " + string,
                notifications != null && !notifications.isEmpty());
    }

    @When("the exist owner with name {string} sends a message {string} to exist user with name {string}")
    public void the_exist_owner_with_name_sends_a_message_to_exist_user_with_name(String string, String string2, String string3) {
        try {
            com.sendMessage(string, string3, string2);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Then("the message should be delivered to the user with name {string}")
    public void the_message_should_be_delivered_to_the_user_with_name(String string) {
        Communication.User owner = com.getUserByName(string);
        List<String> inbox = owner.getInbox();
        boolean messageDelivered = inbox.stream().anyMatch(msg -> msg.contains("Welcome to our service!"));
        assertTrue("Message was not delivered to the owner with Name " + string, messageDelivered);
    }

    @Then("the user with name {string} should receive a notification")
    public void the_user_with_name_should_receive_a_notification(String string) {
        Communication.User user = com.getUserByName(string);
        Assert.assertNotNull("User with Name " + string + " does not exist.", user);
        List<String> notifications = user.getNotification();
        assertTrue("No notifications were received by the owner with Name " + string,
                notifications != null && !notifications.isEmpty());
    }

}
