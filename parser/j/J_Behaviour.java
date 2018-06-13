package j;

import java.util.List;

public class J_Behaviour {
	J_State source;
	List<J_Transition> transitions;

	public J_Behaviour(J_State state, List<J_Transition> transitions) {
		// TODO Auto-generated constructor stub
		this.source = state;
		this.transitions = transitions;
	}
	

}
