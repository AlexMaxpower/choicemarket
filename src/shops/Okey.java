package shops;

import models.Product;

public class Okey extends Shop {
    private static final String shopUrl = "https://sbermarket.ru/okey";
    private static final ShopName shopName = ShopName.OKEY;
    
    @Override
    public Product getProductInfo(Product product) {
        return super.getProductInfoBase(product, shopUrl, shopName);
    }
    
    @Override
    public ShopName getShopName() {
        return shopName;
    }
}
