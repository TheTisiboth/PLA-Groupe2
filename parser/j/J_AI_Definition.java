package j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.Menu;

public class J_AI_Definition {
	public static List<J_Automaton> automata;
	static List<J_Automaton> choosen;
	
	public J_AI_Definition(List<J_Automaton> list) {
		// TODO Auto-generated constructor stub
		automata = list;
		
		initializeChoosenAutomatons();
	}
	
	public static void initializeChoosenAutomatons(){
		List<String> names = Menu.retrieveChoosenAutomata();
		choosen = new ArrayList<J_Automaton>();
		
		for(J_Automaton auto : automata) {
			if(names.contains(auto.name)) {
				choosen.add(auto);
			}
		}
	}
	
	public J_Automaton randomAutomaton() {
		Random rand = new Random();
		return choosen.get(rand.nextInt(choosen.size()));
	}
	
	public static ArrayList<String> getAutomataNames(){
		ArrayList<String> names = new ArrayList<String>();
		
		for (J_Automaton auto : automata) {
			names.add(auto.name);
		}
		
		return names;
	  }

}
