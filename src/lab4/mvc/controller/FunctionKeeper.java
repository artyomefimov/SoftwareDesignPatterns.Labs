package lab4.mvc.controller;

public class FunctionKeeper {
    private static FunctionKeeper instance;
    private double coefficient = 1.0;
    private double power = 2.0;

    private FunctionKeeper() {}

    public static FunctionKeeper getInstance() {
        if (instance == null)
            instance = new FunctionKeeper();
        return instance;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double countY(double x) {
        return coefficient * Math.pow(x, power);
    }

    public String getFunction() {
        return "y = " + coefficient + "x^(" + power + ")";
    }
}
