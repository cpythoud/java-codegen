package org.jcodegen.java;

import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
public abstract class ConditionalBlock<T extends ConditionalBlock<T>> extends JavaCodeBlock<T> {

    private final Condition condition;

    private final List<ConditionCombination> combinations = new ArrayList<ConditionCombination>();


    public ConditionalBlock(final String keyword, final int indentationLevel, final Condition condition) {
        super(keyword, indentationLevel);
        this.condition = condition;
    }


    public T andCondition(final Condition condition) {
        combinations.add(new ConditionCombination(condition, ConditionCombination.Type.AND));
        return getThis();
    }

    public T orCondition(final Condition condition) {
        combinations.add(new ConditionCombination(condition, ConditionCombination.Type.OR));
        return getThis();
    }


    protected void appendCondition(final StringBuilder buf) {
        buf.append(" (");
        buf.append(condition);

        if (combinations.size() > 0)
            Condition.appendCombinations(buf, combinations);

        buf.append(")");
    }
}
