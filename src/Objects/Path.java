package Objects;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import Objects.SnakePart.Direction;

public class Path extends Point {
    private static int ID = 0;
    public int id = ID++;

    public Direction direction;

    public Set<Integer> touched = new HashSet<>();

    public Path(SnakePart box, SnakePart.Direction direction) {
        this.direction = direction;
        x = box.x + box.width / 2;
        y = box.y + box.height / 2;
    }

    public void checkDirection(SnakePart snake) {
        if (touched.contains(snake.id)) {
            return;
        }
        if (x == snake.getMidpointX() && y == snake.getMidpointY()) {
            snake.direction = direction;
            touched.add(snake.id);
        }
    }
}
