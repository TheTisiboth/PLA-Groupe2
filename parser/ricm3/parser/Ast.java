package ricm3.parser;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import expression.J_Expression;
import expression.FunCall.Actions.Hit;
import expression.FunCall.Actions.J_Action;
import expression.FunCall.Actions.Move;
import expression.FunCall.Actions.Power;
import expression.FunCall.Actions.Protect;
import expression.FunCall.Actions.Turn;
import expression.FunCall.Condition.Cell;
import expression.FunCall.Condition.Closest;
import expression.FunCall.Condition.MyDir;
import expression.FunCall.Condition.True;
import expression.J_BinaryOp.And;
import expression.J_BinaryOp.Or;
import expression.J_UnaryOp.Not;
import j.EnumCons;
import j.J_AI_Definition;
import j.J_Automaton;
import j.J_Behaviour;
import j.J_Parameters;
import j.J_State;
import j.J_Transition;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {

	// All this is only for the graphical .dot output of the Abstract Syntax Tree

	public String kind; 	// the name of the non-terminal node

	public int id = Id.fresh(); // a unique id used as a graph node

	// AST as tree

	public String dot_id(){
		return Dot.node_id(this.id) ;
	}

	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.dot_id(), this.dot_id()) + this.as_dot_tree() ;
	}

	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}

	public String as_tree_node() {
		return Dot.declare_node(this.dot_id(), this.kind, "");
	}

	public String tree_edges() {
		return "undefined: " + this.kind + ".tree_edges" ;
	}

	// AST as automata in .dot format

	public String as_dot_aut() {
		return "undefined " + this.kind + ".as_dot_aut";
	}

	public Object  make() {
		  return null; // TODO à définir dans la plupart des classes internes ci-dessous.
	}

	public static abstract class Expression extends Ast {
	}

	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.kind = "Terminal" ;
			this.value = string;
		}

		public String toString() {
			return value ;
		}

		public String tree_edges(){
			String value_id = Dot.node_id( -this.id) ;
			return Dot.declare_node( value_id, value, "shape=none, fontsize=10, fontcolor=blue" ) + Dot.edge(this.dot_id(), value_id) ;
		}
		boolean equals(Terminal terminal) {
			return value.equals(terminal.value);
		}

		public Object make() {
			return value;
		}
	}

	// Value = Constant U Variable

	public static class Value extends Ast {}

	public static class Constant extends Value {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}


		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public Object make() {
			switch((String) value.make()) {
			case "N":
				return new J_Parameters(EnumCons.North);
			case "S":
				return new J_Parameters(EnumCons.South);
			case "E":
				return new J_Parameters(EnumCons.East);
			case "O":
				return new J_Parameters(EnumCons.Ouest);
			case "F":
				return new J_Parameters(EnumCons.Front);
			case "B":
				return new J_Parameters(EnumCons.Back);
			case "L":
				return new J_Parameters(EnumCons.OnMyLeft);
			case "R":
				return new J_Parameters(EnumCons.OnMyRight);
			case "V":
				return new J_Parameters(EnumCons.Void);
			case "T":
				return new J_Parameters(EnumCons.Team);
			case "A":
				return new J_Parameters(EnumCons.Adversaire);
			case "D":
				return new J_Parameters(EnumCons.Danger);
			case "P":
				return new J_Parameters(EnumCons.Pickable);
			case "J":
				return new J_Parameters(EnumCons.Jumpable);
			case "G":
				return new J_Parameters(EnumCons.Gate);
			case "M":
				return new J_Parameters(EnumCons.Missile);
			}
			return  null;
		}
	}

	public static class Variable extends Value {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public String toString() {
			return name.toString() ;
		}
	}

	// Parameter = Underscore U Key U Direction U Entity
	// Parameter are not Expression (no recursion)

	public static abstract class Parameter extends Ast {}

	public static class Underscore extends Parameter {
		Underscore(){
			this.kind = "Underscore" ;
		}
		public String tree_edges() {
			return "" ;
		}
		public String toString() {
			return "_";
		}
	}

	public static class Key extends Parameter {

		Constant value;

		Key(String string) {
			this.kind = "Key";
			this.value = new Constant(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString() ;
		}
	}

	public static class Direction extends Parameter {

		Value value;

		Direction(Value value) {
			this.kind = "Direction";
			this.value = value;
		}

		public Object make() {
			return value.make();
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString() ;
		}
	}

	public static class Entity extends Parameter {

		Value value;

		Entity(Value expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString() ;
		}

		public Object make() {
			return value.make();
		}
	}

	// Expression = UnaryOp Expression U  Expression BinaryOp Expression U FunCall(Parameters)


	public static class UnaryOp extends Expression {

		Terminal operator;
		Expression operand;

		UnaryOp(String operator, Expression operand) {
			this.kind = "UnaryOp";
			this.operator = new Terminal(operator);
			this.operand = operand;
		}

		public Object make() {
			switch((String)operator.make()) {
			case "!":
				return new Not((J_Expression)operand.make());
			}

			System.out.println("Operateur unaire non pris en compte: " + (String)operator.make());;
			return null;
		}

		public String tree_edges() {
			return operator.as_tree_son_of(this) + operand.as_tree_son_of(this);
		}

		public String toString() {
			return operator + "(" + operand + ")" ;
		}
	}

	public static class BinaryOp extends Expression {

		Terminal operator;
		Expression left_operand;
		Expression right_operand;

		BinaryOp(Expression l, String operator, Expression r) {
			this.kind = "BinaryOp";
			this.operator = new Terminal(operator);
			this.left_operand = l;
			this.right_operand = r;
		}

		public String tree_edges() {
			return left_operand.as_tree_son_of(this) + operator.as_tree_son_of(this)
					+ right_operand.as_tree_son_of(this);
		}

		public String toString() {
			return "(" + left_operand + " " + operator + " " + right_operand + ")" ;
		}

		public Object make() {
			switch((String)operator.make()) {
			case "&":
				return new And((J_Expression)left_operand.make(), (J_Expression) right_operand.make());
			case "/":
				return new Or((J_Expression)left_operand.make(), (J_Expression) right_operand.make());
			}

			System.out.println("Operateur unaire non pris en compte: " + (String)operator.make());;
			return null;
		}
	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Parameter> parameters;

		FunCall(String name, List<Parameter> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				output += parameter.as_tree_son_of(this);
			}
			return output;
		}

		public Object make() {
			List<J_Parameters> j_params = new LinkedList<J_Parameters>();

			ListIterator<Parameter> iter = parameters.listIterator();
			while(iter.hasNext()) {
				Parameter expr = iter.next();
				j_params.add((J_Parameters)expr.make());
			}


			switch((String)name.make()) {
			case "True":
				return new True(j_params);
			case "Closest":
				return new Closest(j_params);
			case "Turn":
				return new Turn(j_params);
			case "Cell":
				return new Cell(j_params);
			case "Move":
				return new Move(j_params);
			case "Power":
				return new Power(j_params);
			case "Hit":
				return new Hit(j_params);
			case "Protect":
				return new Protect(j_params);
			case "MyDir" :
				return new MyDir(j_params);
			}

			System.out.println("Expression non prise en compte: " + (String)name.make());

			return null;
		}
	}

	public static class Condition extends Ast {

		Expression expression;

		Condition(Expression expression) {
			this.kind = "Condition";
			this.expression = expression;
		}

		public Object make() {
			return expression.make();
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public String toString() {
			return expression.toString() ;
		}
	}

	public static class Action extends Ast {

		Expression expression;

		Action(Expression expression) {
			this.kind = "Action";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public Object make() {
			return expression.make();
		}
	}

	public static class State extends Ast {

		Terminal name;

		State(String string) {
			this.kind = "State";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public boolean equals(State state) {
			return name.equals(state.name);
		}

		public Object make() {
			return new J_State((String)name.make());
		}

		public String dot_id_of_state_of(Automaton automaton){
			return Dot.name( automaton.id + "." + name.toString() ) ;
		}

		public String as_state_of(Automaton automaton){
			return Dot.declare_node( this.dot_id_of_state_of(automaton), name.toString(), "shape=circle, fontsize=4") ;
		}
	}

	public static class AI_Definitions extends Ast {

		List<Automaton> automata;

		AI_Definitions(List<Automaton> list) {
			this.kind = "AI_Definitions";
			this.automata = list;
		}

		public Object make(){
			ListIterator<Automaton> iter = this.automata.listIterator();
			List<J_Automaton> j_automata = new LinkedList<J_Automaton>();
			while(iter.hasNext()){
				Automaton automaton = iter.next();
				j_automata.add((J_Automaton)automaton.make());
			}
			//TODO corriger ça
			return new J_AI_Definition(j_automata);
		}

		public String tree_edges() {
			String output = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				output += automaton.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_tree() {
			return Dot.graph("AST", this.as_tree_node() + this.tree_edges());
		}

		public String as_dot_aut() {
			String string = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				string += automaton.as_dot_aut();
			}
			return Dot.graph("Automata", string);
		}

	}


	public static class Automaton extends Ast {

		Terminal name;
		State entry;
		List<Behaviour> behaviours;

		Automaton(String name, State entry, List<Behaviour> behaviours) {
			this.kind = "Automaton";
			this.name = new Terminal(name);
			this.entry = entry;
			this.behaviours = behaviours;
		}

		public Object make(){
			J_State j_entry = (J_State)entry.make();
			List<J_Behaviour> j_behaviours = new LinkedList<J_Behaviour>();

			ListIterator<Behaviour> iter = this.behaviours.listIterator();
			while(iter.hasNext()){
					Behaviour behaviour = iter.next();
					j_behaviours.add((J_Behaviour)behaviour.make());
			}

			return new J_Automaton((String)name.make(), j_entry, j_behaviours);
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			output += entry.as_tree_son_of(this);
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				output += behaviour.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_aut() {
			String string = new String();
			string += Dot.declare_node(this.dot_id(), name.toString(), "shape=box, fontcolor=red") ;
			string += Dot.edge(this.dot_id(), entry.dot_id_of_state_of(this)) ;
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				string += behaviour.as_transition_of(this);
			}
			return Dot.subgraph(this.id, string) ;
		}

	}

	public static class Behaviour extends Ast {

		State source;
		List<Transition> transitions;

		Behaviour(State state, List<Transition> transitions) {
			this.kind = "Behaviour";
			this.source = state;
			this.transitions = transitions;
		}

		public Object make(){
			J_State j_state = (J_State)source.make();

			List<J_Transition> j_transitions = new LinkedList<J_Transition>();

			ListIterator<Transition> iter = this.transitions.listIterator();
			while(iter.hasNext()){
				Transition transition = iter.next();
				j_transitions.add((J_Transition)transition.make());
			}
			return new J_Behaviour(j_state, j_transitions);
		}

		public String tree_edges() {
			String output = new String();
			output += source.as_tree_son_of(this);
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				output += transition.as_tree_son_of(this);
			}
			return output;
		}

		public String as_transition_of(Automaton automaton) {
			String string = new String();
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				string += transition.as_transition_from(automaton, source);
			}
			return source.as_state_of(automaton) + string ;
		}
	}


	public static class Transition extends Ast {

		Condition condition;
		Action action;
		State target;

		Transition(Condition condition, Action action, State target) {
			this.kind = "Transition";
			this.condition = condition;
			this.action = action;
			this.target = target;
		}

		public Object make() {
			J_Expression cond = (J_Expression) condition.make();
			J_Action act = (J_Action) action.make();
			J_State stat = (J_State) target.make();

			return new J_Transition(cond, act, stat);
		}

		public String tree_edges() {
			return condition.as_tree_son_of(this) + action.as_tree_son_of(this) + target.as_tree_son_of(this);
		}

		public String toString() {
			return condition + "? " + action ;
		}

		public String as_transition_from(Automaton automaton, State source) {
			String string = new String();
			string += Dot.declare_node( this.dot_id() , this.toString(), "shape=box, fontcolor=blue, fontsize=6") ;
			string += Dot.edge(source.dot_id_of_state_of(automaton), this.dot_id()) ;
			string += Dot.edge(this.dot_id(), target.dot_id_of_state_of(automaton)) ;
			return string ;
		}
	}
}
