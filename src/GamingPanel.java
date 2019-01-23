import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GamingPanel extends JPanel {
    private BufferedImage appleImage;
    private LinkedList<Integer> coordsList;
    public JLabel label;

    public GamingPanel() {
        setSize(1000, 1000);
        setBackground(Color.GREEN);
        setLayout(null);
        try {
            appleImage = ImageIO.read(new File("images//Apple.png"));
            label.setBounds(50, 50, 100, 100);
            label.setText("Record:");
            label.setForeground(Color.GRAY);
            appleSetCoords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(appleImage, 0, 0, this);
    }


}