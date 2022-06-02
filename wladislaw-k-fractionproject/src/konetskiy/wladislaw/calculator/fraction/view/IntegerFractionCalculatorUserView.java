package konetskiy.wladislaw.calculator.fraction.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import konetskiy.wladislaw.calculator.fraction.bean.IntegerFraction;
import konetskiy.wladislaw.calculator.fraction.fractioncalculator.FractionCalculator;
import konetskiy.wladislaw.calculator.fraction.fractioncalculator.IntegerFractionCalculator;
import konetskiy.wladislaw.calculator.fraction.util.FractionParser;
import konetskiy.wladislaw.calculator.fraction.util.IntegerFractionParser;

/**
 * Implementation of the view of FractionCalculatorUserView for the IntegerFraction Calculator. 
 * User input is read directly by this class via BufferedReader.
 */
public class IntegerFractionCalculatorUserView implements FractionCalculatorUserView<IntegerFraction> {
	
	private static final BufferedReader USER_INPUT_READER = new BufferedReader(new InputStreamReader((System.in)));
	
	private final int maxFractionsCount;
	private FractionCalculator<IntegerFraction> calculator;
	private List<IntegerFraction> tmpBuffer;
	
	private static final IntegerFractionCalculatorUserView INSTANCE = new IntegerFractionCalculatorUserView();
	
	private IntegerFractionCalculatorUserView() {
		calculator = IntegerFractionCalculator.getInstance();
		tmpBuffer = new ArrayList<>();
		maxFractionsCount = 1000;
	}
	
	public static IntegerFractionCalculatorUserView getInstance() {
		return INSTANCE;
	}
	
	@Override
	public FractionCalculator<IntegerFraction> getCalculator() {
		return calculator;
	}
	
	/**
	 * THIS METHOD MUST BE CALLED BEFORE EXITING PROGRAM!
	 * <br>IF OPERATION <b><i>ENDNPUT</i></b> IS CALLED, THE PROGRAMM TERMINATES ITS WORK!<br>
	 */
	public static void closeUserInputReader() {
		try {
			USER_INPUT_READER.close();
		} catch (IOException ioe) {
			System.out.println("Exception occured while closing the reader of user input");
		} finally {
			System.exit(0);
		}
	}

	// return the type of operation depending on user input (the method works until a correct input is provided)
	@Override
	public Operation getUserOperation() {
		System.out.println("\nChoose operation to perform (type its name in the console)");
		System.out.println("All availlable operations are as follows:\n" + IntegerFractionOperation.getAllNames());
		System.out.println("Be careful: operation \"endinput\" will terminate the programm");
		String line;
		line = "";
		Operation operation = null;
		
		while (operation == null) {
			try {
				line = USER_INPUT_READER.readLine();
			} catch (IOException ioe) {
				System.out.println("Exception occured while reading user input");
			}
			operation = IntegerFractionOperation.getValueFromName(line);
			if (operation == null || operation == IntegerFractionOperation.NONE) {
				System.out.println("Choose the correct operation other than \"none\"");
			}
		}
		
		//VERY IMPORTANT - CLOSES USER_INPUT_READER!
		if (operation == IntegerFractionOperation.ENDINPUT) {
			closeUserInputReader();
		}

		return operation;
	}
	
	/**
	 * Fractions are received through the following manner: numerator(Integer)/denominator(Integer).
	 */
	@Override
	public FractionCalculatorUserView<IntegerFraction> getValidUserInputObjects() {
		System.out.println("Choose how much fractions would like to print in the console (between 1 and " + maxFractionsCount + ")?");
		int fractionsCount;
		fractionsCount = getUserNaturalNumber(maxFractionsCount);
		
		System.out.println("The number of fractions you have to enter: " + fractionsCount);
		System.out.println("Fractions should be typed in the following manner: numerator(integer value)/denominator(integer value)");
		FractionParser<IntegerFraction> parser = IntegerFractionParser.getInstance();
		
		int counter;
		counter = 0;
		while (counter < fractionsCount) {
			try {
				String line = USER_INPUT_READER.readLine();
				try {
					IntegerFraction tmpFraction = parser.parse(line);
					calculator.getBuffer().updateDAOContents(tmpFraction);
					counter++;
				} catch (NumberFormatException nfe) {
					System.out.println("Please, provide the correct input");
				}
			} catch (IOException e) {
				System.out.println("Exception occured while closing the reader of user input");
			}		
		}
		
		return this;
	}

