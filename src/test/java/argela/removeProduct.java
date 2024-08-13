package argela;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_app.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class removeProduct {

    ProductManager proManager;
    String name2;
    List<Map<String, String>> products;
    private List<ProductManager.Product> currentProduct;

    public removeProduct() {
        proManager = ProductManager.getInstance();
        products = new ArrayList<>();

    }

    @When("the user removes the product {string}")
    public void the_user_removes_the_product(String name) {

        String nameP=name;


            try {
                proManager.removeProduct(nameP);
                assertTrue(true);
            } catch (Exception e) {
                fail();

            }
        }


    @Then("the product {string} should be removed successfully")
    public void the_product_should_be_removed_successfully(String name) {



            assertNull(proManager.getPrpduct(name));

    }

    @When("the user tries to remove a non-existing product with the following details:")
    public void the_user_tries_to_remove_a_non_existing_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("productName");
            name2=name;
//            quantity = Integer.parseInt(columns.get("quantity"));
//            description = columns.get("description");
//            price = Double.parseDouble(columns.get("price"));
//            product = new ProductManager.Product(name, price, quantity, description);
//            currentProduct.add(product);
            try {
                proManager.removeProduct(name);
                assertTrue(false);
            } catch (Exception C) {
                assertTrue(C instanceof IllegalStateException);
                assertTrue(C.getMessage().contains("product with name "+name+"does not exist"));

            }
        }
    }

    @Then("the product should not be removed")
    public void the_product_should_not_be_removed() {



            assertNull(proManager.getPrpduct(name2));

    }


}
