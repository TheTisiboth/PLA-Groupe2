package expression.FunCall.Condition;

import java.util.List;
import java.util.ListIterator;

import j.J_Parameters;
import main.Directions;
import main.Teams;
import model.Entity;

public class Cell extends J_Condition {

	public Cell(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public boolean eval(Entity e) {
		Teams team = parameters.get(1).toEntite();
		Directions d = parameters.get(0).toDirection();
		List<Entity> l = e.checkTile(e.RelativeToRealDir(d));
		ListIterator<Entity> iter = l.listIterator();
		while(iter.hasNext()) {
			Entity ent = iter.next();
			if(team.equals(ent.getTeam())) {
				return true;
			}
		}
		return false;
	}
	
}
