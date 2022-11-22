package bridge.controller;

import bridge.Bridge;
import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.GameCommand;
import bridge.Moving;
import bridge.MovingHistory;
import bridge.MovingResult;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;

public class BridgeGameController {

    public static final boolean IN_PROGRESS = true;
    public static final boolean GAME_OVER = false;

    private final InputView inputView;
    private final OutputView outputView;

    public BridgeGameController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        outputView.printGameIntro();

        BridgeGame bridgeGame = new BridgeGame(makeBridge());
        MovingHistory movingHistory = new MovingHistory();

        progressGame(bridgeGame, movingHistory);

        outputView.printResult(movingHistory, bridgeGame);
    }

    private Bridge makeBridge() {
        int bridgeSize = readBridgeSize();

        return Bridge.of(makeDirections(bridgeSize));
    }

    private int readBridgeSize() {
        int bridgeSize;

        try {
            bridgeSize = inputView.readBridgeSize();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return readBridgeSize();
        }
        return bridgeSize;
    }

    private List<String> makeDirections(int bridgeSize) {
        BridgeMaker bridgeMaker = getBridgeMaker();

        return bridgeMaker.makeBridge(bridgeSize);
    }

    private BridgeMaker getBridgeMaker() {
        BridgeNumberGenerator numberGenerator = new BridgeRandomNumberGenerator();

        return new BridgeMaker(numberGenerator);
    }

    private void progressGame(BridgeGame bridgeGame, MovingHistory movingHistory) {
        boolean inProgress = IN_PROGRESS;
        while (inProgress) {
            MovingResult movingResult = move(bridgeGame, movingHistory);
            inProgress = updateGameStatus(bridgeGame, movingResult, movingHistory);
        }
    }

    private MovingResult move(BridgeGame bridgeGame, MovingHistory movingHistory) {
        Moving moving = readMoving();
        MovingResult movingResult = bridgeGame.move(moving);
        movingHistory.save(movingResult);

        outputView.printMap(movingHistory);

        return movingResult;
    }

    private Moving readMoving() {
        Moving moving;

        try {
            moving = inputView.readMoving();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return readMoving();
        }
        return moving;
    }

    private boolean updateGameStatus(BridgeGame bridgeGame, MovingResult movingResult, MovingHistory movingHistory) {
        if (movingResult.isFail()) {
            return askRestart(bridgeGame, movingHistory);
        }
        if (isCompleted(bridgeGame)) {
            return GAME_OVER;
        }
        return IN_PROGRESS;
    }


    private boolean askRestart(BridgeGame bridgeGame, MovingHistory movingHistory) {
        GameCommand command = inputView.readGameCommand();

        if (command.isRestart()) {
            bridgeGame.retry();
            movingHistory.reset();
            return IN_PROGRESS;
        }
        return GAME_OVER;
    }

    private boolean isCompleted(BridgeGame bridgeGame) {
        return bridgeGame.getGameResult().isSuccess();
    }
}
