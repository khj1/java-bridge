package bridge;

import bridge.model.Moving;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        return Stream.generate(bridgeNumberGenerator::generate)
                .map(Moving::toDirection)
                .limit(size)
                .collect(Collectors.toList());
    }
}
