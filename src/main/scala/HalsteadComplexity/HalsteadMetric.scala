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
    var n1:Double = operators.toSet.size
    var n2:Double = operands.toSet.size
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

    def reset(operators:ArrayBuffer[String], operands:ArrayBuffer[String] ): Unit = {
        n1 = operands.toSet.size
        n2 = operators.toSet.size
        N1 = operators.size
        N2 = operands.size
    }

    def results(): String = {
        "\nn1: number of Distinct Operators: " + n1 +
        "\nn2: number of Distinct Operands: " + n2 +
        "\nN1: Total number of Operators: " + N1 +
        "\nN2: Total number of Operands: " + N2 +
        "\n\n-----------------------------" +
        "\n\nProgram Vocabulary: " + n +
        "\nProgram Length: " + N +
        "\nCalculated program length: " + _N +
        "\nProgram Volume: " + V +
        "\nProgram Difficulty: " + D +
        "\nProgram Effort: " + E +
        "\n\n-----------------------------" +
        "\n\nTime required to program: " + T + " seconds" +
        "\n\n-----------------------------" +
        "\n\nNumber of delivered bugs: " +
        "\n\tMethod 1: " + B +
        "\n\tMethod 2: " + B2 +
        "\n\n-----------------------------"
    }


}
