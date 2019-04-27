package main;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.CreatureAnimationController;

public class PlayerAnimationController extends CreatureAnimationController<Player> {

  private static String[] states = { "walk", "idle" };

  public PlayerAnimationController(Player entity) {
    super(entity, null);
  }

  @Override
  public Animation getCurrentAnimation() {
    if (this.getEntity().isDead()) 
    	return super.getCurrentAnimation();
    else {
    	return super.getCurrentAnimation();
    }
    
  }

  @Override
  public Animation getDefaultAnimation() {
    // TODO Auto-generated method stub
    return super.getDefaultAnimation();
  }

}