	@Override
	public IntegerFraction objectAction(Operation operation) {
		IntegerFraction result = new IntegerFraction();
		IntegerFractionOperation operationCasted = (IntegerFractionOperation) operation;
		
		while (calculator.getBuffer().receiveDAOContents().isEmpty()) {
			System.out.println("No fractions are in the calculator, please provide some input or read data from the file");
			IntegerFractionOperation subOperation = (IntegerFractionOperation) getUserOperation();	
			while ((subOperation != IntegerFractionOperation.INPUT) || (subOperation != IntegerFractionOperation.READ)) {
				System.out.println("No fractions are in the calculator, please provide some input or read data from the file");
				subOperation = (IntegerFractionOperation) getUserOperation();
				if (subOperation == IntegerFractionOperation.INPUT) {
					nonObjectAction(IntegerFractionOperation.INPUT);
					break;
				}
				if (subOperation == IntegerFractionOperation.READ) {
					nonObjectAction(IntegerFractionOperation.READ);
					break;
				}
			}
		}
		
		int counter;
		int fractionsCount;
		counter = 0;
		fractionsCount = 0;
		
		switch (operationCasted) {		
		case ADD:
			System.out.println("Please, select how much fractions would you like to get for to be summed up");
			fractionsCount = getUserNaturalNumber(maxFractionsCount);
			System.out.println("Please, select fractions for the sum (fractions can be repeated)");
			while (counter < fractionsCount) {
				tmpBuffer.add(chooseObject());
				counter++;
			}
			result = calculator.add(tmpBuffer);
			break;
		case SUBTRACT:
			System.out.println("Please, select how much fractions would you like to get for the first subtraction element");
			fractionsCount = getUserNaturalNumber(maxFractionsCount);
			System.out.println("Please, select fractions for the first subtraction element (fractions can be repeated)");
			while (counter < fractionsCount) {
				tmpBuffer.add(chooseObject());
				counter++;
			}
			result = calculator.add(tmpBuffer);
			tmpBuffer.clear();
			
			System.out.println("Please, select how much fractions would you like to get for the second subtraction element");
			fractionsCount = getUserNaturalNumber(maxFractionsCount);
			System.out.println("Please, select fractions for the second subtraction element (fractions can be repeated)");
			while (counter < fractionsCount) {
				tmpBuffer.add(chooseObject());
				counter++;
			}
			result = calculator.subtract(result, tmpBuffer);
			break;
		case MULTIPLY:
			System.out.println("Please, select how much fractions would you like to get for to be multipliyed");
			fractionsCount = getUserNaturalNumber(maxFractionsCount);
			System.out.println("Please, select fractions for the multiplication (fractions can be repeated)");
			while (counter < fractionsCount) {
				tmpBuffer.add(chooseObject());
				counter++;
			}
			result = calculator.multiply(tmpBuffer);
			break;
		case DIVIDE:
			System.out.println("Please, select how much fractions would you like to get for the first division element");
			fractionsCount = getUserNaturalNumber(maxFractionsCount);
			System.out.println("Please, select fractions for the first division element (fractions can be repeated)");
			while (counter < fractionsCount) {
				tmpBuffer.add(chooseObject());
				counter++;
			}
			result = calculator.multiply(tmpBuffer);
			tmpBuffer.clear();
			
			System.out.println("Please, select how much fractions would you like to get for the second division element");
			fractionsCount = getUserNaturalNumber(maxFractionsCount);
			System.out.println("Please, select fractions for the first division element (fractions can be repeated");
			while (counter < fractionsCount) {
				tmpBuffer.add(chooseObject());
				counter++;
			}
			result = calculator.divide(result, tmpBuffer);
			break;
		default:
			break;
		}
		
		tmpBuffer.clear();
				
		System.out.println("Would you like the result to be save to the calculator? (print \"yes\" or \"no\")");
		String answer;
		answer = "";
		while (!(answer.equals("yes") || answer.equals("no")) ) {
			try {
				answer = USER_INPUT_READER.readLine();
				if (!(answer.equals("yes") || answer.equals("no")) ) {
					System.out.println("Please, provide the correct input (print \"yes\" or \"no\")");
				} else if (answer.equals("yes")) {
					calculator.getBuffer().updateDAOContents(result);
					System.out.println("The result has been saved to the calculator\n");
				} else {
					System.out.println("The result has not been saved to the calculator");
				}
			} catch (IOException e) {
				System.out.println("Exception occured while reading the input");
			}
		}
		
		return result;
	}
	
