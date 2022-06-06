package konetskiy.wladislaw.calculator.fraction.fractioncalculator;

import java.util.List;
import konetskiy.wladislaw.calculator.fraction.bean.IntegerFraction;
import konetskiy.wladislaw.calculator.fraction.dao.FractionDAO;
import konetskiy.wladislaw.calculator.fraction.dao.IntegerFractionDAO;

/**
 * Implementation of the calculator for a common fraction with integer (wrapper class) fields.
 */
public class IntegerFractionCalculator implements FractionCalculator<IntegerFraction> {
	
	private FractionDAO<IntegerFraction> daoBuffer;
	private static final IntegerFractionCalculator INSTANCE = new IntegerFractionCalculator();
	
	private IntegerFractionCalculator() {
		this.daoBuffer = IntegerFractionDAO.getInstance();
	}
	
	public static IntegerFractionCalculator getInstance() {
		return INSTANCE;
	}
	
	public FractionDAO<IntegerFraction> getBuffer() {
		return daoBuffer;
	}
	
	@Override
	public IntegerFraction add(IntegerFraction fr1, IntegerFraction fr2) {
		
		IntegerFraction result = new IntegerFraction();
		
		if (fr1.getDenominator().equals(fr2.getDenominator())) {
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
	public IntegerFraction add(List<IntegerFraction> fractions) {
		IntegerFraction result = new IntegerFraction();
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
	public IntegerFraction add(IntegerFraction fr, List<IntegerFraction> fractions) {
		
		IntegerFraction result = this.add(fractions);
		result = this.add(fr, result);
		
		result.reduce();
		
		return result;
	}

	@Override
	public IntegerFraction subtract(IntegerFraction fr1, IntegerFraction fr2) {
		
		IntegerFraction tmpFr = new IntegerFraction(-1 * fr2.getNumerator(), fr2.getDenominator());
		IntegerFraction result = this.add(fr1, tmpFr);
		
		result.reduce();
		
		return result;
	}

	@Override
	public IntegerFraction subtract(IntegerFraction fr, List<IntegerFraction> fractions) {

		IntegerFraction result = this.add(fractions);
		result = this.subtract(fr, result);
		
		result.reduce();
		
		return result;
	}

	@Override
	public IntegerFraction multiply(IntegerFraction fr1, IntegerFraction fr2) {
		
		IntegerFraction result = new IntegerFraction();
		
		result.setNumerator(fr1.getNumerator() * fr2.getNumerator());
		result.setDenominator(fr1.getDenominator() * fr2.getDenominator());
		
		result.reduce();
		
		return result;
	}
	
	@Override
	public IntegerFraction multiply(List<IntegerFraction> fractions) {
		
		IntegerFraction result = new IntegerFraction();
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
	public IntegerFraction multiply(IntegerFraction fr, List<IntegerFraction> fractions) {
		
		IntegerFraction result = this.multiply(fractions);
		result = this.multiply(fr, result);
		
		result.reduce();
		
		return result;
	}

	@Override
	public IntegerFraction divide(IntegerFraction fr1, IntegerFraction fr2) {
		
		IntegerFraction tmpFr = inverse(fr2);
		IntegerFraction result = this.multiply(fr1, tmpFr);
		
		result.reduce();
		
		return result;
	}

	@Override
	public IntegerFraction divide(IntegerFraction fr, List<IntegerFraction> fractions) {
		
		IntegerFraction result = this.multiply(fractions);
		result = this.inverse(result);
		result = multiply(fr, result);
		
		result.reduce();
		
		return result;
	}

	@Override
	public IntegerFraction inverse(IntegerFraction fr) {
		
		IntegerFraction result = new IntegerFraction();
		
		result.setNumerator(fr.getDenominator());
		result.setDenominator(fr.getNumerator());
		
		return result;
	}	

}
