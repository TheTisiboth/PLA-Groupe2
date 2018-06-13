package model;

import j.*;

public class Automate {
	J_Automaton comportement;
	Entity entite;
	
	long last;
	long cooldown = 1000;
	
	public Automate(Entity entite, J_Automaton comportement) {
		this.entite = entite;
		this.comportement = comportement;
	}
	

	public void step(long now) {
		if(((AliveEntity)entite).m_life != -1) {
		if(now - last > cooldown) {
			comportement.step(entite);
			last = now;
		}
		}
	}
}
