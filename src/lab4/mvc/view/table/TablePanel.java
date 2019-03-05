package lab4.mvc.view.table;

import lab4.mvc.controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private int selectedRow;

    public TablePanel() {
        setLayout(new BorderLayout());

        configureTable();

        add(table, BorderLayout.CENTER);
        add(new JScrollPane(table), BorderLayout.CENTER);

        AddRowButtonPanel addRowButtonPanel = new AddRowButtonPanel((ValuesTableModel) table.getModel(), this);
        addRowButtonPanel.setPreferredSize(new Dimension(this.getWidth(), 42));
        addRowButtonPanel.setSize(new Dimension(this.getWidth(), 42));

        add(addRowButtonPanel, BorderLayout.SOUTH);
        add(new FunctionPanel(), BorderLayout.NORTH);
    }

    private void configureTable() {
        table = new JTable(new ValuesTableModel());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Double.class, centerRenderer);

        table.setRowSelectionAllowed(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int row = table.rowAtPoint(e.getPoint());
                    table.getSelectionModel().setSelectionInterval(row, row);

                    selectedRow = row;
                }
            }
        });
    }

    public ValuesTableModel getModel() {
        return ((ValuesTableModel) table.getModel());
    }

    public int getSelectedRow() {
        return selectedRow;
    }
}
