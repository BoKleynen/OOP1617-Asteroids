package asteroids.model.programs.expressions.entityExpressions.entityCharacteristicExpressions;

import asteroids.model.programs.expressions.Expression;

/**
 * @author  Bo Kleynen & Yrjo Koyen
 */
public class GetX extends EntityCharacteristicExpression {

    public GetX(Expression entity) {
        super(entity);
    }

    @Override
    public Double getValue() {
        return getEntity().getValue().getPosition().getX();
    }

    @Override
    public Expression<Double> clone() {
        Expression<Double> clone = new GetX(getEntity().clone());
        clone.setStatement(getStatement());
        return clone;
    }
}
