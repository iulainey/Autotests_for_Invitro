package ui.utils;

public class PriceUtils {
    public static int parsePrice(String text) {

        String digits = text
                .replace('\u00A0', ' ')
                .replaceAll("[^0-9]", "");

        if (digits.isBlank()) {
            throw new IllegalStateException(
                    "Price text is empty! Raw text: [" + text + "]"
            );
        }

        return Integer.parseInt(digits);
    }
}
