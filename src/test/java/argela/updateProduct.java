package argela;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_app.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class updateProduct {

    ProductManager proManager;
    List<Map<String, String>> products;
    private List<ProductManager.Product> currentProduct;

    public updateProduct() {
        this.proManager = ProductManager.getInstance();
        products = new ArrayList<>();
    }

    @When("the user updates a product with the following details:")
    public void the_user_updates_a_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            quantity = Integer.parseInt(columns.get("NewQuantity"));
            description = columns.get("NewDescription");
            price = Double.parseDouble(columns.get("NewPrice"));
            product = new ProductManager.Product(name, price, quantity, description);
            currentProduct.add(product);
            try {
                proManager.updateProduct(product);
                assertTrue(true);
            } catch (Exception e) {
                fail();

            }
        }

    }

    @Then("the product should be updated successfully")
    public void the_product_should_be_updated_successfully() {
        for (ProductManager.Product currentProduct : currentProduct) {
            ProductManager.Product product = proManager.getPrpduct(currentProduct.getName());
            assertNotNull(product);
            assertEquals(currentProduct.getName(), product.getName());
            assertEquals(currentProduct.getDescription(), product.getDescription());
            assertEquals(currentProduct.getPrice(), product.getPrice(), 0.001);
            assertEquals(currentProduct.getQuantity(), product.getQuantity());
        }
    }

    @When("the user updates a non-existing product with the following details:")
    public void the_user_updates_a_non_existing_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            quantity = Integer.parseInt(columns.get("NewQuantity"));
            description = columns.get("NewDescription");
            price = Double.parseDouble(columns.get("NewPrice"));
            product = new ProductManager.Product(name, price, quantity, description);
            currentProduct.add(product);
            try {
                proManager.updateProduct(product);
                assertTrue(false);
            } catch (Exception C) {
                assertTrue(C instanceof IllegalStateException);
                assertTrue(C.getMessage().contains("their is no such product named"));

            }
        }
    }

    /*  @Then("the product should not be updated")
      public void the_product_should_not_be_updated() {
          // Write code here that turns the phrase above into concrete actions
          throw new io.cucumber.java.PendingException();
    }
  /*
     @Then("an error message should be displayed stating {string}")
      public void an_error_message_should_be_displayed_stating(String string) {
          // Write code here that turns the phrase above into concrete actions
          throw new io.cucumber.java.PendingException();
      }
  */
    @When("the user updates a product with the following invalid details:")
    public void the_user_updates_a_product_with_the_following_invalid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            description = columns.get("NewDescription");
            try {
                price = Double.parseDouble(columns.get("NewPrice"));
            } catch (Exception CX) {
                price = 0;
            }
            try {
                quantity = Integer.parseInt(columns.get("NewQuantity"));
            } catch (Exception CX) {
                quantity = 0;
            }
            product = new ProductManager.Product(name, price, quantity, description);
            currentProduct.add(product);
            try {
                proManager.updateProduct(product);
                assertTrue(false);
            } catch (Exception C) {
                assertTrue(C instanceof IllegalStateException);
                assertTrue(C.getMessage().contains("invalid details"));

            }


        }
    }



}
