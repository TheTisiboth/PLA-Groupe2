package expression.FunCall.Condition;

import java.util.List;

import expression.FunCall.J_FunCall;
import j.J_Parameters;
import model.Entity;

public class J_Condition extends J_FunCall{

	public J_Condition(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean eval(Entity e) {
		System.out.println("NYI");
		return false;
	}
}