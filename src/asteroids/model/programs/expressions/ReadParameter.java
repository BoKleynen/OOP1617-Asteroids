package asteroids.model.programs.expressions;


import asteroids.model.programs.function.CalledFunction;

/**
 * @author  Bo Kleynen & Yrjo Koyen
 */
public class ReadParameter extends Expression {
    public ReadParameter(String paramName) {
        this.paramName = paramName;
    }

    private String paramName;

    @Override
    public Object getValue() {
//        System.out.println("reading param");
//        System.out.println(getStatement());
        return ((CalledFunction) getStatement().getParent()).getParameter(paramName).getValue();
    }

    @Override
    public Expression clone() {
        Expression clone = new ReadParameter(paramName);
        clone.setStatement(getStatement());
        return clone;
    }
}
