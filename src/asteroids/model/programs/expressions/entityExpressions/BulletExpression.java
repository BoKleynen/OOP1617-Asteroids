package asteroids.model.programs.expressions.entityExpressions;

import asteroids.model.Bullet;
import asteroids.model.programs.expressions.Expression;

import java.util.Comparator;

/**
 * @author  Bo Kleynen & Yrjo Koyen
 */
public class BulletExpression extends EntityExpression<Bullet> {

    @Override
    public Bullet getValue() {
        return getWorld()
                .getAllBullets()
                .stream()
                .filter(bullet -> bullet.getParentShip() == getShip())
                .min(Comparator.comparingDouble(bullet -> bullet.getDistanceBetween(getShip())))
                .orElse(null);
    }

    @Override
    public Expression<Bullet> clone() {
        Expression clone = new BulletExpression();
        clone.setStatement(getStatement());
        return clone;
    }
}
