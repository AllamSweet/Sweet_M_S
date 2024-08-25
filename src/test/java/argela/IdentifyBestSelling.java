package argela;

import io.cucumber.java.en.Then;
import sweet_app.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class IdentifyBestSelling {

    ProductManager proManager;
    List<Map<String, String>> products;
    private List<ProductManager.SoldItem> currentBestSoledProduct;

    public IdentifyBestSelling() {

        proManager=ProductManager.getInstance();
        products=new ArrayList<>();

    }

    @Then("no product will be exist in the list")
    public void no_product_will_be_exist_in_the_list() {
     assertTrue(  proManager.getBestSellingProduct().isEmpty());
    }

    @Then("the best-selling product should be")
    public void the_best_selling_product_should_be(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedProducts = dataTable.asMaps(String.class, String.class);
        String actualProductname;
        Map<String, ProductManager.Product> products = proManager.getProductlist();
        for (int i = 0; i < expectedProducts.size(); i++) {
            Map<String, String> expectedProduct = expectedProducts.get(i);
            ProductManager.SoldItem actualProduct = proManager.getBestSellingProduct().get(i);

            actualProductname = actualProduct.getName();

            assertEquals(expectedProduct.get("ProductName"), products.get(actualProductname).getName());
//            assertEquals(Integer.parseInt(expectedProduct.get("QuantitySold")), actualProduct.getQuantitySold());
        }
    }

}



