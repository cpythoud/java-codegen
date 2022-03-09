package org.jcodegen.java;

/**
 * ...
 */
class ConditionCombination {

    public enum Type {
        AND, OR
    }

    private final Condition condition;
    private final Type type;

    public ConditionCombination(final Condition condition, final Type type) {
        this.condition = condition;
        this.type = type;
    }

    public Condition getCondition() {
        return condition;
    }

    public Type getType() {
        return type;
    }
}
