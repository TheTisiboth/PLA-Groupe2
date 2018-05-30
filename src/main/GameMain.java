package main;

import java.awt.Dimension;

import controller.Controller;
import controller.Options;
import edu.ricm3.game.GameUI;
import model.Model;
import view.View;

public class GameMain {
	  public static void main(String[] args) {

		    // construct the game elements: model, controller, and view.
		    Model model = new Model();
		    Controller controller = new Controller(model);
		    View view = new View(model,controller);

		    Dimension d = new Dimension(Options.LARGEUR_PX, Options.HAUTEUR_PX);
		    new GameUI(model,view,controller,d);
		    
		    // notice that the main thread will exit here,
		    // but not your program... hence the hooking
		    // of the window events to System.exit(0) when
		    // the window is closed. See class WindowListener.

		    /*
		     * *** WARNING *** WARNING *** WARNING *** WARNING ***
		     * If you do something here, on this "main" thread,
		     * you will have parallelism and thus race conditions.
		     * 
		     *           ONLY FOR ADVANCED DEVELOPERS
		     *           
		     * *** WARNING *** WARNING *** WARNING *** WARNING ***
		     */
		    return;
		  }
}
