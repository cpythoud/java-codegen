package org.jcodegen.java;

/**
 * ...
 */
public class FunctionCall extends Expression<FunctionCall> {

    private final String function;
    private final StringOrCode<Expression> object;


    public FunctionCall(final String function) {
        this(function, 0);
    }

    public FunctionCall(final String function, final int indentationLevel) {
        super("FunctionCall", indentationLevel);
        this.function = function;
        this.object = null;
    }

    public FunctionCall(final String function, final String object) {
        this(function, object, 0);
    }

    public FunctionCall(final String function, final String object, final int indentationLevel) {
        super("FunctionCall", indentationLevel);
        this.function = function;
        this.object = new StringOrCode<Expression>(object);
    }

    public FunctionCall(final String function, final Expression object) {
        this(function, object, 0);
    }

    public FunctionCall(final String function, final Expression object, final int indentationLevel) {
        super("FunctionCall", indentationLevel);
        this.function = function;
        this.object = new StringOrCode<Expression>(object);
    }


    @Override
    protected FunctionCall getThis() {
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        startExpression(buf);

        if (object != null) {
            buf.append(object);
            buf.append(".");
        }
        buf.append(function);
        appendArguments(buf);

        endExpression(buf);

        return buf.toString();
    }
}
