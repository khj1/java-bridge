package model;

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
}
