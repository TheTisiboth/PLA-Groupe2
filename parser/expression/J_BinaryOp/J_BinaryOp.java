package expression.J_BinaryOp;

import expression.J_Expression;
import expression.FunCall.Condition.J_Condition;

public class J_BinaryOp extends J_Expression {
	J_Expression left_condition;
	J_Expression right_condition;
	
	public J_BinaryOp(J_Expression left_condition, J_Expression right_condition) {
		this.left_condition = left_condition;
		this.right_condition = right_condition;
	}
}
