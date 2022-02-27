package shops;

import models.Product;
import models.ShopName;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.Converter;

import java.io.IOException;

public class Shop {
    public double minOrder;
    private String shopUrl;
    private ShopName shopName;
    
    public Product getProductInfoBase(Product product, String shopUrl, ShopName shopName) {
        Connection connection = Jsoup.connect(shopUrl + product.shortUrl);
        connection.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
        connection.timeout(20000);
        connection.referrer("http://google.com");
        try {
            Document docCustomConn = connection.get();
            if (product.name == null) {
                product.name = Converter.titleToName(docCustomConn.title());
            }
            String html = docCustomConn.toString();
            
            if (html.contains("data-qa=\"addToCart_buy_button\">Нет в наличии")) {
                product.cost.put(shopName, null);
            } else {
                int indexStartSubstring = html.indexOf("unitPrice&quot;:") + 16;
                int indexEndSubstring = html.indexOf(",&quot;originalUnitPrice");
                if (indexStartSubstring > 0 && indexEndSubstring > indexStartSubstring) {
                    String cost = html.substring(indexStartSubstring, indexEndSubstring);
                    product.cost.put(shopName, Double.parseDouble(cost));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public Product getProductInfo(Product product) {
        return product;
    }
    
    public ShopName getShopName() {
        return shopName;
    }
}
