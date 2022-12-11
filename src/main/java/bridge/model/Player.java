package bridge.model;

public class Player {

    private static final int FIRST_TRIAL = 1;
    private static final int STARTING_POINT = 0;
    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;

    private int position;
    private int trialCount;

    private Player(int position, int trialCount) {
        this.position = position;
        this.trialCount = trialCount;
    }

    public static Player init() {
        return new Player(STARTING_POINT, FIRST_TRIAL);
    }

    public int getTrialCount() {
        return trialCount;
    }

    public boolean isAt(int position) {
        return this.position == position;
    }

    public void retry() {
        position = STARTING_POINT;
        trialCount++;
    }

    public MovingResult move(Bridge bridge, Moving moving) {
        if (isMovable(bridge, moving)) {
            position++;
            return MovingResult.of(moving, SUCCESS);
        }
        return MovingResult.of(moving, FAIL);
    }

    private boolean isMovable(Bridge bridge, Moving moving) {
        return bridge.hasSameMovingAt(position, moving);
    }
}
