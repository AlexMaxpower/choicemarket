package shops;

import models.Product;

public class Globus extends Shop {
    private static final String shopUrl = "https://sbermarket.ru/globus";
    private static final ShopName shopName = ShopName.GLOBUS;
    
    @Override
    public Product getProductInfo(Product product) {
        return super.getProductInfoBase(product, shopUrl, shopName);
    }
    
    @Override
    public ShopName getShopName() {
        return shopName;
    }
}
