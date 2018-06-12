package j;

import expression.J_Expression;
import expression.FunCall.Actions.J_Action;
import expression.FunCall.Condition.J_Condition;
import model.Entity;

public class J_Transition {
	J_Expression condition;
	J_Action action;
	J_State target;
	
	public J_Transition(J_Expression condition, J_Action action, J_State state) {
		// TODO Auto-generated constructor stub
		this.condition = condition;
		this.action = action;
		this.target = state;
	}
	
	public boolean eval() {
		return condition.eval();
	}

	public void exec(Entity entite) {
		//TODO executer cette merde
		action.exec(entite);
	}

}
