package org.jcodegen.java;

/**
 * ...
 */
public abstract class Declaration<T extends Declaration<T>> extends JavaCodeBlock<T> {

    private final String name;
    //private final String codeEntity;

    private Visibility visibility = Visibility.PUBLIC;

    private boolean isAbstract = false;
    private boolean isFinal = false;
    private boolean isStatic = false;

    private String annotations = null;


    public Declaration(final String keyword, final int indentLevel, final String name/*, final String codeEntity*/) {
        super(keyword, indentLevel);
        this.name = name;
        //this.codeEntity = codeEntity;
    }


    public T visibility(final Visibility visibility) {
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
            throw new IllegalArgumentException(getAbstractAndFinalErrorMessage());

        isStatic = true;
        return getThis();
    }

    public T annotate(final String annotations) {
        this.annotations = annotations;
        return getThis();
    }


    protected String getName() {
        return name;
    }

    protected String getAbstractAndFinalErrorMessage() {
        return getKeyword() + " cannot be abstract AND final";
    }

    protected String getAbstractAndStaticErrorMessage() {
        return getKeyword() + " cannot be abstract AND static";
    }

    protected void appendAnnotations(final StringBuilder buf) {
        if (annotations != null) {
            buf.append(getTabs());
            buf.append(annotations);
            buf.append("\n");
        }
    }

    protected void appendDeclarationStart(final StringBuilder buf) {
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
    }
}
