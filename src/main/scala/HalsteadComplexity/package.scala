import java.io.File

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


    /**
      * Name:
      *     ParseFilesInDir
      *
      * Description:
      *     Recursively parses Files in the local project Resources/ directory producing
      *     an array of Strings containing file paths to each of the source files
      *     found.
      *
      * Source:
      *     This function was adapted from the accepted answer of this StackOverflow question
      *     http://stackoverflow.com/questions/2637643/how-do-i-list-all-files-in-a-subdirectory-in-scala
      *
      * @return Array[String]
      */
    def parseFilesInDir(dir: File): Array[File] = {
        val files = dir.listFiles
        val allFiles = files ++ files.filter(_.isDirectory).flatMap(parseFilesInDir)
        allFiles.filter( f => """.*\.java$""".r.findFirstIn(f.getName).isDefined)
    }
}
