package expression.FunCall.Condition;

import java.util.List;

import j.J_Parameters;
import model.Entity;

public class True extends J_Condition {

	public True(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public boolean eval(Entity e) {
		return true;
	}

}
