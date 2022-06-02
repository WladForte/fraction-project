package konetskiy.wladislaw.calculator.fraction.fractioncalculator;

import konetskiy.wladislaw.calculator.fraction.bean.Fraction;
import konetskiy.wladislaw.calculator.fraction.dao.FractionDAO;

/**
 * A calculator interface to perform certain operations on homogeneous objects.
 * @param <T> any Fraction class that implements the interface Fraction
 */
public interface FractionCalculator<T extends Fraction<?>> extends Calculator<T> {
	
	T inverse(T fr);
	
	FractionDAO<T> getBuffer();
	
}
