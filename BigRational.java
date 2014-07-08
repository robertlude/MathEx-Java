package de.ertlu.rob.MathEx;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigRational {
	public static final BigInteger DEFAULT_MAXIMUM_DENOMINATOR = BigInteger.valueOf(1000000);
	
	public final BigInteger numerator;
	public final BigInteger denominator;
	
	public BigRational(BigDecimal approximateValue) {
		this(approximateValue, DEFAULT_MAXIMUM_DENOMINATOR);
	}
	public BigRational(BigDecimal approximateValue, BigInteger maximumDenominator) {
		BigInteger m00 = BigInteger.ONE;
		BigInteger m01 = BigInteger.ZERO;
		BigInteger m10 = BigInteger.ZERO;
		BigInteger m11 = BigInteger.ONE;
		BigDecimal x = approximateValue;
		BigInteger xRoundedInt, temp;
		BigDecimal xRoundedDec;
		
		BigInteger n = m00;
		BigInteger d = m10;
		
		while ( m10.multiply(xRoundedInt = x.setScale(0, RoundingMode.HALF_EVEN).toBigInteger()).add(m11).compareTo(maximumDenominator) <= 0) {
			xRoundedDec = new BigDecimal(xRoundedInt);
			
			temp = m00.multiply(xRoundedInt).add(m01);
			m01 = m00;
			m00 = temp;
			
			temp = m10.multiply(xRoundedInt).add(m11);
			m11 = m10;
			m10 = temp;
			
			if ( x.compareTo(xRoundedDec) == 0 ) break;
			
			x = BigDecimal.ONE.divide(x.subtract(xRoundedDec), 34, RoundingMode.HALF_EVEN);
		}

		n = m00;
		d = m10;
		
		if ( d.compareTo(BigInteger.ZERO) < 0 ) {
			n = n.multiply(BigInteger.valueOf(-1));
			d = d.multiply(BigInteger.valueOf(-1));
		}
		
		numerator = n;
		denominator = d;
	}
	
	public String toString() {
		return "(" + numerator + "/" + denominator + ")";
	}
}
