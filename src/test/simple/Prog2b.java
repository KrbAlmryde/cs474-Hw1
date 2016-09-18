/*============================================================================== 
|     Assignment: Program #2(b): One-Month Calendar 
|         Author: Kyle Almryde
| Sect. Leader: Lane Simons
| 
|        Course: CSc 227 
|    Instructor: L. McCann 
|      Due Date: January 30, 2012, at 10:00 p.m.
|
|
|  Description:  This program requests from the User the numerical Month,
|                Day, and Year, in order to calculate to produces a One-Month
|                Gregorian Calendar. This program utilizes Babwani"s Congruence
|                to compute the starting date of the month the User chooses.
|                Following computation, this program will display to the
|                terminal the chosen Month and year followed by the computed
|                One month Calendar. This program WILL account for leap years
|                when prompted and will calculate the appropriate number of 
|                days. 
|                Note: This program checks for invalid input from the User. 
|                
|       Sources: "An extended approach to the Julian and the Gregorian Calendar”
|                Mathematical Gazette, November 2004 (vol. 88, no. 513 pages
|                569-573).
|
| Deficiencies: None; this program meets specifications.
*=============================================================================*/



import java.util.*;        // Gives easy access to Java API"s "util" package

public class Prog2b
{
    public static void main (String [] args)
    {
        int day,           //Day of the month e.g. 30 for January 30
            year,          //The Year e.g. 2007
            monthnum,      //The numerical Month e.g. 1 for January
            century,       //The first two digits of the year
            decade,        //The last two digits of the year
            counter,       //A counter used to build the month calendar
            length,        //The Length of the Month
            monthCode,     //The coded value required to compute Babwani's
                           //algorithm
            dW;            //The Babwani Congruence algortith for the day of

        String    month;   //The alphabetical Month, eg January, February etc

        Scanner keyboard = new Scanner (System.in); 

        System.out.println("\n The Gregorian Calendar is the internationally"
                        + " accepted civil calendar.This\n program will"
                        + " generate a One Month Gregorian Calendar based on"
                        + " the NUMERICAL\n MONTH, DAY, and YEAR of the User's" 
                        + " choosing. For example, if the date January\n 29th"
                        + " 2007 was desired, the User would enter: 1 29 2007");

    //-----The User will enter the numerical Month of their choosing-----//
        System.out.print("\nPlease enter the Month (1-12) (e.g. 29) : ");
        do {                                //This control structure will ensure
            monthnum = keyboard.nextInt();  //the User does not enter a negative
            if ( monthnum <= 0 )            //integer, or a value higher than 12
                System.out.print( monthnum + " is not a positive integer."
                                + "\nPlease enter an integer value greater than"
                                + " 0 : ");
        } while ( monthnum <= 0 || monthnum >12 );


    //-----The User will enter the numerical day or their choosing-----//
        System.out.print("Please enter the Day (e.g. 1) : "); 
        do {                            //This control structure will ensure
            day = keyboard.nextInt();   //the User does not enter a negative
            if ( day <= 0 )             //integer for the day variable
                System.out.print( day + " is not a positive integer."
                                + "\nPlease enter a value for Month greater "
                                + "than 0 : ");
        } while ( day <= 0 );


    //-----The User will enter the Numerical Year of their Choosing-----//
        System.out.print("Please enter the Year (e.g. 2007) : "); 
        
        do {                            //This control structure will ensure
            year = keyboard.nextInt();  //the User does not enter a negative
            if ( year <= 0 )            //integer for the year variable
                System.out.print( year + " is not a positive integer."
                                + "\nPlease enter an integer value greater than"
                                + " 0 : ");
        } while ( year <= 0 );



        century = (year/100);    //This will hold the first two digits of the
                                 //year variable, resulting in 20 for 2007
                                    
        decade=(year%100);       //This will hold the last two digits of the
                                 //year variable, resulting in 07 for 2007

/*------------------------------------------------------------------------------
|       Author's Note
|
|      The following block contains excerpted examples of the code used
|      to produce the present program. Due to the structure of the program,
|      the following pieces of code have been nested repeatedly within 
|      numerous control structures. This was done as part of the program
|      requirments. To improve readability and ease of comprehension, the
|      individual "snippets" of code (which would have been made into 
|      individual classes and methods if the option were available) have
|      been reproduced here. A description of their purpose and function
|      will be included.
|
--------------------------------------------------------------------------------
|      Babwani's Congruence
|
|      This is the code that performs the Babwani Congruence
|      calculation which determines the day of week. This code has
|      been indented to fit the 80 column limit of most terminals,
|      allowing for readability. 
|
|             dW = (int)((((Math.floor(5*decade/4)))
|                + +(monthCode)-((2) *(century%4)+7))%7);
|
--------------------------------------------------------------------------------
|       Leap year or not 
|
|      The following FOUR control sturctures determines whether 
|      the input year qualifies as a leap year or not. If the 
|      year is to be considered a leap year, it must either be
|      divisible by 4, 100, or 400. If it is not a leap year, 
|      then it will not be divisible. These control structures 
|      are for the months January and February, which have 
|      different month codes, and in the case of February, lengths,
|      depending on whether or not they fall within a leap year 
|      or a non leap year.
|
|      if ((monthnum == 1) && ((year % 4) == 0) || ((year % 400) == 0) 
|                                               && ((year % 100) != 0)) {
|
|
|      if ((monthnum == 1) && ((year % 4) != 0)){
|
--------------------------------------------------------------------------------
|      Month Building FOR-loop
|
|      This is the for loop used to construct the One month calendar
|      It uses the vaule produced by Babwani's Algorithim and User
|      input to place the appropriate amount of white space and in
|      order to correctly format the columns with each other. 
|
|        for (counter= 0; counter< dW; counter++)
|          System.out.print("    ");
|
|        for (counter= 1; counter<= length; counter++) {
|          if (counter< 10)
|            System.out.print("   " + counter);
|          else
|            System.out.print("  " + counter);
|
|          if ((counter+ dW) % 7 == 0)
|            System.out.println();
|        }
|
------------------------------------------------------------------------------*/


		//------------True Leap Year Control block------------//

           if ((monthnum == 1) && ((year % 4) == 0) || ((year % 400) == 0) 
                                                   && ((year % 100) != 0)) {
                month = "January";
                length = 31;                
                monthCode=6;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);

                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");

                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");

                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);

                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }
            if ((monthnum == 2) && ((year % 4) == 0) || ((year % 400) == 0) 
                                                    && ((year % 100) != 0)) {
                month = "February";
                length=29;
                monthCode=2;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }


		//------------False Leap Year Control block------------//

            if ((monthnum == 1) && ((year % 4) != 0)){
                month = "January";
                length = 31;                
                monthCode=0;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if ((monthnum == 2) && ((year % 4) != 0)){
                month = "February";
                length=28;
                monthCode=3;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }    
            }

		//------------Non-Leap Year Control blocks------------//

            if ( monthnum == 3 ) {
                month = "March";
                monthCode= 3;                    //
                length=31;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if ( monthnum == 4) {
                month = "April";
                length = 30;
                monthCode= 6;                    //
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }                        
            }    
            if  ( monthnum == 5 ) {
                month = "May";
                length = 31;
                monthCode= 1;                    //
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if  ( monthnum == 6 ) {
                month = "June";
                length = 30;
                monthCode= 4;                    //
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if  ( monthnum == 7 ) {
                month = "July";
                length = 31;            //
                monthCode= 6;                    //
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if  ( monthnum == 8 ) {
                month = "August";
                length = 31;
                monthCode= 2;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if  ( monthnum == 9 ) {
                month = "September";
                length = 30;
                monthCode= 5;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if  ( monthnum == 10 ) {
                month = "October";
                length = 31;
                monthCode= 0;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }
            }    
            if  ( monthnum == 11 ) {
                month = "November";
                length = 30;
                monthCode= 3;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }    
            }    
            if  ( monthnum == 12 ) {
                month = "September";
                length = 31;
                monthCode= 5;
                dW = (int)((((Math.floor(5*decade/4))) 
                    + + (monthCode) - ((2)*(century % 4))+7) % 7);
                    
                System.out.println("\n  " + month + " " + year + " : "  );
                System.out.println("\n  Su  Mo  Tu  We  Th  Fr  Sa");
                    
                for (counter= 0; counter< dW; counter++)
                  System.out.print("    ");
                
                for (counter= 1; counter<= length; counter++) {
                  if (counter< 10)
                    System.out.print("   " + counter);
                  else
                    System.out.print("  " + counter);
            
                  if ((counter+ dW) % 7 == 0)
                    System.out.println();
                }                        
            }
                System.out.println("\n");         //This will produce a newline
                                                  //character at the bottom of
                                                  //the month
    }
}    
