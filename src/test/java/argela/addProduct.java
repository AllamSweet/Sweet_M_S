package argela;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_app.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class addProduct {

    ProductManager proManager;
    List<Map<String, String>> products;
    private List<ProductManager.Product> currentProduct;

    public addProduct() {
        this.proManager = ProductManager.getInstance();
        products = new ArrayList<>();

    }

    @When("the user adds a product with the following details:")
    public void theUserAddsAProductWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            quantity = Integer.parseInt(columns.get("Quantity"));
            description = columns.get("Description");
            price = Double.parseDouble(columns.get("Price"));
            product = new ProductManager.Product(name, price, quantity, description);
            currentProduct.add(product);
            try {
                proManager.addProduct(product);
                assertTrue(true);
            } catch (Exception e) {
                fail();

            }
        }
    }

    @Then("the product should be added successfully")
    public void theProductShouldBeAddedSuccessfully() {
        for (ProductManager.Product currentProduct : currentProduct) {
            ProductManager.Product product = proManager.getPrpduct(currentProduct.getName());
            assertNotNull(product);
            assertEquals(currentProduct.getName(), product.getName());
            assertEquals(currentProduct.getDescription(), product.getDescription());
            assertEquals(currentProduct.getPrice(), product.getPrice(), 0.001);
            assertEquals(currentProduct.getQuantity(), product.getQuantity());
        }
    }

    @When("the user adds existing product with the following details:")
    public void the_user_adds_existing_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            quantity = Integer.parseInt(columns.get("Quantity"));
            description = columns.get("Description");
            price = Double.parseDouble(columns.get("Price"));
            product = new ProductManager.Product(name, price, quantity, description);
            currentProduct.add(product);
            try {
                proManager.addProduct(product);
                fail();
            } catch (Exception e) {
                assertTrue(e instanceof IllegalAccessException);
                assertTrue(e.getMessage().contains("Product with name"));

            }
        }


    }


    @When("the user adds a product with the following missing details:")
    public void the_user_adds_a_product_with_the_following_missing_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            description = columns.get("Description");
            try {
                price = Double.parseDouble(columns.get("Price"));
            } catch (Exception CX) {
                price = 0;
            }
            try {
                quantity = Integer.parseInt(columns.get("Quantity"));
            } catch (Exception CX) {
                quantity = 0;
            }
            product = new ProductManager.Product(name, price, quantity, description);
            currentProduct.add(product);
            try {
                proManager.addProduct(product);
                assertTrue(false);
            } catch (Exception C) {
                assertTrue(C instanceof IllegalAccessException);
                assertTrue(C.getMessage().contains("invalid product details."));

            }


        }
    }

    @When("the following product should not be add to the list")
    public void the_following_product_should_not_be_add_to_the_list() {
        for (ProductManager.Product currentProduct : currentProduct) {


            assertNull(proManager.getPrpduct(currentProduct.getName()));
        }

    }

    @When("the user adds a product with the following invalid details:")
    public void the_user_adds_a_product_with_the_following_invalid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantity;
        String name, description;
        double price;
        ProductManager.Product product;
        currentProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            quantity = Integer.parseInt(columns.get("Quantity"));
            description = columns.get("Description");
            price = Double.parseDouble(columns.get("Price"));
            product = new ProductManager.Product(name, price, quantity, description);
            currentProduct.add(product);
            try {
                proManager.addProduct(product);
                assertTrue(false);
            } catch (Exception C) {
                assertTrue(C instanceof IllegalAccessException);
                assertTrue(C.getMessage().contains("invalid product details."));

            }
        }
    }


}
