package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;
import main.Directions;
import model.AliveEntity;
import model.Entity;

public class Hit extends J_Action {

	public Hit(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public void exec(Entity entite) {
		
		if(parameters.size() > 0)
			((AliveEntity)entite).attack(parameters.get(0).toDirection());
		else
			((AliveEntity)entite).attack();
	}

}
