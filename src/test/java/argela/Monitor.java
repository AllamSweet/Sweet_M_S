package argela;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_app.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Monitor {



    ProductManager proManager;
    List<Map<String, String>> products;
    private List<ProductManager.SoldItem> currentSoledProduct;

    public Monitor() {
        proManager = ProductManager.getInstance();
        products = new ArrayList<>();
    }





    @When("the owner adds a sold product with the following details:")
    public void the_owner_adds_a_sold_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantitySold;
        String name, description;
        double price ;
        double totalRevenue;
         double costPercentage;
         double discountPercentage;
        ProductManager.SoldItem soldItem;
        currentSoledProduct = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            totalRevenue=Double.parseDouble(columns.get("TotalRevenue"));
            costPercentage=Double.parseDouble(columns.get("CostPercentage"));
            price = Double.parseDouble(columns.get("PricePerUnit"));
            discountPercentage= Double.parseDouble(columns.get("DiscountPercentage"));
            quantitySold=Integer.parseInt(columns.get("QuantitySold"));

            soldItem = new ProductManager.SoldItem( name, price, totalRevenue, costPercentage,quantitySold, discountPercentage);
            currentSoledProduct.add(soldItem);
            try {
                proManager.addSellProduct( name, price, totalRevenue, costPercentage, quantitySold, discountPercentage);
                assertTrue(true);
            } catch (Exception e) {
                fail();

            }
        }

    }

    @Then("the product sold should be added to the list")
    public void the_product_sold_should_be_added_to_the_list() {
        for (ProductManager.SoldItem currentSoledProduct : currentSoledProduct) {
            ProductManager.SoldItem soldItem = proManager.getSoldItem(currentSoledProduct.getName());
            assertNotNull(soldItem);
            assertEquals(currentSoledProduct.getName(), soldItem.getName());
            assertEquals(currentSoledProduct.getCostPercentage(), soldItem.getCostPercentage(),0.001);
            assertEquals(currentSoledProduct.getPrice(), soldItem.getPrice(), 0.001);
            assertEquals(currentSoledProduct.getQuantitySold(), soldItem.getQuantitySold(),0.001);
        }
    }

    @When("the owner cannot add a sold product that does not exist:")
    public void the_owner_cannot_add_a_sold_product_that_does_not_exist(io.cucumber.datatable.DataTable dataTable) {

            List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
            int quantitySold;
            String name, description;
            double price ;
            double totalRevenue;
            double costPercentage;
            double discountPercentage;
            ProductManager.SoldItem soldItem;
            currentSoledProduct = new ArrayList<>();
            for (Map<String, String> columns : rows) {
                name = columns.get("ProductName");
                totalRevenue=Double.parseDouble(columns.get("TotalRevenue"));
                costPercentage=Double.parseDouble(columns.get("CostPercentage"));
                price = Double.parseDouble(columns.get("PricePerUnit"));
                discountPercentage= Double.parseDouble(columns.get("DiscountPercentage"));
                quantitySold=Integer.parseInt(columns.get("QuantitySold"));

                soldItem = new ProductManager.SoldItem( name, price, totalRevenue, costPercentage,quantitySold, discountPercentage);
                currentSoledProduct.add(soldItem);
                try {
                    proManager.addSellProduct( name, price, totalRevenue, costPercentage, quantitySold, discountPercentage);
                    assertTrue(false);
                } catch (Exception C) {
                    assertTrue(C instanceof IllegalStateException);
                    assertTrue(C.getMessage().contains("this product ("+name+")"+"dose not exist"));

                }
            }
    }

    @Then("the product sold should not be added to the list")
    public void the_product_sold_should_not_be_added_to_the_list() {
        for (ProductManager.SoldItem currentSoldProduct : currentSoledProduct) {
            ProductManager.SoldItem soldproduct = proManager.getSoldItem(currentSoldProduct.getName());
            assertNull(soldproduct);
        }
    }

    @Given("the following products have been sold:")
    public void the_following_products_have_been_sold(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productData : products) {
           String name =productData.get("ProductName");
            int quantitySold = Integer.parseInt(productData.get("QuantitySold"));
            double costPercentage = Double.parseDouble(productData.get("CostPercentage"));
            try {
                proManager.sellProduct(name, costPercentage, quantitySold);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the total sales revenue should be {double}")
    public void the_total_sales_revenue_should_be(Double expectedRevenue) {
        double actualRevenue = proManager.calculateTotalRevenue();
        assertEquals(expectedRevenue, actualRevenue, 0.01);
    }

    @Given("the following products have not been sold:")
    public void the_following_products_have_not_been_sold(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productData : products) {
            String name = (productData.get("ProductName"));
            int quantitySold = Integer.parseInt(productData.get("QuantitySold"));
            double costPercentage = Double.parseDouble(productData.get("CostPercentage"));
            try {
                proManager.sellProduct(name, costPercentage, quantitySold);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("no product available"));
            }
        }
    }

    @Then("the total revenue should be {double}")
    public void the_total_revenue_should_be(Double double1) {
        double actualrevenue = proManager.calculateTotalRevenue();
        assertEquals(double1, actualrevenue, 0.001);
    }

    @Then("the profit should be {double}")
    public void the_profit_should_be(Double double1) {
        double actualprofit = proManager.getProfittot();
        assertEquals(double1, actualprofit, 0.001);
    }







}
