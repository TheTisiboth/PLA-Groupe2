package expression.FunCall.Actions;

import java.util.List;

import j.J_Parameters;
import model.Entity;

public class Power extends J_Action {

	public Power(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public void exec(Entity entite) {
		entite.Power();
	}
}
