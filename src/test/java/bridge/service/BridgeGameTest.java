package bridge.service;

import model.Bridge;
import model.Moving;
import model.MovingResult;
import org.junit.jupiter.api.BeforeEach;
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

    BridgeGame bridgeGame;

    @BeforeEach
    void setUp() {
        Bridge bridge = Bridge.from(List.of("U", "D"));
        bridgeGame = BridgeGame.of(bridge);
    }

    @ParameterizedTest(name = "방향: {0}, 결과: {1}")
    @MethodSource("createBridgeGameMoveSource")
    void 이동_결과를_확인할_수_있다(Moving moving, boolean result) {
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

    @ParameterizedTest(name = "첫번째 시도: {0}, 두번째 시도: {1}, 결과: {2}")
    @MethodSource("createIsCompleteTestSource")
    void 다리를_끝까지_건넜는지_확인할_수_있다(Moving firstMove, Moving secondMove, boolean expectedResult) {
        bridgeGame.move(firstMove);
        bridgeGame.move(secondMove);

        assertThat(bridgeGame.isComplete()).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> createIsCompleteTestSource() {
        return Stream.of(
                Arguments.arguments(Moving.UP, Moving.DOWN, true),
                Arguments.arguments(Moving.UP, Moving.UP, false)
        );
    }
}
