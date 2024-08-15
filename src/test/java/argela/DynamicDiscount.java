package argela;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_app.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DynamicDiscount {
    ProductManager ds;
    String productname;

    public DynamicDiscount() {
        ds = ProductManager.getInstance();
    }

    @When("the owner applies a discount of {int}% to the product with name {string}")
    public void the_owner_applies_a_discount_of_to_the_product_with_name(Integer dis, String name) {
        try {
            productname = name;
            ds.applyDiscount(name, dis);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Then("the product's price should be updated to {double}")
    public void the_product_s_price_should_be_updated_to(Double price) {
        double actualnewprice = ds.calculateprice(productname);
        assertEquals(price, actualnewprice, 0.01);
    }

    @When("the owner attempts to apply an invalid discount of {int}% to the product with name {string}")
    public void the_owner_attempts_to_apply_an_invalid_discount_of_to_the_product_with_name(Integer dis, String name) {
        try {
            productname = name;
            ds.applyDiscount(name, dis);
            assertTrue(false);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalStateException);
            assertTrue(ex.getMessage().contains("Invalid product name or discount percentage."));
        }
    }

    @Then("the product's price should remain {double}")
    public void the_product_s_price_should_remain(Double price) {
        double actualprice = ds.calculateprice(productname);
        assertEquals(price, actualprice, 0.01);
    }
}