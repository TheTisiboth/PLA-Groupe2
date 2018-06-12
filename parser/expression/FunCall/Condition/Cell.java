package expression.FunCall.Condition;

import java.util.List;

import j.J_Parameters;

public class Cell extends J_Condition {

	public Cell(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public boolean eval() {
		System.out.println("Cell(" + parameters.get(0) + ", " + parameters.get(1) + ")");
		return true;
	}
	
}
