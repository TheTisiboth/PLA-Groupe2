package j;

import ricm3.parser.Ast;
import ricm3.parser.Ast.AI_Definitions;
import ricm3.parser.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Ast ast = AutomataParser.from_file("example/automata.txt");
		J_AI_Definition j_ast = (J_AI_Definition)((AI_Definitions)ast).make();
		
		j_ast.automata.get(0).step();
		j_ast.automata.get(0).step();
		j_ast.automata.get(0).step();
		
		System.out.println("fin");
	}

}
