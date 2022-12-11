package bridge.model;

import java.util.Arrays;

public enum GameCommand {
    RESTART("R"),
    QUIT("Q");

    private static final String INVALID_GAME_COMMAND_INPUT = "R 또는 Q만 입력할 수 있습니다.";

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String command) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.isSameCommand(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_GAME_COMMAND_INPUT));
    }

    private boolean isSameCommand(String command) {
        return this.command.equals(command);
    }

    public boolean isRestart() {
        return this == GameCommand.RESTART;
    }
}
