package konetskiy.wladislaw.calculator.fraction.bean;

import java.io.Serializable;

/**
 * A fraction class which fields are of primitive integer type. The denominator cannot be zero. 
 * If the denominator is a negative number, the sign is moved to the numerator (method setDenominator()).
 * <br>WARNING: old version, left as an initial variant, new fractions implement Fraction interface.</br>
 */
public class SimpleFraction implements Serializable, Comparable<SimpleFraction> {

	private static final long serialVersionUID = 1627709517002826376L;
	private static final String separator = "/";
	
	private int numerator;
	private int denominator;
	
	public SimpleFraction() {
		this.numerator = 1;
		this.denominator = 1;
	}
	
	public SimpleFraction(int numerator) {
		this();
		this.numerator = numerator;
	}
	
	public SimpleFraction(int numerator, int denominator) {
		this(numerator);
		this.setDenominator(denominator);
	}
	
	public final int getNumerator() {
		return this.numerator;
	}
	
	public final int getDenominator() {
		return this.denominator;
	}
	
	public final void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	
	public final void setDenominator(int denominator) {
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
		
		SimpleFraction otherFr = (SimpleFraction) obj;
		if (this.denominator == otherFr.denominator) {
			return this.numerator == otherFr.numerator;
		}
			
		SimpleFraction tmpFr = new SimpleFraction(this.numerator, this.denominator);
		SimpleFraction tmpOtherFr = new SimpleFraction(otherFr.numerator, otherFr.denominator);
		tmpFr.reduce();
		tmpOtherFr.reduce();
		
		return tmpFr.denominator == tmpOtherFr.denominator && tmpFr.numerator == tmpOtherFr.numerator;
	}
	
	@Override
	public int hashCode() {
		SimpleFraction tmpFr = new SimpleFraction(this.numerator, this.denominator);
		tmpFr.reduce();
		final int prime = 31;
		int result = 1;
		result = prime * result + tmpFr.denominator;
		result = prime * result + tmpFr.numerator;
		return result;
	}
	
	public int compareTo(SimpleFraction otherFr) {
		int comparResult = 0;
		
		if (this.denominator == otherFr.denominator) {
			comparResult = Integer.compare(this.numerator, otherFr.numerator);
			return comparResult;
		}
		
		int commonDen = this.findCD(otherFr);
		SimpleFraction tmpFr = new SimpleFraction((commonDen / this.denominator * this.numerator), commonDen);
		SimpleFraction tmpOtherFr = new SimpleFraction((commonDen / otherFr.denominator * otherFr.numerator), commonDen);
		comparResult = Integer.compare(tmpFr.numerator, tmpOtherFr.numerator);
		
		return comparResult;
	}
	
	@Override
	public String toString() {
		return this.getClass() + " {numerator=" + numerator + ", denominator=" + denominator + "}";
	}
	
	public String toShortString() {
		return "{" + numerator + separator + denominator + "}";
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
	public int findCD(SimpleFraction otherFr) {
		if (this.denominator == otherFr.denominator) {
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
