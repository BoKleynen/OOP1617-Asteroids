package asteroids.model.programs.statements.simpleStatements;

import asteroids.model.programs.Parent;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.statements.Statement;

/**
 * Created by Bo on 28/04/2017.
 */
public class Assignment<T extends Parent<T>> extends Statement<T> {

    public Assignment(String name, Expression value) {
        this.name = name;
        this.value = value;
        value.setStatement(this);
    }

    private String name;

    public String getName() {
        return name;
    }

    private Expression value;

    public Expression getValue() {
        return value;
    }

    @Override
    public void execute() {
        System.out.println("adding: " + name + " = " + value.getValue());
    	getParent().addVariable(name, value);
    }

    @Override
    public boolean isValidFunctionStatement() {
        return true;
    }

    @Override
    public Statement<T> clone() throws CloneNotSupportedException {
        Statement<T> clone = new Assignment<>(name, value.clone());
        return clone;
    }
}
