package expression.J_BinaryOp;

import expression.J_Expression;
import expression.FunCall.Condition.J_Condition;
import model.Entity;

public class And extends J_BinaryOp {

	public And(J_Expression left_condition, J_Expression right_condition) {
		super(left_condition, right_condition);
		// TODO Auto-generated constructor stub
	}

	public boolean eval() {
		return (left_condition.eval() && right_condition.eval());
	}

}
