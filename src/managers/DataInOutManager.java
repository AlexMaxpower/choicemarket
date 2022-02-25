package managers;

import models.ShopName;
import models.ShoppingList;

import java.util.Map;

public interface DataInOutManager {

    ShoppingList getShoppingList();
    Map<ShopName, Integer> getMinOrderMap();
    Map<String, String> getProductUrlMap(ShopName shopName);
}
