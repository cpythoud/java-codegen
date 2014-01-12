package org.jcodegen.java;

/**
 * ...
 */
public class AnonymousClassCreation extends Expression<AnonymousClassCreation> {

    private final String className;

    private JavaCodeBlock context = null;

    public AnonymousClassCreation(final String className) {
        this(className, 0);
    }

    public AnonymousClassCreation(final String className, final int indentationLevel) {
        super("AnonymousClass", indentationLevel);
        this.className = className;
    }


    @Override
    protected AnonymousClassCreation getThis() {
        return this;
    }

    public AnonymousClassCreation setContext(final JavaCodeBlock context) {
        this.context = context;
        return getThis();
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        if (context != null)
            setIndentationLevel(context.getIndentationLevel() + 1);

        buf.append("new ");
        buf.append(className);
        buf.append("() ");

        appendContent(buf);

        //buf.append("\n");

        return buf.toString();
    }
}
