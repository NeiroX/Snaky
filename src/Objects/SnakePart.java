package Objects;

import java.awt.*;
import Objects.Path;
import java.util.Collection;
import Snaky.GlobalConstants;

public class SnakePart extends Rectangle {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN;
    }

    public static int ID = 0;
    public int id = ID++;

    public Direction direction;

    public SnakePart(int x, int y, Direction direction) {
        super(x, y, GlobalConstants.SNAKE_W, GlobalConstants.SNAKE_W);
        this.direction = direction;
    }

    public int getMidpointX() {
        return x + width / 2;
    }

    public int getMidpointY() {
        return y + width / 2;
    }

    private SnakePart() {
    }

    public SnakePart addSnakeBody() {
        SnakePart box = new SnakePart();
        box.width = GlobalConstants.SNAKE_W;
        box.height = GlobalConstants.SNAKE_W;
        box.direction = direction;

        switch (direction) {
            case LEFT:
                box.y = y;
                box.x = x + GlobalConstants.SNAKE_W + GlobalConstants.SNAKE_SPACE;
                break;
            case RIGHT:
                box.y = y;
                box.x = x - GlobalConstants.SNAKE_W - GlobalConstants.SNAKE_SPACE;
                break;
            case DOWN:
                box.x = x;
                box.y = y - GlobalConstants.SNAKE_W - GlobalConstants.SNAKE_SPACE;
                break;
            case UP:
                box.x = x;
                box.y = y + GlobalConstants.SNAKE_W + GlobalConstants.SNAKE_SPACE;
                break;
        }
        return box;
    }


    public void move(Collection<Path> paths) {
        for (int steps = 0; steps < GlobalConstants.SNAKE_SPEED; steps++) {
            for (Path path : paths) {
                path.checkDirection(this);
            }
            move(1);
        }
    }


    public void move(int amount) {
        switch (direction) {
            case LEFT:
                x -= amount;
                break;
            case RIGHT:
                x += amount;
                break;
            case UP:
                y -= amount;
                break;
            case DOWN:
                y += amount;
                break;
        }
    }

}
