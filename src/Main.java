import managers.ChoiceMarketManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("ChoiceMarket - оптимизация заказов из Сбермаркета");
        System.out.println("-----");
        ChoiceMarketManager choiceMarketManager = new ChoiceMarketManager();
        choiceMarketManager.run();
    }
}
