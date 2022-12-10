package bridge.model;

import model.Moving;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MovingTest {

    @ParameterizedTest(name = "명령어: {0}, 방향: {1}")
    @MethodSource("createDirectionSource")
    void 입력된_명령어에_맞계_올바른_방향으로_이동한다(int command, String expectedDirection) {
        Assertions.assertThat(Moving.toDirection(command)).isEqualTo(expectedDirection);
    }

    public static Stream<Arguments> createDirectionSource() {
        return Stream.of(
                Arguments.arguments(0, "D"),
                Arguments.arguments(1, "U")
        );
    }

    @ParameterizedTest(name = "방향: {0}, 움직임: {1}")
    @MethodSource("createMovingSource")
    void 입력된_방향에_맞는_움직임을_반환한다(String direction, Moving expectedMoving) {
        assertThat(Moving.from(direction)).isEqualTo(expectedMoving);
    }

    public static Stream<Arguments> createMovingSource() {
        return Stream.of(
                Arguments.arguments("U", Moving.UP),
                Arguments.arguments("D", Moving.DOWN)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "A"})
    void 잘못된_방향_입력에_대한_예외처리(String direction) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Moving.from(direction));
    }
}
