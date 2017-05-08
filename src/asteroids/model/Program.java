package asteroids.model;

import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.valueExpressions.ValueExpression;
import asteroids.model.util.exceptions.NotEnoughTimeRemainingException;
import asteroids.model.programs.function.Function;
import asteroids.model.programs.statements.Statement;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import java.util.*;

/**
 * @author  Bo Kleynen & Yrjo Koyen
 */
public class Program {

	public Program(List<Function> functions, Statement main) {
		setFunctions(functions);
		setMainStatement(main);
	}

	private List<Object> printedObjects = new ArrayList<>();

	public void addPrintedObject(Object object) {
		printedObjects.add(object);
	}

	public List<Object> execute(double time) {
		incrementTimeRemaining(time);
		main.execute();
		return printedObjects;
	}

	public void pause() {
		isPaused = true;
	}

	private Map<String, Function> functions = new HashMap<>();

	public Function getFunction(String functionName) {
		return functions.get(functionName);
	}

	public void setFunctions(List<Function> functions) {
		for (Function function: functions) {
			this.functions.put(function.getFunctionName(), function);
			function.setProgram(this);
		}
	}

	private Statement main;

	private Map<String, Expression> globalVariables = new HashMap<>();

	public Expression readGlobalVariable(String variableName) {
		return new ValueExpression<>(getGlobalVariable(variableName).getValue());
	}

	public Expression getGlobalVariable(String variableName) {
		return globalVariables.get(variableName);
	}

	public void addGlobalVariable(String variableName, Expression expression) {
		if (globalVariables.containsKey(variableName))
			if (globalVariables.get(variableName).getValue().getClass() == expression.getValue().getClass())
				globalVariables.put(variableName, expression);
			else
				throw new IllegalArgumentException("Expected type of " + variableName +
						" is: " + globalVariables.get(variableName).getValue().getClass().toString() + 
						" but received: " + expression.getValue().getClass().toString());
		else
			globalVariables.put(variableName, expression);
	}

	private void setMainStatement(Statement main) {
		this.main = main;
		main.setProgram(this);
	}

	private Ship ship;

	@Basic
	public Ship getShip() {
		return ship;
	}

	@Basic @Raw
	public void setShip(Ship ship) {
		this.ship = ship;
	}

	private double timeRemaining = 0;

	@Basic
	public double getTimeRemaining() {
		return timeRemaining;
	}

	public void incrementTimeRemaining(double time) {
		timeRemaining += time;
	}

	public void decrementTimeRemaining(double time) {
		double newTime = timeRemaining - time;

		if (newTime <= 0)
			throw new NotEnoughTimeRemainingException();

		timeRemaining = newTime;
	}

	private boolean isPaused = false;
}
