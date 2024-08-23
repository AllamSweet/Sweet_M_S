package sweet_app;
import io.cucumber.java.an.E;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StoresManager {
public static StoresManager instance;

public String errorMessage="";
private double totalSale=0.0;
private double totalProfit=0.0;

private Map<String,List<Sale>>storSales=new HashMap<>();
    private Map<String,Double>selPrices=new HashMap<>();
    private Map<String,Double>costPrices=new HashMap<>();
    private static final Logger logger=Logger.getLogger(StoresManager.class.getName());


    public static StoresManager getInstance(){

        if (instance==null){
            instance=new StoresManager();

        }
        return instance;
    }

    public void addproduct(Sale product){
        logger.info("adding product:"+product.getName());
        if (productExists(product.name)){
            errorMessage="Product with name:"+product.name+"already exists";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);

        }
        if (product.name==null||product.name.isEmpty()){
            errorMessage="invalid product details:"+product;
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        selPrices.put(product.getName(),0.0);
        costPrices.put(product.getName(),0.0);
        logger.info("product with name:"+product.name+"added successfully");



    }
public static class Sale{
    private String name;
    private double price;
    private int quantitySold;
    private double totalRevenue;
    private double costPercentage;

    private double discountPercentage;

    private double saleAmount;
    private double profitAmount;

    public Sale(String name, double price, double totalRevenue, double costPercentage, int quantitySold, double discountPercentage,double saleAmount,double profitAmount) {
        this.discountPercentage = discountPercentage;
        this.price = price;
        this.totalRevenue = totalRevenue;
        this.name = name;
        this.costPercentage = costPercentage;
        this.saleAmount=saleAmount;
        this.profitAmount=profitAmount;
        this.quantitySold=quantitySold;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCostPercentage() {
        return costPercentage;
    }

    public void setCostPercentage(double costPercentage) {
        this.costPercentage = costPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
    public double getSaleAmount() {
        return saleAmount;
    }

    public double getProfitAmount() {
        return profitAmount;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
   }

   public void setSelPrices(String productName,double price){
       selPrices.put(productName,price);

   }
public void setCostPrices(String productName,double price){

        costPrices.put(productName,price);

}

    public double getSelPrices(String productName) {
        if (selPrices.containsKey(productName)){
            return selPrices.get(productName);

        }else {
            errorMessage="selling price for product name:"+productName+"not found";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);


        }


    }

    public double calculateTotalSales(){

        double total =0.0;
        for (List<Sale>saleList:storSales.values()){
            for (Sale sale:saleList){
                total+=sale.getSaleAmount();
            }

        }
        return total;

    }
    public double getTotalProfit(){
        return totalProfit;
    }
    public double getTotalSale(){
        return totalSale;
    }
    public Map<String,Double>genaratfinancialReport(){
        Map<String,Double>report=new HashMap<>();
        report.put("Total Sales",calculateTotalSales());
        report.put("Total profit",getTotalProfit());
        return report;

    }

    public Map<String,String>getBestSellingProductsByStore() {


        Map<String, String> bestSellingProducts = new HashMap<>();
        for (Map.Entry<String, List<Sale>> entry : storSales.entrySet()) {
            String store = entry.getKey();
            List<Sale> saleList = entry.getValue();

            Sale bestSellingSale = null;
            for (Sale sale : saleList) {
                if (bestSellingSale == null || sale.getQuantitySold() > bestSellingSale.getQuantitySold()) {
                    bestSellingSale = sale;
                }}
                if (bestSellingSale != null) {

                    bestSellingProducts.put(store, bestSellingSale.getName());
                }




        }
        return bestSellingProducts;

    }

    public double getCostPrice(String productName){
        if (costPrices.containsKey(productName)){

            return costPrices.get(productName);
        }else {

            errorMessage="Cost price for product name:"+productName+"not found";

            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);


        }

    }


    public boolean productExists(String productName){
        return selPrices.containsKey(productName)&&costPrices.containsKey(productName);

    }
    public void recordSale(String store,String productName,int quantitySold){
        double sellingPrice=getSelPrices(productName);
        double costPrice=getCostPrice(productName);
        double saleAmount=sellingPrice*quantitySold;
        double profitAmount=(sellingPrice-costPrice)*quantitySold;
        System.out.println(costPrice);
    }



    }































