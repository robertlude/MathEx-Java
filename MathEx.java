package de.ertlu.rob.MathEx;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public final class MathEx {
    public static int defaultScale = 36;
    public static RoundingMode defaultRoundingMode = RoundingMode.HALF_UP;

    public static final BigInteger BI_n1 = BigInteger.valueOf(-1);
    public static final BigInteger BI_0 = BigInteger.ZERO;
    public static final BigInteger BI_1 = BigInteger.ONE;
    public static final BigInteger BI_2 = BigInteger.valueOf(2);
    public static final BigInteger BI_3 = BigInteger.valueOf(3);
    public static final BigInteger BI_4 = BigInteger.valueOf(4);
    public static final BigInteger BI_5 = BigInteger.valueOf(5);
    public static final BigInteger BI_6 = BigInteger.valueOf(6);
    public static final BigInteger BI_7 = BigInteger.valueOf(7);
    public static final BigInteger BI_8 = BigInteger.valueOf(8);
    public static final BigInteger BI_9 = BigInteger.valueOf(9);
    public static final BigInteger BI_10 = BigInteger.TEN;
    public static final BigInteger BI_20 = BigInteger.valueOf(20);
    public static final BigInteger BI_30 = BigInteger.valueOf(30);
    public static final BigInteger BI_40 = BigInteger.valueOf(40);
    public static final BigInteger BI_50 = BigInteger.valueOf(50);
    public static final BigInteger BI_60 = BigInteger.valueOf(60);
    public static final BigInteger BI_70 = BigInteger.valueOf(70);
    public static final BigInteger BI_80 = BigInteger.valueOf(80);
    public static final BigInteger BI_90 = BigInteger.valueOf(90);
    public static final BigInteger BI_100 = BigInteger.valueOf(100);
    public static final BigInteger BI_200 = BigInteger.valueOf(200);
    public static final BigInteger BI_300 = BigInteger.valueOf(300);
    public static final BigInteger BI_400 = BigInteger.valueOf(400);
    public static final BigInteger BI_500 = BigInteger.valueOf(500);
    public static final BigInteger BI_600 = BigInteger.valueOf(600);
    public static final BigInteger BI_700 = BigInteger.valueOf(700);
    public static final BigInteger BI_800 = BigInteger.valueOf(800);
    public static final BigInteger BI_900 = BigInteger.valueOf(900);
    public static final BigInteger BI_1000 = BigInteger.valueOf(1000);

    public static final BigDecimal BD_0 = BigDecimal.ZERO;
    public static final BigDecimal BD_1 = BigDecimal.ONE;
    public static final BigDecimal BD_2 = BigDecimal.valueOf(2);
    public static final BigDecimal BD_3 = BigDecimal.valueOf(3);
    public static final BigDecimal BD_4 = BigDecimal.valueOf(4);
    public static final BigDecimal BD_5 = BigDecimal.valueOf(5);
    public static final BigDecimal BD_6 = BigDecimal.valueOf(6);
    public static final BigDecimal BD_7 = BigDecimal.valueOf(7);
    public static final BigDecimal BD_8 = BigDecimal.valueOf(8);
    public static final BigDecimal BD_9 = BigDecimal.valueOf(9);
    public static final BigDecimal BD_10 = BigDecimal.valueOf(10);
    public static final BigDecimal BD_20 = BigDecimal.valueOf(20);
    public static final BigDecimal BD_30 = BigDecimal.valueOf(30);
    public static final BigDecimal BD_40 = BigDecimal.valueOf(40);
    public static final BigDecimal BD_50 = BigDecimal.valueOf(50);
    public static final BigDecimal BD_60 = BigDecimal.valueOf(60);
    public static final BigDecimal BD_70 = BigDecimal.valueOf(70);
    public static final BigDecimal BD_80 = BigDecimal.valueOf(80);
    public static final BigDecimal BD_90 = BigDecimal.valueOf(90);
    public static final BigDecimal BD_100 = BigDecimal.valueOf(100);
    public static final BigDecimal BD_200 = BigDecimal.valueOf(200);
    public static final BigDecimal BD_300 = BigDecimal.valueOf(300);
    public static final BigDecimal BD_400 = BigDecimal.valueOf(400);
    public static final BigDecimal BD_500 = BigDecimal.valueOf(500);
    public static final BigDecimal BD_600 = BigDecimal.valueOf(600);
    public static final BigDecimal BD_700 = BigDecimal.valueOf(700);
    public static final BigDecimal BD_800 = BigDecimal.valueOf(800);
    public static final BigDecimal BD_900 = BigDecimal.valueOf(900);
    public static final BigDecimal BD_1000 = BigDecimal.valueOf(1000);
    
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
        return BigNewtonRaphsonMethod.Logarithm.solve(value, BD_1, scale, roundingMode);
    }

    public static BigDecimal log(BigDecimal value, BigDecimal base) {
        return log(value, base, defaultScale, defaultRoundingMode);
    }
    public static BigDecimal log(BigDecimal value, BigDecimal base, int scale, RoundingMode roundingMode) {
        return ln(value, scale, roundingMode).divide(ln(base, scale, roundingMode), scale * 2, roundingMode);
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

    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) < 0 ? a : b;
    }
    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static byte clamp(final byte min, final byte value, final byte max) {
        if ( value <= min ) return min;
        if ( value >= max ) return max;
        return value;
    }
    public static char clamp(final char min, final char value, final char max) {
        if ( value <= min ) return min;
        if ( value >= max ) return max;
        return value;
    }
    public static short clamp(final short min, final short value, final short max) {
        if ( value <= min ) return min;
        if ( value >= max ) return max;
        return value;
    }
    public static int clamp(final int min, final int value, final int max) {
        if ( value <= min ) return min;
        if ( value >= max ) return max;
        return value;
    }
    public static long clamp(final long min, final long value, final long max) {
        if ( value <= min ) return min;
        if ( value >= max ) return max;
        return value;
    }
    public static float clamp(final float min, final float value, final float max) {
        if ( value <= min ) return min;
        if ( value >= max ) return max;
        return value;
    }
    public static double clamp(final double min, final double value, final double max) {
        if ( value <= min ) return min;
        if ( value >= max ) return max;
        return value;
    }
    public static BigInteger clamp(final BigInteger min, final BigInteger value, final BigInteger max) {
        if ( value.compareTo(min) <= 0 ) return min;
        if ( value.compareTo(max) >= 0 ) return max;
        return value;
    }
    public static BigDecimal clamp(final BigDecimal min, final BigDecimal value, final BigDecimal max) {
        if ( value.compareTo(min) <= 0 ) return min;
        if ( value.compareTo(max) >= 0 ) return max;
        return value;
    }
}
