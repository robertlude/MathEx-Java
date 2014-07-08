package de.ertlu.rob.MathEx;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public abstract class BigPowerSeries {
	public static class ExponentialPowerSeries extends BigPowerSeries {
		@Override
		protected BigDecimal getCoefficient(BigInteger termIndex, int scale, RoundingMode roundingMode) {
			return MathEx.reciprocal(MathEx.factorial(termIndex), scale, roundingMode);
		}
	}
	
	public static final ExponentialPowerSeries Exponential = new ExponentialPowerSeries();
	
	protected BigDecimal getCoefficient(BigInteger termIndex, int scale, RoundingMode roundingMode) {
		return MathEx.BD_0;
	}
	
	public BigDecimal f(BigDecimal x) {
		return f(x, MathEx.defaultScale, MathEx.defaultRoundingMode);
	}
	public BigDecimal f(BigDecimal x, int scale, RoundingMode roundingMode) {
		BigDecimal lastValue = null;
		BigDecimal currentValue = MathEx.BD_0;
		BigDecimal term;
		for ( BigInteger termIndex = MathEx.BI_0 ; lastValue == null || (! MathEx.equals(lastValue, currentValue, scale, roundingMode)) ; termIndex = termIndex.add(MathEx.BI_1) ) {
			term = MathEx.raise(x, termIndex).multiply(getCoefficient(termIndex, scale, roundingMode));
			lastValue = currentValue;
			currentValue = currentValue.add(term);
		}
		return currentValue;
	}
}
