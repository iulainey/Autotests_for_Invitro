package ui.context;

public class CartContext {

    private static final ThreadLocal<String> PRODUCT_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> PRODUCT_NAME = new ThreadLocal<>();
    private static final ThreadLocal<Integer> PRODUCT_PRICE = new ThreadLocal<>();

    public static void rememberProduct(String id, String name, int price) {
        PRODUCT_ID.set(id);
        PRODUCT_NAME.set(name);
        PRODUCT_PRICE.set(price);
    }

    public static String productId() {
        return PRODUCT_ID.get();
    }

    public static String productName() {
        return PRODUCT_NAME.get();
    }

    public static Integer productPrice() {
        return PRODUCT_PRICE.get();
    }

    public static void clear() {
        PRODUCT_ID.remove();
        PRODUCT_NAME.remove();
        PRODUCT_PRICE.remove();
    }
}