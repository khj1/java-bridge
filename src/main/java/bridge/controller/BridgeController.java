package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.model.Bridge;
import bridge.model.BridgeSize;
import bridge.model.GameCommand;
import bridge.model.Moving;
import bridge.model.MovingResult;
import bridge.repository.MovingHistory;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BridgeController {

    private static final boolean PLAYABLE = true;
    private static final boolean NOT_PLAYABLE = false;
    
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BridgeGame bridgeGame;

    public BridgeController() {
        bridgeGame = setUpGame();
    }

    private BridgeGame setUpGame() {
        outputView.printIntro();
        return BridgeGame.of(makeBridge());
    }

    public void run() {
        boolean playable = PLAYABLE;
        while (playable) {
            MovingResult result = move();
            playable = updateStatus(result);
        }
        outputView.printResult(bridgeGame);
    }

    private MovingResult move() {
        Moving moving = checkError(inputView::readMoving);
        MovingResult result = bridgeGame.move(moving);
        MovingHistory.save(result);
        outputView.printMap();

        return result;
    }

    private boolean updateStatus(MovingResult result) {
        if (result.isFail()) {
            return askRestart();
        }
        if (bridgeGame.isComplete()) {
            return NOT_PLAYABLE;
        }
        return PLAYABLE;
    }

    private boolean askRestart() {
        GameCommand gameCommand = checkError(inputView::readGameCommand);
        if (gameCommand.isRestart()) {
            retry();
            return PLAYABLE;
        }
        return NOT_PLAYABLE;
    }

    private void retry() {
        bridgeGame.retry();
        MovingHistory.clear();
    }

    private Bridge makeBridge() {
        BridgeMaker bridgeMaker = getBridgeMaker();
        BridgeSize bridgeSize = checkError(inputView::readBridgeSize);
        List<String> directions = bridgeMaker.makeBridge(bridgeSize.get());

        return Bridge.from(directions);
    }

    private static BridgeMaker getBridgeMaker() {
        BridgeNumberGenerator numberGenerator = new BridgeRandomNumberGenerator();

        return new BridgeMaker(numberGenerator);
    }

    private <T> T checkError(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return checkError(inputReader);
        }
    }
}
