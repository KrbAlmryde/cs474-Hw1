package HalsteadComplexity

import java.util

import org.eclipse.jdt.core.dom._

import scala.collection.JavaConverters._
import scala.collection.mutable.{ArrayBuffer,HashSet, HashMap}

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


    // This is a helper to make sure we keep track of the declared variable names weve
    // already parsed
    var declared = HashMap[String, Int]()
    // Creates and Instance of the HalsteadMetric class, supplies it with
    // the appropriate operators and operands, and performs the final calculation
    def computeMetrics(): Unit = {
        val metric = new HalsteadMetric(operators, operands)
        println( metric.results )
    }


    // This is the heavy lifting function. Parses the supplied file
    // identifying operands and operators.
    def parse(source: String): Unit = {

        // Set the source
        parser.setSource(source.toCharArray)

        // Lets me know things are working
        println("\n\nParse is running!\n")

        // Create our CompilationUnit
        val cu = parser.createAST(null).asInstanceOf[CompilationUnit]

        // Dive! Dive! Dive!
        // Begin tree traversal
        cu.accept(new ASTVisitor() {

            // Our Method Declartion Node handler
            override def visit(node: MethodDeclaration):Boolean = {
                // Clears the names Hash so we start clean and clear
                declared.clear()
                val name:SimpleName = node.getName
                operators+= name.toString
                println("\n\nMethod-def:   \t" + name + ": " + cu.getLineNumber(node.getStartPosition) )

                // In order to capture the Parameter names, we have traverse the children
                node.accept(new ASTVisitor() {
                    // Just want to catch the SingleVariableDeclaration
                    override def visit(node: SingleVariableDeclaration): Boolean = {
                        val name: SimpleName = node.getName
                        declared.put(name.getIdentifier, cu.getLineNumber(name.getStartPosition))

                        System.out.println("\tParameter of '" + name.getIdentifier + declared + "' at line " + cu.getLineNumber(name.getStartPosition))
                        false
                    }
                })
                true
            }


            override def visit(node: MethodInvocation): Boolean = {
                val name:String = node.getName.getIdentifier
                operators+= name.toString
                println("\t\tMethodInvke:   \t'" + name + "': " + cu.getLineNumber( node.getStartPosition ))
                true
            }

            // Capture and Variables declared, with or without values assigned.
            // In the case of variables WITH an assignment, we will also count that
            // assignment operator towards our total
            override def visit(node: VariableDeclarationFragment): Boolean = {
                val name: String = node.getName.getIdentifier
                val lineNum:Int = cu.getLineNumber(node.getStartPosition)

                // In case we encounter something weird...
                try {
                    val value = node.getInitializer.toString
                    if (value != "null")
                        // This is a special case in which we also count the "=" sign
                        // since we know that a value has been assigned
                        operators+="="

                    println("\tDeclaration of '" + name + ":" + value + "' at line " + lineNum)
                }
                // Something weird was encountered...
                catch {
                    case npe: NullPointerException => println("\t\t....moving right along!" + npe)
                    case e => println("\t\t....Something fucked up...!" +  e)
                }
                // No matter, STAY THE COURSE!
                finally {
                    operands+=name
                    declared.put(name, cu.getLineNumber(node.getStartPosition))
                }

                true
            }

            override def visit(node: SimpleName): Boolean = {
                val name:String = node.getIdentifier
                val lineNum:Int = cu.getLineNumber(node.getStartPosition)
                if (declared.contains(name) && declared(name) != lineNum ) {
                    operands+=name
                    System.out.println("\t\tUsage of '" + name + "' at line " + lineNum)
                }
                true
            }

            override def visit(node: IfStatement):Boolean = {
                println("\t\tIF:           \tIF-else: " + cu.getLineNumber(node.getStartPosition) )
                operators+= "if-else"
                true
            }

            override def visit(node: WhileStatement):Boolean = {
                println("\t\tWhile-loop:   \tWhile: " + cu.getLineNumber(node.getStartPosition) )
                operators+= "while()"

                true
            }

            override def visit(node: ForStatement):Boolean = {
                println("\tFor-loop:   \tFor: " + cu.getLineNumber(node.getStartPosition) )
                operators+= "for(;;)"
                true
            }

            override def visit(node: EnhancedForStatement):Boolean = {
                println("\t\tEnhanced-For: \tFor: " + cu.getLineNumber(node.getStartPosition) )
                operators+= "for(:)"

                true
            }

//            override def visit(node: ExpressionStatement):Boolean = {
//                println("\n\tExpressState: \t" + node.getExpression + " " + cu.getLineNumber(node.getStartPosition) )
//                //                node.accept(new ASTVisitor() {
////                    override def visit(fd:VariableDeclarationFragment): Boolean = {
////                        println("   " + fd.getName + " in Expression: ");
////                        false
////                    }
////                })
//
//                true
//            }

            override def visit(node: ConditionalExpression):Boolean = {
                println("\tConditional:  \t?-: : " + cu.getLineNumber(node.getStartPosition) )
                operators+= "?:"
                true
            }

            override def visit(node: ParenthesizedExpression): Boolean = {
                println("Parenthesize: \t(): "+ cu.getLineNumber(node.getStartPosition) )
                operators+= "()"
                true
            }

            override def visit(node: Block): Boolean = {
                val names = node.statements.asScala
                println("Block:        \t{}: " + cu.getLineNumber(node.getStartPosition) )
                operators+= "{}"
                true
            }

            override def visit(node: PrimitiveType): Boolean = {
                val name:String = node.getPrimitiveTypeCode.toString
                println("\t\tPrimitiveType:\t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                operators+= name
                true
            }


            override def visit(node: BooleanLiteral): Boolean = {
                val name:String = node.booleanValue.toString
                println("\t\tBooleanLiteral:\t" + node.booleanValue + ": " + cu.getLineNumber(node.getStartPosition) )
                operators+= name
                true
            }

            override def visit(node: Assignment): Boolean = {
                val name:String = node.getOperator.toString
                println("\t\tAssignment:   \t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                operators+=name
                true
            }


            // This will grab all INFIX operators of the following:
            override def visit(node: InfixExpression): Boolean = {
                val name:String = node.getOperator.toString
                println("\t\tInfixExpress: \t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                operators+=name
                true
            }

            // This will grab all POSTFIX operators of the following:
            override def visit(node: PostfixExpression): Boolean = {
                val name:String = node.getOperator.toString
                println("\t\tPostExpress:  \t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                operators+=name
                true
            }


            override def visit(node: PrefixExpression): Boolean = {
                val name:String = node.getOperator.toString
                println("\t\tPreFixExpress:\t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                operators+=name
                true
            }

            override def visit(node: ParameterizedType): Boolean = {
                val name:String = node.getType.toString
                println("\t\tParameterizedType:   \t'" + node + "': " + cu.getLineNumber( node.getStartPosition ))
                operators+=name
                true
            }


            override def visit(node: SimpleType): Boolean = {
                val name:String = node.getName.getFullyQualifiedName
                println("\t\tSimpleType:   \t'" + node + "': " + cu.getLineNumber( node.getStartPosition ))
                operators+=name
                true
            }


            /*
                    START OF OPERANDS
             */

            override def visit(node: NumberLiteral): Boolean = {
                val name:String = node.getToken
                println("\t\tNumberLiteral:\t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                operands+= name
                true
            }

            override def visit(node: StringLiteral): Boolean = {
                val name:String = node.getLiteralValue
                println("\t\tStringLiteral:\t" + name + ": " + cu.getLineNumber(node.getStartPosition) )
                operands+= name
                true
            }






            //            override def visit(node: SimpleName): Boolean = {
            ////                val name:String = node.getIdentifier
            ////                false
            ////            }
            //
            //                val binding:IBinding  = node.resolveBinding
            //
            //                def isInstance(x:Any): Boolean = x match {
            //                    case b: IVariableBinding => true
            //                    case _ => false
            //                }
            //
            //                if ( isInstance(binding) ) {
            ////                    println("SimpleName:   \t" + node + "': " + cu.getLineNumber( node.getStartPosition ) )
            ////                    val variable:IVariableBinding  = binding.asInstanceOf[IVariableBinding];
            //                    System.out.println("Check!: " + node.toString)
            //
            //                }
            //
            //                true
            //            }


        })

    }

}
