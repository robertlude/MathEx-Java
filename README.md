# MathEx-Java 0.2.2

MathEx-Java is a Java Math library supporting big numbers by Robert Lude.

MathEx-Java aims to provide accurate implementations of useful mathematical concepts to Java.

## Usage

At your project source root, type

`git submodule add git@github.com:robertlude/MathEx-Java.git de/ertlu/rob/MathEx`

## Components of MathEx

* `BigNewtonRaphsonMethod` - A Newton-Raphson method wrapper
  * `NthRoot` - N<sup>th</sup> root finder
  * `Logarithm` - Natural logarithm finder
* `BigPowerSeries` - A power series wrapper
  * `Exponential` - Calculates exponential function
* `BigRational` - Rational wrapper that can approximate to any desired position
* `MathEx` - Miscellaneous functions
  * `equals`
  * `factorial`
  * `ln`
  * `log`
  * `max`
  * `min`
  * `raise`
  * `reciprocal`

## History

v0.2.2

* Added min/max BigDecimal functions to MathEx

v0.2.1

* Made `BigNewtonRaphsonMethod.NthRoot` and `BigNewtonRaphsonMethod.Logarithm` thread-safe

v0.2.0

* Added convenience functions and constants

v0.1.0

* Implemented `BigNewtonRaphsonMethod`, `BigPowerSeries`, and `BigRational`
