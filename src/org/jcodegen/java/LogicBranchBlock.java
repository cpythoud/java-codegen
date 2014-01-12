package org.jcodegen.java;

/**
 * ...
 */

public abstract class LogicBranchBlock<T extends LogicBranchBlock<T>> extends ConditionalBlock<T> {

    public LogicBranchBlock(final String keyword, final int indentationLevel, final Condition condition) {
        super(keyword, indentationLevel, condition);
    }

    protected boolean contentIsSuitableForOneLiner(final boolean moreElses) {
        if (!contentIsAOneLiner())
            return false;

        if (!moreElses)
            return true;

        return !contentContainsBlock("if");
    }

}
