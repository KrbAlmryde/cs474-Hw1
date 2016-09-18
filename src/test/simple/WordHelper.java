/*+----------------------------------------------------------------------
||
||  Class WordHelper 
||
||         Author:  Kyle Almryde
||
||        Purpose:  A description of why this class exists.  For what
||                   
||                   reason was it written?  Which jobs does it perform?]
||
||  Inherits From:  None.
||
||     Interfaces:  None.
||
|+-----------------------------------------------------------------------
||
||      Constants:  None.
||
|+-----------------------------------------------------------------------
||
||   Constructors:  WordHelper ()
||					WordHelper (String currentWord)
||
||  Class Methods:  None.
||
||  Inst. Methods:  int		  numberOfSyllables ()
||					void      setWord (String cleanCurrentWord )
||                  String    getWord ()
||					String    syllablize ()
||					String    pronounce ()
||					String    translateSyllable (String )
||
++-----------------------------------------------------------------------*/

import java.util.*;			// Gives easy access to Java API"s "util" package
import java.lang.*;

public class WordHelper
{	
	
						/* Instance Variables */
						
	private String inputString,	  // This represents the inputted String entered
								  // by the user. This item will be processed
								  // by the WordHelper Class. 
								  
								  
					i,			  // A simple counter
					
					stripString,  // This represents the stripped inputString. If
								  // the inputString contained any values which
								  // are not an alphabetical character, they will
								  // be removed and only alphabetical characters
								  // will remain. 
								
					lowCaseWord,  // This represents the inputString value after
								  // as a lower case string object stripped of 
								  // all non-characters
					
					vowels,		  // An array containing the vowels
								  // a,e,i,o,u,y
					vcs,
					
					syllablized,
					
					phoneme,
					
					dipthong,

					tokens;		  // A token to hold the string array produced
								  // by the String.split method	

								  
			int		numberOfVowels,	// This represents the number of vowels
									// in a word
									
					syllableCount;	// The total number of syllables
					


	
		/*---------------------------------------------------------------------
		|  Method WordHelper (constructors)
		|
		|  Purpose:  Sets the state of the new object to match that of 
		|			 the word referenced by the parameter, in lower-case.
		|			 Thus, if the given is 'CompundWord', the object will
		|			 represent the state of the word 'compoundword'. If 
		|			 If the user includes non-letters in the word, retain
		|			 only the letters. 
		|
		|  Pre-condition:  A String of characters composing a word, or not.
		|
		|  Post-condition: A phonetic translation of the User entered word.
		|
		|  Parameters:  For WordHelper(), none. For WordHelper(String), the
		|               argument is the User entered, uni- or multisylabic 
		|			 	word String.
		|
		|  Returns:  A new WordHelper object.
		*-------------------------------------------------------------------*/
	
	public WordHelper (String inputString)
	{
		StringBuilder strip = new StringBuilder();
		String[] tokens = inputString.split("[^\\w]+");

		
		for ( int i = 0; i < tokens.length ; i++ )
			
			strip.append(tokens[i]);
			
			String stripString = strip.toString();
			
			lowCaseWord = stripString.toLowerCase();
	}	
		

		/*---------------------------------------------------------------------
		|  Methods:  getWord(), setWord() 
		|
		|  Purpose:  Permits the programmer to access or adjust the
		|            WordHelper's currentWord string.
		|
		|  Pre-condition:  None.
		|
		|  Post-condition: In the case of getWord, the component is
		|                  returned and is unchanged.  For setWord, the
		|                  component is changed to match the value of
		|                  the argument.
		|
		|  Parameters:  None for getters; the appropriate fraction 
		|               component for the setters.
		|
		|  Returns:  The appropriate fraction component from the getters;
		|            nothing from the setters.
		*-------------------------------------------------------------------*/

	public String getWord ()
	{
		return lowCaseWord;
	}




	public void setWord (String newlowerWord)
	{
		lowCaseWord = newlowerWord;
	}
	
	
	
	
		
	/*---------------------------------------------------------------------
	|  Method numberOfSyllables()
	|
	|  Purpose:  To return an estimated number of syllables in the
	|			 word.
	|
	|  Pre-condition:  .
	|
	|  Post-condition: .
	|                  
	|
	|  Parameters: .
	|
	|  Returns:  The approximate number of syllables in the word
	|
	*-------------------------------------------------------------------*/

