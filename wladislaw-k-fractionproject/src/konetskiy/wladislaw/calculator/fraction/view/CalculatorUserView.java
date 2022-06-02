package konetskiy.wladislaw.calculator.fraction.view;

import konetskiy.wladislaw.calculator.fraction.fractioncalculator.Calculator;

/**
 * This interface enables interactions with the user, like, for example, getting user input, receiving the result from certain operations, 
 * storing data, getting data from the source etc./
 * Classes, implementing this interface, receive input from the user in console directly.
 * @param <T> - the object that is being operated on
 */
public interface CalculatorUserView<T> {
	
	Calculator<T> getCalculator();
	
	Operation getUserOperation();
	
	CalculatorUserView<T> getValidUserInputObjects();
	
	T objectAction(Operation operation);
	
	CalculatorUserView<T> nonObjectAction(Operation operation);
	
	T chooseObject();
	
	/**
	 * The class, implementing this inner interface (usually enum), stores all accessible operations on the CalulatorView.
	 */
	interface Operation {
		
		String getName();
		
	}
	
}
