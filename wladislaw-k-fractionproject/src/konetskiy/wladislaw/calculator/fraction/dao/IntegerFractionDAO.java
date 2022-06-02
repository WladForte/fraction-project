package konetskiy.wladislaw.calculator.fraction.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import konetskiy.wladislaw.calculator.fraction.bean.IntegerFraction;
import konetskiy.wladislaw.calculator.fraction.util.FractionParser;
import konetskiy.wladislaw.calculator.fraction.util.IntegerFractionParser;

/**
 * An implementation of DAO class for IntegerFraction class.
 */
public class IntegerFractionDAO implements FractionDAO<IntegerFraction> {

	private static final char NEW_FRACTION_SEPARATOR_BEGIN = '\r';
	private static final char NEW_FRACTION_SEPARATOR_END = '\n';
	
	private List<IntegerFraction> fractionsBuffer;
	private final String sourceName;
	private final File source;
	private boolean hasSourceBeenRead;
	private boolean hasBufferBeenCleared;
	
	private IntegerFractionDAO() {
		this.fractionsBuffer = new ArrayList<>();
		sourceName = "\\src\\konetskiy\\wladislaw\\calculator\\fraction\\file\\fractionsSource.txt";
		File file = new File("");
		this.source = new File(file.getAbsolutePath() + sourceName);
		hasSourceBeenRead = false;
		hasBufferBeenCleared = false;
	}
	
	private static final IntegerFractionDAO INSTANCE = new IntegerFractionDAO();
	
	public static IntegerFractionDAO getInstance() {
		return INSTANCE;
	}
		
	@Override
	public FractionDAO<IntegerFraction> readDataFromSource() throws IOException, FileNotFoundException {
		int charCount;
		int charArraySize;
		char[] data;
		fractionsBuffer = new ArrayList<>();
		charCount = 0;
		charArraySize = 1024 * 8;
		data = new char[charArraySize];
		
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			while (charCount != -1) {
				charCount = reader.read(data);
			}
			} catch (FileNotFoundException fnfe) {
				throw new FileNotFoundException("File has not been found");
			} catch (IOException ioe) {
				throw new IOException("Input/output exception has occured");
			}
		
			fractionsBuffer = transformDataToFractionsUpd(data);
			hasSourceBeenRead = true;
			hasBufferBeenCleared = false;

		return this;
	}

	@Override
	public FractionDAO<IntegerFraction> writeDataToSource() throws IOException, FileNotFoundException {
		if (hasBufferBeenCleared) {
			return this;
		}
		
		StringBuilder builder = new StringBuilder();
		for (IntegerFraction fraction: this.fractionsBuffer) {
			builder.append(fraction.getNumerator());
			builder.append(IntegerFraction.getSeparator());
			builder.append(fraction.getDenominator());
			builder.append(NEW_FRACTION_SEPARATOR_BEGIN).append(NEW_FRACTION_SEPARATOR_END);
		}
		
		char[] data;
		data = new char[builder.length()];
		builder.getChars(0, builder.length(), data, 0);
		
		if (hasSourceBeenRead) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(source, false))) {    // append data
				writer.write(data);
			} catch (FileNotFoundException fnfe) {
				throw new FileNotFoundException("File has not been found");
			} catch (IOException ioe) {
				throw new IOException("Input/output exception has occured");
			} finally {
				hasSourceBeenRead = false;
			}
		} else {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(source, true))) {    // do not append data
				writer.write(data);
			} catch (FileNotFoundException fnfe) {
				throw new FileNotFoundException("File has not been found");
			} catch (IOException ioe) {
				throw new IOException("Input/output exception has occured");
			}
		}
		
		return this;
	}

	@Override
	public FractionDAO<IntegerFraction> deleteDataFromSource() throws IOException, FileNotFoundException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(source))) {
			writer.write("");
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("File has not been found");
		} catch (IOException ioe) {
			throw new IOException("Input/output exception has occured");
		}
		
		return this;
	}
	
	@Override
	public FractionDAO<IntegerFraction> clearDAOContents() {
		fractionsBuffer.clear();
		hasBufferBeenCleared = true;
		return this;
	}

	@Override
	public FractionDAO<IntegerFraction> updateDAOContents(IntegerFraction fraction) {
		fractionsBuffer.add(fraction);
		hasBufferBeenCleared = false;
		return this;
	}
	
	@Override
	public FractionDAO<IntegerFraction> updateDAOContents(List<IntegerFraction> fractionsList) {
		for (IntegerFraction fraction: fractionsList) {
			fractionsBuffer.add(fraction);
		}
		hasBufferBeenCleared = false;
		return this;
	}
	
	@Override
	public List<IntegerFraction> receiveDAOContents() {
		return fractionsBuffer;
	}
	
	/**
	 * The method transforms the data stored in a char array into a list of fractions.
	 * The method can return empty list (not null) if no fractions could be read from the char array. 
	 * According to this method, fractions are stored in the following manner:
	 * <br>1/2\r\n3/4\r\n-12/15\r\n<br> 
	 * Characters between <b>NEW_FRACTION_SEPARATOR_BEGIN</b> and <b>NEW_FRACTION_SEPARATOR_END</b> are skipped.
	 * @param data - array of characters
	 * @return list of fractions (can return empty list)
	 */
	private List<IntegerFraction> transformDataToFractionsUpd(char[] data) {
		List<IntegerFraction> list = new ArrayList<>();
		StringBuilder dataBuilder = transfromNonDefaultArrayElementsToBuilder(data);
		FractionParser<IntegerFraction> parser = IntegerFractionParser.getInstance();
		
		int fractionSequenceLength;
		boolean insideParsing;
		fractionSequenceLength = 0;
		insideParsing = true;

		for (int i = 0; i < dataBuilder.length(); i++) {
			
			if (insideParsing) {
				if (dataBuilder.charAt(i) != NEW_FRACTION_SEPARATOR_BEGIN) {				
					fractionSequenceLength++;
				} else {				
					CharSequence fractionSequence = dataBuilder.subSequence(i - fractionSequenceLength, i);
					
					try {
						list.add(parser.parse(fractionSequence.toString()));
					} catch (NumberFormatException nfe) {
					}
					
					fractionSequenceLength = 0;
					insideParsing = false;
				}
			} else if (dataBuilder.charAt(i) == NEW_FRACTION_SEPARATOR_END) {
				insideParsing = true;
			}
			
		}
		
		return list;
	}
	
	private StringBuilder transfromNonDefaultArrayElementsToBuilder(char[] data) {
		StringBuilder dataBuilder = new StringBuilder();
		
		for (int i = 0; i < data.length; i++) {
			if (data[i] != '\u0000') {
				dataBuilder.append(data[i]);
			} else {
				break;
			}
		}
		
		return dataBuilder;
	}

}
