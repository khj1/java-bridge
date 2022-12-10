package bridge.model;

public class MovingResult {

    private final Moving moving;
    private final boolean isSuccess;

    private MovingResult(Moving moving, boolean isSuccess) {
        this.moving = moving;
        this.isSuccess = isSuccess;
    }

    public static MovingResult of(Moving moving, boolean isSuccess) {
        return new MovingResult(moving, isSuccess);
    }

    public boolean isUp() {
        return moving == Moving.UP;
    }

    public boolean isDown() {
        return moving == Moving.DOWN;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
