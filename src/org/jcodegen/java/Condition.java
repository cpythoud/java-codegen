package org.jcodegen.java;

import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
public class Condition {

    private final StringOrCode<JavaCodeBlock> conditionCode;
    private final boolean reverse;

    private boolean parentheses = false;

    private final List<ConditionCombination> combinations = new ArrayList<>();


    public Condition(String conditionCode) {
        this(conditionCode, false);
    }

    public Condition(String conditionCode, boolean reverse) {
        this.conditionCode = new StringOrCode<>(conditionCode);
        this.reverse = reverse;
    }

    public Condition(JavaCodeBlock conditionCode) {
        this(conditionCode, false);
    }

    public Condition(JavaCodeBlock conditionCode, boolean reverse) {
        this.conditionCode = new StringOrCode<>(conditionCode);
        this.reverse = reverse;
    }

    public Condition andCondition(Condition condition) {
        combinations.add(new ConditionCombination(condition, ConditionCombination.Type.AND));
        return this;
    }

    public Condition orCondition(Condition condition) {
        combinations.add(new ConditionCombination(condition, ConditionCombination.Type.OR));
        return this;
    }

    public Condition needsParentheses() {
        parentheses = true;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

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

    protected static void appendCombinations(StringBuilder buf, List<ConditionCombination> combinations) {
        for (ConditionCombination combination: combinations) {
            if (combination.getType() == ConditionCombination.Type.AND)
                buf.append(" && ");
            else if (combination.getType() == ConditionCombination.Type.OR)
                buf.append(" || ");
            else
                throw new AssertionError("should never have gotten here");

            buf.append(combination.getCondition());
        }
    }

}
