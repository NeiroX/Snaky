

import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Timer time = new Timer();
        startGame startGamePanel = new startGame();

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
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
    }
}
