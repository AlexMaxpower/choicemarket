package app;

import managers.ChoiceMarketManager;

public class ChoiceMarket {

	private static final String ARGUMENT_SHOPS = "-shops";

	private static final String ARGUMENT_LIST = "-list";

	private static final String START_TEXT = """
			ChoiceMarket - оптимизация заказов из Сбермаркета
			-----
			""";

	private static final int MAX_ARGUMENTS = 4;
	public static final int STATUS_ARGUMENTS_NOT_IN_RANGE = -1;
	public static final int STATUS_WRONG_ARGUMENT = -2;
	private static final String SHOPPINGLIST_FILENAME = "resources/shoppinglist.txt";
	private static final String MINORDER_FILENAME = "resources/minorder.txt";
	public static final String USAGE_TEXT = """
			Использование: choicemarket -list <файл со списком покупок> -shops <файл со списком магазинов>
			При запуске без параметров используются файлы по умолчанию.
			""";

	public int start(String[] args) {
		System.out.println(START_TEXT);

		if (args.length % 2 != 0 || args.length > MAX_ARGUMENTS) {
			printUsage();
			return STATUS_ARGUMENTS_NOT_IN_RANGE;
		}

		String[] filenames = { SHOPPINGLIST_FILENAME, MINORDER_FILENAME };

		int i = 0;
		while (i < args.length) {
			if (args[i].equals(ARGUMENT_LIST)) {
				filenames[0] = args[i + 1];
			} else if (args[i].equals(ARGUMENT_SHOPS)) {
				filenames[1] = args[i + 1];
			} else {
				printUsage();
				return STATUS_WRONG_ARGUMENT;
			}
			i += 2;
		}

		ChoiceMarketManager choiceMarketManager = new ChoiceMarketManager();
		choiceMarketManager.run(filenames);
		return 0;
	}

	private static void printUsage() {
		System.out.println(USAGE_TEXT);
	}
}
