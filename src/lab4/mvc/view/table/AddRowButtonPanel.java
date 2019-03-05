package lab4.mvc.view.table;

import lab4.mvc.controller.Controller;

import javax.swing.*;

public class AddRowButtonPanel extends JPanel {
    private JButton addRowButton = new JButton("Add row");
    private JButton deleteRowButton = new JButton("Delete row");
    private ValuesTableModel model;
    private TablePanel tablePanel;

    public AddRowButtonPanel(ValuesTableModel model, TablePanel tablePanel) {
        this.model = model;
        this.tablePanel = tablePanel;

        addRowButton.addActionListener((listener) -> this.model.addRow());

        deleteRowButton.addActionListener((listener) -> Controller.getInstance().deleteRows(tablePanel.getSelectedRow()));

        add(addRowButton);
        add(deleteRowButton);
    }
}
