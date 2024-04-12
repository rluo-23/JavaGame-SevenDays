package arc.main.java.ui;
import javax.swing.*;
import java.awt.*; 
class ImageButton extends JButton {
    private Image image;

    public ImageButton(String imagePath) {
        this.image = new ImageIcon(imagePath).getImage();
        setContentAreaFilled(false); 
        setBorderPainted(false); 
        setFocusPainted(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}


