package de.ertlu.rob.MathEx;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class MathEx {
    public static int defaultScale = 36;
    public static RoundingMode defaultRoundingMode = RoundingMode.HALF_UP;

    public static final BigInteger BI_n1 = BigInteger.valueOf(-1);
    public static final BigInteger BI_0 = BigInteger.ZERO;
    public static final BigInteger BI_1 = BigInteger.ONE;
    public static final BigInteger BI_2 = BigInteger.valueOf(2);
    public static final BigInteger BI_3 = BigInteger.valueOf(3);
    public static final BigInteger BI_10 = BigInteger.TEN;

    public static final BigDecimal BD_0 = BigDecimal.ZERO;
    public static final BigDecimal BD_1 = BigDecimal.ONE;
    public static final BigDecimal BD_2 = BigDecimal.valueOf(2);
    public static final BigDecimal BD_10 = BigDecimal.valueOf(10);
    
    public static boolean equals(BigDecimal a, BigDecimal b) {
    	return equals(a, b, defaultScale, defaultRoundingMode);
    }
    public static boolean equals(BigDecimal a, BigDecimal b, int scale) {
    	return equals(a, b, scale, defaultRoundingMode);
    }
    public static boolean equals(BigDecimal a, BigDecimal b, int scale, RoundingMode roundingMode) {
    	return a.setScale(scale, roundingMode).compareTo(b.setScale(scale, roundingMode)) == 0;
    }
    
    public static BigInteger factorial(BigInteger value) {
    	if ( value.compareTo(BI_2) < 0 ) return BI_1;
    	return value.multiply(factorial(value.subtract(BI_1)));
    }

    public static BigDecimal ln(BigDecimal value) {
        return ln(value, defaultScale, defaultRoundingMode);
    }
    public static BigDecimal ln(BigDecimal value, int scale, RoundingMode roundingMode) {
        return BigNewtonRaphsonMethod.Logarithm.solve(value, scale, roundingMode);
    }

    public static BigDecimal log(BigDecimal value, BigDecimal base) {
        return log(value, base, defaultScale, defaultRoundingMode);
    }
    public static BigDecimal log(BigDecimal value, BigDecimal base, int scale, RoundingMode roundingMode) {
        return ln(value).divide(ln(base), scale * 2, roundingMode);
    }
    
    public static BigDecimal raise(BigDecimal value, BigInteger exponent) {
    	if ( exponent.compareTo(BI_0) < 0 ) return BD_1.divide(raise(value, exponent.multiply(BI_n1)));
    	if ( exponent.compareTo(BI_0) == 0 ) return BD_1;
    	if ( exponent.compareTo(BI_1) == 0 ) return value;
    	if ( exponent.mod(BI_2).compareTo(BI_0) == 0 ) return raise(value.multiply(value), exponent.shiftRight(1));
    	return value.multiply(raise(value.multiply(value), exponent.subtract(BI_1).shiftRight(1)));
    }
    public static BigDecimal raise(BigDecimal value, BigDecimal exponent) {
    	BigRational approximateExponent = new BigRational(exponent);
    	BigDecimal valueRoot = BigNewtonRaphsonMethod.NthRoot.solve(value, approximateExponent.denominator);
    	return raise(valueRoot, approximateExponent.numerator);
    }

    public static BigDecimal reciprocal(BigInteger value) {
    	return reciprocal(new BigDecimal(value), defaultScale, defaultRoundingMode);
    }
    public static BigDecimal reciprocal(BigInteger value, int scale, RoundingMode roundingMode) {
    	return reciprocal(new BigDecimal(value), scale, roundingMode);
    }
    public static BigDecimal reciprocal(BigDecimal value) {
    	return reciprocal(value, defaultScale, defaultRoundingMode);
    }
    public static BigDecimal reciprocal(BigDecimal value, int scale, RoundingMode roundingMode) {
    	return BD_1.divide(value, scale * 2, roundingMode);
    }
}
