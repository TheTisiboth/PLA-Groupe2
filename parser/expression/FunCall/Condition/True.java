package expression.FunCall.Condition;

import java.util.List;

import j.J_Parameters;

public class True extends J_Condition {

	public True(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public boolean eval() {
		return true;
	}

}
