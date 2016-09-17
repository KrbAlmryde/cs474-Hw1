/**
  * Created by krbalmryde on 9/15/16.
  */
package object HalsteadComplexity {
    val lnOf2 = math.log(2) // natural log of 2

    /**
      * log2
      * Healper function which calculates the base 2
      * logarithmic function of the supplied parameter
      *
      * This code was based off of sluu99's code found here:
      * https://gist.github.com/sluu99/2928186
      * @param x: Double a scalar value
      * @return Double: value of the expression
      */
    def log2(x: Double): Double = {
        math.log(x)/lnOf2
    }

    type Metric = Tuple4[Int, Int, Int, Int]
}
