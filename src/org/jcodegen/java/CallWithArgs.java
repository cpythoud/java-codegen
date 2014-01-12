package org.jcodegen.java;

import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
public abstract class CallWithArgs<T extends CallWithArgs<T>> extends JavaCodeBlock<T> {

    private List<StringOrCode<JavaCodeBlock>> arguments = new ArrayList<StringOrCode<JavaCodeBlock>>();


    public CallWithArgs(final String keyword, final int indentationLevel) {
        super(keyword, indentationLevel);
    }


    public T addArgument(final String arg) {
        arguments.add(new StringOrCode<JavaCodeBlock>(arg));
        return getThis();
    }

    public T addArguments(final String... args) {
        for (String arg: args)
            arguments.add(new StringOrCode<JavaCodeBlock>(arg));
        return getThis();
    }

    public T addArgument(final Expression arg) {
        arguments.add(new StringOrCode<JavaCodeBlock>(arg));
        return getThis();
    }

    public T addArguments(final Expression... args) {
        for (Expression arg: args)
            arguments.add(new StringOrCode<JavaCodeBlock>(arg));
        return getThis();
    }

    public T addArgument(final OperatorExpression arg) {
        arguments.add(new StringOrCode<JavaCodeBlock>(arg));
        return getThis();
    }

    public T addArguments(final OperatorExpression... args) {
        for (OperatorExpression arg: args)
            arguments.add(new StringOrCode<JavaCodeBlock>(arg));
        return getThis();
    }


    protected void appendArguments(final StringBuilder buf) {
        buf.append("(");
        if (arguments.size() > 0)
            appendCommaSeparatedListItems(buf, StringOrCode.getStrings(arguments));
        buf.append(")");
    }
}
