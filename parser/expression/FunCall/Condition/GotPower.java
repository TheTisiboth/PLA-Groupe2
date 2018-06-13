package expression.FunCall.Condition;

import java.util.List;

import j.J_Parameters;
import model.Entity;

public class GotPower extends J_Condition{
	
	public GotPower(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	public boolean eval(Entity e) {
		return (e.getPower() > 0);
	}

}
