package konetskiy.wladislaw.calculator.fraction.test;

import konetskiy.wladislaw.calculator.fraction.bean.IntegerFraction;

/**
* A class for testing main properties of the class IntegerFraction
*/
public class IntegerFractionTest {
	
public static void main(String[] args) {
		
		System.out.println("------Testing hashCode()------");
		
		System.out.println("\nEqual objects provide equal hashcodes:");
		IntegerFraction fr1 = new IntegerFraction(1000, 5000);
		IntegerFraction fr2 = new IntegerFraction(1000, 5000);
		IntegerFraction fr3 = new IntegerFraction(3000, 15000);
		IntegerFraction fr4 = new IntegerFraction(7000, 35000);
		IntegerFraction fr5 = new IntegerFraction(20000, 100000);
		System.out.println(fr1.toShortString() + " hashcode = " + fr1.hashCode());
		System.out.println(fr2.toShortString() + " hashcode = " + fr2.hashCode());
		System.out.println(fr3.toShortString() + " hashcode = " + fr3.hashCode());
		System.out.println(fr4.toShortString() + " hashcode = " + fr4.hashCode());
		System.out.println(fr5.toShortString() + " hashcode = " + fr5.hashCode());
		
		System.out.println("\nDifferent objects provide different hashcodes:");
		fr1 = new IntegerFraction(1000, 5000);
		fr2 = new IntegerFraction(3000, 8000);
		System.out.println(fr1.toShortString() + " hashcode = " + fr1.hashCode());
		System.out.println(fr2.toShortString() + " hashcode = " + fr2.hashCode());
		
		System.out.println("\nAfter object is changed, it provides different hashcode:");
		fr1 = new IntegerFraction(0, 11000);
		System.out.println("Before: " + fr1.toShortString() + " hashcode = " + fr1.hashCode());
		fr1.setNumerator(6);
		System.out.println("After: " + fr1.toShortString() + " hashcode = " + fr1.hashCode());
		
		
		
		System.out.println("\n------Testing equals()------");
		
		System.out.println("\nTesting reflexivity-principle - different objects are unequal:");
		fr1 = new IntegerFraction(65000, 90000);
		fr2 = new IntegerFraction(7000, 9000);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		
		System.out.println("\nTesting symmetry-principle:");
		fr1 = new IntegerFraction(3000, 15000);
		fr2 = new IntegerFraction(7000, 35000);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		System.out.println(fr2.toShortString() + " and " + fr1.toShortString() + " equality = " + fr2.equals(fr1));
		
		System.out.println("\nTesting transitivity-principle:");
		fr1 = new IntegerFraction(5000, 9000);
		fr2 = new IntegerFraction(20000, 36000);
		fr3 = new IntegerFraction(25000, 45000);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		System.out.println(fr2.toShortString() + " and " + fr3.toShortString() + " equality = " + fr2.equals(fr3));
		System.out.println(fr1.toShortString() + " and " + fr3.toShortString() + " equality = " + fr1.equals(fr3));
		
		System.out.println("\nTesting consistency-principle:");
		fr1 = new IntegerFraction(1000, 3000);
		fr2 = new IntegerFraction(1000, 3000);
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		fr2.setNumerator(29000);
		System.out.println("Fraction 2 numerator has been changed to " + fr2.getNumerator());
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		fr2.setNumerator(1000);
		System.out.println("Fraction 2 numerator has been restored to " + fr2.getNumerator());
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		
		System.out.println("\nTesting non-nullity-principle:");
		fr1 = new IntegerFraction(1000, 3000);
		System.out.println(fr1.toShortString() + " and Fraction null" + " equality = " + fr1.equals(null));
		
		
		
		System.out.println("\n------Testing compareTo()------");
		
		System.out.println("\nTesting results");
		fr1 = new IntegerFraction(1000, 2000);
		fr2 = new IntegerFraction(3000, 8000);
		fr3 = new IntegerFraction(11000, 12000);
		fr4 = new IntegerFraction(9000, 18000);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr4)) + fr4.toShortString());
		
		System.out.println("\nTesting sgn(x.compareTo(y)) == -sgn(y.compareTo(x)):");
		fr1 = new IntegerFraction(10000, 16000);
		fr2 = new IntegerFraction(13000, 29000);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr1)) + fr1.toShortString());
		
		System.out.println("\nTesting (x.compareTo(y) > 0 && y.compareTo(z) > 0) implies x.compareTo(z) > 0:");
		fr1 = new IntegerFraction(5000, 6000);
		fr2 = new IntegerFraction(10000, 20000);
		fr3 = new IntegerFraction(1000, 7000);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		fr1 = new IntegerFraction(5000, 21000);
		fr2 = new IntegerFraction(17000, 20000);
		fr3 = new IntegerFraction(48000, 49000);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		
		System.out.println("\nTesting x.compareTo(y) == 0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)):");
		fr1 = new IntegerFraction(23000, 27000);
		fr2 = new IntegerFraction(6000, 12000);
		fr3 = new IntegerFraction(1000, 9000);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		fr1 = new IntegerFraction(3000, 27000);
		fr2 = new IntegerFraction(7000, 12000);
		fr3 = new IntegerFraction(11000, 9000);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr3)) + fr3.toShortString());
		System.out.println(fr2.toShortString() + comparResult(fr2.compareTo(fr3)) + fr3.toShortString());
		
		
		System.out.println("\nTesting consistency with equals:");
		fr1 = new IntegerFraction(1000, 10000);
		fr2 = new IntegerFraction(5000, 50000);
		System.out.println(fr1.toShortString() + comparResult(fr1.compareTo(fr2)) + fr2.toShortString());
		System.out.println(fr1.toShortString() + " and " + fr2.toShortString() + " equality = " + fr1.equals(fr2));
		fr3 = new IntegerFraction(4000, 5000);
		fr4 = new IntegerFraction(16000, 5000);
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
