package managers;

import models.ShoppingList;
import shops.ShopName;

import java.util.Map;

public interface DataInOutManager {
    
    ShoppingList getShoppingList(String filename);
    
    Map<ShopName, Integer> getMinOrderMap(String filename);
    
    Map<String, String> getProductUrlMap(ShopName shopName);
}
