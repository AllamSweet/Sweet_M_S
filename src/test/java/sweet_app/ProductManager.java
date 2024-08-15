package sweet_app;

import io.cucumber.java.an.E;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ProductManager {

    private static ProductManager instance;

    private ProductManager() {
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public Map<String, Product> getProductlist() {
        return eProduct;
    }

    public SoldItem getSoldItem(String name) {
        return soldItem.get(name);
    }

    public Product getPrpduct(String name) {

        return eProduct.get(name);
    }

    private double totalSales;
    private double totalProfit;
    private Map<String, Product> eProduct = new HashMap<>();
    private Map<String, SoldItem> soldItem = new HashMap<>();


    public static class SoldItem {
        private String name;
        private double price;
        private int quantitySold;
        private double totalRevenue;
        private double costPercentage;

        private double discountPercentage;


        public SoldItem(String name, double price, double totalRevenue, double costPercentage, int quantitySold, double discountPercentage) {
            this.discountPercentage = discountPercentage;
            this.price = price;
            this.totalRevenue = totalRevenue;
            this.name = name;
            this.costPercentage = costPercentage;

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

        public void setTotalRevenue(double totalRevenue) {
            this.totalRevenue = totalRevenue;
        }
    }

    public static class Product {
        private String name;
        private double price;
        private int quantity;
        private String description;
        private int quantitySold;

        public Product(String name, double price, int quantity, String description) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.description = description;
            quantitySold = 0;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getDescription() {
            return description;
        }

        public void setName(String name) {

            this.name = name;

        }

        public void setPrice(double price) {

            this.price = price;

        }

        public void setQuantity(int quantity) {

            this.quantity = quantity;

        }

        public void setDescription(String description) {

            this.description = description;

        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", description='" + description + '\'' +
                    '}';
        }

        public int getQuantitySold() {
            return quantitySold;
        }

        public void setQuantitySold(int quantitySold) {
            this.quantitySold = quantitySold;
        }
    }

    public boolean productExists(String name) {
        return eProduct.containsKey(name);

    }

    private String errorMessage;
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    public void removeProduct(String name) {
        logger.info("deleting product :" + name);
        if (!productExists(name)) {
            errorMessage = "product with name " + name + "does not exist";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        eProduct.remove(name);

        logger.info("deleting product :" + name + "is don");


    }

    public void updateProduct(Product product) {

        String name = product.getName();
        if (!eProduct.containsKey(name)) {

            errorMessage = "their is no such product named";
            logger.severe(errorMessage);
            throw new IllegalStateException(errorMessage);
        } else {
            if (product.getName() == null ||
                    product.getName().isEmpty() ||
                    product.getDescription().isEmpty() ||
                    product.getPrice() <= 0 ||
                    product.getQuantity() <= 0 ||
                    product.getDescription() == null) {
                logger.warning("invalid product details" + product);
                errorMessage = "invalid details";
                throw new IllegalStateException(errorMessage);
            }
            eProduct.put(name, product);
            logger.info("product name " + name + "update successfully");
        }
    }

    public void addProduct(Product product) throws IllegalAccessException {
        if (productExists(product.getName())) {

            errorMessage = "Product with name";
            logger.warning(errorMessage);
            throw new IllegalAccessException(errorMessage);
        }
        if (product.getName() == null
                || product.getName().isEmpty()
                || product.getDescription() == null
                || product.getDescription().isEmpty()
                || product.getQuantity() <= 0
                || product.getPrice() <= 0) {
            errorMessage = "invalid product details.";
            throw new IllegalAccessException(errorMessage);

        }
        eProduct.put(product.getName(), product);
        logger.warning("prodct" + product.getName() + "added");
    }


    public void addSellProduct(String name, double price, double totalRevenue, double costPercentage, int quantitySold, double discountPercentage) {
        if (productExists(name)) {
            SoldItem soldItem1 = new SoldItem(name, price, totalRevenue, costPercentage, quantitySold, discountPercentage);
            soldItem.put(soldItem1.getName(), soldItem1);
        } else {
            errorMessage = "this product (" + name + ")" + "dose not exist";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);

        }

    }

    public double calculateTotalRevenue() {
        return totalSales;
    }

    public double getProfittot() {
        return totalProfit;
    }

    public void sellProduct(String name, double costPercentage, int quantitySold) {
        Product product = eProduct.get(name);

        if (product != null && product.getQuantity() >= quantitySold) {
            double saleAmount = product.getPrice() * quantitySold;
            double costAmount = saleAmount * costPercentage / 100;
            double profit = saleAmount - costAmount;
            totalSales += saleAmount;
            totalProfit += profit;
            product.setQuantity(product.getQuantity() - quantitySold);
            product.setQuantitySold(product.getQuantitySold() + quantitySold);
            eProduct.put(name, product);
            logger.warning(name);
            logger.info("sold" + quantitySold + "of " + name);
            logger.info("sale amount:" + saleAmount);
            logger.info("cost amount:" + costAmount);
            logger.info("profit" + profit);
            logger.info("update total sales:" + totalSales);
            logger.info("update total profit:" + totalProfit);
        } else {

            errorMessage = "no product available";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);


        }

    }

    public List<SoldItem> getBestSellingProduct() {

        List<SoldItem> productList = new ArrayList<>(soldItem.values());
        productList.sort((p2, p1) -> Integer.compare(p1.getQuantitySold(), p2.getQuantitySold()));

        return productList;
    }

    public void applyDiscount(String name, double discountPercentage) {
        Product product = eProduct.get(name);
        if (product != null && discountPercentage > 0 && discountPercentage <= 100) {
            double originalPrice = product.getPrice();
            double discountAmount = originalPrice * (discountPercentage / 100);
            double newPrice = originalPrice - discountAmount;
            product.setPrice(newPrice);

            logger.info("Applied " + discountPercentage + "% discount to product name: " + name);
            logger.info("Original price: " + originalPrice + ", New price: " + newPrice);
        } else {
            errorMessage = "Invalid product name or discount percentage.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    public double calculateprice(String name) {
        return eProduct.get(name).getPrice();
    }
}