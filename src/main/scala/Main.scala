/**
  * Created by krbalmryde on 9/14/16.
  */

// Java specific imports
import java.io.File

// Scala specific imports
import scala.io.Source
import HalsteadComplexity._

object Main extends App {

    val cuParser = new CompilationUnitParser()

    // Traverse the supplied directory and extract our Java source files
    // These will be stored in an array
    val sourceFiles = parseFilesInDir( new File("./src/test/simple/Readable.java"))

    // Parse each source file with our CompilationUnityParser
    sourceFiles.foreach( f => {
        println(f)
        cuParser.parse(Source.fromFile(f).mkString)
    })
    println("Parsed " + sourceFiles.length + " files!")
    cuParser.computeMetrics()
}
