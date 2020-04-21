package org.jcodegen.java;

public class FinallyBlock extends JavaCodeBlock<FinallyBlock> {

    public FinallyBlock() {
        this(0);
    }

    public FinallyBlock(int indentationLevel) {
        super("finally", indentationLevel);
    }

    @Override
    protected FinallyBlock getThis() {
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        buf.append(" finally ");
        appendContent(buf);

        return buf.toString();
    }
}
