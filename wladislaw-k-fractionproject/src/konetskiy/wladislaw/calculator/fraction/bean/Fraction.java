package konetskiy.wladislaw.calculator.fraction.bean;

/**
 * A fraction interface which denominator and numerator of not primitive types (like Integer, BigInteger, other Fraction).
 * <br>WARNING: IMPLEMETED ONLY WITH INTEGER (WRAPPER CLASS)</br>
 * @param <T> can be Integer, BigInteger, other Fraction, etc.
 */
public interface Fraction<T> {
	
	T getNumerator();
	
	T getDenominator();
	
	void setNumerator(T n);
	
	void setDenominator(T d);
	
	String toShortString();

}
