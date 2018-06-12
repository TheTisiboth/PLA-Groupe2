package expression.FunCall.Condition;

import java.util.List;

import j.J_Parameters;
import main.Directions;
import model.Entity;
import ricm3.parser.Ast.Direction;

public class MyDir extends J_Condition {
	
	public MyDir(List<J_Parameters> params) {
		super(params);
	}
	
	public boolean eval(Entity e ) {
		J_Parameters d = parameters.get(0);
		Directions f = d.toDirection();
		return (e.getOrientation().equals(e.RelativeToRealDir(f)));
	}
}
