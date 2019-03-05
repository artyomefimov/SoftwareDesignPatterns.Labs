package lab4.mvc.view.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TablePanel extends JPanel {
    private JTable table;
    public TablePanel() {
        setLayout(new BorderLayout());

        table = new JTable(new ValuesTableModel());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Double.class, centerRenderer);

        add(table, BorderLayout.CENTER);
        add(new JScrollPane(table), BorderLayout.CENTER);

        AddRowButtonPanel addRowButtonPanel = new AddRowButtonPanel((ValuesTableModel) table.getModel());
        addRowButtonPanel.setPreferredSize(new Dimension(this.getWidth(), 42));
        addRowButtonPanel.setSize(new Dimension(this.getWidth(), 42));

        add(addRowButtonPanel, BorderLayout.SOUTH);
        add(new FunctionPanel(), BorderLayout.NORTH);
    }

    public ValuesTableModel getModel() {
        return ((ValuesTableModel) table.getModel());
    }
}
