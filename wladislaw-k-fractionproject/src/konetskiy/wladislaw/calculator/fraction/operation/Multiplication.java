package konetskiy.wladislaw.calculator.fraction.operation;

import java.util.List;

/**
 * An interface with a method to perform multiplication of two homogeneous objects, or multiple homogeneous objects, stored in a list. 
 * The return type of this method is a new object of the same type.
 */
public interface Multiplication<T> {
	
	T multiply(T o1, T o2);
	
	T multiply(List<T> items);
	
	T multiply(T o, List<T> items);

}
