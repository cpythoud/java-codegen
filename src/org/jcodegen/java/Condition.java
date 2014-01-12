package org.jcodegen.java;

import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
public class Condition {

    /*private enum Type {
        AND, OR
    }

    private static class Combination {
        public final Condition condition;
        public final Type type;

        public Combination(final Condition condition, final Type type) {
            this.condition = condition;
            this.type = type;
        }
    }*/

    private final StringOrCode<JavaCodeBlock> conditionCode;
    private final boolean reverse;

    private boolean parentheses = false;

    private final List<ConditionCombination> combinations = new ArrayList<ConditionCombination>();


    public Condition(final String conditionCode) {
        this(conditionCode, false);
    }

    public Condition(final String conditionCode, final boolean reverse) {
        this.conditionCode = new StringOrCode<JavaCodeBlock>(conditionCode);
        this.reverse = reverse;
    }

    public Condition(final JavaCodeBlock conditionCode) {
        this(conditionCode, false);
    }

    public Condition(final JavaCodeBlock conditionCode, final boolean reverse) {
        this.conditionCode = new StringOrCode<JavaCodeBlock>(conditionCode);
        this.reverse = reverse;
    }

    public Condition andCondition(final Condition condition) {
        combinations.add(new ConditionCombination(condition, ConditionCombination.Type.AND));
        return this;
    }

    public Condition orCondition(final Condition condition) {
        combinations.add(new ConditionCombination(condition, ConditionCombination.Type.OR));
        return this;
    }

    public Condition needsParentheses() {
        parentheses = true;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();

        if (reverse)
            buf.append("!");
        if (parentheses)
            buf.append("(");
        buf.append(conditionCode);

        if (combinations.size() > 0)
            appendCombinations(buf, combinations);

        if (parentheses)
            buf.append(")");

        return buf.toString();
    }

    protected static void appendCombinations(final StringBuilder buf, final List<ConditionCombination> combinations) {
        for (ConditionCombination combination: combinations) {
            if (combination.getType() == ConditionCombination.Type.AND)
                buf.append(" && ");
            else if (combination.getType() == ConditionCombination.Type.OR)
                buf.append(" || ");
            else
                throw new AssertionError("should never have gotten here");

                /*final boolean multiSubConditions = combination.condition.combinations.size() > 0;
                if (multiSubConditions)
                    buf.append("(");*/
            buf.append(combination.getCondition());
                /*if (multiSubConditions)
                    buf.append(")");*/
        }
    }
}
