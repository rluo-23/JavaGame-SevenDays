package arc.main.java.ui;
import javax.swing.*;
import java.awt.*; 

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        super(); 
        backgroundImage = new ImageIcon(imagePath).getImage();
        setOpaque(false); // Set JPanel to be transparent
    }

    // Rewrite the paintComponent method to draw the image to fill the entire panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
