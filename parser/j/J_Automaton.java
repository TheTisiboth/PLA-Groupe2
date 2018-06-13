package j;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

import model.Entity;

public class J_Automaton {
	public String name;
	J_State entry;
	List<J_Behaviour> behaviours;
	
	public J_Automaton(String name, J_State entry, List<J_Behaviour> behaviours) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.entry = entry;
		this.behaviours = behaviours;
	}
	
	public void step(Entity entite) {
		ListIterator<J_Behaviour> iter = behaviours.listIterator();
		while(iter.hasNext()) {
			J_Behaviour behav = iter.next();
			
			if(behav.source.equals(entry)) {
				ListIterator<J_Transition> iter_trans = behav.transitions.listIterator();
				
				while(iter_trans.hasNext()) {
					J_Transition transition = iter_trans.next();

					if(transition.eval(entite)) {
						transition.exec(entite);
						
						if(transition.target.isRandom()) {
							entry = getRandomState();
						}
						else {
							entry = transition.target;							
						}
						return;
					}
				}
			}
		}
	}
	
	private J_State getRandomState(){
		return behaviours.get(ThreadLocalRandom.current().nextInt(0, behaviours.size())).source;
	}

	public J_Automaton getCopy() {
		return new J_Automaton(name, entry, behaviours);
	}
}
