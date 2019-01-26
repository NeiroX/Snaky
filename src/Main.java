import javax.swing.JFrame;

import UI.startGame;
import UI.Keyboard;


public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Keyboard keyboard = new Keyboard();
        startGame start = new startGame(frame, keyboard);

        frame.addKeyListener(keyboard);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, Snaky.GlobalConstants.SCREEN_W, Snaky.GlobalConstants.SCREEN_H);
        frame.add(start);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
