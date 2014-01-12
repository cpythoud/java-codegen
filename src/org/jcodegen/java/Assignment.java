package org.jcodegen.java;

/**
 * ...
 */
public class Assignment extends JavaCodeBlock<Assignment> {

    private final StringOrCode<FunctionCall> lvalue;
    private final StringOrCode<Expression> rvalue;

    private boolean embedded = false;


    public Assignment(final String lvalue, final String rvalue) {
        this(lvalue, rvalue, 0);
    }

    public Assignment(final String lvalue, final String rvalue, final int indentationLevel) {
        super("=", indentationLevel);
        this.lvalue = new StringOrCode<FunctionCall>(lvalue);
        this.rvalue = new StringOrCode<Expression>(rvalue);
    }

    public Assignment(final FunctionCall lvalue, final String rvalue) {
        this(lvalue, rvalue, 0);
    }

    public Assignment(final FunctionCall lvalue, final String rvalue, final int indentationLevel) {
        super("=", indentationLevel);
        this.lvalue = new StringOrCode<FunctionCall>(lvalue);
        this.rvalue = new StringOrCode<Expression>(rvalue);
    }

    public Assignment(final String lvalue, final Expression rvalue) {
        this(lvalue, rvalue, 0);
    }

    public Assignment(final String lvalue, final Expression rvalue, final int indentationLevel) {
        super("=", indentationLevel);
        this.lvalue = new StringOrCode<FunctionCall>(lvalue);
        this.rvalue = new StringOrCode<Expression>(rvalue);
    }

    public Assignment(final FunctionCall lvalue, final Expression rvalue) {
        this(lvalue, rvalue, 0);
    }

    public Assignment(final FunctionCall lvalue, final Expression rvalue, final int indentationLevel) {
        super("=", indentationLevel);
        this.lvalue = new StringOrCode<FunctionCall>(lvalue);
        this.rvalue = new StringOrCode<Expression>(rvalue);
    }


    @Override
    protected Assignment getThis() {
        return this;
    }

    public Assignment embedded() {
        embedded = true;
        return getThis();
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        if (!embedded)
            buf.append(getTabs());

        buf.append(lvalue.toString());
        buf.append(" = ");
        buf.append(rvalue.toString());

        if (!embedded)
            buf.append(";\n");

        return buf.toString();
    }
}
