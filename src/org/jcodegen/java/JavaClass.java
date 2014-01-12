package org.jcodegen.java;

import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
public class JavaClass extends Declaration<JavaClass> {

    private String extendedClass = null;
    private final List<String> implementedInterfaces = new ArrayList<String>();


    public JavaClass(final String name) {
        this(name, 0);
    }

    public JavaClass(final String name, final int indentationLevel) {
        super("class", indentationLevel, name);
    }

    @Override
    protected JavaClass getThis() {
        return this;
    }


    public JavaClass extendsClass(final String extendedClass) {
        this.extendedClass = extendedClass;
        return this;
    }

    public JavaClass implementsInterface(final String implementedInterface) {
        implementedInterfaces.add(implementedInterface);
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        appendAnnotations(buf);

        appendDeclarationStart(buf);

        buf.append("class ");
        buf.append(getName());
        buf.append(" ");

        if (extendedClass != null) {
            buf.append("extends ");
            buf.append(extendedClass);
            buf.append(" ");
        }

        if (implementedInterfaces.size() > 0) {
            buf.append("implements ");
            appendCommaSeparatedListItems(buf, implementedInterfaces);
            buf.append(" ");
        }

        appendContent(buf);

        buf.append("\n");

        return buf.toString();
    }

    public ConstructorDeclaration createConstructor() {
        return new ConstructorDeclaration(getName(), getIndentationLevel() + 1);
    }
}
