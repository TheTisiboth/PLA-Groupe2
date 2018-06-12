package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;
import model.Entity;

public class Turn extends J_Action{

	public Turn(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public void exec(Entity entite) {
		entite.setOrientation(parameters.get(0).toDirection());
		System.out.println("Execution: Turn, sens: " + parameters.get(0).toString());
	}

}
