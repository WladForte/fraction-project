package konetskiy.wladislaw.calculator.fraction.logger;

import java.util.List;

/**
 * This interface provides methods for storing and extracting data about operations performed on certain objects
 * @param <T> - indicates what kind of objects being operated are in logger sources.
 * WARNING: NOT IMPLEMENTED
 */
public interface OperationLogger {
	
	OperationLogger readDataFromLoggerSource();
	
	OperationLogger writeDataToLoggerSource();
	
	OperationLogger deleteDataFromLoggerSource();
	
	OperationLogger clearLoggerContents();
	
	OperationLogger updateLoggerContents(String str);
	
	OperationLogger updateLoggerContents(List<String> strList);
	
	List<String> provideLoggerContents();

}
