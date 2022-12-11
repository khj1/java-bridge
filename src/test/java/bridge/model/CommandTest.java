package bridge.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommandTest {

    @ParameterizedTest()
    @ValueSource(strings = {"", " ", "S"})
    void 잘못된_명령어_입력_예외처리(String command) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> GameCommand.from(command));
    }
}
