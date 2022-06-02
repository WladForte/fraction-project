package konetskiy.wladislaw.calculator.fraction.dao;

import konetskiy.wladislaw.calculator.fraction.bean.Fraction;

/**
 * An interface for DAO with Fractions
 * @param <T> any kind of Fraction that implements Fraction interface.
 */
public interface FractionDAO<T extends Fraction<?>> extends DAO<T> {
	
}
