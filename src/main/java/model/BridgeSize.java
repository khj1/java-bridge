package model;

public class BridgeSize {

    private static final String INVALID_BRIDGE_SIZE = "다리 길이가 부적합합니다.";
    private static final int BRIDGE_SIZE_LOWER_BOUND = 3;
    private static final int BRIDGE_SIZE_UPPER_BOUND = 20;

    private final int size;

    public BridgeSize(int size) {
        validate(size);
        this.size = size;
    }

    private void validate(int size) {
        if (isValidSize(size)) {
            throw new IllegalArgumentException(INVALID_BRIDGE_SIZE);
        }
    }

    private static boolean isValidSize(int size) {
        return size < BRIDGE_SIZE_LOWER_BOUND || size > BRIDGE_SIZE_UPPER_BOUND;
    }

    public static BridgeSize from(int size) {
        return new BridgeSize(size);
    }
}
