package models;

import wagu.Block;
import wagu.Board;
import wagu.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingList {
    private List<ShoppingListItem> shoppingList;

    public ShoppingList() {
        shoppingList = new ArrayList<>();
    }

    public List<ShoppingListItem> get() {
        return shoppingList;
    }

    public void print() {
        List<List<String>> rowsList = new ArrayList<>();
        List<String> headersList = Arrays.asList("shortUrl", "Количество", "Обязательно или нет");
        for (int i=0; i<shoppingList.size(); i++) {
            String mandatoryToString;
            if (shoppingList.get(i).getMandatory()) {
                mandatoryToString = "обязательно";
            }
            else {
                mandatoryToString = "можно в другой раз";
            }

            rowsList.add(Arrays.asList(shoppingList.get(i).getShortUrl(),
                    Integer.toString(shoppingList.get(i).getQuantity()),
                    mandatoryToString));
        }

        Board board = new Board(110);
        Table table = new Table(board, 110, headersList, rowsList);
        List<Integer> colAlignList = Arrays.asList(
                Block.DATA_CENTER,
                Block.DATA_CENTER,
                Block.DATA_CENTER);
        table.setColAlignsList(colAlignList);
        List<Integer> colWidthsListEdited = Arrays.asList(60, 12, 26);
        table.setGridMode(Table.GRID_FULL).setColWidthsList(colWidthsListEdited);
        Block tableBlock = table.tableToBlocks();
        board.setInitialBlock(tableBlock);
        board.build();
        String tableString = board.getPreview();
        System.out.println(tableString);
    }
}
