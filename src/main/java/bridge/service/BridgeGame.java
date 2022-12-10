package bridge.service;

import model.Bridge;
import model.Moving;
import model.MovingResult;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static final int STARTING_POINT = 0;
    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;

    private final Bridge bridge;
    private int position = STARTING_POINT;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
    }

    public static BridgeGame of(Bridge bridge) {
        return new BridgeGame(bridge);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     *
     * @return
     */
    public MovingResult move(Moving moving) {
        if (bridge.getMovingAt(position) == moving) {
            position++;
            return MovingResult.of(moving, SUCCESS);
        }
        return MovingResult.of(moving, FAIL);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }

    public boolean isComplete() {
        return bridge.size() == position;
    }
}
