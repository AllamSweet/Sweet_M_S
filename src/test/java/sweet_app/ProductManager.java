package sweet_app;

import io.cucumber.java.an.E;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ProductManager {

    private static ProductManager instance;
    private ProductManager() {
    }

   public static ProductManager getInstance(){
        if (instance == null){
            instance = new ProductManager();
        }
        return instance;
   }

    public Product getPrpduct(String name) {

        return eProduct.get(name);
    }

    private Map<String, Product> eProduct = new HashMap<>();

    public static class Product {
        private String name;
        private double price;
        private int quantity;
        private String description;

        public Product(String name, double price, int quantity, String description) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.description = description;
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

    }

    public boolean productExists(String name) {
        return eProduct.containsKey(name);

    }

    private String errorMessage;
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());
public void removeProduct(String name){
    logger.info("deleting product :"+name);
    if(!productExists(name)){
        errorMessage="product with name "+name+"does not exist";
        logger.warning(errorMessage);
      throw new IllegalStateException(errorMessage);
    }
    eProduct.remove(name);

    logger.info("deleting product :"+name+"is don");


}
public void updateProduct(Product product) {

    String name=product.getName();
    if (!eProduct.containsKey(name)){

        errorMessage="their is no such product named";
        logger.severe(errorMessage);
        throw new IllegalStateException(errorMessage);
    }else {
                 if(product.getName()==null||
                product.getName().isEmpty()||
         product.getDescription().isEmpty()||
                      product.getPrice()<=0||
                   product.getQuantity()<=0||
                product.getDescription()==null){
                 logger.warning("invalid product details"+product);
                 errorMessage="invalid details";
                 throw new IllegalStateException(errorMessage);
                 }
                 eProduct.put(name,product);
                 logger.info("product name "+name+"update successfully");
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
    }


}
