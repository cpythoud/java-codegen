package org.jcodegen.java;

/**
 * ...
 */
public class VarDeclaration extends Declaration<VarDeclaration> {

    private String type;
    private StringOrCode<Expression> value;


    public VarDeclaration(final String type, final String name) {
        super("Variable", 0, name);
        visibility(Visibility.NONE);
        this.type = type;
        this.value = null;
    }

    public VarDeclaration(final String type, final String name, final String value) {
        this(type, name, value, Visibility.NONE);
    }

    public VarDeclaration(final String type, final String name, final String value, final Visibility visibility) {
        this(type, name, value, visibility, 0);
    }

    public VarDeclaration(final String type, final String name, final String value, final Visibility visibility, final int indentationLevel) {
        super("Variable", indentationLevel, name);
        visibility(visibility);
        this.type = type;
        this.value = new StringOrCode<Expression>(value);
    }

    public VarDeclaration(final String type, final String name, final Expression value) {
        this(type, name, value, Visibility.NONE);
    }

    public VarDeclaration(final String type, final String name, final Expression value, final Visibility visibility) {
        this(type, name, value, visibility, 0);
    }

    public VarDeclaration(final String type, final String name, final Expression value, final Visibility visibility, final int indentationLevel) {
        super("Variable", indentationLevel, name);
        visibility(visibility);
        this.type = type;
        this.value = new StringOrCode<Expression>(value);
    }


    @Override
    protected VarDeclaration getThis() {
        return this;
    }

    @Override
    public VarDeclaration markAsAbstract() {
        throw new UnsupportedOperationException("Field or variable cannot be abstract.");
    }

    @Override
    public VarDeclaration markAsSynchronized() {
        throw new UnsupportedOperationException("Field or variable cannot be synchronized.");
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        appendAnnotations(buf);

        appendDeclarationStart(buf);

        buf.append(type);
        buf.append(" ");

        buf.append(getName());

        if (value != null) {
            buf.append(" = ");
            buf.append(value);
        }

        buf.append(";\n");

        return buf.toString();
    }


    public static VarDeclaration declareAndInit(final String type, final String name) {
        return new VarDeclaration(type, name, new ObjectCreation(type));
    }

    public static VarDeclaration declareAndInitFinal(final String type, final String name) {
        return declareAndInit(type, name).markAsFinal();
    }


    public static VarDeclaration createListDeclaration(final String type, final String name) {
        return createListDeclaration(type, name, Visibility.NONE);
    }

    public static VarDeclaration createListDeclaration(final String type, final String name, final Visibility visibility) {
        return createListDeclaration(type, name, visibility, 0);
    }

    public static VarDeclaration createListDeclaration(final String type, final String name, final Visibility visibility, final int indentationLevel) {
        return createGenericContainerDeclaration("List", "ArrayList", type, name, visibility, indentationLevel);
    }


    public static VarDeclaration createGenericContainerDeclaration(final String genericType, final String type, final String name) {
        return createGenericContainerDeclaration(genericType, type, name, Visibility.NONE);
    }

    public static VarDeclaration createGenericContainerDeclaration(final String genericType, final String type, final String name, final Visibility visibility) {
        return createGenericContainerDeclaration(genericType, type, name, visibility, 0);
    }

    public static VarDeclaration createGenericContainerDeclaration(final String genericType, final String type, final String name, final Visibility visibility, final int indentationLevel) {
        return createGenericContainerDeclaration(genericType, genericType, type, name, visibility, indentationLevel);
    }

    public static VarDeclaration createGenericContainerDeclaration(final String genericInterface, final String genericType, final String type, final String name) {
        return createGenericContainerDeclaration(genericInterface, genericType, type, name, Visibility.NONE);
    }

    public static VarDeclaration createGenericContainerDeclaration(final String genericInterface, final String genericType, final String type, final String name, final Visibility visibility) {
        return createGenericContainerDeclaration(genericInterface, genericType, type, name, visibility, 0);
    }

    public static VarDeclaration createGenericContainerDeclaration(final String genericInterface, final String genericType, final String type, final String name, final Visibility visibility, final int indentationLevel) {
        return new VarDeclaration(getParametrizedType(genericInterface, type), name, new ObjectCreation(getParametrizedType(genericType, type)), visibility, indentationLevel);
    }

    private static String getParametrizedType(final String genericType, final String concreteType) {
        return genericType + "<" + concreteType + ">";
    }
}
