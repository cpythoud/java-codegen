package org.jcodegen.java;

/**
 * ...
 */
public abstract class Declaration<T extends Declaration<T>> extends JavaCodeBlock<T> {

    // TODO :
    // revisit subclass hierarchy to prevent creation of illegal constructs in java,
    // like synchronized constructors or abstract fields,
    // at compile time instead of throwing UnsupportedOperationExceptions

    private final String name;

    private Visibility visibility = Visibility.PACKAGE_PRIVATE;

    private boolean isAbstract = false;
    private boolean isFinal = false;
    private boolean isStatic = false;
    private boolean isSynchronized = false;

    private String annotations = null;


    public Declaration(String keyword, int indentLevel, String name) {
        super(keyword, indentLevel);
        this.name = name;
    }


    public T visibility(Visibility visibility) {
        this.visibility = visibility;
        return getThis();
    }

    public T markAsAbstract() {
        if (isFinal)
            throw new IllegalArgumentException(getAbstractAndFinalErrorMessage());
        if (isStatic && !getKeyword().equals("class"))
            throw new IllegalArgumentException(getAbstractAndFinalErrorMessage());

        isAbstract = true;
        return getThis();
    }

    public T markAsFinal() {
        if (isAbstract)
            throw new IllegalArgumentException(getAbstractAndFinalErrorMessage());

        isFinal = true;
        return getThis();
    }

    public T markAsStatic() {
        if (isAbstract)
            throw new IllegalArgumentException(getAbstractAndStaticErrorMessage());

        isStatic = true;
        return getThis();
    }

    public T markAsSynchronized() {
        if (isAbstract)
            throw new IllegalArgumentException(getAbstractAndSynchronizedErrorMessage());

        isSynchronized = true;
        return getThis();
    }

    public T annotate(String annotations) {
        this.annotations = annotations;
        return getThis();
    }


    public String getName() {
        return name;
    }

    protected String getAbstractAndFinalErrorMessage() {
        return getKeyword() + " cannot be abstract AND final";
    }

    protected String getAbstractAndStaticErrorMessage() {
        return getKeyword() + " cannot be abstract AND static";
    }

    protected String getAbstractAndSynchronizedErrorMessage() {
        return getKeyword() + " cannot be abstract AND synchronized";
    }

    protected void appendAnnotations(StringBuilder buf) {
        if (annotations != null) {
            buf.append(getTabs());
            buf.append(annotations);
            buf.append("\n");
        }
    }

    protected void appendDeclarationStart(StringBuilder buf) {
        buf.append(getTabs());
        if (visibility != Visibility.PACKAGE_PRIVATE && visibility != Visibility.NONE) {
            buf.append(visibility.getVal());
            buf.append(" ");
        }
        if (isAbstract)
            buf.append("abstract ");
        if (isStatic)
            buf.append("static ");
        if (isFinal)
            buf.append("final ");
        if (isSynchronized)
            buf.append("synchronized ");
    }
}
