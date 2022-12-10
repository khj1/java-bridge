package bridge.service;

import model.Bridge;
import model.Moving;
import model.MovingResult;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeGameTest {

    @ParameterizedTest(name = "방향: {0}, 결과: {1}")
    @MethodSource("createBridgeGameMoveSource")
    void 이동_결과를_확인할_수_있다(Moving moving, boolean result) {
        Bridge bridge = Bridge.from(List.of("U", "D", "D"));
        BridgeGame bridgeGame = BridgeGame.of(bridge);

        MovingResult actualResult = bridgeGame.move(moving);
        MovingResult expectedResult = MovingResult.of(moving, result);

        assertThat(actualResult)
                .usingRecursiveComparison()
                .isEqualTo(expectedResult);
    }

    public static Stream<Arguments> createBridgeGameMoveSource() {
        return Stream.of(
                Arguments.arguments(Moving.UP, true),
                Arguments.arguments(Moving.DOWN, false)
        );
    }
}
