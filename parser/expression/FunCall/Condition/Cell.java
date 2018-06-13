package expression.FunCall.Condition;

import java.util.List;
import java.util.ListIterator;

import controller.Options;
import j.J_Parameters;
import main.Directions;
import main.Teams;
import model.Entity;
import model.Model;

public class Cell extends J_Condition {

	public Cell(List<J_Parameters> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public boolean eval(Entity e) {
		Directions dir = e.RelativeToRealDir(parameters.get(0).toDirection());
		Teams team = parameters.get(1).toEntite();
		
		Model model = e.m_model;
		
		int x = e.getTile().getCaseX();
		int y = e.getTile().getCaseY();
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
		switch(team) {
			case Void:
				for(int i = 3; i >= 0; i--) {
					if(model.getRoom().getTile(x, y).getEntityOnLayer(i) != null) { 
							return false;
					}
				}
				return true;
			default :
				for(int i = 3; i >= 0; i--) {
					if(model.getRoom().getTile(x, y).getEntityOnLayer(i) != null) {
						if(model.getRoom().getTile(x, y).getEntityOnLayer(i).getTeam() == team) 
							return true;
					}
				}
				return false ;
		}
	}
	
}
