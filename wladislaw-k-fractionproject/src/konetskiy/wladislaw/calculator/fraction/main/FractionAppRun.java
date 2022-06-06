package konetskiy.wladislaw.calculator.fraction.main;

import konetskiy.wladislaw.calculator.fraction.bean.IntegerFraction;
import konetskiy.wladislaw.calculator.fraction.view.FractionCalculatorUserView;
import konetskiy.wladislaw.calculator.fraction.view.IntegerFractionCalculatorUserView;

public class FractionAppRun {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the fraction calculator app");
		System.out.println("Fractions,being operated on this app, are those, which fields are integer values");
		
		FractionCalculatorUserView<IntegerFraction> view = IntegerFractionCalculatorUserView.getInstance();
		IntegerFractionCalculatorUserView.Operation operation = IntegerFractionCalculatorUserView.IntegerFractionOperation.NONE;
		
		
		
		while (operation != IntegerFractionCalculatorUserView.IntegerFractionOperation.ENDINPUT) {
			IntegerFraction result = null;
			operation = view.getUserOperation();
			if (operation == IntegerFractionCalculatorUserView.IntegerFractionOperation.ADD ||
				operation == IntegerFractionCalculatorUserView.IntegerFractionOperation.SUBTRACT ||
				operation == IntegerFractionCalculatorUserView.IntegerFractionOperation.MULTIPLY ||
				operation == IntegerFractionCalculatorUserView.IntegerFractionOperation.DIVIDE) {
				result = view.objectAction(operation);
				System.out.println("The result of the operation: " + result.toShortString());
			} else {
				view.nonObjectAction(operation);
			}
			result = null;
		}

	}

}
