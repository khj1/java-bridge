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
        boolean playable = true;
        while (playable) {
            MovingResult result = move();
            outputView.printMap();

            playable = updateStatus(result);
        }
        outputView.printResult(bridgeGame);
    }

    private MovingResult move() {
        Moving moving = checkError(inputView::readMoving);
        MovingResult result = bridgeGame.move(moving);
        MovingHistory.save(result);

        return result;
    }

    private boolean updateStatus(MovingResult result) {
        if (result.isSuccess()) {
            return !bridgeGame.isComplete();
        }
        return askRestart();
    }

    private boolean askRestart() {
        GameCommand gameCommand = checkError(inputView::readGameCommand);
        if (gameCommand.isQuit()) {
            return false;
        }
        bridgeGame.retry();
        MovingHistory.clear();
        return true;
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
