package konetskiy.wladislaw.calculator.fraction.view;

import konetskiy.wladislaw.calculator.fraction.bean.Fraction;
import konetskiy.wladislaw.calculator.fraction.fractioncalculator.FractionCalculator;

/**
 * Calculator view for fractions.
 * @param <T> - any type of Fraction that implements Fraction interface.
 */
public interface FractionCalculatorUserView<T extends Fraction<?>> extends CalculatorUserView<T> {
	
	@Override
	FractionCalculator<T> getCalculator();
	
	@Override
	FractionCalculatorUserView<T> getValidUserInputObjects();
	
	@Override
	FractionCalculatorUserView<T> nonObjectAction(Operation operation);
	
}
