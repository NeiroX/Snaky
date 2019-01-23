
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.TimerTask;

public class GamingPanel extends JPanel {

    private final static Random RAND = new Random();
    private static BufferedImage APPLE_IMAGE ;

    static {
        try {
            APPLE_IMAGE = ImageIO.read(new File("images//Apple.png"));
        } catch (IOException ex) {
        }
    }

    private Point applePosition;

    private final LinkedList<Rectangle> snakeBodies = new LinkedList<>();

    private JLabel label = new JLabel();
    java.util.Timer time = new java.util.Timer();

    public GamingPanel() {
        snakeBodies.add(new Rectangle(GlobalConstants.SCREEN_W / 2, GlobalConstants.SCREEN_H / 2,
                GlobalConstants.DOT_SIZE, GlobalConstants.DOT_SIZE));
        
        setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        setBackground(Color.GREEN);
        setLayout(null);

        label.setBounds(50, 50, 100, 100);
        label.setText("Record:");
        label.setForeground(Color.GRAY);
        appleSetCoords();
        
        time.schedule(new TimerTask() {
                          @Override
                          public void run() {
                             checkCollision();
                          }
                      }, 500, 34);
    }

    private void appleSetCoords() {
        int xa = RAND.nextInt(GlobalConstants.SCREEN_W - 100) + 20;
        int ya = RAND.nextInt(GlobalConstants.SCREEN_H - 100) + 20;
        applePosition = new Point(xa, ya);
    }
    
    
    protected void checkCollision(){
        if(snakeBodies.getFirst().contains(applePosition)){
            //I ate the apple
            Rectangle last = snakeBodies.getLast();
            //snakeBodies.add(new Rectangle(last.x , GlobalConstants.SCREEN_H / 2,
             //s   GlobalConstants.DOT_SIZE, GlobalConstants.DOT_SIZE));
            appleSetCoords();
        }
        repaint();
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
