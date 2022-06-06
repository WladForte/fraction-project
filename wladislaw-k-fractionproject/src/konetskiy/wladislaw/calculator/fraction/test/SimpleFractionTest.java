package konetskiy.wladislaw.calculator.fraction.test;

import konetskiy.wladislaw.calculator.fraction.bean.SimpleFraction;

/**
* A class for testing main properties of the class SimpleFraction
*/
public class SimpleFractionTest {
	
	public static void main(String[] args) {
		
		System.out.println("------Testing hashCode()------");
		
		System.out.println("\nEqual objects provide equal hashcodes:");
		SimpleFraction fr1 = new SimpleFraction(1, 5);
		SimpleFraction fr2 = new SimpleFraction(1, 5);
		SimpleFraction fr3 = new SimpleFraction(3, 15);
		SimpleFraction fr4 = new SimpleFraction(7, 35);
		SimpleFraction fr5 = new SimpleFraction(20, 100);
		System.out.println(fr1.toShortString() + " hashcode = " + fr1.hashCode());
		System.out.println(fr2.toShortString() + " hashcode = " + fr2.hashCode());
		System.out.println(fr3.toShortString() + " hashcode = " + fr3.hashCode());
		System.out.println(fr4.toShortString() + " hashcode = " + fr4.hashCode());
		System.out.println(fr5.toShortString() + " hashcode = " + fr5.hashCode());
		
		System.out.println("\nDifferent objects provide different hashcodes:");
		fr1 = new SimpleFraction(1, 5);
		fr2 = new SimpleFraction(3, 8);
		System.out.println(fr1.toShortString() + " hashcode = " + fr1.hashCode());
		System.out.println(fr2.toShortString() + " hashcode = " + fr2.hashCode());
		
		System.out.println("\nAfter object is changed, it provides different hashcode:");
		fr1 = new SimpleFraction(0, 11);
		System.out.println("Before: " + fr1.toShortString() + " hashcode = " + fr1.hashCode());
		fr1.setNumerator(6);
		System.out.println("After: " + fr1.toShortString() + " hashcode = " + fr1.hashCode());
		
		
		
		System.out.println("\n------Testing equals()------");
		
		System.out.println("\nTesting reflexivity-principle - different objects are unequal:");
		fr1 = new SimpleFraction(65, 90);
		fr2 = new SimpleFraction(7, 9);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		
		System.out.println("\nTesting symmetry-principle:");
		fr1 = new SimpleFraction(3, 15);
		fr2 = new SimpleFraction(7, 35);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		System.out.println(fr2.toShortString() + " and " + fr1.toShortString() + " equality = " + fr2.equals(fr1));
		
		System.out.println("\nTesting transitivity-principle:");
		fr1 = new SimpleFraction(5, 9);
		fr2 = new SimpleFraction(20, 36);
		fr3 = new SimpleFraction(25, 45);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		System.out.println(fr2.toShortString() + " and " + fr3.toShortString() + " equality = " + fr2.equals(fr3));
		System.out.println(fr1.toShortString() + " and " + fr3.toShortString() + " equality = " + fr1.equals(fr3));
		
		System.out.println("\nTesting consistency-principle:");
		fr1 = new SimpleFraction(1, 3);
		fr2 = new SimpleFraction(1, 3);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		fr2.setNumerator(29);
		System.out.println("Fraction 2 numerator has been changed to " + fr2.getNumerator());
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		fr2.setNumerator(1);
		System.out.println("Fraction 2 numerator has been restored to " + fr2.getNumerator());
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		
		System.out.println("\nTesting non-nullity-principle:");
		fr1 = new SimpleFraction(1, 3);
		System.out.println(fr1.toShortString() + " and Fraction null" + " equality = " + fr1.equals(null));
		
		
		
		System.out.println("\n------Testing compareTo()------");
		
		System.out.println("\nTesting results");
		fr1 = new SimpleFraction(1, 2);
		fr2 = new SimpleFraction(3, 8);
		fr3 = new SimpleFraction(11, 12);
		fr4 = new SimpleFraction(9, 18);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr4)) + fr4.toShortString());
		
		System.out.println("\nTesting sgn(x.compareTo(y)) == -sgn(y.compareTo(x)):");
		fr1 = new SimpleFraction(10, 16);
		fr2 = new SimpleFraction(13, 29);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr1)) + fr1.toShortString());
		
		System.out.println("\nTesting (x.compareTo(y) > 0 && y.compareTo(z) > 0) implies x.compareTo(z) > 0:");
		fr1 = new SimpleFraction(5, 6);
		fr2 = new SimpleFraction(10, 20);
		fr3 = new SimpleFraction(1, 7);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		fr1 = new SimpleFraction(5, 21);
		fr2 = new SimpleFraction(17, 20);
		fr3 = new SimpleFraction(48, 49);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		
		System.out.println("\nTesting x.compareTo(y) == 0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)):");
		fr1 = new SimpleFraction(23, 27);
		fr2 = new SimpleFraction(6, 12);
		fr3 = new SimpleFraction(1, 9);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		fr1 = new SimpleFraction(3, 27);
		fr2 = new SimpleFraction(7, 12);
		fr3 = new SimpleFraction(11, 9);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		
		
		System.out.println("\nTesting consistency with equals:");
		fr1 = new SimpleFraction(1, 10);
		fr2 = new SimpleFraction(5, 50);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		fr3 = new SimpleFraction(4, 5);
		fr4 = new SimpleFraction(16, 5);
		System.out.println(fr3.toShortString() + comparResult(fr3.compareTo(fr4)) + fr4.toShortString());
		System.out.println(fr3.toShortString() + " and " + fr4.toShortString() + " equality = " + fr3.equals(fr4));
		
	}
	
	public static String comparResult(int a) {
		String result = " is equal to ";
		if (a > 0) {
			result = " is greater than ";
		}
		if (a < 0) {
			result = " is less than ";
		}
		if (a == 0) {
			result = " is equal to ";
		}
		
		return result;
	}

}
