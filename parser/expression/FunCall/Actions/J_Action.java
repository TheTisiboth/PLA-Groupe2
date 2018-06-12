package expression.FunCall.Actions;

import java.util.List;

import expression.FunCall.J_FunCall;
import j.J_Parameters;
import model.Entity;

public class J_Action extends J_FunCall{

	public J_Action(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	
	public void exec(Entity entite) {
		System.out.println("NYI action");
	}
}