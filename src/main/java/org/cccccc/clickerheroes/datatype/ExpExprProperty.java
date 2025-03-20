package org.cccccc.clickerheroes.datatype;

import javafx.beans.property.LongPropertyBase;
import org.cccccc.clickerheroes.datatype.scale.Scale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpExprProperty extends LongPropertyBase {
    // exponent expression class
    private final int IGNORED_THRESHOLD = 7;
    private final String NAME;

    protected double real;
    protected int exp;

    public ExpExprProperty(String name) {
        this.NAME = name;
        this.real = 0.0;
        this.exp = 0;
    }

    public ExpExprProperty(String name, String value) {
        this(name, convertStringToScale(value));
    }

    public ExpExprProperty(String name, double val) {
        this(name, new Scale(val));
    }

    private ExpExprProperty(int val) {
        this("TemporaryValue", val);
    }

    private ExpExprProperty(String name, Scale scale) {
        this.NAME = name;
        this.real = scale.getReal();
        this.exp = scale.getExp();
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

    public void scaling() {
        Scale scale = new Scale(real, exp);
        this.real = scale.getReal();
        this.exp = scale.getExp();
    }

    public boolean isZero() {
        return real == 0;
    }

    public void reset() {
        this.real = 0.0f;
        this.exp = 0;
    }

    public void customMultiply(int val) {
        this.customMultiply((double) val);
    }

    public void customMultiply(double val) {
        ExpExprProperty temp = new ExpExprProperty("temp", val);
        this.customMultiply(temp);
    }

    public void customMultiply(ExpExprProperty val) {
        ExpExprProperty multiplied = customMultiply(this, val);
        this.real = multiplied.real;
        this.exp = multiplied.exp;
    }

    public static ExpExprProperty customMultiply(ExpExprProperty obj, int val) {
        double real = obj.real * val;
        int exp = obj.exp;
        Scale scale = new Scale(real, exp);
        return new ExpExprProperty("multipliedResult", scale);
    }

    public static ExpExprProperty customMultiply(ExpExprProperty obj1, ExpExprProperty obj2) {
        double real = obj1.real * obj2.real;
        int exp = obj1.exp + obj2.exp;
        Scale scale = new Scale(real, exp);
        return new ExpExprProperty("multipliedResult", scale);
    }

    public boolean isLessThan(ExpExprProperty obj) {
        if (exp < obj.exp) {
            return true;
        }
        return exp == obj.exp && real < obj.real;
    }

    public void customSubtract(ExpExprProperty obj) {
        if (this.exp < obj.exp) {
            this.real = 0.0;
            this.exp = 0;
            return;
        }
        int exp = this.exp - obj.exp;
        if (exp > IGNORED_THRESHOLD) {
            return;
        }
        double subtractedReal = this.real * Math.pow(10, exp) - obj.real;
        if (subtractedReal <= 0) {
            this.real = 0.0f;
            this.exp = 0;
            return;
        }
        Scale scale = new Scale(subtractedReal, -exp);
        this.real = scale.getReal();
        this.exp += scale.getExp();
    }

    public void customAdd(int val) {
        this.customAdd(new ExpExprProperty(val));
    }

    public void customAdd(ExpExprProperty obj) {
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
        this.real = scale.getReal();
        this.exp += scale.getExp();
    }

    public void customDivide(double val) {
        ExpExprProperty divisor = new ExpExprProperty("divisor", val);
        ExpExprProperty result = customDivide(this, divisor);
        this.real = result.real;
        this.exp = result.exp;
    }

    public static ExpExprProperty customDivide(ExpExprProperty obj1, ExpExprProperty obj2) {
        double real = obj1.real / obj2.real;
        int exp = obj1.exp - obj2.exp;
        Scale scale = new Scale(real, exp);
        return new ExpExprProperty("dividedResult", scale);
    }

    public void customPower(int val) {
        Scale powered = power(new Scale(this.real, this.exp), val);
        this.real = powered.getReal();
        this.exp = powered.getExp();
    }

    protected void set(ExpExprProperty obj) {
        this.real = obj.real;
        this.exp = obj.exp;
    }

    private Scale power(Scale powered, int val) {
        if (val == 0) {
            return new Scale(1.0);
        }
        if (val == 1) {
            return powered;
        }
        Scale intermediate = power(powered, val / 2);
        intermediate.multiply(intermediate);
        if (val % 2 == 1) {
            intermediate.multiply(powered);
        }
        intermediate.scale();
        return intermediate;
    }

    @Override
    public String toString() {
        return String.format("%.4f x %d", this.real, this.exp);
    }
}
