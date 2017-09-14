package org.jcodegen.java;

/**
 * ...
 */
public class FunctionArgument {
    private final String name;
    private final String type;
    private final boolean isFinal;

    public FunctionArgument(final String type, final String name) {
        this(type, name, true);
    }

    public FunctionArgument(final String type, final String name, final boolean isFinal) {
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        if (isFinal)
            buf.append("final ");
        buf.append(type);
        buf.append(" ");
        buf.append(name);

        return buf.toString();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
