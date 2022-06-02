package konetskiy.wladislaw.calculator.fraction.operation;

/**
 * 
 * This interface parses the string into the object. 
 * @param <T> - type of object that is being operated on.
 * @param <S> - the type of data, that should be parsed: CharSequence(String, StringBuilder, ...), Character Array, etc.
 */
public interface Parser<T, S> {
	
	T parse(S input);
	
}
