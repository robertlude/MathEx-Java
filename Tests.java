package de.ertlu.rob.MathEx;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tests {
	public static final int TEST_SCALE = 10;
	
	private static final int BigRationalTestCount = 3;
	private static List<String> BigRationalValue = Collections.unmodifiableList(Arrays.asList("0.75","3.1415926535897932384626433832795028841971693993751058","2.7182818284590452353602874713526624977572470936999595"));
	private static List<Integer> BigRationalExpectedNumerator = Collections.unmodifiableList(Arrays.asList(3,1146408,14665106));
	private static List<Integer> BigRationalExpectedDenominator = Collections.unmodifiableList(Arrays.asList(4,364913,5394991));
	
	private static final int BigNewtonRaphsonSolverNthRootTestCount = 3;
	private static List<String> BigNewtonRaphsonSolverNthRootRadicand = Collections.unmodifiableList(Arrays.asList("27","777","3.1415926535897932384626433832795028841971693993751058"));
	private static List<Integer> BigNewtonRaphsonSolverNthRootDegree = Collections.unmodifiableList(Arrays.asList(3,10,20));
	private static List<String> BigNewtonRaphsonSolverNthRootExpectedResult = Collections.unmodifiableList(Arrays.asList("3","1.9455486801207226424191929698851300406254633521468308","1.0589062060597545193339155633578975929059121117679223"));
	
	private static final int BigPowerSeriesExponentialTestCount = 3;
	private static List<String> BigPowerSeriesExponentialX = Collections.unmodifiableList(Arrays.asList("1","2","3"));
	private static List<String> BigPowerSeriesExponentialExpectedResult = Collections.unmodifiableList(Arrays.asList("2.7182818284590452353602874713526624977572470936999595","7.3890560989306502272304274605750078131803155705518473","20.085536923187667740928529654581717896987907838554150"));
	
	private static final int BigNewtonRaphsonSolverLogarithmTestCount = 3;
	private static List<String> BigNewtonRaphsonSolverLogarithmX = Collections.unmodifiableList(Arrays.asList("1","2","3"));
	private static List<String> BigNewtonRaphsonSolverLogarithmExpectedResult = Collections.unmodifiableList(Arrays.asList("0","0.6931471805599453094172321214581765680755001343602552","1.0986122886681096913952452369225257046474905578227494"));
	
	public static void main(String[] args) {
		test_BigRational();
		test_BigNewtonRaphsonSolver_NthRoot();
		test_BigPowerSeries_Exponential();
		test_BigNewtonRaphsonSolver_Logarithm();
	}
	
	private static void test_BigRational() {
		System.out.print("Testing BigRational\n");
		int testsPassed = 0;
		for ( int i = 0 ; i < BigRationalTestCount ; ++i ) {
			BigRational test = new BigRational(new BigDecimal(BigRationalValue.get(i)));
			if ( BigInteger.valueOf(BigRationalExpectedNumerator.get(i)).compareTo(test.numerator) == 0 && BigInteger.valueOf(BigRationalExpectedDenominator.get(i)).compareTo(test.denominator) == 0 ) {
				++testsPassed;
			} else {
				System.out.print("    Test " + (i + 1) + " failed: " + test + "\n");
			}
		}
		System.out.print("  Passed " + testsPassed + "/" + BigNewtonRaphsonSolverNthRootTestCount + "\n");
	}
	
	private static void test_BigNewtonRaphsonSolver_NthRoot() {
		System.out.print("Testing BigNewtonRaphsonSolver.NthRoot\n");
		int testsPassed = 0;
		for ( int i = 0 ; i < BigNewtonRaphsonSolverNthRootTestCount ; ++i ) {
			BigDecimal result = BigNewtonRaphsonMethod.NthRoot.solve(new BigDecimal(BigNewtonRaphsonSolverNthRootRadicand.get(i)), BigInteger.valueOf(BigNewtonRaphsonSolverNthRootDegree.get(i))); 
			BigDecimal expectation = new BigDecimal(BigNewtonRaphsonSolverNthRootExpectedResult.get(i)); 
			if ( checkMatch(result, expectation) ) {
				++testsPassed;
			} else {
				System.out.print("Test #" + (i + 1) + " failed: " + result + "\n");
			}
		}
		System.out.print("  Passed " + testsPassed + "/" + BigNewtonRaphsonSolverNthRootTestCount + "\n");
	}
	
	private static void test_BigPowerSeries_Exponential() {
		System.out.print("Testing BigFormalPowerSeries.Exponential\n");
		int testsPassed = 0;
		for ( int i = 0 ; i < BigPowerSeriesExponentialTestCount ; ++i ) {
			BigDecimal result = BigPowerSeries.Exponential.f(new BigDecimal(BigPowerSeriesExponentialX.get(i)), TEST_SCALE, RoundingMode.HALF_EVEN);
			BigDecimal expectation = new BigDecimal(BigPowerSeriesExponentialExpectedResult.get(i));
			if ( checkMatch(result, expectation) ) {
				++testsPassed;
			} else {
				System.out.print("Test #" + (i + 1) + " failed: " + result + "\n");
			}
		}
		System.out.print("  Passed " + testsPassed + "/" + BigPowerSeriesExponentialTestCount + "\n");
	}
	
	private static void test_BigNewtonRaphsonSolver_Logarithm() {
		System.out.print("Testing BigNewtonRaphsonSolver.Logarithm\n");
		int testsPassed = 0;
		for ( int i = 0 ; i < BigNewtonRaphsonSolverLogarithmTestCount ; ++i ) {
			BigDecimal result = BigNewtonRaphsonMethod.Logarithm.solve(new BigDecimal(BigNewtonRaphsonSolverLogarithmX.get(i))); 
			BigDecimal expectation = new BigDecimal(BigNewtonRaphsonSolverLogarithmExpectedResult.get(i)); 
			if ( checkMatch(result, expectation) ) {
				++testsPassed;
			} else {
				System.out.print("Test #" + (i + 1) + " failed: " + result + "\n");
			}
		}
		System.out.print("  Passed " + testsPassed + "/" + BigNewtonRaphsonSolverNthRootTestCount + "\n");
	}
	
	private static boolean checkMatch(BigDecimal value, BigDecimal expected) {
		return value.setScale(TEST_SCALE, RoundingMode.HALF_EVEN).compareTo(expected.setScale(TEST_SCALE, RoundingMode.HALF_EVEN)) == 0;
	}
}
