package arc.main.java.ui;
import javax.swing.*;
import java.awt.*; 
class ImageButton extends JButton {
    private Image image;

    public ImageButton(String imagePath) {
        this.image = new ImageIcon(imagePath).getImage();
        setContentAreaFilled(false); // 不填充内容区域
        setBorderPainted(false); // 不绘制边框
        setFocusPainted(false); // 不绘制焦点状态
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // 在按钮上绘制图片，调整图片大小以填满按钮
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}


