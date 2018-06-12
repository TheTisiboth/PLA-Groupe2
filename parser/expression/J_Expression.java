package expression;

public abstract class J_Expression {
	
	public String toString() {
		return "Expression";
	}
	
	//TODO abstract
	public  boolean eval() {
		System.out.print("Eval pas definie pour cette expression");
		return false;
	}
}