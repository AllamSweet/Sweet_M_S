package argela;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import sweet_app.StoresManager;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminMonitor {

StoresManager storesManager;
List<Map<String,String>>products;
private List<StoresManager.Sale>currentSoldProducts;
public AdminMonitor(){

    storesManager=StoresManager.getInstance();
    products=new ArrayList<>();

}


    @Given("the following product exists:")
    public void the_following_product_exists(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String productName;
        int quantitySold;
        double saleAmount;
        double price;
       StoresManager.Sale sales;
        currentSoldProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            productName = columns.get("Name");
            quantitySold = Integer.parseInt(columns.get("Quantity"));
            price = Double.parseDouble(columns.get("Price"));
            sales = new StoresManager.Sale(productName,price , 0.0, 0.0,300,0.0,20.0,50.0);
            currentSoldProducts.add(sales);
            try {
                storesManager.addproduct(sales);

                assertTrue(true);
            } catch (Exception C) {
               assertTrue(false);
            }
        }
    }

    @When("these products sold to some:")
    public void these_products_sold_to_some(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int quantitySold;
        String name, storees;
        double price ;
        double totalRevenue;
        double costPercentage;
        double discountPercentage;
        StoresManager.Sale soldItem;
        currentSoldProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name = columns.get("ProductName");
            storees=columns.get("Store");
            totalRevenue=Double.parseDouble(columns.get("TotalRevenue"));
            costPercentage=Double.parseDouble(columns.get("CostPercentage"));
            price = Double.parseDouble(columns.get("PricePerUnit"));
            discountPercentage= Double.parseDouble(columns.get("DiscountPercentage"));
            quantitySold=Integer.parseInt(columns.get("QuantitySold"));

            soldItem = new StoresManager.Sale( name, price, totalRevenue, costPercentage,quantitySold, discountPercentage,100.00,300.00);
            currentSoldProducts.add(soldItem);
            try {
                storesManager.setSelPrices(name,price);
                storesManager.recordSale( "dopahstore",name,quantitySold);
                assertTrue(true);
            } catch (Exception C) {
               fail();
            }
        }
    }



    @Then("the total sales should be {double}")
    public void the_total_sales_should_be(double totalSales) {
        double actualTotalSales=storesManager.getTotalProfit();
        assertEquals(totalSales,actualTotalSales,0.001);
    }

    @Then("the total profit should be {double}")
    public void the_total_profit_should_be(Double profit) {
        double actualTotalProfit=storesManager.getTotalProfit();
        assertEquals(profit,actualTotalProfit,0.001);
    }

    @Then("the financial report should include:")
    public void the_financial_report_should_include(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Double> expectedReport = dataTable.asMap(String.class, Double.class);
        Map<String,Double> actualReport= storesManager.genaratfinancialReport();
       assertEquals(expectedReport.get("Total Sales"),actualReport.get("Total Sales"));
        assertEquals(expectedReport.get("Total Profit"),actualReport.get("Total Profit"));
}

    @Given("the following sales data for stores:")
    public void the_following_sales_data_for_stores(io.cucumber.datatable.DataTable dataTable) {
            List<Map<String,String>>rows=dataTable.asMaps(String.class,String.class);
            for (Map<String,String>columns:rows){
                String store=columns.get("Store");
                String name=columns.get("ProductName");
                int quantitySold=Integer.parseInt(columns.get("QuantitySold"));
                try {
                    storesManager.recordSale(store,name,quantitySold);
                }catch (Exception e){
                    fail();
                }

            }
    }

    @Then("the best-selling products for each store should be:")
    public void the_best_selling_products_for_each_store_should_be(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String>expectedBestSellers=new HashMap<>();
        List<Map<String,String>>rows=dataTable.asMaps(String.class,String.class);
        for (Map<String,String>columns:rows){
            expectedBestSellers.put(columns.get("Store"),columns.get("Best-Selling Product"));
        }
        Map<String,String>actualBestSellers=storesManager.getBestSellingProductsByStore();
        for (Map.Entry<String,String>entry:expectedBestSellers.entrySet()){
            assertEquals(entry.getValue(),actualBestSellers.get(entry.getKey()));
        }
    }



}
