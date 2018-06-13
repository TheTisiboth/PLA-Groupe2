package expression.FunCall;

import java.util.List;

import expression.J_Expression;
import j.J_Parameters;

public class J_FunCall extends J_Expression{
	protected List<J_Parameters> parameters;
	
	public J_FunCall(List<J_Parameters> params) {
		parameters = params;
	}
}
