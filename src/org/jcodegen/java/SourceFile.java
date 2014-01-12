package org.jcodegen.java;

/**
 * ...
 */
public class SourceFile {

    private final String packageName;
    private final String className;

    private final JavaClass javaClass;
    private final ImportsManager importsManager = new ImportsManager();

    private String startComment;
    private String endComment;


    public SourceFile(final String packageName, final String className) {
        this.packageName = packageName;
        this.className = className;
        this.javaClass = new JavaClass(className);
    }

    public JavaClass getJavaClass() {
        return javaClass;
    }

    public ImportsManager getImportsManager() {
        return importsManager;
    }

    public String getFilename() {
        return className + ".java";
    }


    public void setStartComment(final String startComment) {
        this.startComment = startComment;
    }

    public void setEndComment(final String endComment) {
        this.endComment = endComment;
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        if (startComment != null)
            buf.append(startComment);

        appendPackageName(buf);
        buf.append(importsManager.getImports());
        javaClass.setIndentationLevel(0);  // sanitize indentation ?
        buf.append(javaClass.toString());

        if (endComment != null)
            buf.append(endComment);

        return buf.toString();
    }

    private void appendPackageName(final StringBuilder buf) {
        buf.append("package ");
        buf.append(packageName);
        buf.append(";\n\n");
    }
}
