package lab4.mvc.view.table;

import lab4.mvc.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class FunctionPanel extends JPanel {
    private JLabel yLabel = new JLabel("y= ");
    private JTextField coeffField = new JTextField();
    private JLabel xLabel = new JLabel("x^");
    private JTextField powerField = new JTextField("2");
    private JButton chooseFunctionButton = new JButton("Choose function");

    private Controller controller = Controller.getInstance();

    public FunctionPanel() {
        setLayout(new GridLayout(1, 5));
        add(yLabel);
        add(coeffField);
        add(xLabel);
        add(powerField);
        add(chooseFunctionButton);

        chooseFunctionButton.addActionListener((listener) -> {
            controller.getNewFunctionParametersFrom(coeffField, powerField);
        });
    }
}
