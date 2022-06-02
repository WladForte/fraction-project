package konetskiy.wladislaw.calculator.fraction.fractioncalculator;

import java.util.List;
import konetskiy.wladislaw.calculator.fraction.bean.SimpleFraction;

/**
 * Implementation of the calculator for a common fraction with integer primitive fields.
 * <br>WARNING: old version, left as an initial variant, new calculators implement FractionCalculator interface
 * (instead of implementing Calculator interface directly).</br>
 */
public class SimpleFractionCalculator implements Calculator<SimpleFraction> {
	
	private static final SimpleFractionCalculator INSTANCE = new SimpleFractionCalculator();
	
	private SimpleFractionCalculator() {};
	
	public static SimpleFractionCalculator getInstance() {
		return INSTANCE;
	}
	
	@Override
	public SimpleFraction add(SimpleFraction fr1, SimpleFraction fr2) {
		
		SimpleFraction result = new SimpleFraction();
		
		if (fr1.getDenominator() == fr2.getDenominator()) {
			result.setNumerator(fr1.getNumerator() + fr2.getNumerator());
			result.setDenominator(fr1.getDenominator());
		} else {
			int commonDen = fr1.findCD(fr2);
			int tmpFrNum1 = commonDen / fr1.getDenominator() * fr1.getNumerator();
			int tmpFrNum2 = commonDen / fr2.getDenominator() * fr2.getNumerator();
			result.setNumerator(tmpFrNum1 + tmpFrNum2);
			result.setDenominator(commonDen);
		}
		
		result.reduce();
		
		return result;
	}

	@Override
	public SimpleFraction add(List<SimpleFraction> fractions) {
		
		SimpleFraction result = new SimpleFraction();
		int commonDen;
		commonDen = fractions.get(0).getDenominator();
		result.setDenominator(commonDen);
		
		for (int i = 1; i < fractions.size(); i++) {
			commonDen = result.findCD(fractions.get(i));
			result.setDenominator(commonDen);
		}
		
		result.setNumerator(commonDen / fractions.get(0).getDenominator() * fractions.get(0).getNumerator());
		
		for (int i = 1; i < fractions.size(); i++) {
			int tmpFrNum = result.getDenominator() / fractions.get(i).getDenominator() * fractions.get(i).getNumerator(); 
			result.setNumerator(result.getNumerator() + tmpFrNum);
		}
		
		result.reduce();
		
		return result;
	}
	
	@Override
	public SimpleFraction add(SimpleFraction fr, List<SimpleFraction> fractions) {
		
		SimpleFraction result = this.add(fractions);
		result = this.add(fr, result);
		
		result.reduce();
		
		return result;
	}

	@Override
	public SimpleFraction subtract(SimpleFraction fr1, SimpleFraction fr2) {
		
		SimpleFraction tmpFr = new SimpleFraction(-1 * fr2.getNumerator(), fr2.getDenominator());
		SimpleFraction result = this.add(fr1, tmpFr);
		
		result.reduce();
		
		return result;
	}

	@Override
	public SimpleFraction subtract(SimpleFraction fr, List<SimpleFraction> fractions) {
		
		SimpleFraction result = this.add(fractions);
		result = this.subtract(fr, result);
		
		result.reduce();
		
		return result;
	}

	@Override
	public SimpleFraction multiply(SimpleFraction fr1, SimpleFraction fr2) {
		
		SimpleFraction result = new SimpleFraction();
		
		result.setNumerator(fr1.getNumerator() * fr2.getNumerator());
		result.setDenominator(fr1.getDenominator() * fr2.getDenominator());
		
		result.reduce();
		
		return result;
	}
	
	@Override
	public SimpleFraction multiply(List<SimpleFraction> fractions) {
		
		SimpleFraction result = new SimpleFraction();
		result.setNumerator(fractions.get(0).getNumerator());
		result.setDenominator(fractions.get(0).getDenominator());
		
		for (int i = 1; i < fractions.size(); i++) {
			result.setNumerator(result.getNumerator() * fractions.get(i).getNumerator());
			result.setDenominator(result.getDenominator()* fractions.get(i).getDenominator());
		}
		
		result.reduce();
		
		return result;
	}


	@Override
	public SimpleFraction multiply(SimpleFraction fr, List<SimpleFraction> fractions) {
		
		SimpleFraction result = this.multiply(fractions);
		result = this.multiply(fr, result);
		
		result.reduce();
		
		return result;
	}

	@Override
	public SimpleFraction divide(SimpleFraction fr1, SimpleFraction fr2) {
		
		SimpleFraction tmpFr = inverse(fr2);
		SimpleFraction result = this.multiply(fr1, tmpFr);
		
		result.reduce();
		
		return result;
	}

	@Override
	public SimpleFraction divide(SimpleFraction fr, List<SimpleFraction> fractions) {
		
		SimpleFraction result = this.multiply(fractions);
		result = this.inverse(result);
		result = multiply(fr, result);
		
		result.reduce();
		
		return result;
	}
	
	public SimpleFraction inverse(SimpleFraction fr) {
		
		SimpleFraction result = new SimpleFraction();
		
		result.setNumerator(fr.getDenominator());
		result.setDenominator(fr.getNumerator());
		
		return result;
	}

}
