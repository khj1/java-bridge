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

    public void run() {
        outputView.printIntro();
        BridgeSize bridgeSize = checkError(inputView::readBridgeSize);
        BridgeNumberGenerator numberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(numberGenerator);
        List<String> directions = bridgeMaker.makeBridge(bridgeSize.get());
        Bridge bridge = Bridge.from(directions);

        BridgeGame bridgeGame = BridgeGame.of(bridge);
        MovingHistory movingHistory = new MovingHistory();

        boolean playable = true;
        while (playable) {
            Moving moving = checkError(inputView::readMoving);
            MovingResult result = bridgeGame.move(moving);
            movingHistory.save(result);
            outputView.printMap(movingHistory);

            if (!result.isSuccess()) {
                GameCommand gameCommand = checkError(inputView::readGameCommand);
                if (gameCommand.isQuit()) {
                    playable = false;
                }
                bridgeGame.retry();
                movingHistory = movingHistory.clear();
            }
            playable = !bridgeGame.isComplete();
        }
        outputView.printResult(movingHistory, bridgeGame);
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
