package konetskiy.wladislaw.calculator.fraction.util;

import konetskiy.wladislaw.calculator.fraction.bean.IntegerFraction;

/**
 * The test for utility class IntegerFractionParser.
 */
public class IntegerFractionParserTest {

	public static void main(String[] args) {
		
		FractionParser<IntegerFraction> parser = IntegerFractionParser.getInstance();
		
		String testString1 = "23/45";
		String testString2 = "0/2";
		String testString3 = "0/-23";
		String testString4 = "5/0";
		String testString5 = "34/sd42";
		String testString6 = "23424";
		String testString7 = "345dg34";
		
		IntegerFraction fr1 = parser.parse(testString1);
		System.out.println(fr1.toShortString());
		
		IntegerFraction fr2 = parser.parse(testString2);
		System.out.println(fr2.toShortString());
		
		IntegerFraction fr3 = parser.parse(testString3);
		System.out.println(fr3.toShortString());
		
		try {
			IntegerFraction fr4 = parser.parse(testString4);
			System.out.println(fr4.toShortString());
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		}

		try {
			IntegerFraction fr5 = parser.parse(testString5);
			System.out.println(fr5.toShortString());
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		}
		
		try {
			IntegerFraction fr6 = parser.parse(testString6);
			System.out.println(fr6.toShortString());
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		}
		
		try {
			IntegerFraction fr7 = parser.parse(testString7);
			System.out.println(fr7.toShortString());
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		}

	}

}
