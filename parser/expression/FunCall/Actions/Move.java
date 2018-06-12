package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;
import main.Directions;
import model.Entity;

public class Move extends J_Action {

	public Move(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public void exec(Entity entite) {
		if(parameters.size() > 0)
			entite.move(entite.RelativeToRealDir(parameters.get(0).toDirection()));
		else
			entite.move(entite.RelativeToRealDir(Directions.FRONT));
	}

}
