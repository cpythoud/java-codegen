package org.jcodegen.java;

import org.dbbeans.util.Strings;

/**
 * ...
 */
public class FunctionDeclaration extends DeclarationWithArguments<FunctionDeclaration> {

    private final String returnType;


    public FunctionDeclaration(final String name) {
        this(name, "void", 0);
    }

    public FunctionDeclaration(final String name, final String returnType) {
        this(name, returnType, 0);
    }

    public FunctionDeclaration(final String name, final String returnType, final int indentationLevel) {
        super("Function", indentationLevel, name);
        this.returnType = returnType;
    }

    public FunctionDeclaration(final String name, final GenericType returnType) {
        this(name, returnType, 0);
    }

    public FunctionDeclaration(final String name, final GenericType returnType, final int indentationLevel) {
        super("Function", indentationLevel, name);
        this.returnType = returnType.toString();
    }


    @Override
    protected FunctionDeclaration getThis() {
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        appendAnnotations(buf);

        appendDeclarationStart(buf);

        buf.append(returnType);
        buf.append(" ");

        buf.append(getName());

        appendArgumentList(buf);
        appendExceptionsThrown(buf);
        buf.append(" ");

        appendContent(buf);

        buf.append("\n");

        return buf.toString();
    }


    public static FunctionDeclaration getter(final String type, final String var) {
        return getter(type, var, 0);
    }

    public static FunctionDeclaration getter(final String type, final String var, final int indentLevel) {
        return new FunctionDeclaration("get" + Strings.capitalize(var), type, indentLevel).addContent(
                new ReturnStatement(var)
        );
    }


}
