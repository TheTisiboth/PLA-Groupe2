package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;
import model.AliveEntity;
import model.Entity;

public class Pop extends J_Action{

	public Pop(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public void exec(Entity e) {
		AliveEntity a = (AliveEntity)e;
		if(parameters.size() > 0) {
			a.pop(parameters.get(0).toDirection());
		}
		else
			a.pop();
	}

}
