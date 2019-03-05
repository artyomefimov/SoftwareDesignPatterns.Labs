package lab4.mvc.view.table;

import lab4.mvc.controller.Controller;
import lab4.mvc.controller.FunctionKeeper;
import lab4.mvc.model.PairXY;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

public class ValuesTableModel extends AbstractTableModel {
    private String[] columnNames = {"x", "y"};
    private Map<Integer, PairXY<Double, Double>> values = new HashMap<>();

    private Controller controller = Controller.getInstance();
    private FunctionKeeper function = FunctionKeeper.getInstance();

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    @Override
    public int getRowCount() {
        return values.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PairXY<Double, Double> row = values.get(rowIndex);
        return columnIndex == 0 ? row.x : row.y;
    }

    public void addRow() {
        PairXY<Double, Double> newPair = PairXY.create(0.0, 0.0);
        values.put(getRowCount(), newPair);
        fireTableRowsInserted(values.size(), values.size());
        controller.addYAndDraw(getRowCount() - 1, newPair);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 1)
            fireTableCellUpdated(rowIndex, columnIndex);
        else {
            countXY(aValue, rowIndex, columnIndex);
        }
    }

    private void countXY(Object aValue, int rowIndex, int columnIndex) {
        double x = ((Double) aValue);
        double y = function.countY(x);

        PairXY<Double, Double> newPair = PairXY.create(x, y);
        values.put(rowIndex, newPair);
        controller.addYAndDraw(rowIndex, newPair);

        fireTableCellUpdated(rowIndex, columnIndex);
        setValueAt(y, rowIndex, 1);
    }

    public void setValues(Map<Integer, PairXY<Double, Double>> values) {
        this.values = values;
        fireTableDataChanged();
    }
}
