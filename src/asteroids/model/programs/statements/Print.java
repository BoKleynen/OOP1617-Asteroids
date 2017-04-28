package asteroids.model.programs.statements;

import asteroids.model.programs.expressions.Expression;

/**
 * Created by Bo on 28/04/2017.
 */
public class Print extends Statement{

    public Print(Expression expression) {
        this.expression = expression;
    }

    private Expression expression;

    @Override
    public void execute() {
        System.out.println(expression.toString());
    }
}
