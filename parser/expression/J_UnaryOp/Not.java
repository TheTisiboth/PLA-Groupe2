package expression.J_UnaryOp;

import expression.J_Expression;
import expression.FunCall.Condition.J_Condition;
import model.Entity;

public class Not extends J_UnaryOp {
	
		
	public Not(J_Expression condition) {
		super(condition);
	}

	public boolean eval() {
		return !condition.eval();
	}
}
