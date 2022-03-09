package org.jcodegen.java;

/**
 * ...
 */
public abstract class Expression<T extends Expression<T>> extends CallWithArgs<T> {

    private boolean embedded = true;


    public Expression(final String keyword, final int indentationLevel) {
        super(keyword, indentationLevel);
    }


    public T byItself() {
        embedded = false;
        return getThis();
    }


    protected void startExpression(final StringBuilder buf) {
        if (!embedded)
            buf.append(getTabs());
    }

    protected void endExpression(final StringBuilder buf) {
        if (!embedded)
            buf.append(";\n");
    }
}
