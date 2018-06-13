package expression.FunCall.Condition;

import java.util.List;

import controller.Options;
import j.J_Parameters;
import main.Directions;
import main.Teams;
import model.Entity;
import model.Model;

public class Closest extends J_Condition {

	public Closest(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	//TODO Void
	public boolean eval(Entity e) {
		Directions dir = e.RelativeToRealDir(parameters.get(1).toDirection());
		Teams team = parameters.get(0).toEntite();
		
		Model model = e.m_model;
		
		int x = e.getTile().getCaseX();
		int y = e.getTile().getCaseY();
		
		while(x > -1 && x < (Options.LARGEUR - 1) &&  y > -1 && y < (Options.HAUTEUR - 1)) {
			switch(dir) {
			case LEFT:
				x--;
			case RIGHT:
				x++;
			case UP:
				y--;
			case DOWN:
				y++;
			}
			
			for(int i = 3; i >= 0; i--) {
				if(model.getRoom().getTile(x, y).getEntityOnLayer(i) != null) {
					if(model.getRoom().getTile(x, y).getEntityOnLayer(i).getTeam() == team) 
						return true;
					else
						return false;
				}
			}
			if(team == Teams.Void)
				return true;
		}
		
		return false;
	}

}
