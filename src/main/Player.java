package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Line2D;

import de.gurkenlabs.litiengine.Direction;
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
	private int tick;

	private static Player instance;
	private Polygon p;

	private Player() {
		super("Dean");

		// setup movement controller
		this.addController(new KeyboardEntityController<>(this));
		this.setController(EntityAnimationController.class, new PlayerAnimationController(this));

		Environment y = Game.world().getEnvironment("level1");
		y.add(this, RenderType.UI);

		int[] xs = new int[] { 0, 10, 10, 0 };
		int[] ys = new int[] { 0, 0, 10, 10 };

		// create a polygon with these source points
		Polygon p = new Polygon(xs, ys, 4);
		tick = 0;

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
		tick++;

		if (tick <= -10) {
			setX(getX() + 1);
		} else if (tick <= 0) {
			setX(getX() - 1);
		}
	}

	public boolean isAttack() {
		return isAttack;
	}

	public int getTick() {
		return tick;
	}

	public void trythis() {
		if (tick > 0) {
			Environment y = Game.world().getEnvironment("level1");

			int d = 10;

			double xd = Player.instance().getCenter().getX() - Input.mouse().getMapLocation().getX();
			double xy = Player.instance().getCenter().getY() - Input.mouse().getMapLocation().getY();

			if (xd == 0) {
				if (xy >= 0) {
					setFacingDirection(Direction.UP);
				} else {
					setFacingDirection(Direction.DOWN);
				}
			} else {
				int slope = (int) (xy / xd);
				if ((slope > 1 || slope < -1)) {
					if (xy > 0) {
						setFacingDirection(Direction.UP);
					} else {
						setFacingDirection(Direction.DOWN);
					}
				} else {
					if (xd > 0) {
						setFacingDirection(Direction.LEFT);
					} else {
						setFacingDirection(Direction.RIGHT);
					}
				}
			}

			int offX = (int) (d * Math.sin(Math.atan(xy / xd)));
			int offY = (int) (d * Math.cos(Math.atan(xy / xd)));
			int[] xs = new int[] { (int) Player.instance().getCenter().getX() - offX,
					(int) Player.instance().getCenter().getX() + offX,
					(int) Input.mouse().getMapLocation().getX() + offX,
					(int) Input.mouse().getMapLocation().getX() - offX };
			int[] ys = new int[] { (int) Player.instance().getCenter().getY() + offY,
					(int) Player.instance().getCenter().getY() - offY,
					(int) Input.mouse().getMapLocation().getY() - offY,
					(int) Input.mouse().getMapLocation().getY() + offY };

			// create a polygon with these source points
			p = new Polygon(xs, ys, 4);

			for (ICombatEntity x : y.findCombatEntities(p)) {
				if (x != Player.instance()) {
					System.out.println("test");
					x.hit(10);
				}
			}

			tick = -20;
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