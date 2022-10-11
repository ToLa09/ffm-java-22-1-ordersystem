package ordersystem.ffmjava221ordersystem.shop.repo;

import ordersystem.ffmjava221ordersystem.shop.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepo {

    private Map<String, Product> products = new HashMap<>();

    public ProductRepo() {
    }

//    public ProductRepo(Map<String, Product> products) {
//        this.products = products;
//    }
    public void addProduct(String id, Product product){
        products.put(id,product);
    }

    public Product getProduct(String id){
        if (products.containsKey(id)) {
            return products.get(id);
        }
        else {
            throw new IllegalArgumentException("No product found with given ID");
        }
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        String output = "Folgende Produkte sind verfügbar:\n";
        for (String key : products.keySet()) {
            String entry = products.get(key).toString();
            output = output.concat(entry + " Inventarnummer: " + key + "\n");
        }
        return output;
    }

    public Product getProductBySerialID(int serialID) {
        for (Product entry: products.values()) {
            if (entry.productNumber() == serialID) {
                return entry;
            }
        }
        throw new RuntimeException("Kein Produkt mit dieser ID vorhanden");
    }
}
