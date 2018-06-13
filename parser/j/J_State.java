package j;

public class J_State {
	J_Terminal name;
	
	public J_State(String string) {
		// TODO Auto-generated constructor stub
		this.name = new J_Terminal(string);
	}
	
	public boolean equals(J_State state) {
		return state.name.equals(name);
	}

}
