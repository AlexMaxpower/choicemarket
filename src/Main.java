import managers.ChoiceMarketManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("ChoiceMarket - оптимизация заказов из Сбермаркета");
        System.out.println("-----");
        String[] filenames = {"resources/shoppinglist.txt", "resources/minorder.txt"};
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            if (args[i].equals("-list")) {
                filenames[0] = args[i + 1];
                System.out.println(filenames[0]);
            } else if (args[i].equals("-shops")) {
                filenames[1] = args[i + 1];
                System.out.println(filenames[1]);
            }
            i += 1;
        }
        System.out.println(filenames[0] + " " + filenames[1]);
        ChoiceMarketManager choiceMarketManager = new ChoiceMarketManager();
        choiceMarketManager.run(filenames);
    }
}
