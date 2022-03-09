package org.jcodegen.java;

public class TernaryOperator extends Expression<TernaryOperator> {

    private final Condition condition;
    private final StringOrCode<Expression> trueResult;
    private final StringOrCode<Expression> falseResult;


    public TernaryOperator(final Condition condition, final Expression trueResult, final Expression falseResult) {
        super("Ternary", 0);
        this.condition = condition;
        this.trueResult = new StringOrCode<Expression>(trueResult);
        this.falseResult = new StringOrCode<Expression>(falseResult);
    }

    public TernaryOperator(final Condition condition, final String trueResult, final Expression falseResult) {
        super("Ternary", 0);
        this.condition = condition;
        this.trueResult = new StringOrCode<Expression>(trueResult);
        this.falseResult = new StringOrCode<Expression>(falseResult);
    }

    public TernaryOperator(final Condition condition, final Expression trueResult, final String falseResult) {
        super("Ternary", 0);
        this.condition = condition;
        this.trueResult = new StringOrCode<Expression>(trueResult);
        this.falseResult = new StringOrCode<Expression>(falseResult);
    }

    public TernaryOperator(final Condition condition, final String trueResult, final String falseResult) {
        super("Ternary", 0);
        this.condition = condition;
        this.trueResult = new StringOrCode<Expression>(trueResult);
        this.falseResult = new StringOrCode<Expression>(falseResult);
    }


    @Override
    protected TernaryOperator getThis() {
        return this;
    }


    @Override
    public String toString() {
        return condition.toString() + " ? " + trueResult.toString() + " : " + falseResult.toString();
    }
}
