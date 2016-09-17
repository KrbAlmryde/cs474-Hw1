package HalsteadComplexity

import scala.collection.mutable.ArrayBuffer

/**
  * Created by krbalmryde on 9/15/16.
  *
  * HalsteadComplexity.HalsteadMetric computes the metric of the same name
  * constructor arguments provide the necessary inputs to
  * calculate the software metric
  *
  * @param operators ArrayBuffer[String] -- Array of non-unique Operator names
  * @param operands ArrayBuffer[String]  -- Array of non-unique Operand names
  */
class HalsteadMetric(operators:ArrayBuffer[String], operands:ArrayBuffer[String]) {
    var n1:Double = operands.toSet.size
    var n2:Double = operators.toSet.size
    var N1:Double = operators.size
    var N2:Double = operands.size

    // Program Length
    def N(): Double = N1 + N2

    // Program Vocabulary
    def n(): Double = n1 + n2

    // Calculated Program Length
    def _N(): Double = n1 * log2(n2) + n2 * log2(n2)

    // Volume
    def V(): Double = N * log2( n() )

    // Difficulty
    def D(): Double =  (n1 / 2) * (N2 / n2)

    // Effort
    def E(): Double = D * V

    // Time required to program
    def T(): Double = E / 18

    // Number of delivered bugs
    def B(): Double = math.pow(E(), 2/3) / 3000
    def B2(): Double = V / 3000


    def results(): Unit = {
        println("n1: number of Distinct Operators: " + n1)
        println("n2: number of Distinct Operands: " + n2)

        println("N1: Total number of Operators: " + N1)
        println("N2: Total number of Operands: " + N2)

        println("-----------------------------")

        println("Program Vocabulary: " + n)
        println("Program Length: " + N)
        println("Calculated program length: " + _N)
        println("Program Volume: " + V)
        println("Program Difficulty: " + D)
        println("Program Effort: " + E)

        println("-----------------------------")

        println("Time required to program: " + T + " seconds")

        println("-----------------------------")

        println("Number of delivered bugs: ")
        println("\tMethod 1: " + B)
        println("\tMethod 2: " + B2)

        println("-----------------------------")
    }


}
