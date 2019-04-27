import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable {
  public static final int MAX_ADDITIONAL_JUMPS = 1;

  private static Player instance;

  private Player() {
    super("Dean");

    // setup movement controller
    this.addController(new KeyboardEntityController<>(this));

    //TODO setup the player's abilities

  }

  public static Player instance() {
    if (instance == null) {
      instance = new Player();
    }

    return instance;
  }

  @Override
  public void update() {

  }
}