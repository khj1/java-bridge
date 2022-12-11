package bridge.service;

import bridge.model.Bridge;
import bridge.model.Moving;
import bridge.model.MovingResult;
import bridge.model.Player;

public class BridgeGame {

    private final Bridge bridge;
    private final Player player;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
        player = Player.init();
    }

    public static BridgeGame of(Bridge bridge) {
        return new BridgeGame(bridge);
    }

    public MovingResult move(Moving moving) {
        return player.move(bridge, moving);
    }

    public void retry() {
        player.retry();
    }

    public boolean isComplete() {
        int endPoint = bridge.size();

        return player.isAt(endPoint);
    }

    public int getTrialCount() {
        return player.getTrialCount();
    }
}
