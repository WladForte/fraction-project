package konetskiy.wladislaw.calculator.fraction.operation;

import java.util.List;

/**
 * An interface with a method to perform subtraction of two homogeneous objects, or multiple homogeneous objects, stored in a list. 
 * The return type of this method is a new object of the same type.
 */
public interface Subtraction<T> {
	
	T subtract(T o1, T o2);
	
	T subtract(T o,List<T> items);

}
