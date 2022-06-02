package konetskiy.wladislaw.calculator.fraction.fractioncalculator;

import konetskiy.wladislaw.calculator.fraction.operation.Addition;
import konetskiy.wladislaw.calculator.fraction.operation.Division;
import konetskiy.wladislaw.calculator.fraction.operation.Multiplication;
import konetskiy.wladislaw.calculator.fraction.operation.Subtraction;

/**
 * A calculator interface to perform certain operations on homogeneous objects.
 */
public interface Calculator<T> extends Addition<T>, Subtraction<T>, Multiplication<T>, Division<T> {
	
}
