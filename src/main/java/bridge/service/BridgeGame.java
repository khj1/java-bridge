package bridge.service;

import bridge.model.Bridge;
import bridge.model.Moving;
import bridge.model.MovingResult;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static final int STARTING_POINT = 0;
    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;
    private static final int FIRST_TRIAL = 1;

    private final Bridge bridge;
    private int position = STARTING_POINT;
    private int trialCount = FIRST_TRIAL;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
    }

    public static BridgeGame of(Bridge bridge) {
        return new BridgeGame(bridge);
    }

    public MovingResult move(Moving moving) {
        if (bridge.getMovingAt(position) == moving) {
            position++;
            return MovingResult.of(moving, SUCCESS);
        }
        return MovingResult.of(moving, FAIL);
    }

    public void retry() {
        position = STARTING_POINT;
        trialCount++;
    }

    public boolean isComplete() {
        return bridge.size() == position;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
