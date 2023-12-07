package managers;

import models.ShoppingList;
import models.ShoppingListItem;
import shops.ShopName;
import utils.Converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileInOutManager implements DataInOutManager {

    @Override
    public ShoppingList getShoppingList(String filename) throws ReadFileException {
        String dataFile = readFileContents(filename);
        
        ShoppingListItem shoppingListItem;
        ShoppingList shoppingList = new ShoppingList();
        String[] lines = dataFile.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if ((!lines[i].trim().startsWith("#")) && (lines[i].trim().length() != 0)) {
                String[] lineContents = lines[i].split(",");
                String name = Converter.urlToShortUrl(lineContents[0].trim());
                Integer quantity = 1;
                Boolean mandatory = true;
                
                if (lineContents.length == 2) {
                    if (lineContents[1].contains("*")) {
                        mandatory = false;
                    } else {
                        quantity = Integer.parseInt(lineContents[1].trim());
                    }
                    
                } else if (lineContents.length == 3) {
                    quantity = Integer.parseInt(lineContents[1].trim());
                    if (lineContents[2].contains("*")) {
                        mandatory = false;
                    }
                }
                
                shoppingListItem = new ShoppingListItem(name, quantity, mandatory);
                shoppingList.getShoppingList().add(shoppingListItem);
            }
            
        }
        System.out.println("Файл со списком покупок успешно считан!");
        return shoppingList;
    }
    
    @Override
    public Map<ShopName, Integer> getMinOrderMap(String filename) throws ReadFileException {
        String dataFile = readFileContents(filename);
        
        Map<ShopName, Integer> minOrderMap = new LinkedHashMap<>();
        String[] lines = dataFile.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if ((!lines[i].trim().startsWith("#")) && (!lines[i].isBlank())) {
                String[] lineContents = lines[i].split(",");
                
                for (ShopName shopName : ShopName.values()) {
                    if (shopName.name().equals(lineContents[0].trim())) {
                        if (lineContents.length == 1) {
                            minOrderMap.put(shopName, 1500);
                        } else {
                            minOrderMap.put(shopName, Integer.parseInt(lineContents[1].trim()));
                        }
                    }
                }
            }
        }
        
        System.out.println("Файл с минимальной стоимостью заказа успешно считан!");
        return minOrderMap;
    }
    
    @Override
    public Map<String, String> getProductUrlMap(ShopName shopName) throws ReadFileException {
        Map<String, String> productUrlMap = new HashMap<>();
        String fileNameUrlMap = "resources/" + shopName.name().toLowerCase() + ".txt";
        
        String dataFile = readFileContents(fileNameUrlMap);
        
        String[] lines = dataFile.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if ((!lines[i].contains("#")) && (lines[i].trim().length() != 0)) {
                
                String[] lineContents = lines[i].split(",");
                String shortUrlSber = Converter.urlToShortUrl(lineContents[0].trim());
                String urlOtherShop = lineContents[1].trim();
                
                productUrlMap.put(shortUrlSber, urlOtherShop);
            }
        }
        return productUrlMap;
    }
    
    private String readFileContents(String path) throws ReadFileException {
        try {
			return Files.readString(Path.of(path));
		} catch (IOException e) {
			throw new ReadFileException("Невозможно прочитать файл " + path + ".");
		}
    }
}
