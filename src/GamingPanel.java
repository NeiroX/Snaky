import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.TimerTask;

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

    private Point applePosition;
    private String torward;
    private int xs = GlobalConstants.SCREEN_W / 2;
    private int ys = GlobalConstants.SCREEN_H / 2;
    private final LinkedList<Rectangle> snakeBodies = new LinkedList<>();

    public GamingPanel(JFrame frame) {
        snakeBodies.add(new Rectangle(GlobalConstants.SCREEN_W / 2, GlobalConstants.SCREEN_H / 2,
                GlobalConstants.DOT_SIZE, GlobalConstants.DOT_SIZE));
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                setTorward(e.getKeyCode());
            }
        });
        setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        setBackground(new Color(0, 153, 0));
        setFocusable(true);
        setLayout(null);

        JLabel labelRecord = new JLabel();
        labelRecord.setBounds(50, 50, 100, 100);
        labelRecord.setText("Record: " + GlobalConstants.recordMax);
        labelRecord.setForeground(Color.GRAY);
        JLabel labelScore = new JLabel();
        labelScore.setBounds(900, 50, 100, 100);
        labelScore.setText("Score: " + GlobalConstants.recordNow);
        labelScore.setForeground(Color.PINK);
        appleSetCoords();

        java.util.Timer time = new java.util.Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                checkCollision(frame);
            }
        }, 500, 34);
    }

    private void appleSetCoords() {
        int xa = RAND.nextInt(GlobalConstants.SCREEN_W - 100) + 20;
        int ya = RAND.nextInt(GlobalConstants.SCREEN_H - 100) + 20;
        applePosition = new Point(xa, ya);
    }


    private void checkCollision(JFrame frame) {
        if (snakeBodies.getFirst().contains(applePosition)) {
            //I ate the apple
            Rectangle last = snakeBodies.getLast();
            snakeBodies.add(new Rectangle(last.x, GlobalConstants.SCREEN_H / 2,
                    GlobalConstants.DOT_SIZE, GlobalConstants.DOT_SIZE));
            appleSetCoords();
            GlobalConstants.recordNow++;
        }
        if (xs == 0 || xs == 1000 || ys == 0 || ys == 1000) {
            changePanel(frame);
        }
        Rectangle head = snakeBodies.getFirst();

        for (Rectangle body : snakeBodies) {
            if (head == body)
                continue;
            if (head.contains(body)) {
                changePanel(frame);
            }
        }
        Compare(torward);
        repaint();
    }

    private void Compare(String torward) {
        if (torward.equals("Up")) {
            ys -= 20;
        } else if (torward.equals("Down")) {
            ys += 20;
        } else if (torward.equals("Right")) {
            xs += 20;
        } else if (torward.equals("Left")) {
            xs -= 20;
        }
        snakeBodies.getFirst().x = xs;
        snakeBodies.getFirst().y = ys;
    }

    private void setTorward(int Number) {
        if (Number == 38) {
            torward = "Up";
        } else if (Number == 40) {
            torward = "Down";
        } else if (Number == 37) {
            torward = "Left";
        } else if (Number == 39) {
            torward = "Right";
        }
    }

    private void changePanel(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.add(new EndGame(frame));
        frame.invalidate();
        frame.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(APPLE_IMAGE, applePosition.x, applePosition.y, this);
        for (Rectangle snake : snakeBodies) {
            g.fillRect(snake.x, snake.y, snake.width, snake.height);
        }
    }

}
