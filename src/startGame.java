

import java.awt.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;
import java.io.IOException;
import java.util.Timer;


public class startGame extends JPanel {
    private static BufferedImage image;
    private static Timer time = new Timer();

    static {
        try {
            image = ImageIO.read(new File("images//strlk.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Image is not found");
        }
    }

    public startGame(JFrame frame) {
        setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        setBackground(Color.BLACK);
        setLayout(null);
        JLabel label = new JLabel();
        label.setText("Use these button for controle the snake");
        label.setBounds(150, 150, 1000, 200);
        label.setFont(new Font("TimesRoman", Font.ITALIC, 50));
        label.setForeground(Color.WHITE);
        add(label);
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                changePanel(frame);
            }
        }, 5000);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 300, 300, this);
    }
    public void changePanel(JFrame frame){
        frame.getContentPane().removeAll();
        frame.add(new GamingPanel(frame));
        frame.invalidate();
        frame.repaint();
    }

}
