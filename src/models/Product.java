package models;

import java.util.HashMap;
import java.util.Map;

public class Product {
    public String name;
    public Map<ShopName,Double> cost = new HashMap<>();
    public String shortUrl;
    public Integer quantity;
    public Boolean mandatory;

    public Product(String shortUrl, Integer quantity, Boolean mandatory) {
        this.shortUrl = shortUrl;
        this.quantity = quantity;
        this.mandatory = mandatory;
    }
}
