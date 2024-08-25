package sweet_app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StoresManager {
public static StoresManager instance;
    private static final String REPORT_FILE = "store_reports.txt";

public String errorMessage="";
private double totalSale=0.0;
private double totalProfit=0.0;

private Map<String,List<Sale>>storSales=new HashMap<>();
    private Map<String,Double>selPrices=new HashMap<>();
    private Map<String,Double>costPrices=new HashMap<>();
    private static final Logger logger=Logger.getLogger(StoresManager.class.getName());

    public StoresManager() {
        loadReports();

    }

    private void loadReports() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String store = parts[0].trim();

                    String productName = parts[1].trim();
                    int quantitySold = Integer.parseInt(parts[2].trim());
                    double saleAmount = Double.parseDouble(parts[3].trim());
                    double profitAmount = Double.parseDouble(parts[4].trim());

                    // Provide default values for parameters that are not available in the file
                    double price = getSelPrices(productName); // You may need to handle cases where the price is not set
                    double costPercentage = 0.0;
                    double discountPercentage = 0.0;

                    Sale sale = new Sale(productName, price, saleAmount, costPercentage, quantitySold, discountPercentage, saleAmount, profitAmount);
                    List<Sale> salesList = storSales.computeIfAbsent(store, k -> new ArrayList<>());
                    salesList.add(sale);
                } else {
                    logger.warning("Invalid report data format: " + line);
                }
            }
        } catch (IOException e) {
            logger.severe("Error loading reports: " + e.getMessage());
        }
    }

    private void saveReports() {
        try (FileWriter writer = new FileWriter(REPORT_FILE)) {
            for (Map.Entry<String, List<Sale>> entry : storSales.entrySet()) {
                String store = entry.getKey();
                for (Sale sale : entry.getValue()) {
                    writer.write(serializeSale(store, sale) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            logger.severe("Error saving reports: " + e.getMessage());
        }
    }
    private String serializeSale(String store, Sale sale) {
        return store + "," +
                sale.getName() + "," +   // Using getName() instead of getProductId()
                sale.getPrice() + "," +
                sale.getQuantitySold() + "," +
                sale.getSaleAmount() + "," +
                sale.getProfitAmount();
    }

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
        saveReports();
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
       saveReports();
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
                    saveReports();
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
      saveReports();
        System.out.println(costPrice);
    }
    public boolean storeExists(String storeName) {
        return storSales.containsKey(storeName);
    }
    public void addProductToStore(String storeName, Sale product) {
        if (!storeExists(storeName)) {
            storSales.put(storeName, new ArrayList<>());
        }
        storSales.get(storeName).add(product);
    }
//    public void recordSale(String storeName, String productName, int quantitySold) {
//        double sellingPrice = getSelPrices(productName);
//        double costPrice = getCostPrice(productName);
//        double saleAmount = sellingPrice * quantitySold;
//        double profitAmount = (sellingPrice - costPrice) * quantitySold;
//
//        // Record the sale
//        Sale sale = new Sale(productName, sellingPrice, saleAmount,
//                (costPrice / sellingPrice) * 100,
//                quantitySold, 0, saleAmount, profitAmount);
//
//        addProductToStore(storeName, sale);
//    }



    }































