package argela;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import sweet_app.UserManagment;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManageUserAccounts {



    UserManagment admin;

String dopah;
    private List<UserManagment.UserAcount>currentAccount;
    public ManageUserAccounts() {
        admin = UserManagment.getInstance();
    }

    @Given("the admin is logged in")
    public void the_admin_is_logged_in() {
       admin.setAdminIsLoggedIn(true);
       assertTrue(admin.isAdminIsLoggedIn());
    }

    @When("the admin adds a store owner account with the following details:")
    public void the_admin_adds_a_store_owner_account_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name,pass,type,email,phone;
        UserManagment.UserAcount user;
        currentAccount = new ArrayList<>();

        for (Map<String, String> columns : rows) {
            name = columns.get("Name");
            email = columns.get("Email");
            type = columns.get("Role");
            phone = columns.get("Phone");
            user = new UserManagment.UserAcount(name,type ,email, phone);
            currentAccount.add(user);
            try {
                admin.adduserAccount(user);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the store owner account should be added successfully")
    public void the_store_owner_account_should_be_added_successfully() {
        for (UserManagment.UserAcount currentUser : currentAccount) {
            UserManagment.UserAcount userAcount=admin.getUserAcount(currentUser.getName());
                   assertNotNull(userAcount);

            assertEquals(currentUser.getName(), userAcount.getName());
            assertEquals(currentUser.getEmail(), userAcount.getEmail());
            assertEquals(currentUser.getPhone(), userAcount.getPhone());
            assertEquals(currentUser.getRole(), userAcount.getRole());
        }
    }
    @When("the admin adds a store owner account with the following missing details:")
    public void the_admin_adds_a_store_owner_account_with_the_following_missing_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name,type,email,phone;
        UserManagment.UserAcount user;
        currentAccount = new ArrayList<>();
        for (Map<String, String> columns : rows) {

            name = columns.get("Name");
            email = columns.get("Email");
            type = columns.get("Role");
            phone = columns.get("Phone");
            user = new UserManagment.UserAcount(name,type ,email, phone);
            try {
                admin.adduserAccount(user);
                assertTrue(false);
            } catch (Exception C) {
                assertTrue(C instanceof IllegalStateException);
                assertTrue(C.getMessage().contains("Invalid user account details."));

            }


        }
    }


    @When("the admin updates the store owner account with the following details:")
    public void the_admin_updates_the_store_owner_account_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name,type,email,phone;
        UserManagment.UserAcount user;
        currentAccount = new ArrayList<>();
        for (Map<String, String> columns : rows) {

            name = columns.get("Name");
            email = columns.get("Email");
            type = columns.get("Role");
            phone = columns.get("Phone");
            user = new UserManagment.UserAcount(name,type ,email, phone);
            try {
                admin.updateOwner(user);
                assertTrue(true);
            } catch (Exception C) {
               fail();

            }


        }
    }

    @Then("the store owner account should be updated successfully")
    public void the_store_owner_account_should_be_updated_successfully() {
        for (UserManagment.UserAcount currentowner : currentAccount) {
            UserManagment.UserAcount account = admin.getUserAcount(currentowner.getName());
            assertNotNull(account);
            assertEquals(currentowner.getName(), account.getName());
            assertEquals(currentowner.getEmail(), account.getEmail());
            assertEquals(currentowner.getPhone(), account.getPhone());
            assertEquals(currentowner.getRole(), account.getRole());
        }
    }

    @Given("the following raw material supplier account exists:")
    public void the_following_raw_material_supplier_account_exists(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name,type,email,phone;
        UserManagment.UserAcount user;
        currentAccount = new ArrayList<>();

        for (Map<String, String> columns : rows) {
            name = columns.get("Name");
            email = columns.get("Email");
            type = columns.get("Role");
            phone = columns.get("Phone");
            user = new UserManagment.UserAcount(name,type ,email, phone);
            currentAccount.add(user);
            try {
                admin.adduserAccount(user);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @When("the admin deletes the raw material supplier account with name {string}")
    public void the_admin_deletes_the_raw_material_supplier_account_with_name(String string) {
        dopah=string;
        try {
            admin.removeAccount(string);
            assertTrue(true);
        } catch (Exception e) {
            fail();

        }
    }

    @Then("the raw material supplier account should be deleted successfully")
    public void the_raw_material_supplier_account_should_be_deleted_successfully() {
        assertNull(admin.getUserAcount(dopah));
    }

}
