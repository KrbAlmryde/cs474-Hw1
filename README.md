# README #

This README documents the installation and usage for the cs474 HW1 tasked with computing the
[Halstead Complexity Measure](https://en.wikipedia.org/wiki/Halstead_complexity_measures)  

### What is this repository for? ###

* Using the [Scala language](http://www.scala-lang.org/) this application parses a **Java** project, using the 
[Eclipse AST Parser](https://www.eclipse.org/articles/article.php?file=Article-JavaCodeManipulation_AST/index.html) 
to generate an Abstract Syntax tree in order to identify operators and operands which may be used to compute the 
**Halstead Complexity Measure**.  

### Definitions and stuff

#### Operators ####
For the purpose of this application our basic definition of **operators** includes the following

* Those found in the [JLS-3.12](https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.12).
* **Method** signatures and invocations
* The following java defined **Keywords**
    * for, while, if-else, switch, case
    * Primitive types {boolean, int, long, float, etc.}
    * Block identifiers '{}'
    * Parenthesize ()


#### Operands ####
For the purpose of this application our basic definition of **operands** includes the following
* Java sanctioned **identifiers** 
*   This includes declarations as well as references within code blocks 
* Numerical Literals {1, 2, 3.0, 42 }



### How do I get set up? ###
Place your Test files/Folder/ that you would like to use to see if this application is up to spec in the following 
directory
        
        HW1/
            src/
                main/
                    resources/   // <--- HERE!!
                    
The application already points to the directory and will begin parsing immediately. The application does not make any 
distinctions between projects, that is, you will likely see a project called **AlphaSuperAwesomeCoolDynamiteWolfSquadron**,
another called **jedit-svn**. Unless you make explict effort to Target a particular project, in the file **Main.scala**
 
        HW1/
            src/
                scala/
                    Main.scala
        
This line #18        

       val sourceFiles = parseFilesInDir( new File("/path/to/your/test/repository") )
          

 
####Setting up from IntelliJ ####

1) If no project is currently open in IntelliJ IDEA, click **Import Project** on the Welcome screen. Otherwise, select **File | New | Project from Existing Sources**.

2) In the dialog that opens, select the directory that contains the project to be imported, or a file that contains an appropriate project description. Click **OK**.

3) On the first page of the **Import Project** wizard, select Gradle, and click **Next**. (This page is not shown if IntelliJ IDEA has guessed what you are importing.)

4) On the next page of the wizard, specify Gradle project settings and global Gradle settings, click **Finish**.

####From the commandline 

1) (Make sure you are in the root level of the project directory) type the following:

        gradle --daemon run
         
### Who do I talk to? ###

* If you have any specific questions contact me via [kyle.almryde@gmail.com](kyle.almryde@gmail.com)
* If you have any complaints, please direct them to 

![drmark@uic.edu](https://www.cs.uic.edu/~drmark/index_htm_files/3017.jpg)