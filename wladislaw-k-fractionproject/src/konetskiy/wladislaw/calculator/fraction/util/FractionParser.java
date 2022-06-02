package konetskiy.wladislaw.calculator.fraction.util;

import konetskiy.wladislaw.calculator.fraction.bean.Fraction;
import konetskiy.wladislaw.calculator.fraction.operation.Parser;

/**
 * This interface serves the purpose to parse fractions. 
 * @param <T> - any fraction that implements interface Fraction.
 */
public interface FractionParser<T extends Fraction<?>> extends Parser<T, String> {

}
