package j;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class J_Automaton {
	String name;
	J_State entry;
	List<J_Behaviour> behaviours;
	
	public J_Automaton(String name, J_State entry, List<J_Behaviour> behaviours) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.entry = entry;
		this.behaviours = behaviours;
	}
	
	public void step() {
		ListIterator<J_Behaviour> iter = behaviours.listIterator();
		while(iter.hasNext()) {
			J_Behaviour behav = iter.next();
			
			if(behav.source.equals(entry)) {
				ListIterator<J_Transition> iter_trans = behav.transitions.listIterator();
				
				while(iter_trans.hasNext()) {
					J_Transition transition = iter_trans.next();

					if(transition.eval()) {
						transition.exec();
						entry = transition.target;
						
						System.out.println("Transition, nouvelle etat: " + entry.name.value);
						return;
					}
				}
			}
		}
	}

}
