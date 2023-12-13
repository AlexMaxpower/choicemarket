package shops;

import models.Product;

public class Metro extends Shop {
    private static final String shopUrl = "https://sbermarket.ru/metro";
    private static final ShopName shopName = ShopName.METRO;
    
    @Override
    public Product getProductInfo(Product product) {
        return super.getProductInfoBase(product, shopUrl, shopName);
    }
    
    @Override
    public ShopName getShopName() {
        return shopName;
    }
}
