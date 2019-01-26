package UI;

import java.awt.*;
import javax.imageio.*;
import javax.swing.*;

import Snaky.GlobalConstants;

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

    public startGame(JFrame frame, Keyboard keyboard) {
        setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        setBackground(Color.BLACK);
        setLayout(null);
        JLabel label = new JLabel();
        label.setText("Use these button for controle the snake");
        label.setBounds(150, 150, GlobalConstants.SCREEN_W / 2, GlobalConstants.SCREEN_H / 2);
        label.setFont(new Font("TimesRoman", Font.ITALIC, 20));
        label.setForeground(Color.WHITE);
        add(label);
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                changePanel(frame, keyboard);
            }
        }, 5000);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 150, 300, this);
    }

    public void changePanel(JFrame frame, Keyboard keyboard) {
        frame.getContentPane().removeAll();
        frame.add(new GamingPanel(frame, keyboard));
        frame.invalidate();
        frame.repaint();
    }

}
