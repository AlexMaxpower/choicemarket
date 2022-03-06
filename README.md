## ChoiceMarket - парсер цен на продукты магазинов сайта СберМаркет
#### Также можно мониторить цены других магазинов (Озон, Перекресток.Впрок, Утконос) по заранее подготовленному списку соответствия

Программа выводит таблицу с ценами и показывает в каком магазине стоимость продукта минимальная

![Таблица с ценами](https://github.com/AlexMaxpower/ChoiceMarket/raw/master/resources/choicemarket.jpg)

**Параметры командной строки:**

-list <имя файла со списком покупок> -shops <имя файла со списком магазинов для парсинга>

**Если параметры не заданы, то берутся файлы по умолчанию:**

/resources/minorder.txt - список магазинов с минимальной суммой заказа

/resources/shoppinglist.txt - список покупок с количеством

Списки соответствия товаров Сбермаркета и сторонних магазинов находятся в папке /resources

Например:

/resources/ozon.txt - список соответствия для товаров Озона

/resources/utkonos.txt - список соответствия для товаров Утконоса

/resources/vprok.txt - список соответствия для товаров Перекресток.Впрок


