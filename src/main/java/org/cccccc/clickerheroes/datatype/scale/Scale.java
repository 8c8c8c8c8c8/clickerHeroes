package org.cccccc.clickerheroes.datatype.scale;

public class Scale {
    private final double DECIMAL_UNIT = 10.0;
    private double real;
    private int exp;

    public double getReal() {
        return real;
    }

    public int getExp() {
        return exp;
    }

    public Scale(double real, int exp) {
        if (real < 0) {
            throw new AssertionError("real is less than 0");
        }
        this.real = real;
        this.exp = exp;
        scale();
    }

    public Scale(double real) {
        this(real, 0);
    }

    public void scale() {
        if (getReal() > 10) {
            roundDown();
        } else if (getReal() < 1) {
            roundUp();
        }
        expressSevenDecimal();
    }

    private void roundUp() {
        while (getReal() < 1) {
            real = getReal() * DECIMAL_UNIT;
            exp = getExp() - 1;
        }
    }

    private void roundDown() {
        while (getReal() > 10) {
            real = getReal() / DECIMAL_UNIT;
            exp = getExp() + 1;
        }
    }

    private void expressSevenDecimal() {
        // round down val to seven decimal places
        double seventhDecimal = 1e7f;
        real = Math.floor(getReal() * seventhDecimal) / seventhDecimal;
    }

    public void multiply(Scale obj) {
        this.real *= obj.real;
        this.exp *= obj.exp;
    }
}
