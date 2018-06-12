package j;

import main.Directions;
import main.Teams;

public class J_Parameters {
	EnumCons value;
	
	public J_Parameters(EnumCons value) {
		this.value = value;
	}
	
	public String toString() {
		return value.toString();
	}

	public Directions toDirection() {
		switch(value) {
		case Ouest:
			return Directions.LEFT;
		case East:
			return Directions.RIGHT;
		case South:
			return Directions.DOWN;
		case North: 
			return Directions.UP;
		case OnMyLeft:
			return Directions.OnMyLeft;
		case OnMyRight:
			return Directions.OnMyRight;
		case Front:
			return Directions.FRONT;
		case Back:
			return Directions.BACK;
		}
		return null;
	}
	
	public Teams toEntite() {
		switch(value) {
		case Void:
			return Teams.Void;
		case Team:
			return Teams.Team;
		case Adversaire:
			return Teams.Adversaire;
		case Danger:
			return Teams.Danger;
		case Pickable:
			return Teams.Pickable;
		case Jumpable:
			return Teams.Wall;
		case Gate:
			return Teams.Gate;
		case Missile:
			return Teams.Missile;
		}
		return null;
	}
}
