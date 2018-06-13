package expression.J_UnaryOp;

import expression.J_Expression;
import expression.FunCall.Condition.J_Condition;

public abstract class J_UnaryOp extends J_Expression {
	J_Expression condition;
	
	public J_UnaryOp(J_Expression condition) {
		// TODO Auto-generated constructor stub
		this.condition = condition;
	}

	public boolean eval() {
		System.out.println("Absence d'eval dans UnaryOp");
		return false;
	}
}
