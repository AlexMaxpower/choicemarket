package shops;

import models.Product;

public class Lenta extends Shop {
    private static final String shopUrl = "https://sbermarket.ru/lenta";
    private static final ShopName shopName = ShopName.LENTA;
    
    @Override
    public Product getProductInfo(Product product) {
        return super.getProductInfoBase(product, shopUrl, shopName);
    }
    
    @Override
    public ShopName getShopName() {
        return shopName;
    }
    
}

