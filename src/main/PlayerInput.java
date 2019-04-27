package main;

import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.input.Input;

public final class PlayerInput {
	private PlayerInput() {
	}

	public static void init() {
		// make the game exit upon pressing ESCAPE
		Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
		Input.keyboard().onKeyPressed(KeyEvent.VK_SPACE, e -> System.out.println("test"));
		Input.mouse().onPressed(e -> {
			System.out.println(Input.mouse().getMapLocation());

			Player.instance().trythis();
		});
	}
}