package managers;

import models.Product;
import models.ShopName;
import models.ShoppingList;
import reports.PriceReport;
import shops.*;
import utils.ProgressBar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChoiceMarketManager {
    
    private ShoppingList shoppingList;
    private List<Shop> shops = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Map<ShopName, Integer> minOrders = new LinkedHashMap<>();
    
    public void run() {
        
        DataInOutManager fileInOutManager = new FileInOutManager();
        shoppingList = fileInOutManager.getShoppingList();
        shoppingList.print();
        
        minOrders = fileInOutManager.getMinOrderMap();
        if (minOrders.isEmpty()) {
            System.out.println("Магазины для парсинга не заданы в файле minorder.txt");
            return;
        }
        for (ShopName minOrder : minOrders.keySet()) {
            switch (minOrder) {
                case METRO:
                    Metro metro = new Metro();
                    metro.setMinOrder(minOrders.get(ShopName.METRO));
                    shops.add(metro);
                    break;
                case AUCHAN:
                    Auchan auchan = new Auchan();
                    auchan.setMinOrder(minOrders.get(ShopName.AUCHAN));
                    shops.add(auchan);
                    break;
                case LENTA:
                    Lenta lenta = new Lenta();
                    lenta.setMinOrder(minOrders.get(ShopName.LENTA));
                    shops.add(lenta);
                    break;
                case GLOBUS:
                    Globus globus = new Globus();
                    globus.setMinOrder(minOrders.get(ShopName.GLOBUS));
                    shops.add(globus);
                    break;
                case OKEY:
                    Okey okey = new Okey();
                    okey.setMinOrder(minOrders.get(ShopName.OKEY));
                    shops.add(okey);
                    break;
                case MAGNIT:
                    Magnit magnit = new Magnit();
                    magnit.setMinOrder(minOrders.get(ShopName.MAGNIT));
                    shops.add(magnit);
                    break;
                case SELGROS:
                    Selgros selgros = new Selgros();
                    selgros.setMinOrder(minOrders.get(ShopName.SELGROS));
                    shops.add(selgros);
                    break;
                case UTKONOS:
                    Utkonos utkonos = new Utkonos();
                    utkonos.setMinOrder(minOrders.get(ShopName.UTKONOS));
                    shops.add(utkonos);
                    break;
                case VPROK:
                    Vprok vprok = new Vprok();
                    vprok.setMinOrder(minOrders.get(ShopName.VPROK));
                    shops.add(vprok);
                    break;
                case OZON:
                    Ozon ozon = new Ozon();
                    ozon.setMinOrder(minOrders.get(ShopName.OZON));
                    shops.add(ozon);
                    break;
                default:
                    break;
            }
            System.out.println(minOrder.name() + " " + minOrders.get(minOrder));
        }
        
        System.out.println();
        
        Product product;
        
        for (int i = 0; i < shoppingList.get().size(); i++) {
            ProgressBar progressBar = new ProgressBar(0, shops.size());
            product = new Product(shoppingList.get().get(i).getShortUrl(),
                shoppingList.get().get(i).getQuantity(),
                shoppingList.get().get(i).getMandatory());
            System.out.print("Запуск процесса парсинга " + progressBar.getBar() + " "
                + progressBar.getProcent() + "% \r");
            
            for (Shop shop : shops) {
                shop.getProductInfo(product);
                System.out.print(product.name + " " + progressBar.nextBar() + " "
                    + progressBar.nextProcent() + "% \r");
            }
            System.out.println(product.name + " " + progressBar.nextBar() + " "
                + "100%");
            products.add(product);
        }
        
        PriceReport priceReport = new PriceReport();
        priceReport.print(products, shops);
    }
}
