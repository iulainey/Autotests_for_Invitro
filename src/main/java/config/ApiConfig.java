package config;

public class ApiConfig {
    public static String baseUrl() {

        return ConfigLoader.get("base.url.api");
    }
}
