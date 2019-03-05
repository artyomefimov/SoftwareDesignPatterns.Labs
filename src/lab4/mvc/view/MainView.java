package lab4.mvc.view;

import lab4.mvc.controller.Controller;
import lab4.mvc.view.graphic.GraphPanel;
import lab4.mvc.view.table.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {
        super("MVC");
        setLayout(new BorderLayout());
        TablePanel tablePanel = new TablePanel();
        tablePanel.setPreferredSize(new Dimension(200, this.getHeight()));
        tablePanel.setSize(new Dimension(200, this.getHeight()));
        add(new TablePanel(), BorderLayout.WEST);

        GraphPanel graphPanel = new GraphPanel();
        graphPanel.setPreferredSize(new Dimension(600, this.getHeight()));
        graphPanel.setSize(new Dimension(600, this.getHeight()));

        Controller controller = Controller.getInstance();
        controller.setGraphPanel(graphPanel);
        controller.setTableModel(tablePanel.getModel());

        add(graphPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1366,768);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}