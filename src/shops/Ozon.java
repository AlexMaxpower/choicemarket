package shops;

import managers.DataInOutManager;
import managers.FileInOutManager;
import models.Product;
import models.ShopName;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.Converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Ozon extends Shop{

    private double minOrder;
    private String shopUrl = "https://www.ozon.ru";
    private Map<String,String> shortUrlMap = new HashMap<>();
    private ShopName shopName = ShopName.OZON;

    public Ozon() {
        fillingShortUrlMap();
    }
    public double getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(double minOrder) {
        if (minOrder>0) this.minOrder = minOrder;
    }

    @Override
    public Product getProductInfo(Product product){
        if (!shortUrlMap.containsKey(product.shortUrl)) {
            product.cost.put(shopName, -1.0);
            return product;
        }

        Connection connection = Jsoup.connect(shortUrlMap.get(product.shortUrl));
        connection.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
        connection.timeout(20000);
        connection.referrer("http://google.com");

        try {
            Document docCustomConn = connection.get();
            if (product.name == null) {
                product.name = Converter.titleToName(docCustomConn.title());
            }
            String html = docCustomConn.toString();

            if (html.contains("Этот товар закончился")) {
                product.cost.put(shopName, null);
            }

            else {
                int indexStartSubstring = html.indexOf("InStock\",\"price\":\"")+18;
                int indexEndSubstring = html.indexOf("\",\"priceCurrency\"");
                if (indexStartSubstring>0 && indexEndSubstring>indexStartSubstring) {
                    String cost = html.substring(indexStartSubstring, indexEndSubstring);
                    product.cost.put(shopName, Double.parseDouble(cost));
                }
            }
        }

        catch(IOException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public ShopName getShopName() {
        return shopName;
    }

    private void fillingShortUrlMap() {
        DataInOutManager fileInOutManager = new FileInOutManager();
        shortUrlMap = fileInOutManager.getProductUrlMap(shopName);
    }
}