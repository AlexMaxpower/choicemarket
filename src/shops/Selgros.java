package shops;

import models.Product;
import models.ShopName;

public class Selgros extends Shop{
    private double minOrder;
    private final String shopUrl = "https://sbermarket.ru/selgros";
    private final ShopName shopName = ShopName.SELGROS;

    public double getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(double minOrder) {
        if (minOrder>0) this.minOrder = minOrder;
    }

    @Override
    public Product getProductInfo(Product product){
        return super.getProductInfoBase(product, shopUrl, shopName);
    }

    @Override
    public ShopName getShopName() {
        return shopName;
    }
}