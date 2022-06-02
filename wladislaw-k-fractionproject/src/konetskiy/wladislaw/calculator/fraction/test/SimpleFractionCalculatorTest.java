package konetskiy.wladislaw.calculator.fraction.test;

import java.util.ArrayList;
import java.util.List;
import konetskiy.wladislaw.calculator.fraction.bean.SimpleFraction;
import konetskiy.wladislaw.calculator.fraction.fractioncalculator.SimpleFractionCalculator;

/**
 * A class for testing main properties of the class SimpleFractionCalculator
 */
public class SimpleFractionCalculatorTest {

	public static void main(String[] args) {
		
		System.out.println("Addition");
		
		List<SimpleFraction> list = new ArrayList<SimpleFraction>();
		list.add(new SimpleFraction(1, 2));
		list.add(new SimpleFraction(1, 4));
		list.add(new SimpleFraction(1, 5));
		list.add(new SimpleFraction(3, 5));
		list.add(new SimpleFraction(1, 3));
		
		SimpleFractionCalculator c = SimpleFractionCalculator.getInstance();
		
		SimpleFraction expected1 = new SimpleFraction(3, 4);
		SimpleFraction expected2 = new SimpleFraction(113, 60);
		SimpleFraction expected3 = new SimpleFraction(173, 60);
		SimpleFraction result1 = c.add(list.get(0), list.get(1));
		SimpleFraction result2 = c.add(list);
		SimpleFraction result3 = c.add(new SimpleFraction(1, 1), list);
		System.out.println(expected1.toShortString() + " = " + result1.toShortString() + " - " + expected1.equals(result1));
		System.out.println(expected2.toShortString() + " = " + result2.toShortString() + " - " + expected2.equals(result2));
		System.out.println(expected3.toShortString() + " = " + result3.toShortString() + " - " + expected3.equals(result3));
		
		System.out.println("---------------");
		System.out.println("Subtraction");
		
		expected1.setNumerator(1);
		expected1.setDenominator(4);
		expected2.setNumerator(-53);
		expected2.setDenominator(60);
		result1 = c.subtract(list.get(0), list.get(1));
		result2 = c.subtract(new SimpleFraction(1, 1), list);
		System.out.println(expected1.toShortString() + " = " + result1.toShortString() + " - " + expected1.equals(result1));
		System.out.println(expected2.toShortString() + " = " + result2.toShortString() + " - " + expected2.equals(result2));
		
		System.out.println("---------------");
		System.out.println("Multiplication");
		
		List<SimpleFraction> list2 = new ArrayList<SimpleFraction>();
		list2.add(new SimpleFraction(18, 24));
		list2.add(new SimpleFraction(1, 2));
		list2.add(new SimpleFraction(1, 3));
		list2.add(new SimpleFraction(1, 4));
		list2.add(new SimpleFraction(1, 5));
		
		expected1.setNumerator(3);
		expected1.setDenominator(8);
		expected2.setNumerator(1);
		expected2.setDenominator(160);
		expected3.setNumerator(1);
		expected3.setDenominator(320);
		result1 = c.multiply(list2.get(0), list2.get(1));
		result2 = c.multiply(list2);
		result3 = c.multiply(new SimpleFraction(1, 2), list2);
		System.out.println(expected1.toShortString() + " = " + result1.toShortString() + " - " + expected1.equals(result1));
		System.out.println(expected2.toShortString() + " = " + result2.toShortString() + " - " + expected2.equals(result2));
		System.out.println(expected3.toShortString() + " = " + result3.toShortString() + " - " + expected3.equals(result3));;
		
		System.out.println("---------------");
		System.out.println("Division");
		
		expected1.setNumerator(3);
		expected1.setDenominator(2);
		expected2.setNumerator(80);
		expected2.setDenominator(1);
		result1 = c.divide(list2.get(0), list2.get(1));
		result2 = c.divide(new SimpleFraction(1, 2), list2);
		System.out.println(expected1.toShortString() + " = " + result1.toShortString() + " - " + expected1.equals(result1));
		System.out.println(expected2.toShortString() + " = " + result2.toShortString() + " - " + expected2.equals(result2));

	}

}
