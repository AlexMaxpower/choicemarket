package managers;

import models.ShoppingList;
import shops.ShopName;

import java.util.Map;

public interface DataInOutManager {
    
    ShoppingList getShoppingList(String filename) throws ReadFileException;
    
    Map<ShopName, Integer> getMinOrderMap(String filename) throws ReadFileException;
    
    Map<String, String> getProductUrlMap(ShopName shopName) throws ReadFileException;
}
