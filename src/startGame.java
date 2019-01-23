import java.awt.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;


public class startGame extends JPanel {
    private BufferedImage image;

    public startGame() {
        setSize(1000, 1000);
        setBackground(Color.BLACK);
        setLayout(null);
        try {
            image = ImageIO.read(new File("images//strlk.png"));
            JLabel label = new JLabel();
            label.setText("Use these button for controle the snake");
            label.setBounds(150, 150, 1000, 200);
            label.setFont(new Font("TimesRoman", Font.ITALIC, 50));
            label.setForeground(Color.WHITE);
            add(label);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Image is not found");
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 300, 300, this);
    }


}
