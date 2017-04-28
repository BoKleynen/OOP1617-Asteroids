package asteroids.model.programs.expressions.valueExpressions;

import asteroids.model.Ship;
import asteroids.model.programs.expressions.Expression;

/**
 * Created by Bo on 28/04/2017.
 */
public class ShipExpression extends Expression<Ship> {

    @Override
    public Ship getValue() {
        return getStatement().getProgram().getShip().getWorld().getClosestShip(getStatement().getProgram().getShip());
    }

}