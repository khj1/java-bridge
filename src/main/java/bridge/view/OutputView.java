package bridge.view;

import bridge.model.MovingResult;
import bridge.repository.MovingHistory;
import bridge.service.BridgeGame;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OutputView {

    private static final String BLANK = " ";
    private static final String SUCCESS_MARK = "O";
    private static final String FAIL_MARK = "X";
    private static final String BRIDGE_MAP_FORMAT = "[ %s ]";
    private static final String BRIDGE_SEPARATOR = " | ";
    private static final String SUCCESS = "성공";
    private static final String FAIL = "실패";
    private static final String COMPLETE_STATUS_IS = "게임 성공 여부: %s";
    private static final String TRIAL_COUNT_IS = "총 시도한 횟수: %s";
    private static final String GAME_RESULT_IS = "최종 게임 결과";
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String GAME_INTRO = "다리 건너기 게임을 시작합니다.";

    public void printMap() {
        List<MovingResult> results = MovingHistory.get();
        List<String> upSide = convertToUpSideMap(results);
        List<String> downSide = convertToDownSideMap(results);

        printSide(upSide);
        printSide(downSide);
        System.out.println();
    }

    private List<String> convertToDownSideMap(List<MovingResult> results) {
        return results.stream()
                .map(result -> convert(result, result::isDown))
                .collect(Collectors.toList());
    }

    private List<String> convertToUpSideMap(List<MovingResult> results) {
        return results.stream()
                .map(result -> convert(result, result::isUp))
                .collect(Collectors.toList());
    }

    private void printSide(List<String> side) {
        System.out.printf(BRIDGE_MAP_FORMAT, String.join(BRIDGE_SEPARATOR, side));
        System.out.println();
    }

    private String convert(MovingResult result, Supplier<Boolean> supplier) {
        if (supplier.get()) {
            if (result.isSuccess()) {
                return SUCCESS_MARK;
            }
            return FAIL_MARK;
        }
        return BLANK;
    }

    public void printResult(BridgeGame bridgeGame) {
        printOutro();
        printMap();
        printCompleteStatus(bridgeGame);
        printTrialCount(bridgeGame);
    }

    private void printOutro() {
        System.out.println(GAME_RESULT_IS);
    }

    private void printCompleteStatus(BridgeGame bridgeGame) {
        System.out.printf(COMPLETE_STATUS_IS, convertCompleteStatus(bridgeGame));
        System.out.println();
    }

    private void printTrialCount(BridgeGame bridgeGame) {
        System.out.printf(TRIAL_COUNT_IS, bridgeGame.getTrialCount());
        System.out.println();
    }

    private String convertCompleteStatus(BridgeGame bridgeGame) {
        if (bridgeGame.isComplete()) {
            return SUCCESS;
        }
        return FAIL;
    }

    public void printError(IllegalArgumentException error) {
        System.out.print(ERROR_PREFIX);
        System.out.println(error.getMessage());
    }

    public void printIntro() {
        System.out.println(GAME_INTRO);
    }
}
