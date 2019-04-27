import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import screens.IngameScreen;

public class Program {

	public static void main(String[] args) {
		
		//Doing some fucking info shit
		
	    Game.info().setName("Pirate Blood idk");
	    Game.info().setSubTitle("Sparsh is tard");
	    Game.info().setVersion("v0.0.1");
	    Game.info().setWebsite("www.I'mgay.com");
	    Game.info().setDescription("For Ludum Dare 44");
	    
	    //inits the game shit from engine idk
		Game.init(args);
		
		//Set graphic render scale (set the scale for the graphics)
		Game.graphics().setBaseRenderScale(4.001f); 
		
	    //load data from the utiLITI game file
	    Resources.load("game.litidata");
	    
	    //init logic
	    PlayerInput.init();
	    GameLogic.init();
	    
	    //load the screen
	    Game.screens().add(new IngameScreen());
	    
	    //load the first level (resources for the map were implicitly loaded from the game file)
	    Game.world().loadEnvironment("level1");
	    
		Game.start();
	}
}
