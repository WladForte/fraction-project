package konetskiy.wladislaw.calculator.fraction.dao;

import java.io.IOException;
import java.util.List;

/**
 * This interface provides methods for storing and extracting data from various sources, like files, databases, etc.
 * @param <T> - indicates what kind of objects will be operated in data sources.
 */
public interface DAO<T> {
	
	DAO<T> readDataFromSource() throws IOException;
	
	DAO<T> writeDataToSource() throws IOException;
	
	DAO<T> deleteDataFromSource() throws IOException;
	
	DAO<T> clearDAOContents();
	
	DAO<T> updateDAOContents(T obj);
	
	DAO<T> updateDAOContents(List<T> list);
	
	List<T> receiveDAOContents();

}
