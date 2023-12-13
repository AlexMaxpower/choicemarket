package managers;

import models.Product;
import models.ShoppingList;
import models.ShoppingListItem;
import reports.PriceReport;
import shops.*;
import utils.ProgressBar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChoiceMarketManager {
    
    public void run(String[] filenames) {
        DataInOutManager fileInOutManager = new FileInOutManager();
        ShoppingList shoppingList;
        Map<ShopName, Integer> minOrders = new LinkedHashMap<>();
        
		try {
			shoppingList = fileInOutManager.getShoppingList(filenames[0]);
			minOrders = fileInOutManager.getMinOrderMap(filenames[1]);
		} catch (ReadFileException e) {
			System.err.println(e.getMessage());
			return;
		}
        
        if (minOrders.isEmpty()) {
            System.err.println("Магазины для парсинга не заданы в файле " + filenames[1]);
            return;
        }
        
        shoppingList.print();
        printMinOrders(minOrders);
        System.out.println();
        
        List<Shop> shops = fillShops(minOrders);
        List<Product> products = new ArrayList<>();
        
        fillProductsList(shoppingList, shops, products);
        
        PriceReport priceReport = new PriceReport();
        priceReport.print(products, shops);
    }

	private void fillProductsList(ShoppingList shoppingList, List<Shop> shops, List<Product> products) {
		for (ShoppingListItem item : shoppingList.getShoppingList()) {
        	ProgressBar progressBar = new ProgressBar(0, shops.size());
        	Product product = new Product(item.getShortUrl(),
        			item.getQuantity(),
        			item.getMandatory());
        	
        	System.out.print("Запуск процесса парсинга " + progressBar.getBar() + " "
        			+ progressBar.getProcent() + "% \r");
        	
        	for (Shop shop : shops) {
        		shop.getProductInfo(product);
        		System.out.print(product.name + " " + progressBar.nextBar() + " "
        				+ progressBar.nextProcent() + "% \r");
        	}
        	System.out.println(product.name + " " + progressBar.nextBar() + " 100%");
        	
        	products.add(product);
        }
	}

	private void printMinOrders(Map<ShopName, Integer> minOrders) {
		minOrders.entrySet()
				.stream()
				.forEach(entry -> System.out.println(entry.getKey().name() + " " + entry.getValue()));
	}

	private List<Shop> fillShops(Map<ShopName, Integer> minOrders) {
		List<Shop> shops = new ArrayList<>();
		
		for (ShopName shopEnum : minOrders.keySet()) {
			double minOrderValue = minOrders.get(shopEnum);
			
			// Создаём объект, устанавливаем минимальное количество и добавляем в список магазинов
			try {
				Shop shopInstance = shopEnum.getShopClazz().getConstructor().newInstance();
				shopInstance.setMinOrder(minOrderValue);
				shops.add(shopInstance);
			} catch (Exception e) {
				System.out.println(e.getCause().getMessage());
			}
        }
		
		return shops;
	}
}
