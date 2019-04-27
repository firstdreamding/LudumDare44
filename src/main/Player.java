package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Line2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable, IRenderable {
	public static final int MAX_ADDITIONAL_JUMPS = 1;
	private boolean isAttack = true;

	private static Player instance;
	private Polygon p;

	private Player() {
		super("Dean");

		// setup movement controller
		this.addController(new KeyboardEntityController<>(this));
		this.setController(EntityAnimationController.class, new PlayerAnimationController(this));
		
		Environment y = Game.world().getEnvironment("level1");
		y.add(this, RenderType.UI);
		
		int[] xs = new int[]{0, 10, 10, 0};
		int[] ys = new int[]{0, 0, 10, 10};

		//create a polygon with these source points
		Polygon p = new Polygon(xs, ys, 4);
		
		// TODO setup the player's abilities

	}

	public static Player instance() {
		if (instance == null) {
			instance = new Player();
		}

		return instance;
	}

	@Override
	public void update() {
		Environment e = Game.world().getEnvironment("level1");
		// your source points
		for (ICombatEntity x : e.findCombatEntities(new Area(new Line2D.Double(getX(), getY(), getX() + 50, getY())))) {
			System.out.println("test");
		}
	}

	public boolean isAttack() {
		return isAttack;
	}

	public void trythis() {
		Environment y = Game.world().getEnvironment("level1");
		
		int[] xs = new int[] { (int) Player.instance().getCenter().getX() - 10,
				(int) Player.instance().getCenter().getX() + 10, (int) Input.mouse().getMapLocation().getX() + 10,
				(int) Input.mouse().getMapLocation().getX() - 10 };
		int[] ys = new int[] { (int) Player.instance().getCenter().getY() - 10,
				(int) Player.instance().getCenter().getY() + 10, (int) Input.mouse().getMapLocation().getY() + 10,
				(int) Input.mouse().getMapLocation().getY() - 10 };
				
		


		// create a polygon with these source points
		p = new Polygon(xs, ys, 4);
		

		for (ICombatEntity x : y.findCombatEntities(p)) {
			if (x != Player.instance()) {
				System.out.println("test");
			}
		}

	}

	@Override
	public void render(Graphics2D g) {
		if (p != null) {
			g.setColor(Color.RED);
			g.draw(p);
		}
	}
}