package org.jcodegen.java;

public class JavaInterface extends Declaration<JavaInterface> {

    private String extendedInterface = null;


    public JavaInterface(String name) {
        this(name, 0);
    }

    public JavaInterface(String name, int indentationLevel) {
        super("interface", indentationLevel, name);
    }

    @Override
    protected JavaInterface getThis() {
        return this;
    }


    public JavaInterface extendsInterface(String extendedInterface) {
        this.extendedInterface = extendedInterface;
        return this;
    }


    @Override
    public JavaInterface markAsSynchronized() {
        throw new UnsupportedOperationException("Class cannot be synchronized.");
    }


    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        appendAnnotations(buf);

        appendDeclarationStart(buf);

        buf.append("interface ");
        buf.append(getName());
        buf.append(" ");

        if (extendedInterface != null) {
            buf.append("extends ");
            buf.append(extendedInterface);
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
