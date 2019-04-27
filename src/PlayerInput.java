import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.input.Input;

public final class PlayerInput {
  private PlayerInput() {
  }

  public static void init() {
    // make the game exit upon pressing ESCAPE
    Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
    Input.keyboard().onKeyPressed(KeyEvent.VK_SPACE, e -> System.out.println("test"));
  }
}