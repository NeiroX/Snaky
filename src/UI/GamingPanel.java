package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

import Objects.Path;
import Objects.SnakePart;
import Objects.SnakePart.*;
import Snaky.GlobalConstants;


public class GamingPanel extends JPanel {

    private final static Random RAND = new Random();
    private static BufferedImage APPLE_IMAGE;

    static {
        try {
            APPLE_IMAGE = ImageIO.read(new File("images//Apple.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Something is wrong. Ask developer about problem");
        }
    }

    private Rectangle applePosition;
    private int xs = GlobalConstants.SCREEN_W / 2;
    private int ys = GlobalConstants.SCREEN_H / 2;
    private final LinkedList<SnakePart> snakeBodies = new LinkedList<>();
    private final Map<Integer, Path> pathsMap = new HashMap<>();
    private Direction direction = Direction.LEFT;
    private final Timer timer;
    private boolean End = false;
    private final Keyboard keyboard;

    public GamingPanel(JFrame frame, Keyboard keyboard) {
        timer = new Timer(34, (ActionEvent e) -> updateGame(frame));
        timer.setInitialDelay(500);
        this.keyboard = keyboard;
        SnakePart part = new SnakePart(GlobalConstants.SCREEN_W / 2, GlobalConstants.SCREEN_H / 2, Direction.LEFT);
        snakeBodies.add(part);
        setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        setBackground(Color.WHITE);
        setFocusable(true);
        setLayout(null);

        JLabel labelRecord = new JLabel();
        labelRecord.setBounds(50, GlobalConstants.SCREEN_H - 50, 100, 100);
        labelRecord.setText("Record: " + GlobalConstants.recordMax);
        labelRecord.setForeground(Color.GRAY);
        JLabel labelScore = new JLabel();
        labelScore.setBounds(GlobalConstants.SCREEN_W - 110, GlobalConstants.SCREEN_H - 50, 100, 100);
        labelScore.setText("Score: " + GlobalConstants.recordNow);
        labelScore.setForeground(Color.PINK);
        add(labelRecord);
        add(labelScore);

        appleSetCoords();
        timer.start();
    }

    private void appleSetCoords() {
        int xa = RAND.nextInt(GlobalConstants.SCREEN_W - 100) + 20;
        int ya = RAND.nextInt(GlobalConstants.SCREEN_H - 100) + 20;
        applePosition = new Rectangle(xa, ya, GlobalConstants.APPLE_SIZE, GlobalConstants.APPLE_SIZE);
    }

    List<Path> donePath = new ArrayList<>();

    private void cleanUp() {
        for (Path p : pathsMap.values()) {
            if (p.touched.size() == snakeBodies.size()) {
                donePath.add(p);
            }
        }
        for (Path p : donePath) {
            pathsMap.remove(p.id);
        }
        donePath.clear();
    }

    private boolean isNewDirection() {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            return keyboard.direction == Direction.UP || keyboard.direction == Direction.DOWN;
        } else {
            return keyboard.direction == Direction.LEFT || keyboard.direction == Direction.RIGHT;
        }
    }

    private void checkCollision() {
        SnakePart head = snakeBodies.get(0);
        if (head.x < 0 || (head.x + head.width) > GlobalConstants.SCREEN_W) {
            End = true;
        } else if (head.y < 0 || (head.y + head.height) > GlobalConstants.SCREEN_H) {
            End = true;
        } else {
            for (SnakePart part : snakeBodies) {
                if (head.id == part.id) {
                    continue;
                }
                if (head.intersects(part) && (part.id - head.id) > 2) {
                    End = true;
                    break;
                }
            }
        }
    }

    private void updateGame(JFrame frame) {
        if (End) {
            changePanel(frame, keyboard);
            End = false;
        }
        SnakePart head = snakeBodies.get(0);

        if (head.intersects(applePosition)) {
            SnakePart oldTail = snakeBodies.get(snakeBodies.size() - 1);

            SnakePart newTail = oldTail.addSnakeBody();

            snakeBodies.add(newTail);
            appleSetCoords();
            GlobalConstants.SNAKE_SPEED += GlobalConstants.SNAKE_SPEED_INCREASE;
            GlobalConstants.recordNow++;
        }

        Collection<Path> paths = pathsMap.values();
        for (SnakePart part : snakeBodies) {
            part.move(paths);
        }

        if (isNewDirection()) {
            direction = keyboard.direction;
            Path p = new Path(head, direction);
            pathsMap.put(p.id, p);
        }
        cleanUp();
        checkCollision();
        repaint();
    }

    private void changePanel(JFrame frame, Keyboard keyboard) {
        frame.getContentPane().removeAll();
        frame.add(new EndGame(frame, keyboard));
        frame.invalidate();
        frame.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(APPLE_IMAGE, applePosition.x, applePosition.y, this);
        g.setColor(Color.BLACK);
        for (SnakePart snake : snakeBodies) {
            g.fillRect(snake.x, snake.y, snake.width, snake.height);
        }
    }

}
