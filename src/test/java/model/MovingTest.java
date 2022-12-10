package model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MovingTest {

    @ParameterizedTest(name = "명령어: {0}, 방향: {1}")
    @MethodSource("createMovingSource")
    void 입력된_명령어에_맞계_올바른_방향으로_이동한다(int command, String expectedDirection) {
        assertThat(Moving.toDirection(command)).isEqualTo(expectedDirection);
    }

    public static Stream<Arguments> createMovingSource() {
        return Stream.of(
                Arguments.arguments(0, "D"),
                Arguments.arguments(1, "U")
        );
    }
}
