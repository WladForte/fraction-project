package konetskiy.wladislaw.calculator.fraction.operation;

import java.util.List;

/**
 * An interface with a method to perform addition of two homogeneous objects, or multiple homogeneous objects, stored in a list. 
 * The return type of this method is a new object of the same type.
 */
public interface Addition<T> {
	
	T add(T o1, T o2);
	
	T add(List<T> items);
	
	T add(T o, List<T> items);

}
