package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;
import model.AliveEntity;
import model.Entity;

public class Wizz extends J_Action {

	public Wizz(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public void exec(Entity e) {
		AliveEntity a = (AliveEntity)e;
		a.wizz();
	}

}
