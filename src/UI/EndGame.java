package UI;

import javax.swing.*;

import Snaky.GlobalConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class EndGame extends JPanel {

    private static JButton endButton = new JButton();
    private static JButton restartButton = new JButton();
    private static JLabel scoreLabel = new JLabel();
    private static JLabel recordLabel = new JLabel();
    private static Timer time = new Timer();

    public EndGame(JFrame frame, Keyboard keyboard) {
        setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        setBackground(Color.BLACK);
        setLayout(null);

        add(endButton);
        add(restartButton);

        scoreLabel.setBounds(GlobalConstants.SCREEN_W / 2 - 100, GlobalConstants.SCREEN_H - 110, 100, 100);
        scoreLabel.setText("SCORE: " + GlobalConstants.recordNow);
        scoreLabel.setForeground(Color.WHITE);

        recordLabel.setForeground(Color.WHITE);
        recordLabel.setBounds(500, GlobalConstants.SCREEN_H - 110, 100, 100);
        if (GlobalConstants.recordNow <= GlobalConstants.recordMax) {
            recordLabel.setText("RECORD: " + GlobalConstants.recordMax);
        } else {
            recordLabel.setText(Integer.toString(GlobalConstants.recordNow));
            GlobalConstants.recordMax = GlobalConstants.recordNow;
        }
        add(recordLabel);
        add(scoreLabel);

        endButton.setBounds(GlobalConstants.SCREEN_W / 2 - 100, 100, 100, 100);
        endButton.setText("Exit");

        restartButton.setText("Restart");
        restartButton.setBounds(300, 600, 100, 100);

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        changePanel(frame, keyboard);
                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, 100));
        g.drawString("Game over", 150, 400);
    }

    private void changePanel(JFrame frame, Keyboard keyboard) {
        frame.getContentPane().removeAll();
        frame.add(new GamingPanel(frame, keyboard));
        frame.invalidate();
        GlobalConstants.recordNow = 0;
        frame.repaint();
    }

}

