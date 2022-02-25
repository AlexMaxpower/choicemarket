package models;

public class ShoppingListItem {
    private String shortUrl;
    private Integer quantity;
    private Boolean mandatory;

    public String getShortUrl() {
        return shortUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public ShoppingListItem(String shortUrl, Integer quantity, Boolean mandatory) {
        this.shortUrl = shortUrl;
        this.quantity = quantity;
        this.mandatory = mandatory;
    }
}
