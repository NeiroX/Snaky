import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class EndGame extends JPanel {

    private static JButton endButton = new JButton();
    private static JButton restartButton = new JButton();
    private static JLabel scoreLabel = new JLabel();
    private static JLabel recordLabel = new JLabel();
    private static Timer time = new Timer();
    private static BufferedImage image;

    static {
        try {
            image = ImageIO.read(new File("images//endGame.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something is wrong. Ask developer about problem");
        }
    }

    public EndGame(JFrame frame) {
        setSize(GlobalConstants.SCREEN_W, GlobalConstants.SCREEN_H);
        setBackground(Color.BLACK);
        setLayout(null);
        add(endButton);
        add(restartButton);
        scoreLabel.setBounds(800, 800, 100, 100);
        scoreLabel.setText(Integer.toString(GlobalConstants.recordNow));
        recordLabel.setBounds(100, 100, 100, 100);
        if (GlobalConstants.recordNow <= GlobalConstants.recordMax) {
            recordLabel.setText(Integer.toString(GlobalConstants.recordMax));
        } else {
            recordLabel.setText(Integer.toString(GlobalConstants.recordNow));
            GlobalConstants.recordMax = GlobalConstants.recordNow;
        }
        endButton.setBounds(450, 100, 100, 100);
        endButton.setText("Exit");
        restartButton.setText("Restart");
        restartButton.setBounds(450, 800, 100, 100);
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
                        changePanel(frame);
                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 300, 300, this);
    }
    private void changePanel(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.add(new GamingPanel(frame));
        frame.invalidate();
        GlobalConstants.recordNow = 0;
        frame.repaint();
    }

}

