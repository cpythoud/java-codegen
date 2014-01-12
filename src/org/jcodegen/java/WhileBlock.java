package org.jcodegen.java;

/**
 * ...
 */
public class WhileBlock extends ConditionalBlock<WhileBlock> {

    public WhileBlock(final Condition condition) {
        this(condition, 0);
    }

    public WhileBlock(final Condition condition, final int indentationLevel) {
        super("while", indentationLevel, condition);
    }


    @Override
    protected WhileBlock getThis() {
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        buf.append(getTabs());
        buf.append("while");
        appendCondition(buf);

        if (contentIsAOneLiner())
            appendOneLinerContent(buf);
        else {
            buf.append(" ");
            appendContent(buf);
        }

        buf.append("\n");

        return buf.toString();
    }
}
