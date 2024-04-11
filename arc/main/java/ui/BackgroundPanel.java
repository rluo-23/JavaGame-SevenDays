package arc.main.java.ui;
import javax.swing.*;
import java.awt.*; 

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // 使用图片路径作为构造函数参数
    public BackgroundPanel(String imagePath) {
        super(); 
        backgroundImage = new ImageIcon(imagePath).getImage();
        setOpaque(false); // 使得组件透明，以便能看到背景图片
    }

    // 重写 paintComponent 来绘制背景图片
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制图片以填充整个面板
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
