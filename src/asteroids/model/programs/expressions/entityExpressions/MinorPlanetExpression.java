package asteroids.model.programs.expressions.entityExpressions;

import asteroids.model.MinorPlanet;
import asteroids.model.programs.expressions.Expression;

import java.util.Comparator;

/**
 * Created by Bo on 28/04/2017.
 */
public class MinorPlanetExpression extends Expression<MinorPlanet> {
    @Override
    public MinorPlanet getValue() {
        return getShip()
                .getWorld()
                .getAllMinorPlanets()
                .stream()
                .min(Comparator.comparingDouble(minorPlanet -> minorPlanet.getDistanceBetween(getShip())))
                .orElse(null);
    }
}