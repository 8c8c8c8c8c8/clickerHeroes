package org.cccccc.clickerheroes.datatype;

import javafx.beans.property.LongPropertyBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpExprProperty extends LongPropertyBase {
    // exponent expression class
    private final int IGNORED_THRESHOLD = 7;
    private final String NAME;
    private double real;
    private int exp;

    public ExpExprProperty(String name) {
        this.NAME = name;
        this.real = 0.0f;
        this.exp = 0;
    }
    
    public ExpExprProperty(String name, String value) {
        this(name, convertStringToScale(value));
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

    private ExpExprProperty(Scale scale) {
        this("TemporaryValue", scale);
    }

    private ExpExprProperty(String name, Scale scale) {
        this.NAME = name;
        this.real = scale.real;
        this.exp = scale.exp;
    }

    @Override
    public Object getBean() {
        return this.toString();
    }

    @Override
    public String getName() {
        return this.NAME;
    }
    
    private static Scale convertStringToScale(String value) {
        // ex) value : "3.1415926e79"
        String pattern = "(\\d[.\\d]*)e(\\d+)";
        Pattern ptn = Pattern.compile(pattern);
        Matcher result = ptn.matcher(value);
        if (result.find()) {
            String real = result.group(1);
            String exp = result.group(2);
            return new Scale(Double.parseDouble(real), Integer.parseInt(exp));
        } else {
            throw new IllegalArgumentException("incorrect value");
        }
    }

    private static ExpExprProperty convert(int val) {
        return convert("TemporaryValue", (double) val);
    }

    private static ExpExprProperty convert(String name, double val) {
        return new ExpExprProperty(name, new Scale(val));
    }

    public boolean isZero() {
        return real == 0;
    }

    public void reset() {
        this.real = 0.0f;
        this.exp = 0;
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
        double subtractedReal = this.real *  Math.pow(10, exp) - obj.real;
        if (subtractedReal <= 0) {
            this.real = 0.0f;
            this.exp = 0;
            return;
        }
        Scale scale = new Scale(subtractedReal, -exp);
        this.real = scale.real;
        this.exp += scale.exp;
    }

    public void add(ExpExprProperty obj) {
        if (this.exp < obj.exp) {
            // switch
            double tempReal = this.real;
            int tempExp = this.exp;
            this.real = obj.real;
            this.exp = obj.exp;
            obj.real = tempReal;
            obj.exp = tempExp;
        }
        int exp = this.exp - obj.exp;
        if (exp > IGNORED_THRESHOLD) {
            return;
        }
        double addedReal = this.real * Math.pow(10, exp) + obj.real;
        Scale scale = new Scale(addedReal, -exp);
        this.real = scale.real;
        this.exp += scale.exp;
    }

    public void power(int val) {
        Scale powered = _power(new Scale(this.real, this.exp), val);
        this.real = powered.real;
        this.exp = powered.exp;
    }

    private Scale _power(Scale powered, int val) {
        if (val == 0) {
            return new Scale(1.0f);
        }
        if (val == 1) {
            return powered;
        }
        Scale intermediate = _power(powered, val / 2);
        double real = intermediate.real * intermediate.real;
        int exp = intermediate.exp * 2;
        if (val % 2 == 1) {
            real *= powered.real;
            exp += powered.exp;
        }
        return new Scale(real, exp);
    }

    @Override
    public String toString() {
        return String.format("%.4f x %d", this.real, this.exp);
    }

    private static class Scale {
        private final double DECIMAL_UNIT = 10.0f;
        private double real;
        private int exp;

        Scale() {
            this.real = 0.0f;
            this.exp = 0;
        }
        Scale(double real, int exp) {
            if (real < 0) {
                throw new AssertionError("real is less than 0");
            }
            this.real = real;
            this.exp = exp;
            scale();
        }

        Scale(double real) {
            this(real, 0);
        }

        private void scale() {
            if (real > 10) {
                roundDown();
            } else if (real < 1) {
                roundUp();
            }
            expressSevenDecimal();
        }

        private void roundUp() {
            while (real < 1) {
                real *= DECIMAL_UNIT;
                exp--;
            }
        }

        private void roundDown() {
            while (real > 10) {
                real /= DECIMAL_UNIT;
                exp++;
            }
        }

        private void expressSevenDecimal() {
            // round down val to seven decimal places
            float seventhDecimal = 1e7f;
            real = Math.floor(real * seventhDecimal) / seventhDecimal;
        }
    }
}
