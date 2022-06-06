package konetskiy.wladislaw.calculator.fraction.operation;

import java.util.List;

/**
 * An interface with a method to perform division of two homogeneous objects, or multiple homogeneous objects, stored in a list. 
 * The return type of this method is a new object of the same type.
 */
public interface Division<T> {
	
	T divide(T o1, T o2);
	
	T divide(T o, List<T> items);

}
