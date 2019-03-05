package lab4.mvc.controller;

import lab4.mvc.model.PairXY;
import lab4.mvc.view.graphic.GraphPanel;
import lab4.mvc.view.table.ValuesTableModel;

import javax.swing.*;
import java.util.*;

public class Controller {
    private static Controller instance;

    private Map<Integer, PairXY<Double, Double>> pairs;
    private Comparator<PairXY<Double, Double>> comparator = Comparator.comparingDouble(o -> o.x);

    private FunctionKeeper function = FunctionKeeper.getInstance();

    private GraphPanel graphPanel;
    private ValuesTableModel model;

    private Controller() {
        pairs = new HashMap<>();
    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public void getNewFunctionParametersFrom(JTextField coeffField, JTextField powerField) {
        double coeff, power;
        try {
            coeff = getDoubleValueFromField(coeffField, false);
            power = getDoubleValueFromField(powerField, true);
            function.setCoefficient(coeff);
            function.setPower(power);
            JOptionPane.showMessageDialog(null,
                    function.getFunction(), "New function was chosen",
                    JOptionPane.INFORMATION_MESSAGE);
            redrawWithNewFunction();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Incorrect values", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double getDoubleValueFromField(JTextField field, boolean isPower) throws NumberFormatException {
        double value;
        String text = field.getText();
        if (text.isEmpty()) {
            if (isPower) {
                value = 2.0;
                field.setText("2");
            } else {
                value = 1.0;
            }
            return value;
        }
        try {
            value = Double.parseDouble(text);
//            if (isPower && value < 2.0) {
//                throw new NumberFormatException("Function should be quadratic!");
//            }
            return value;
        } catch (NumberFormatException e) {
            if (isPower)
                field.setText("2");
            else
                field.setText("");
            throw new NumberFormatException("Incorrect value: " + text);
        }
    }

    private void redrawWithNewFunction() {
        if (pairs.isEmpty())
            return;
        PairXY<Double, Double> pair;
        for (Map.Entry<Integer, PairXY<Double, Double>> entry : pairs.entrySet()) {
            pair = entry.getValue();
            pair.y = function.countY(pair.x);
        }

        redraw();
        model.setValues(pairs);
    }

    public void addYAndDraw(int row, PairXY<Double, Double> pair) {
        pairs.put(row, pair);
        redraw();
    }

    private void redraw() {
        try {
            drawNewGraphic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawNewGraphic() throws Exception {
        String argsX = buildArgument(true);
        String argsY = buildArgument(false);
        String commonArg = argsX + " " + argsY;

        runPythonScript(commonArg);
        graphPanel.setGraphicAndRedraw();
    }

    private String buildArgument(boolean isX) {
        StringBuilder builder = new StringBuilder();
        List<PairXY<Double, Double>> list = new ArrayList<>(pairs.values());

        list.sort(comparator);
        for (PairXY<Double, Double> pair : list) {
            if (isX)
                builder.append(pair.x);
            else
                builder.append(pair.y);
            builder.append(',');
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

    private void runPythonScript(String args) throws Exception {
        String pathToPythonWithInstalledMatplotlibAndNumpy = "C:\\ProgramFiles\\Python36\\python.exe ";
        String pathToScriptThatDrawsGraphic = "C:\\Univer\\Patterns\\Labs\\Labs\\src\\lab4\\mvc\\view\\graphic\\drawgraphic.py ";

        Process p = Runtime.getRuntime().exec(pathToPythonWithInstalledMatplotlibAndNumpy + pathToScriptThatDrawsGraphic + args);
        p.waitFor();
        p.destroy();
    }

    public void setGraphPanel(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    public void setTableModel(ValuesTableModel model) {
        this.model = model;
    }
}
