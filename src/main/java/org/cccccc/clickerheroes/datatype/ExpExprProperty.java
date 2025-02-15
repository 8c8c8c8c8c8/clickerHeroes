package org.cccccc.clickerheroes.datatype;

import javafx.beans.property.LongPropertyBase;

public class ExpExprProperty extends LongPropertyBase {
    // exponent expression class
    private final int IGNORED_THRESHOLD = 7;
    private final String NAME;
    private double real = 0.0f;
    private int exp = 0;

    public ExpExprProperty(String name) {
        this.NAME = name;
    }

    public ExpExprProperty(int val) {
        this("TemporaryValue", val);
    }

    public ExpExprProperty(String name, int val) {
        this(name, convert(val));
    }

    private ExpExprProperty(String name, ExpExprProperty obj) {
        this.NAME = name;
        this.real = obj.real;
        this.exp = obj.exp;
    }

    private ExpExprProperty(String name, double real, int exp) {
        this.NAME = name;
        this.real = real;
        this.exp = exp;
    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    private static ExpExprProperty convert(int val) {
        return convert("TemporaryValue", (double) val);
    }

    private static ExpExprProperty convert(String name, double val) {
        int exp = 0;
        double decimalUnit = 10.0f;
        double decimal = val;
        while (decimal >= decimalUnit) {
            decimal /= decimalUnit;
            exp++;
        }
        decimal = floor(decimal);
        return new ExpExprProperty(name, decimal, exp);
    }

    private static double floor(double val) {
        // round down val to seven decimal places
        float seventhDecimal = 1e7f;
        return Math.floor(val * seventhDecimal) / seventhDecimal;
    }

    public void multiply(ExpExprProperty val) {
        ExpExprProperty multiplied = multiply(this, val);
        this.real = multiplied.real;
        this.exp = multiplied.exp;
    }

    public static ExpExprProperty multiply(ExpExprProperty obj1, ExpExprProperty obj2) {
        double real = obj1.real * obj2.real;
        int exp = obj1.exp + obj2.exp;
        ExpExprProperty multiplied = convert("multiplied", real);
        multiplied.exp += exp;
        return new ExpExprProperty(obj1.getName(), multiplied);
    }

    public void subtract(ExpExprProperty obj) {
        if (this.exp < obj.exp) {
            this.real = 0.0f;
            this.exp = 0;
            return;
        }
        int exp = this.exp - obj.exp;
        if (exp > IGNORED_THRESHOLD) {
            return;
        }
        double real = this.real *  Math.pow(10, exp);
        double subtractedReal = real - obj.real;
        if (subtractedReal < 0) {
            this.real = 0.0f;
            this.exp = 0;
            return;
        }
        subtractedReal /= Math.pow(10, exp);
        while (subtractedReal < 1) {
            subtractedReal *= 10.0f;
            this.exp--;
        }
        this.real = floor(subtractedReal);
    }

    public void add(ExpExprProperty obj) {
        int exp = this.exp - obj.exp;
        if (exp > IGNORED_THRESHOLD) {
            return;
        }
        double real = this.real * Math.pow(10, exp);
        // todo
    }

    public void power(int val) {
        // todo
    }
}
