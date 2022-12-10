package bridge.view;

import bridge.model.BridgeSize;
import bridge.model.GameCommand;
import bridge.model.Moving;
import bridge.utils.NumberUtils;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final String REQUEST_BRIDGE_SIZE = "다리의 길이를 입력해주세요.";
    private static final String REQUEST_MOVING = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String REQUEST_GAME_COMMAND = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    /**
     * 다리의 길이를 입력받는다.
     */
    public BridgeSize readBridgeSize() {
        System.out.println(REQUEST_BRIDGE_SIZE);
        int size = NumberUtils.parseInt(Console.readLine());

        return BridgeSize.from(size);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public Moving readMoving() {
        System.out.println(REQUEST_MOVING);

        return Moving.from(Console.readLine());
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public GameCommand readGameCommand() {
        System.out.println(REQUEST_GAME_COMMAND);

        return GameCommand.from(Console.readLine());
    }
}
