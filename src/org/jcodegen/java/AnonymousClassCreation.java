package org.jcodegen.java;

/**
 * ...
 */
public class AnonymousClassCreation extends Expression<AnonymousClassCreation> {

    private final String className;

    private JavaCodeBlock context = null;
    private int extraContextIndentation = 0;

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
        return setContext(context, 0);
    }

    public AnonymousClassCreation setContext(final JavaCodeBlock context, final int extraContextIndentation) {
        this.context = context;
        this.extraContextIndentation = extraContextIndentation;
        return getThis();
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        if (context != null)
            setIndentationLevel(context.getIndentationLevel() + 1 + extraContextIndentation);

        buf.append("new ");
        buf.append(className);
        buf.append("() ");

        appendContent(buf);

        //buf.append("\n");

        return buf.toString();
    }
}
