package bridge.repository;

import bridge.model.MovingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovingHistory {

    private final List<MovingResult> results = new ArrayList<>();

    public void save(MovingResult result) {
        results.add(result);
    }

    public MovingHistory clear() {
        return new MovingHistory();
    }

    public List<MovingResult> get() {
        return Collections.unmodifiableList(results);
    }
}
