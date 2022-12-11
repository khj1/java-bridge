package bridge.view;

import bridge.model.BridgeSize;
import bridge.model.GameCommand;
import bridge.model.Moving;
import bridge.utils.NumberUtils;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String REQUEST_BRIDGE_SIZE = "다리의 길이를 입력해주세요.";
    private static final String REQUEST_MOVING = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String REQUEST_GAME_COMMAND = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    public BridgeSize readBridgeSize() {
        System.out.println(REQUEST_BRIDGE_SIZE);
        int size = NumberUtils.parseInt(Console.readLine());

        return BridgeSize.from(size);
    }

    public Moving readMoving() {
        System.out.println(REQUEST_MOVING);

        return Moving.from(Console.readLine());
    }

    public GameCommand readGameCommand() {
        System.out.println(REQUEST_GAME_COMMAND);

        return GameCommand.from(Console.readLine());
    }
}
