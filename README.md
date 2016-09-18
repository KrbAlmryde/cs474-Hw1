# README #

This README documents the installation and usage for the cs474 HW1 tasked with computing the
[Halstead Complexity Measure](https://en.wikipedia.org/wiki/Halstead_complexity_measures)  

### What is this repository for? ###

* Using the [Scala language](http://www.scala-lang.org/) this application parses a **Java** project, using the 
[Eclipse AST Parser](https://www.eclipse.org/articles/article.php?file=Article-JavaCodeManipulation_AST/index.html) 
to generate an Abstract Syntax tree in order to identify operators and operands which may be used to compute the 
**Halstead Complexity Measure**.  
* 1.1.0

### How do I get set up? ###

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