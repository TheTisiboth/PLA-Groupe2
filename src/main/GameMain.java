package main;

import view.Menu;

public class GameMain {
	  public static void main(String[] args) {

		    // launch the main menu
		    new Menu();
		    
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