	@Override
	public FractionCalculatorUserView<IntegerFraction> nonObjectAction(Operation operation) {
		IntegerFractionOperation operationCasted = (IntegerFractionOperation) operation;
		
		switch (operationCasted) {
		case INPUT:
			getValidUserInputObjects();
			System.out.println("Fractions in the calculator:");
			System.out.println(fractionsListToString(calculator.getBuffer().receiveDAOContents()));
			break;
		case CLEAR:
			calculator.getBuffer().clearDAOContents();
			System.out.println("Calculator contents have been cleared (no changes to the source file have been made)");
			break;
		case READ:
			tmpBuffer.addAll(calculator.getBuffer().receiveDAOContents());
			try {
				calculator.getBuffer().readDataFromSource();
				System.out.println("Fractions read from the file:");
				System.out.println(fractionsListToString(calculator.getBuffer().receiveDAOContents()));
			} catch (IOException e) {
				System.out.println("Exception occured while receiving data from the file");
			}
			calculator.getBuffer().updateDAOContents(tmpBuffer);
			tmpBuffer.clear();
			break;
		case WRITE:
			try {
				calculator.getBuffer().writeDataToSource();
			} catch (IOException e) {
				System.out.println("Exception occured while writing data to the file");
			}
			System.out.println("Fractions from the calculator buffer have been writen to the file");
			break;
		case DELETE:
			try {
				calculator.getBuffer().deleteDataFromSource();
			} catch (IOException e) {
				System.out.println("Exception occured while deleting data from the file");
			}
			break;
		default:
			break;
		}
		
		return this;
	}

	@Override
	public IntegerFraction chooseObject() {		
		int userIndex;
		int availableFractionsCount;
		userIndex = -1;
		availableFractionsCount = calculator.getBuffer().receiveDAOContents().size();
		System.out.println("Choose fraction (type its index in the console)");
		System.out.println("All available fractions, stored in the calculator:\n" + fractionsListToString(calculator.getBuffer().receiveDAOContents()));
		
		
		while (userIndex < 1 || userIndex > availableFractionsCount) {
			try {
				String line = USER_INPUT_READER.readLine();
				try {
					userIndex = Integer.parseInt(line);
					if (userIndex < 1 || userIndex > availableFractionsCount) {
						System.out.println("Please, provide the correct input: positive number not greater than " + availableFractionsCount);
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Please, provide the correct input: positive number not greater than " + availableFractionsCount);
				}
			} catch (IOException e) {
				System.out.println("Exception occured while reading user input");
			}
		}

		return calculator.getBuffer().receiveDAOContents().get(userIndex - 1);
	}
	
	public enum IntegerFractionOperation implements Operation {
		
		ADD("add"),
		SUBTRACT("subtract"),
		MULTIPLY("multiply"),
		DIVIDE("divide"),
		INPUT("input"),
		ENDINPUT("endinput"),    // VERY IMPORTANT, CLOSES CONSOLE INPUT!
		CLEAR("clear"),
		READ("read"),
		WRITE("write"),
		DELETE("delete"),
		NONE("none");    // the value of the operation instead of null
		
		private static final Map<String, IntegerFractionOperation> OPERATIONS_MAP = new HashMap<>();
		private static final List<String> OPERATIONS_NAMES_SORTED_LIST = new ArrayList<>();

		static {
			for (IntegerFractionOperation ifo: values()) {
				OPERATIONS_MAP.put(ifo.name, ifo);
				OPERATIONS_NAMES_SORTED_LIST.add(ifo.getName());
			}
		}
		
		private String name;
		
		private IntegerFractionOperation(String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}

		public static List<String> getAllNames() {			
			return OPERATIONS_NAMES_SORTED_LIST;
		}
		
		private static IntegerFractionOperation getValueFromName(String name) {
			return OPERATIONS_MAP.get(name);
		}
		
	}
	
	private String fractionsListToString(List<IntegerFraction> list) {
		StringBuilder builder = new StringBuilder();
		
		for (IntegerFraction fraction: list) {
			builder.append(fraction.toShortString());
			builder.append(',').append(' ');
		}
		
		if (builder.length() > 2) {
			return builder.subSequence(0, builder.length() - 2).toString();
		} else {
			return "";
		}	
	}
	
	private int getUserNaturalNumber(int maxNumber) {
		int userNumber;
		String line;
		userNumber = -1;
		line = "";
		
		while (userNumber < 1 || userNumber > maxNumber) {
			try {
				line = USER_INPUT_READER.readLine();
				try {
					userNumber = Integer.parseInt(line);
					if (userNumber < 1 || userNumber > maxNumber) {
						System.out.println("Your input is incorrect, please, provide any number between 1 and " + maxNumber);
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Your input is incorrect, please, provide any number between 1 and " + maxNumber);
				}
			} catch (IOException e) {
				System.out.println("Exception occured while reading the line");
			}
		}
		
		return userNumber;
	}

}
