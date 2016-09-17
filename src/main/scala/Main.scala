/**
  * Created by krbalmryde on 9/14/16.
  */

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import java.io.{File, FileFilter, FileSystem}

import HalsteadComplexity.CompilationUnitParser

import scala.util.matching.Regex
import org.junit
object Main {

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

    def main(args: Array[String]): Unit = {

        val cuParser = new CompilationUnitParser()
        cuParser.parse(Source.fromFile("./src/main/resources/Readable.java").mkString)
    /*
        // Traverse the supplied directory and extract our Java source files
        // These will be stored in an array
        val sourceFiles = parseFilesInDir( new File("./src/main/resources"))

        // Parse each source file with our CompilationUnityParser
        sourceFiles.foreach( f => {
            println(f)
            cuParser.parse(Source.fromFile(f).mkString)
        })
    */

    }

}
