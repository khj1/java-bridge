package bridge.repository;

import bridge.model.MovingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovingHistory {

    private static final List<MovingResult> results = new ArrayList<>();

    public static void save(MovingResult result) {
        results.add(result);
    }

    public static void clear() {
        results.clear();
    }

    public static List<MovingResult> get() {
        return Collections.unmodifiableList(results);
    }
}
