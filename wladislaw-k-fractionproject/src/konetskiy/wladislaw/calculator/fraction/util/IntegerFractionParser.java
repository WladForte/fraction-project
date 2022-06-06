package konetskiy.wladislaw.calculator.fraction.util;

import konetskiy.wladislaw.calculator.fraction.bean.IntegerFraction;

/**
 * Implementation of FractionParser interface for IntegerFractions.
 */
public class IntegerFractionParser implements FractionParser<IntegerFraction>{
	
	private static final IntegerFractionParser INSTANCE = new IntegerFractionParser();
	
	private IntegerFractionParser() {};
	
	public static IntegerFractionParser getInstance() {
		return INSTANCE;
	}
	
	
	@Override
	public IntegerFraction parse(String input) throws NumberFormatException{
		int numeratorSequenceLength;
		String fractionSeparator;
		int fractionSeparatorIndex;
		
		numeratorSequenceLength = 0;
		fractionSeparator = IntegerFraction.getSeparator();
		fractionSeparatorIndex = input.indexOf(fractionSeparator.charAt(0));
			
		for (int i = 0; i < fractionSeparatorIndex; i++) {
			numeratorSequenceLength++;
		}
		
		CharSequence numeratorSequence = input.subSequence(0, numeratorSequenceLength);
		CharSequence denominatorSequence = input.subSequence(fractionSeparatorIndex + fractionSeparator.length(), input.length());
		
		Integer tmpNumerator = null;
		Integer tmpDenominator = null;
		
		try {
			tmpNumerator = Integer.parseInt(numeratorSequence.toString());
			try {
				tmpDenominator = Integer.parseInt(denominatorSequence.toString());
			} catch (NumberFormatException nfe) {
				throw new NumberFormatException("The string cannot be parsed (correct example: 12/25)\nGeneral format: numerator(Integer value)/denominator(Integer value)");
			}
		} catch (NumberFormatException nfe) {
			throw new NumberFormatException("The string cannot be parsed (correct example: 12/25)\nGeneral format: numerator(Integer value)/denominator(Integer value)");
		}
		
		if ((tmpNumerator != null) && (tmpDenominator != null) && (tmpDenominator != 0)) {
			return new IntegerFraction(tmpNumerator, tmpDenominator);
		} else  {
			throw new NumberFormatException("The string cannot be parsed (correct example: 12/25)\nGeneral format: numerator(Integer value)/denominator(Integer value)");
		}
		
	}

}
