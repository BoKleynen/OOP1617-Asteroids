package asteroids.tests.part2;

import static org.junit.Assert.*;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.model.util.exceptions.EntityOutOfWorldException;
import asteroids.model.util.vector.Vector;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

public class BulletAssociationsTest {
	
	private Ship s1, s2;
	private World w1, w2;
	
	@Before
	public void setupMutableTestFixtures() {
		s1 = new Ship();
		s2 = new Ship();
		w1 = new World(100, 200);
		w2 = new World(1000, 1000);
	}
	
	@Test
	public void testAddBulletToShip_LegalCase() {
		Bullet b = new Bullet(new Vector(0, 0), new Vector(0, 0), 2);
		
		assertTrue(s1.getNbBullets() == Ship.getInitialBulletAmount());
		b.setPosition(s1.getPosition());
		s1.loadBullet(b);
		
		assertTrue(s1.getNbBullets() == Ship.getInitialBulletAmount() + 1);
		assertTrue(s1.getAllBullets().contains(b));
		assertTrue(b.getShip() == s1);
		
		// Test removing one bullet from the ship.
		s1.removeBullet(b);
		assertTrue(s1.getNbBullets() == Ship.getInitialBulletAmount());
		assertFalse(s1.getAllBullets().contains(b));
		
		// Test adding multiple bullets at the same time
		s2.loadBullet(10);
		assertTrue(s2.getNbBullets() == Ship.getInitialBulletAmount() + 10);
		
		// Test loading multiple new bullets onto a ship.
		s1.loadBullet(100);
		assertTrue(s1.getNbBullets() == Ship.getInitialBulletAmount() + 100);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAddBulletToShip_AddOneBulletTwice() {
		Bullet b = new Bullet(new Vector(0, 0), new Vector(0, 0), 2);
		
		// Adding a bullet multiple times to one ship.
		s1.loadBullet(b);
		s1.loadBullet(b);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAddBulletToShip_AddBulletToTwoShips() {
		Bullet b = new Bullet(new Vector(0, 0), new Vector(0, 0), 2);
		
		// Adding one bullet to multiple ships.
		s1.loadBullet(b);
		s2.loadBullet(b);
	}
	
	@Test(expected = EntityOutOfWorldException.class)
	public void testAddBulletToShip_addBulletToWorlAndShip() {
		Bullet b = new Bullet(new Vector(0, 0), new Vector(0, 0), 2);
		
		// Adding one bullet to a world and a ship.
		w1.addEntity(b);
		s1.loadBullet(b);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testBulletPositionInShip() {
		Bullet b = new Bullet(new Vector(0, 0), new Vector(0, 0), 2);
		
		s1.loadBullet(b);
		assertTrue(b.getPosition().getDistance(s1.getPosition()) == s1.getRadius()/2);
	}
	
}
