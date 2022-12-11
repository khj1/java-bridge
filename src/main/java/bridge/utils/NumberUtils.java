package bridge.utils;

public class NumberUtils {

    private static final String NON_NUMERIC_INPUT = "숫자만 입력할 수 있습니다";

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(NON_NUMERIC_INPUT);
        }
    }
}
