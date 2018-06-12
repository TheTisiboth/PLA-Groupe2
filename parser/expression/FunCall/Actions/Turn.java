package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;

public class Turn extends J_Action{

	public Turn(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public void exec() {
		System.out.println("Execution: Turn, sens: " + parameters.get(0).toString());
	}

}
