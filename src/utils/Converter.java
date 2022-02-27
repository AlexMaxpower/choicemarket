package utils;

public class Converter {
    
    public static String urlToShortUrl(String url) {
        String shortUrl = url;
        int indexLastSlash = url.lastIndexOf("/");
        shortUrl = url.substring(indexLastSlash);
        int indexFirstQuestion = shortUrl.indexOf("?");
        if (indexFirstQuestion > 0) {
            shortUrl = shortUrl.substring(0, indexFirstQuestion);
        }
        return shortUrl;
    }
    
    public static String titleToName(String title) {
        String name = title;
        int indexFirstBuy = name.indexOf(" купить");
        if (indexFirstBuy > 0) {
            name = name.substring(0, indexFirstBuy);
        }
        return name;
    }
}
