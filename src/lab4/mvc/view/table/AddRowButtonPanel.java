package lab4.mvc.view.table;

import javax.swing.*;

public class AddRowButtonPanel extends JPanel {
    private JButton addRowButton = new JButton("Add row");
    private ValuesTableModel model;

    public AddRowButtonPanel(ValuesTableModel model) {
        this.model = model;

        addRowButton.addActionListener((listener) -> this.model.addRow());

        add(addRowButton);
    }
}
