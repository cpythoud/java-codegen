package org.jcodegen.java;

import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
public abstract class JavaCodeBlock <T extends JavaCodeBlock<T>> /*extends CodeBlock*/ {

    private final List<JavaCodeBlock> content = new ArrayList<JavaCodeBlock>();

    private final String keyword;
    private int indentationLevel;


    public JavaCodeBlock(final String keyword, final int indentationLevel) {
        this.keyword = keyword;
        this.indentationLevel = indentationLevel;
    }

    protected abstract T getThis();


    public String getKeyword() {
        return keyword;
    }

    public int getIndentationLevel() {
        return indentationLevel;
    }

    public void setIndentationLevel(final int indentationLevel) {
        this.indentationLevel = indentationLevel;
        for (JavaCodeBlock block: content)
            block.setIndentationLevel(indentationLevel + 1);
    }

    public String getTabs() {
        return getTabs(indentationLevel);
    }

    public static String getTabs(final int indentationLevel) {
        final StringBuilder buf = new StringBuilder();

        for (int i = 0; i < indentationLevel; i++)
            buf.append("\t");

        return buf.toString();
    }


    public T addContent(final JavaCodeBlock codeBlock) {
        codeBlock.setIndentationLevel(getIndentationLevel() + 1);
        content.add(codeBlock);
        return getThis();
    }

    public T addContent(final String code) {
        content.add(new LineOfCode(code, getIndentationLevel() + 1));
        return getThis();
    }


    protected boolean contentIsAOneLiner() {
        return content.size() == 1;
    }

    protected void appendContent(final StringBuilder buf) {
        if (content.isEmpty()) {
            buf.append("{ }");
            return;
        }

        buf.append("{\n");

        for (JavaCodeBlock javaCodeBlock: content)
            buf.append(javaCodeBlock);

        buf.append(getTabs());
        buf.append("}");
    }

    protected void appendOneLinerContent(final StringBuilder buf) {
        if (!contentIsAOneLiner())
            throw new IllegalArgumentException("Block content is more than one line/unit of code or is empty. Cannot be printed as a one liner.");

        buf.append("\n");
        buf.append(content.get(0));
    }

    protected void appendCommaSeparatedListItems(final StringBuilder buf, final List<String> items) {
        for (String item: items) {
            buf.append(item);
            buf.append(", ");
        }
        buf.delete(buf.length() - 2, buf.length());
    }


    protected boolean contentContainsBlock(final String keyword) {
        for (JavaCodeBlock codeBlock: content)
            if (codeBlock.keyword.equals(keyword))
                return true;

        return false;
    }
}
