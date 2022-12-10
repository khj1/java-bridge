package model;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private final List<Moving> bridge;

    private Bridge(List<Moving> bridge) {
        this.bridge = bridge;
    }

    public static Bridge from(List<String> bridge) {
        return new Bridge(convertToMoving(bridge));
    }

    private static List<Moving> convertToMoving(List<String> bridge) {
        return bridge.stream()
                .map(Moving::from)
                .collect(Collectors.toList());
    }

    public Moving getMovingAt(int position) {
        return bridge.get(position);
    }

    public int size() {
        return bridge.size();
    }
}
