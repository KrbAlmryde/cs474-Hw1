/**
  * Created by krbalmryde on 9/14/16.
  */

import java.io.IOException

import HalsteadComplexity.CompilationUnitParser
import org.eclipse.jdt.core.JavaCore

import scala.io.Source
import scala.collection.mutable._
import scala.collection.JavaConverters._
import org.eclipse.jdt.core.dom._
import org.eclipse.jface.text._
import org.eclipse.text.edits._


object Main {

    def parse(source: String) = {

        val parser = ASTParser.newParser(AST.JLS8)
        parser.setSource(source.toCharArray)
        parser.setKind(ASTParser.K_COMPILATION_UNIT)
        parser.setResolveBindings(true)

        println("Parse is running!" + parser)
        val cu = parser.createAST(null).asInstanceOf[CompilationUnit]
        cu.accept(new ASTVisitor() {
            var names:Set[String] = Set()

            override def visit(node: VariableDeclarationFragment):Boolean = {
                val name:SimpleName = node.getName
                names += name.getIdentifier
                println("Declaration of '" + name + "' at line"
                        + cu.getLineNumber( name.getStartPosition )
                )
                false
            }

            override def visit(node: SimpleName):Boolean = {
                if ( names.contains( node.getIdentifier ) ) {
                    println("Usage of '" + node + "' at line "
                            + cu.getLineNumber( node.getStartPosition )
                    )
                }
                true
            }


        })

    }

    def parse2(source: String) = {
        val parser = ASTParser.newParser(AST.JLS8)

        parser.setSource("int i = 9; \n int j = i+1;".toCharArray)
//        parser.setSource(source.toCharArray)
//        parser.setKind(ASTParser.K_COMPILATION_UNIT)

        parser.setKind(ASTParser.K_STATEMENTS);

        val block = parser.createAST(null).asInstanceOf[Block]

        //here can access the first element of the returned statement list
        val str = block.statements().get(0).toString();

        System.out.println(str)

        block.accept(new ASTVisitor() {

            override def visit(node: SimpleName):Boolean = {

                println("Name: " + node.getFullyQualifiedName())

                true
            }

        });
    }


    def main(args: Array[String]): Unit = {
        val sourceFile = Source.fromFile("./src/main/java/Readable.java")
        val cuParser = new CompilationUnitParser()
        cuParser.parse(sourceFile.mkString)

//        parse(sourceFile.mkString)

    }

}
