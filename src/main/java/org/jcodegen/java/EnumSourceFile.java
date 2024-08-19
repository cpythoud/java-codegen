package org.jcodegen.java;

public class EnumSourceFile extends SourceFile {

    private final String enumName;

    private final JavaEnum javaEnum;
    private final ImportsManager importsManager = new ImportsManager();


    public EnumSourceFile(String packageName, String enumName) {
        super(packageName);
        this.enumName = enumName;
        this.javaEnum = new JavaEnum(enumName);
    }

    public JavaEnum getJavaEnum() {
        return javaEnum;
    }

    @Override
    protected String getName() {
        return enumName;
    }

    @Override
    protected void addMainCode(StringBuilder buf) {
        javaEnum.setIndentationLevel(0);  // * sanitize indentation
        buf.append(javaEnum);
    }

}
