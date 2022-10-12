package ordersystem.ffmjava221ordersystem.shop.repo;

import ordersystem.ffmjava221ordersystem.shop.ServiceUtils;
import ordersystem.ffmjava221ordersystem.shop.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepo {
    private final ServiceUtils serviceUtils = new ServiceUtils();

    private final String uuid1 = serviceUtils.generateUUID();
    private final String uuid2 = serviceUtils.generateUUID();
    private final String uuid3 = serviceUtils.generateUUID();
    private final String uuid4 = serviceUtils.generateUUID();

    private final Map<String, Product> products = new HashMap<>(Map.of(
            uuid1, new Product(uuid1,1,"Banane",0.99),
            uuid2, new Product(uuid2,2,"Apfel", 0.39 ),
            uuid3, new Product(uuid3,3,"Chips", 1.99),
            uuid4, new Product(uuid4,4,"Kartoffeln",2.99)
        ));

    public ProductRepo() {
    }
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