	public int numberOfSyllables ()
	{
		/* pullout the vowels and consonants */		
		String[] minusE = lowCaseWord.split("[e$]");	
		StringBuilder temp = new StringBuilder();
		
		for ( int i = 0; i < minusE.length ; i++ )
		
			temp.append(minusE[i]);
			String vowels = temp.toString();
			String[] syllable = vowels.split("[^aeiouy]+|[^aeiouy][^aeiouy]+");
			
		if (syllable.length == 0)
			return 1;
		else		
			return syllable.length;
	}



	/*---------------------------------------------------------------------
	|  Method syllablize()
	|
	|  Purpose:  To examine the word and apply simple heuristis to
	|			 try to identify the syllables within the word. This 
	|			 returns a String reference to a 'divided' version of
	|			 the word. For example, if the word is "identify", the
	|			 returned string will be "i/den/ti/fy", with the 
	|			 forward slashes but without the quotes.
	|
	|  Pre-condition:  .
	|
	|  Post-condition: .
	|                  
	|
	|  Parameters: .
	|
	|  Returns:  The approximate number of syllables in the word
	|
	*-------------------------------------------------------------------*/
	

	/* syllablize */ 
	public String syllablize ()
	{
		
		String vccv = lowCaseWord.replaceAll("([aeiouy])([^aeiouy])([^aeiouy])([aeiouy])", "$1$2/$3$4");	
		String syllablized = vccv.replaceAll("([aeiouy])([^aeiouy])([aeiouy])", "$1/$2$3");
		return syllablized;		

	}//End of syllablize




	public String pronounce ()
	{
		/* examines the 'syllablized' version of the word, one syllable at a 
		/* time, and returns a string representing its phonetic equivalent.
		/* For example, if the original word was written as "ma/jor", this 
		/* method would return "may-jor"; note that the slashes are replaced
		/* with hyphens.*/
		
		
		String temp = WordHelper.translateSyllable.(syllablized);
		String pronoun = tmp.replaceAll("(/)", "-");
		System.out.println(pronoun);
		
		
		return pronoun;
		
	}//End of pronounce
	
	
	
	
	//***********Private Methods************//
	
	
	
	/* translateSyllable */
	private String translateSyllable (String phoneme)
	{
		/* given a syllable, it determines and returns the phonetic version
		/* of that syllable. The rules for performing the conversion are
		/* provided below.
		*/
	
		int length = phoneme.length();
		
		
		switch (phoneme.lastIndexOf(length)) {
			case 'o':
				String trans = phoneme.replaceFirst("o", "oh");	
				return trans;	

			case 'i':
				trans = phoneme.replaceFirst("i", "ee");	
				return trans;	

			case 'a':
				trans = phoneme.replaceFirst("a", "ay");	
				return trans;	


				
							
			// case ce: phoneme = ss; break;
			// case es: phoneme = ez; break;
			// case vy: phoneme = vee; break;
			
			
			
			// if (syllablized.contains("cc")) {
			// 	String dipthong = phoneme.replaceAll("cc", "k");
			// } else if (syllablized.contains("ca")) {
			// 	String dipthong = phoneme.replaceAll("ca", "ka");
			// } else if (syllablized.contains("co")) {
			// 	String dipthong = phoneme.replaceAll("co", "ko");
			// } else if (syllablized.contains("au")) {
			// 	String dipthong = phoneme.replaceAll("au", "aw");
			// } else if (syllablized.contains("ea")) {
			// 	String dipthong = phoneme.replaceAll("ea", "ee");
			// } else if (syllablized.contains("qu")) {
			// 	String dipthong = phoneme.replaceAll("qu", "kw");
			// }
	
		 }
		return trans; 
	}
}
	
	
	


	
	
	
	
	
	
	
	//*****************testing**********************/		
	
		// This is useful code //
		//strip.append(tokens[i].replace( 'o', 'v'));
		//System.out.println(cvc);
		
		/* using a pattern, strip out every vowel, or combination thereof */
		/* This code may need to go elsewhere, as I dont really need it here */
		// String[] numVowels = cvc.split("c+|cvc+^vcv+^vccv+");
		
		/* This pulls out all of the consonants */
		// String[] con = lowerWord.split("[aeiouy]+"); 	
		
	//*****************testing**********************/					
	




