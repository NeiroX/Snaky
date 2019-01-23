import com.sun.jdi.connect.spi.TransportService;

import javax.swing.JFrame;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.beans.


public class main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Timer time = new Timer();
        startGame startGamePanel = new startGame();

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(startGamePanel);

        time.schedule(new TimerTask() {
                          @Override
                          public void run() {

                              frame.remove(startGamePanel);
                              frame.add(new GamingPanel());
                              frame.repaint();
                          }
                      }, 5000
        );

        frame.setResizable(false);
        frame.setVisible(true);
        LinkedList<Integer> bodyCoords = new LinkedList<Integer>();
        // bodyCoords.add(0,0) adding head                  How add the point's coords?
       /* while (true) {

            if
        }


        public void appleSetCoords () {
            Random random = new Random();
            random.nextInt(20);
            int xa = random.nextInt(880) + 20;          I don't know where I need to input this for example
            int ya = random.nextInt(880) + 20;
        }
        public void movingSnake () {

        }*/

    }
}
