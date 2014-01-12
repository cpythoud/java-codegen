package org.jcodegen.java;

/**
 * ...
 */
public class ReturnStatement extends JavaCodeBlock<ReturnStatement> {

    private final StringOrCode<JavaCodeBlock> returnedVal;


    public ReturnStatement() {
        super("return", 0);
        returnedVal = null;
    }

    public ReturnStatement(final String returnedVal) {
        this(returnedVal, 0);
    }

    public ReturnStatement(final JavaCodeBlock returnedVal) {
        this(returnedVal, 0);
    }

    public ReturnStatement(final String returnedVal, final int indentationLevel) {
        super("return", indentationLevel);
        this.returnedVal = new StringOrCode<JavaCodeBlock>(returnedVal);
    }

    public ReturnStatement(final JavaCodeBlock returnedVal, final int indentationLevel) {
        super("return", indentationLevel);
        this.returnedVal = new StringOrCode<JavaCodeBlock>(returnedVal);
    }


    @Override
    protected ReturnStatement getThis() {
        return this;
    }


    @Override
    public String toString() {
        if (returnedVal == null)
            return getTabs() + "return;\n";

        final StringBuilder buf = new StringBuilder();

        buf.append(getTabs());
        buf.append("return ");
        buf.append(returnedVal.toString());
        buf.append(";\n");

        return buf.toString();
    }


    //public static final ReturnStatement RETURN = new ReturnStatement();
}
