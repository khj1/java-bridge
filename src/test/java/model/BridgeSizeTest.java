package model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeSizeTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    void 다리_길이_유효성_검증_테스트(int size) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BridgeSize.from(size));
    }
}
