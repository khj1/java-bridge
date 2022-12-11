package bridge;

import bridge.ApplicationTest.TestNumberGenerator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeMakerTest {

    @Test
    void 다리_생성_기능_테스트() {
        TestNumberGenerator testNumberGenerator = new TestNumberGenerator(Lists.newArrayList(0, 1, 0));
        BridgeMaker bridgeMaker = new BridgeMaker(testNumberGenerator);

        List<String> bridge = bridgeMaker.makeBridge(3);

        assertThat(bridge).containsExactly("D", "U", "D");
    }
}
