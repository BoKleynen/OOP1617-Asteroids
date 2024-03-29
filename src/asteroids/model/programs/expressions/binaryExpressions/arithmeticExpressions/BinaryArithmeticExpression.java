package asteroids.model.programs.expressions.binaryExpressions.arithmeticExpressions;

import asteroids.model.programs.expressions.binaryExpressions.BinaryExpression;
import asteroids.model.programs.expressions.Expression;

/**
 * @author  Bo Kleynen & Yrjo Koyen
 */
public abstract class BinaryArithmeticExpression extends BinaryExpression<Double, Double> {

    public BinaryArithmeticExpression(Expression<Double> leftOperand, Expression<Double> rightOperand) {
        super(leftOperand, rightOperand);
    }
}
