package api.context;

import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;

public class ApiContext {
    private static final ThreadLocal<Response> RESPONSE = new ThreadLocal<>();
    private static final ThreadLocal<CookieFilter> COOKIE_FILTER =
            ThreadLocal.withInitial(CookieFilter::new);

    public static void setResponse(Response response) {
        RESPONSE.set(response);
    }

    public static Response getResponse() {
        return RESPONSE.get();
    }

    public static CookieFilter cookieFilter() {
        return COOKIE_FILTER.get();
    }

    public static void clear() {
        RESPONSE.remove();
        COOKIE_FILTER.remove();
    }
}