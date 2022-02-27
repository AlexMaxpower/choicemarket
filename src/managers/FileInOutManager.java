package managers;

import models.ShopName;
import models.ShoppingList;
import models.ShoppingListItem;
import utils.Converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileInOutManager implements DataInOutManager {
    final static String FILENAME_SHOPPINGLIST = "resources/shoppinglist.txt";
    final static String FILENAME_MINORDER = "resources/minorder.txt";
    
    private ShoppingList shoppingList = new ShoppingList();
    private Map<ShopName, Integer> minOrderMap = new LinkedHashMap<>();
    private List<ShopName> shopsList = new ArrayList<>();
    
    @Override
    public ShoppingList getShoppingList() {
        
        String dataFile = readFileContentsOrNull(FILENAME_SHOPPINGLIST);
        if (dataFile == null) {
            return null;
        }
        
        ShoppingListItem shoppingListItem;
        
        String[] lines = dataFile.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if ((!lines[i].contains("#")) && (lines[i].trim().length() != 0)) {
                String[] lineContents = lines[i].split(",");
                String name = Converter.urlToShortUrl(lineContents[0].trim());
                ;
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
                shoppingList.get().add(shoppingListItem);
            }
            
        }
        System.out.println("Файл со списком покупок успешно считан!");
        return shoppingList;
    }
    
    @Override
    public Map<ShopName, Integer> getMinOrderMap() {
        
        String dataFile = readFileContentsOrNull(FILENAME_MINORDER);
        if (dataFile == null) {
            return null;
        }
        
        String[] lines = dataFile.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if ((!lines[i].contains("#")) && (lines[i].trim().length() != 0)) {
                String[] lineContents = lines[i].split(",");
                
                for (ShopName shopName : ShopName.values()) {
                    if (shopName.name().equals(lineContents[0].trim())) {
                        if (lineContents.length == 1) {
                            minOrderMap.put(shopName, 1500);
                            shopsList.add(shopName);
                        } else {
                            minOrderMap.put(shopName, Integer.parseInt(lineContents[1].trim()));
                            shopsList.add(shopName);
                        }
                    }
                }
            }
        }
        
        System.out.println("Файл с минимальной стоимостью заказа успешно считан!");
        return minOrderMap;
    }
    
    @Override
    public Map<String, String> getProductUrlMap(ShopName shopName) {
        
        Map<String, String> productUrlMap = new HashMap<>();
        String fileNameUrlMap = "resources/" + shopName.name().toLowerCase() + ".txt";
        
        String dataFile = readFileContentsOrNull(fileNameUrlMap);
        if (dataFile == null) {
            return null;
        }
        
        String[] lines = dataFile.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if ((!lines[i].contains("#")) && (lines[i].trim().length() != 0)) {
                
                String[] lineContents = lines[i].split(",");
                String shortUrlSber = Converter.urlToShortUrl(lineContents[0].trim());
                ;
                String urlOtherShop = lineContents[1].trim();
                
                productUrlMap.put(shortUrlSber, urlOtherShop);
            }
        }
        return productUrlMap;
    }
    
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл " + path + ".");
            return null;
        }
    }
}