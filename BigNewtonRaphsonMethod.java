package de.ertlu.rob.MathEx;

import android.util.Log;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public abstract class BigNewtonRaphsonMethod {
	public static class NthRootNewtonRaphsonSolver extends BigNewtonRaphsonMethod {
		private BigDecimal radicand;
		private BigInteger degree;
		
		public BigDecimal f(BigDecimal x) {
			return MathEx.raise(x, degree).subtract(radicand);
		}
		
		public BigDecimal fPrime(BigDecimal x) {
			return (new BigDecimal(degree)).multiply(MathEx.raise(x,degree.subtract(MathEx.BI_1)));
		}
		
		public BigDecimal solve(BigDecimal radicand, BigInteger degree) {
			return solve(radicand, degree, MathEx.BD_1, MathEx.defaultScale, MathEx.defaultRoundingMode);
		}
		public BigDecimal solve(BigDecimal radicand, BigInteger degree, BigDecimal guess, int scale, RoundingMode roundingMode) {
			this.radicand = radicand;
			this.degree = degree;
			return solveLoop(guess, scale, roundingMode);
		}
	}
	
	public static class LogarithmNewtonRaphsonSolver extends BigNewtonRaphsonMethod {
		private BigDecimal value;
		
		@Override
		BigDecimal f(BigDecimal x) {
			return BigPowerSeries.Exponential.f(x).subtract(value);
		}

		@Override
		BigDecimal fPrime(BigDecimal x) {
			return BigPowerSeries.Exponential.f(x);
		}
		
		public BigDecimal solve(BigDecimal value) {
			return solve(value, MathEx.BD_1, MathEx.defaultScale, MathEx.defaultRoundingMode);
		}
		public BigDecimal solve(BigDecimal value, BigDecimal guess, int scale, RoundingMode roundingMode) {
			this.value = value;
			return solveLoop(guess, scale, roundingMode);
		}
		
	}

	public static final NthRootNewtonRaphsonSolver NthRoot = new NthRootNewtonRaphsonSolver();
	public static final LogarithmNewtonRaphsonSolver Logarithm = new LogarithmNewtonRaphsonSolver();
	
	abstract BigDecimal f(BigDecimal x);
	abstract BigDecimal fPrime(BigDecimal x);
	
	protected BigDecimal solveLoop(BigDecimal guess, int scale, RoundingMode roundingMode) {
		BigDecimal lastGuess = null;
		BigDecimal change;
		while ( lastGuess == null || lastGuess.setScale(scale, roundingMode).compareTo(guess.setScale(scale, roundingMode)) != 0 ) {
			change = f(guess).divide(fPrime(guess), scale * 2, roundingMode);
			lastGuess = guess;
			guess = guess.subtract(change);
		}
		return guess;
	}
}
