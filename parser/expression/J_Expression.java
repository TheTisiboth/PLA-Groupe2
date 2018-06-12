package expression;

public abstract class J_Expression {
	
	public String toString() {
		return "Expression";
	}
	
	public boolean eval() {
		System.out.print("Eval pas definie pour cette expression");
		return false;
	}
}