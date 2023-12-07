package shops;

import models.Product;

public class Auchan extends Shop {
    private static final String shopUrl = "https://sbermarket.ru/auchan";
    private static final ShopName shopName = ShopName.AUCHAN;
    
    @Override
    public Product getProductInfo(Product product) {
        return super.getProductInfoBase(product, shopUrl, shopName);
    }
    
    @Override
    public ShopName getShopName() {
        return shopName;
    }
}

