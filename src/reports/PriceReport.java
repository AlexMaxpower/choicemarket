package reports;

import models.Product;
import shops.Shop;
import shops.ShopName;
import wagu.Block;
import wagu.Board;
import wagu.Table;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceReport {
    
    public void print(List<Product> products, List<Shop> shops) {
        
        System.out.println("\nМинимальная стоимость продукта:");
        List<Double> minCosts = new ArrayList<>();
        Double minCost = null;
        String minCostShop = "";
        
        for (Product product : products) {
            for (int i = 0; i < shops.size(); i++) {
                minCost = product.cost.get(shops.get(i).getShopName());
                minCostShop = shops.get(i).getShopName().toString();
                if ((minCost != null) && (minCost > 0)) {
                    break;
                }
            }
            if ((minCost == null) || (minCost == -1.0)) {
                minCost = 0.0;
                minCostShop = "нет в наличии";
            }
            
            for (Shop shop : shops) {
                Double currentCost = product.cost.get(shop.getShopName());
                if ((currentCost != null) && (currentCost > 0)) {
                    if (product.cost.get(shop.getShopName()) < minCost) {
                        minCost = product.cost.get(shop.getShopName());
                        minCostShop = shop.getShopName().toString();
                    }
                }
            }
            minCosts.add(minCost);
            System.out.println(product.name + " - " + minCost + " руб. в " + minCostShop);
        }
        
        Boolean printRemark = false;
        List<String> headersList = new ArrayList<>();
        headersList.add(" Наименование и количество");
        for (int i = 0; i < shops.size(); i++) {
            headersList.add(shops.get(i).getShopName().name());
        }
        
        List<List<String>> rowsList = new ArrayList<>();
        List<String> rowList;
        Map<ShopName, Double> orderSumMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            rowList = new ArrayList<>();
            rowList.add(" " + products.get(i).name + " - " + products.get(i).quantity + " шт.");
            for (Shop shop : shops) {
                String costInTable;
                Double cost = products.get(i).cost.get(shop.getShopName());
                if ((cost != null) && (cost > 0)) {
                    Integer quantity = products.get(i).quantity;
                    costInTable = Double.toString(cost * quantity);
                    Double orderSum = orderSumMap.getOrDefault(shop.getShopName(),0.0);
                    orderSumMap.put(shop.getShopName(), orderSum + cost * quantity);
                    if (cost == minCosts.get(i)) {
                        costInTable += " 🟩";
                    }
                } else {
                    if (cost == null) {
                        costInTable = "—";
                    } else {
                        costInTable = "?";
                        printRemark = true;
                    }
                }
                rowList.add(costInTable);
            }
            rowsList.add(rowList);
        }

        rowList = new ArrayList<>();
        rowList.add(" Сумма заказа");
        for (Shop shop : shops) {
            Double orderSum = orderSumMap.get(shop.getShopName());
            String orderSumInTable = new DecimalFormat("#0.00").format(orderSum).replace(',', '.');
            if (orderSum < shop.getMinOrder()) {
                orderSumInTable += " 🟥";
            }
            rowList.add(orderSumInTable);
        }
        rowsList.add(rowList);

        Board board = new Board(72 + 16 * shops.size());
        Table table = new Table(board, 72 + 16 * shops.size(), headersList, rowsList);
        
        List<Integer> colAlignList = new ArrayList<>();
        colAlignList.add(Block.DATA_MIDDLE_LEFT);
        for (int i = 0; i < shops.size(); i++) {
            colAlignList.add(Block.DATA_CENTER);
        }
        table.setColAlignsList(colAlignList);
        List<Integer> colWidthsListEdited = new ArrayList<>();
        colWidthsListEdited.add(72);
        for (int i = 0; i < shops.size(); i++) {
            colWidthsListEdited.add(12);
        }
        table.setGridMode(Table.GRID_FULL).setColWidthsList(colWidthsListEdited);
        Block tableBlock = table.tableToBlocks();
        board.setInitialBlock(tableBlock);
        board.build();
        String tableString = board.getPreview();
        System.out.println(tableString);
        System.out.println("🟩 - минимальная стоимость товара");
        System.out.println(" — - товара нет в наличии");
        System.out.println("🟥 - сумма заказа меньше минимальной");
        if (printRemark) {
            System.out.println("? - товара нет в таблице соответствия");
        }
    }
}