package bridge.view;

import bridge.model.MovingResult;
import bridge.repository.MovingHistory;
import bridge.service.BridgeGame;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
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

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(MovingHistory history) {
        List<MovingResult> results = history.get();
        List<String> upSide = results.stream()
                .map(result -> convert(result, result::isUp))
                .collect(Collectors.toList());

        List<String> downSide = results.stream()
                .map(result -> convert(result, result::isDown))
                .collect(Collectors.toList());

        System.out.printf(BRIDGE_MAP_FORMAT, String.join(BRIDGE_SEPARATOR, upSide));
        System.out.println();
        System.out.printf(BRIDGE_MAP_FORMAT, String.join(BRIDGE_SEPARATOR, downSide));
        System.out.println();
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

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(MovingHistory history, BridgeGame bridgeGame) {
        System.out.println(GAME_RESULT_IS);
        printMap(history);

        System.out.printf(COMPLETE_STATUS_IS, convertCompleteStatus(bridgeGame));
        System.out.println();
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
}
