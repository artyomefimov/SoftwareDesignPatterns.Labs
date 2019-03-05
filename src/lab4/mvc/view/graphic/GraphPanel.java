package lab4.mvc.view.graphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphPanel extends JPanel {
    private static final String FILENAME = "src/lab4/mvc/view/graphic/graphic.png";
    private Image graphic;

    public GraphPanel() { }

    public void setGraphicAndRedraw() throws IOException {
        graphic = ImageIO.read(new File(FILENAME));
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(graphic, 0,0, this);
    }
}
