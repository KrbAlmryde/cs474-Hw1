package HalsteadComplexity

/**
  * Created by krbalmryde on 9/15/16.
  *
  * HalsteadComplexity.HalsteadMetric computes the metric of the same name
  * constructor arguments provide the necesasry inputs to
  * calculate the software metric
  *
  * @param n1 Double -- number of distinct operators
  * @param n2 Double -- number of distinct operands
  * @param N1 Double -- total number of operators
  * @param N2 Double -- total number of operands
  */
class HalsteadMetric(n1:Double, n2:Double, N1:Double, N2:Double) {

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
