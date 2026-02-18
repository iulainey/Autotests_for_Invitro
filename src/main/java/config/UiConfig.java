package config;

public class UiConfig {
    public static String baseUrl() {
        return ConfigLoader.get("base.url.ui");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(ConfigLoader.get("headless"));
    }

    public static int timeoutSeconds() {
        return Integer.parseInt(ConfigLoader.get("timeout"));
    }

    public static String cartUrl() {
        return ConfigLoader.get("cart.url");
    }
}
