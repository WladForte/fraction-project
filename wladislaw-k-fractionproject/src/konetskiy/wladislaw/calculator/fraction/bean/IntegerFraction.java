package konetskiy.wladislaw.calculator.fraction.bean;

import java.io.Serializable;

/**
 * Implementation of a fraction with Integer (wrapper class) fields. The denominator cannot be zero.
 * If the denominator is a negative number, the sign is moved to the numerator (method setDenominator()).
 */
public class IntegerFraction implements Serializable, Comparable<IntegerFraction>, Fraction<Integer> {

	private static final long serialVersionUID = 8901929799248737013L;
	private static final String SEPARATOR = "/";
	
	private Integer numerator;
	private Integer denominator;
	
	public IntegerFraction() {
		this.numerator = 1;
		this.denominator = 1;
	}
	
	public IntegerFraction(Integer numerator) {
		this();
		this.numerator = numerator;
	}
	
	public IntegerFraction(Integer numerator, Integer denominator) {
		this(numerator);
		this.setDenominator(denominator);
	}
	
	@Override
	public final Integer getNumerator() {
		return this.numerator;
	}
	
	@Override
	public final Integer getDenominator() {
		return this.denominator;
	}
	
	@Override
	public final void setNumerator(Integer numerator) {
		this.numerator = numerator;
	}
	
	@Override
	public final void setDenominator(Integer denominator) {
		if (denominator > 0) {
			this.denominator = denominator;
		}
		if (denominator < 0) {
			this.denominator = -1 * denominator;
			this.numerator = -1 * numerator;
		}
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero");
		}
	}
	
	public static String getSeparator() {
		return SEPARATOR;
	}
	
	@Override
	public int hashCode() {
		IntegerFraction tmpFr = new IntegerFraction(this.numerator, this.denominator);
		tmpFr.reduce();
		final int prime = 31;
		int result = 1;
		result = prime * result + (tmpFr.denominator == null ? 0 : tmpFr.denominator.hashCode());
		result = prime * result + (tmpFr.numerator == null ? 0 : tmpFr.numerator.hashCode());
		return result;
	}
	
	// by getClass
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		IntegerFraction otherFr = (IntegerFraction) obj;
		if (this.denominator.equals(otherFr.denominator)) {
			return this.numerator.equals(otherFr.numerator);
		}
			
		IntegerFraction tmpFr = new IntegerFraction(this.numerator, this.denominator);
		IntegerFraction tmpOtherFr = new IntegerFraction(otherFr.numerator, otherFr.denominator);
		tmpFr.reduce();
		tmpOtherFr.reduce();
		
		return tmpFr.denominator.equals(tmpOtherFr.denominator) && tmpFr.numerator.equals(tmpOtherFr.numerator);
	}
	
	@Override
	public String toString() {
		return this.getClass() + " {numerator=" + numerator + ", denominator=" + denominator + "}";
	}
	
	@Override
	public String toShortString() {
		return "{" + numerator + SEPARATOR + denominator + "}";
	}
	
	@Override
	public int compareTo(IntegerFraction otherFr) {
		int comparResult = 0;
		
		if (this.denominator.equals(otherFr.denominator)) {
			comparResult = Integer.compare(this.numerator, otherFr.numerator);
			return comparResult;
		}
		
		int commonDen = this.findCD(otherFr);
		IntegerFraction tmpFr = new IntegerFraction((commonDen / this.denominator * this.numerator), commonDen);
		IntegerFraction tmpOtherFr = new IntegerFraction((commonDen / otherFr.denominator * otherFr.numerator), commonDen);
		comparResult = Integer.compare(tmpFr.numerator, tmpOtherFr.numerator);
		
		return comparResult;
	}

	// method to find Greatest Common Divisor
	private static int findGCD (int a, int b) {
		if (a == 0 || b == 0) {
			return 1;
		}
		if (a < 0) {
			a = -a;
		}
		if (b < 0) {
			b = -b;
		}
		
		while (b != 0 ) {
			if (a > b) {
				a = a - b;
			} else {
				b = b - a;
			}
		}
		
		return a;
	}
	
	// method to find Least Common Multiple
	private static int findLCM(int a, int b) {
		int lcm = 1;
		
		lcm = Math.abs(a * b) / findGCD(a, b);
		
		return lcm;
	}
	
	public void reduce() {
		if (canBeReduced()) {
			int gcd = findGCD(numerator, denominator);
			numerator /= gcd;
			denominator /= gcd;
		}
	}
	
	public boolean canBeReduced() {
		if (findGCD(numerator, denominator) > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * method to find Common Denominator
	 * @param otherFr another Fraction
	 * @return denominator of a primitive integer type
	 */
	public int findCD(IntegerFraction otherFr) {
		if (this.denominator.equals(otherFr.denominator)) {
			return this.denominator;
		}
		int cd = findLCM(this.denominator, otherFr.denominator);
		return cd;
	}
	
	/**
	 * method to convert current denominator of the Fraction to another denominator
	 * @param num the number, which will be divisible to the new denominator
	 * @return the same Fraction but with different numerator and denominator,
	 * the new denominator is the least common multiple for both initial denominator and the parameter, passed in the method.
	 */
	public void convertToCD(int num) {
		int commonDen;
		commonDen = findLCM(this.denominator, num);
		this.numerator = commonDen / this.denominator * this.numerator;
		this.denominator = commonDen;		
	}

}
