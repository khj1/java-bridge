package model;

import java.util.Arrays;

public enum Moving {
    UP(1, "U"),
    DOWN(0, "D");

    private static final String INVALID_COMMAND_INPUT = "0 또는 1 만 입력할 수 있습니다.";

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

    private String getDirection() {
        return direction;
    }

    private boolean isSameCommand(int command) {
        return this.command == command;
    }
}
