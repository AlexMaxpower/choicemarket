package shops;

import models.Product;

public class Selgros extends Shop {
    private static final String shopUrl = "https://sbermarket.ru/selgros";
    private static final ShopName shopName = ShopName.SELGROS;
    
    @Override
    public Product getProductInfo(Product product) {
        return super.getProductInfoBase(product, shopUrl, shopName);
    }
    
    @Override
    public ShopName getShopName() {
        return shopName;
    }
}