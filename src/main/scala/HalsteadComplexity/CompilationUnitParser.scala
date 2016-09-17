package HalsteadComplexity

import org.eclipse.jdt.core.dom._

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
  * Created by krbalmryde on 9/15/16.
  *
  * This class wraps the parsing behavior found in the AST. It is the
  * primary entry point into parsing a set of Java source files.
  */
class CompilationUnitParser() {
    val parser = ASTParser.newParser(AST.JLS8)
        parser.setKind(ASTParser.K_COMPILATION_UNIT)
        parser.setResolveBindings(true)

    // We store the operators and operands in a mutable ListBuffer containing
    // strings. The intent here is to simply aggregate the list until we have a total
    // count of all operators and operands. That is a far as this step proceeds before
    // handing this information to the Metric Calculator
    var operators:ArrayBuffer[String] = ArrayBuffer[String]()
    var operands:ArrayBuffer[String] = ArrayBuffer[String]()

    def parse(source: String) = {

        parser.setSource(source.toCharArray)

        println("\n\nParse is running!\n")
        val cu = parser.createAST(null).asInstanceOf[CompilationUnit]
        cu.accept(new ASTVisitor() {

            override def visit(node: MethodDeclaration):Boolean = {
                val name:SimpleName = node.getName
                operators += name.getIdentifier
                //                println("Operator Method: '" + name + "' at line" + cu.getLineNumber( name.getStartPosition ) )
                println("Method-def:   \t" + name.getIdentifier + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: MethodInvocation): Boolean = {
                val name:String = node.getName.getIdentifier
                println("MethodInvke:   \t'" + name + "': " + cu.getLineNumber( node.getStartPosition ))
                true
            }

            override def visit(node: VariableDeclarationFragment):Boolean = {
                println("Variable:     \t" + node.getName + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }
            override def visit(node: NameQualifiedType):Boolean = {
                println("NameQualType: \t" + node.getName + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: IfStatement):Boolean = {
                println("IF:           \tIF-else: " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: ConditionalExpression):Boolean = {
                println("Conditional:  \t?-: : " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: WhileStatement):Boolean = {
                println("While-loop:   \tWhile: " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: ForStatement):Boolean = {
                println("For-loop:   \tFor: " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: EnhancedForStatement):Boolean = {
                println("Enhanced-For: \tFor: " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: ParenthesizedExpression): Boolean = {
                println("Parenthesize: \t(): "+ cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: Block): Boolean = {
                val names = node.statements.asScala
                println("Block:        \t{}: " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: PrimitiveType): Boolean = {
                val name:String = node.getPrimitiveTypeCode.toString
                println("PrimitiveType:\t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: NumberLiteral): Boolean = {
                println("NumberLiteral:\t" + node + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: StringLiteral): Boolean = {
                println("StringLiteral:\t" + node + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }


            override def visit(node: Assignment): Boolean = {
                val name:String = node.getOperator.toString
                println("Assignment:   \t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }


            // This will grab all INFIX operators of the following:
            override def visit(node: InfixExpression): Boolean = {
                val name:String = node.getOperator.toString
                println("InfixExpress: \t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            // This will grab all POSTFIX operators of the following:
            override def visit(node: PostfixExpression): Boolean = {
                val name:String = node.getOperator.toString
                println("PostExpress:  \t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }


            override def visit(node: PrefixExpression): Boolean = {
                val name:String = node.getOperator.toString
                println("PreFixExpress:\t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                true
            }

            override def visit(node: SimpleType): Boolean = {
                val name:String = node.getName.getFullyQualifiedName
                println("SimpleType:   \t'" + node + "': " + cu.getLineNumber( node.getStartPosition ))
                true
            }

            override def visit(node: SimpleName): Boolean = {
                val name:String = node.getIdentifier
                println("SimpleName:   \t" + node + "': " + cu.getLineNumber( node.getStartPosition ) )
                true
            }

        })

    }

}
