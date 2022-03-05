package managers;

import models.ShopName;
import models.ShoppingList;

import java.util.Map;

public interface DataInOutManager {
    
    ShoppingList getShoppingList(String filename);
    
    Map<ShopName, Integer> getMinOrderMap(String filename);
    
    Map<String, String> getProductUrlMap(ShopName shopName);
}
