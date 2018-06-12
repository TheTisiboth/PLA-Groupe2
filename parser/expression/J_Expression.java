package expression;

import model.Entity;

public abstract class J_Expression {
	
	public String toString() {
		return "Expression";
	}

	public boolean eval(Entity e) {
		System.out.print("Eval pas definie pour cette expression");
		return false;
	}
	
	public boolean eval() {
		return false;
	}
}