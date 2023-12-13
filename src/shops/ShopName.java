package shops;

public enum ShopName {
	
	// Каждому enum должен соответствовать класс
    METRO(Metro.class),
    LENTA(Lenta.class),
    AUCHAN(Auchan.class),
    GLOBUS(Globus.class),
    OKEY(Okey.class),
    SELGROS(Selgros.class),
    MAGNIT(Magnit.class),
    UTKONOS(Utkonos.class),
    VPROK(Vprok.class),
    OZON(Ozon.class);
	
	private Class<? extends Shop> shopClazz;
	
	private ShopName(Class<? extends Shop> clazz) {
		this.shopClazz = clazz;
	}
	public Class<? extends Shop> getShopClazz() {
		return shopClazz;
	}
}	
