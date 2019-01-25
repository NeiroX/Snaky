

import javax.swing.JFrame;



public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        frame.add(new startGame(frame));
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
