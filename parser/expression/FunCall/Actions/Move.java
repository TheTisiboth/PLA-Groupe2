package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;
import model.Entity;

public class Move extends J_Action {

	public Move(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public void exec(Entity entite) {
		System.out.print("Move(");
		if(parameters.size() > 0)
			entite.move(parameters.get(0).toDirection());
		else
			System.out.print("F)\n");
	}

}
