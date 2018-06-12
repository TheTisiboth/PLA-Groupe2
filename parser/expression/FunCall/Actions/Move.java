package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;

public class Move extends J_Action {

	public Move(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public void exec() {
		System.out.print("Move(");
		if(parameters.size() > 0)
			System.out.print(parameters.get(0).toString() + ")\n");
		else
			System.out.print("F)\n");
	}

}
