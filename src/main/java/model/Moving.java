package model;

import java.util.Arrays;

public enum Moving {
    UP(1, "U"),
    DOWN(0, "D");

    private static final String INVALID_COMMAND_INPUT = "0 또는 1 만 입력할 수 있습니다.";
    private static final String INVALID_DIRECTION_INPUT = "U 또는 D만 입력할 수 있습니다.";

    private final int command;
    private final String direction;

    Moving(int command, String direction) {
        this.command = command;
        this.direction = direction;
    }

    public static String toDirection(int command) {
        return Arrays.stream(values())
                .filter(moving -> moving.isSameCommand(command))
                .map(Moving::getDirection)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_INPUT));
    }

    public static Moving from(String direction) {
        return Arrays.stream(values())
                .filter(moving -> moving.isSameDirection(direction))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_INPUT));
    }

    private boolean isSameCommand(int command) {
        return this.command == command;
    }

    private String getDirection() {
        return direction;
    }

    private boolean isSameDirection(String direction) {
        return this.direction.equals(direction);
    }
}
