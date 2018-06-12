package expression.FunCall.Condition;

import java.util.List;

import j.J_Parameters;
import model.Entity;

public class MyDir extends J_Condition {
	
	public MyDir(List<J_Parameters> params) {
		super(params);
	}
	
	public boolean eval (List<J_Parameters> p, Entity e ) {
		return (e.getOrientation()).equals(p.get(0).toDirection());
	}
}
