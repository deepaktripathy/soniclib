package com.soniclib.utils.php;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.soniclib.utils.PrimitiveUtil;



/**
 * Most of the functions are inspired & tailored from PHP String functions.
 * 
 * @author Deepak Tripathy
 *
 */
public class StringUtil {
	public static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * Splits up a string as few chunks of wanted size. The last array element 
	 * would contain the remaining chars. The newline adds newlines to the end 
	 * of each array element. 
	 * @param str
	 * @param len
	 * @param addNewlines
	 * @return
	 */
	public String[] splitString(String str, int len, boolean addNewlines) {
		String[] splitArray = null;
		if(str != null) {
			if(str.length() == 0)
				return new String[0];
			else {
				//chunk it and add newlines if wanted
				int index = 0;
				while(index + len < str.length()-1) {
					String chunk = str.substring(index, index+len);
					if(addNewlines)
						chunk += NEWLINE;
					
					splitArray[splitArray.length] = chunk;
					index += len;
				}
				//process the remaining chars
				if(index < str.length()-1) {
					String chunk = str.substring(index, index+len);
					if(addNewlines)
						chunk += NEWLINE;
					splitArray[splitArray.length] = chunk;
				}				
			}			
		}
		
		return splitArray;
	}
	
	/**
	 * Joins multiple elements of the Array with the glue & returns the final string.
	 * glue is optional & can be null in which case the pieces are directly joined.
	 * An alias to implode.
	 * 
	 * @param pieces
	 * @param glue
	 * @return
	 */
	public String join_string(String[] pieces, String glue) {
		String result = null;
		if(pieces != null) {
			result = "";
			int index = 0;
			if(pieces.length > 0)
				result += (pieces[index]==null) ?"" :pieces[index];
			index++;
			
			while(index < pieces.length) {
				result += (pieces[index]==null) ?"" :pieces[index];
				index++;
			}
		}
		
		return result;
	}
	
	/**
	 * Joins multiple elements of the Array with the glue & returns the final string.
	 * glue is optional & can be null in which case the pieces are directly joined.
	 * An alias to implode.
	 * 
	 * @param pieces
	 * @param glue
	 * @return
	 */
	public String implode(String[] pieces, String glue) {
		return join_string(pieces, glue);
	}

	public static final int PAD_RIGHT_SIDE = 0;
	public static final int PAD_LEFT_SIDE = 1;
	public static final int PAD_BOTH_SIDE = 2;
	
	/**
 	 * This functions returns the input string padded on the left, the right, or 
	 * both sides to the specified padding length. If the optional argument pad_string 
	 * is not supplied, the input is padded with spaces, otherwise it is padded with 
	 * characters from pad_string up to the limit.
	 * 
	 * The pad_string may be truncated if the required number of padding characters can't 
	 * be evenly divided by the pad_string's length.  
	 * 
	 * @param input
	 * @param newLength integer, if negative, less than, or equal to the length of the input string, no padding takes place.
	 * @param padType, int, takes one if the above constants, otherwise defaults to PAD_RIGHT_SIDE. 
	 * @param padString
	 * @return
	 */
	public String pad(String input, int newLength, int padType, String padString) {
		String result = null;
		if(input != null)
		{
			//TODO: NOT IMPLEMENTED
		}
		return result;
	}

	/**
	 * Repeats a string a specified number of times.
	 * multiplier has to be greater than or equal to 0. If the multiplier is set to 0, 
	 * the function will return an empty string.
	 * @param input
	 * @param multiplier
	 * @return
	 */
	public static String str_repeat ( String input , int multiplier ) {
		if(input != null) {
			StringBuffer result = new StringBuffer();
			for(int index = 0; index < multiplier; index++)
				result.append(input);
			
			return result.toString();
		}
		return null;
	}

	/**
	 * Repeats a string a specified number of times.
	 * multiplier has to be greater than or equal to 0. If the multiplier is set to 0, 
	 * the function will return an empty string.
	 * 
	 * @param input
	 * @param multiplier
	 * @param separator in-between multiple strings
	 * @return
	 */
	public static String str_repeat ( String input , int multiplier, String separator ) {
		String sep = (separator == null)?"" :separator;
		
		if(input != null) {
			StringBuffer result = new StringBuffer();
			if(multiplier > 0)
				result.append(input);
			for(int index = 1; index < multiplier; index++)
				result.append(sep).append(input);
			
			return result.toString();
		}
		return null;
	}

	/**
	 * String comparison of the first n characters (case-insensitive)
	 * @param str1
	 * @param str2
	 * @param startPos
	 * @return
	 */
	public static int strncasecmp(String str1, String str2, int nChars) {
		return strncmp(str1, str2, nChars, true);
	}
	
	/**
	 * String comparison of the first n characters (case-sensitive)
	 * @param str1
	 * @param str2
	 * @param startPos
	 * @return
	 */
	public static int strncmp(String str1, String str2, int nChars) {
		return strncmp(str1, str2, nChars, false);
	}
	
	private static int strncmp(String str1, String str2, int nChars, boolean caseSensitive) {
		String str11 = str1.substring(0, nChars);
		String str22 = str2.substring(0, nChars);
		if(caseSensitive)
			return str11.compareTo(str22);//exact match
		else
			return str11.compareToIgnoreCase(str22);
	}
	
	/**
	 * Returns part of haystack string starting from and including the first occurrence of needle to the end of haystack.
	 * If before is TRUE, this returns the part of the haystack before the first occurrence of the needle (excluding the needle).
	 * Ignores case 
	 * 
	 * @return
	 */ 
	public static String strstr(String input, String searchStr, boolean before){
		int index = StringUtils.indexOfIgnoreCase(input, searchStr);

		if(before)
			return input.substring(0, index);
		else
			return input.substring(index);
	}

	/**
	 * Returns part of haystack string starting from and including the first occurrence of needle to the end of haystack.
	 * If before is TRUE, this returns the part of the haystack before the first occurrence of the needle (excluding the needle).
	 * Ignores case 
	 * 
	 * @return
	 */ 
	public static String strchr(String input, char searchChar, boolean before){
		
		int index = input.indexOf(searchChar);
		//if not found, change case
		if(index == -1)
			index = input.indexOf(changeCase(searchChar));

		if(before)
			return input.substring(0, index);
		else
			return input.substring(index);
	}

	/**
	 * changes case of char. If not a letter, returns the original.
	 * 
	 * @param ch
	 * @return
	 */
	public static char changeCase(char ch) {
		//if(! Character.isLetter(ch))
			//throw new Exception("the supplied char is not a letter, cannot change case");
		
		if(Character.isLowerCase(ch))
			return Character.toUpperCase(ch);
		if(Character.isUpperCase(ch))
			return Character.toLowerCase(ch);
		//only cases where the character is not alpha. in this case the exception would already been thrown
		return ch;
	}
	
	/**
	 * Counts the number of words inside string. If the optional format is not specified, then 
	 * the return value will be an integer representing the number of words found. In the event 
	 * the format is specified, the return value will be an array, content of which is dependent 
	 * on the format. The possible value for the format and the resultant outputs are listed below.
	 * 
	 * For the purpose of this function, 'word' is defined as a locale dependent string containing 
	 * alphabetic characters, which also may contain, but not start with "'" and "-" characters.
	 * 
	 * $str = "Hello fri3nd, you're
	 *         looking          good today!";
	 *         
	 *  print_r(str_word_count($str, 1));
	 *  print_r(str_word_count($str, 2));
	 *  print_r(str_word_count($str, 1, 'рсуч3'));
	 *  
	 *  echo str_word_count($str);
	 *  Array
	 *  (
	 *      [0] => Hello
	 *      [1] => fri
	 *      [2] => nd
	 *      [3] => you're
	 *      [4] => looking
	 *      [5] => good
	 *      [6] => today
	 *  )
	 *      
	 *  Array
	 *  (
	 *    [0] => Hello
	 *    [6] => fri
	 *    [10] => nd
	 *    [14] => you're
	 *    [29] => looking
	 *    [46] => good
	 *    [51] => today
	 *  )
	 *  
	 *  Array
	 *  (
	 *    [0] => Hello
	 *    [1] => fri3nd
	 *    [2] => you're
	 *    [3] => looking
	 *    [4] => good
	 *    [5] => today
	 *  )
	 *  
	 *  7
	 *  
	 * @return
	 */
	public static MixedObject str_word_count(String str) {
		throw new UnsupportedOperationException();
	}
	
	public static MixedObject str_word_count(String str, int format) {
		throw new UnsupportedOperationException();
	}
	

	public static MixedObject str_word_count(String str, int format, String charlist ) {
		throw new UnsupportedOperationException();
	}


	/**
	 * This function returns a copy of str where all occurrences 
	 * of each (single-byte) character in from have been translated to the corresponding 
	 * character in to, i.e., every occurrence of $from[$n] has been replaced with $to[$n], 
	 * where $n is a valid offset in both arguments.
	 * 
	 * If from and to have different lengths, the extra characters in the longer of the two 
	 * are ignored. The length of str will be the same as the return value's.
	 * 
	 * @param $str
	 * @param from
	 * @param to
	 * @return
	 */
	public static String strtr ( String $str , String from , String to ) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This function returns a copy of str. The second should be an array in the form array('from' => 'to', ...). 
	 * The return value is a string where all the occurrences of the array keys have been 
	 * replaced by the corresponding values. The longest keys will be tried first. Once a substring 
	 * has been replaced, its new value will not be searched again.
	 * 
	 * In this case, the keys and the values may have any length, provided that there is no 
	 * empty key; additionally, the length of the return value may differ from that of str. However, 
	 * this function will be the most efficient when all the keys have the same size. 
	 * 
	 * @param $str
	 * @param replace_pairs
	 * @return
	 */
	public static String strtr ( String $str , List<Map<String,String>> replace_pairs )
	{
		throw new UnsupportedOperationException();
	}

	
	/**
	 * Compares two strings from a specified start position (binary safe and optionally case-sensitive)
	 * @param str1
	 * @param str2
	 * @param startPos
	 * @param caseSensitive
	 * @return
	 */
	public static int substr_compare(String str1, String str2, int startPos, boolean caseSensitive) {
		String str11 = str1.substring(startPos);
		String str22 = str2.substring(startPos);
		if(caseSensitive)
			return str11.compareTo(str22);//exact match
		else
			return str11.compareToIgnoreCase(str22);
	}

	
	/**
	 * Replaces newlines at the end of lines with <BR>. 
	 * @param input
	 * @return
	 */
	public String nl2br(String input) {
		if(input != null) {
			String result = "";
			java.io.StringReader sr = new StringReader(input);
			BufferedReader br = new BufferedReader(sr);
			try {
				String firstLine = br.readLine();
				result += firstLine;
				
				//replace newline with <BR> for the rest of the lines
				while( (firstLine = br.readLine()) != null) {
					result += "<BR>" + firstLine;
				}
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Convert any tabs in the string to spaces
	 * @param str
	 * @return
	 */
	public static String tabstospaces(String str, int spaceCount) {
		String spaces = PrimitiveUtil.nTimes(' ', spaceCount);
		
		StringBuffer sb = new StringBuffer(str);
		//do it from the reverse as the result length will change
		for(int index = str.length()-1; index > -1; index--) {
			char ch = str.charAt(index);
			if(ch == '\t')
				sb.deleteCharAt(index).insert(index, spaces);
		}
		return sb.toString();
	}

	/**
	 * Converts the first character of a string to uppercase
	 * Note: only works if the first  char is a alphabet.
	 * @param str
	 * @return
	 */
	public static String ucfirst(String str) {
		String firstCh = str.substring(0,1).toUpperCase();
		StringBuffer sb = new StringBuffer(str);
		sb.deleteCharAt(0);
		sb.insert(0, firstCh.charAt(0));
		
		return sb.toString();
	}

	/**
	 * Returns the number of characters found in a string before any part 
	 * of some specified characters are found
	 * 
	 * @param str
	 * @param chars
	 * @return
	 */
	public static String strcspn(String str, char[] chars) {
		int index = strcspn_index(str, chars);
	
		if(index >-1)
			return str.substring(index);
		else
			return null;
	}

	/**
	 * Returns the index of the first matching character from the set of 
	 * characters in the array.
	 * 
	 * @param str
	 * @param chars
	 * @return
	 */
	public static int strcspn_index(String str, char[] chars) {
		for(int index = 0; index< str.length(); index++) {
			char ch= str.charAt(index);
			if(PrimitiveUtil.containsChar(chars, ch))
				return index;
		}
		return -1;
	}

	/**
	 * Returns the number of characters found in a string that contains only characters 
	 * from a specified charlist
	 * 
	 * @param str
	 * @param chars
	 * @return
	 */
	public static int strspn(String str, char[] chars) {
		int total = 0;
		for(int index = 0; index< str.length(); index++) {
			char ch= str.charAt(index);
			if(PrimitiveUtil.containsChar(chars, ch))
				total++;
		}
		
		return total;
	}

	/**
	 * Counts the number of times a substring occurs in a string
	 * @param str
	 * @param subStr
	 * @return
	 */
	public static int substr_count(String str, String subStr) {
		int count = 0;
		int index = 0;
		while(index < str.length()) {
			int strIndex = str.indexOf(subStr, index);
			
			if(strIndex > -1) {
				count++;
				index = strIndex + subStr.length() +1;//start at the next char.
			}
			else
				return count;
		}
		return count;
	}

	/**
	 * Returns how many times an ASCII character occurs within a string and 
	 * returns the information. This includes even white space and control chars.
	 * 
	 * @return
	 */
	public static Map<Character, Integer> count_chars(String str) {
		Map<Character, Integer> charCountMap = null;
		
		for(int index = 0; index < str.length(); index++) {
			char ch = str.charAt(index);
			
			int chCount = 0;
			if(charCountMap != null && charCountMap.containsKey(ch)) {
				chCount = charCountMap.get(ch);
			}
			//insert OR replace
			charCountMap.put(ch, chCount++);
		}
		
		return charCountMap;
	}

	/**
	 * Randomly shuffles all characters in a string
	 * UNTESTED
	 * 
	 * @param str
	 * @return
	 */
	private static String shuffle(String s)
    {
        String shuffledString = ""; 

        while (s.length() != 0)
        {
            int index = (int) Math.floor(Math.random() * s.length());
            char c = s.charAt(index);
            s = s.substring(0,index)+s.substring(index+1);
            shuffledString += c;
        }

        return shuffledString;
    }

	/**
	 * Splits a string into an array supplied lengths indicate each segment. 
	 * 1. If original length is larger than the total array lengths, 
	 * the extra text is appended as an extra item.
	 * 2. If the original length is smaller, then array would contain the smaller 
	 * last segment, and the extra elements would contain spaces(?).
	 * @param input
	 * @param lengths
	 * @return
	 */
	public static String[] str_split(String input, int[] lengths) {
		
		throw new UnsupportedOperationException();
	}

	/**
	 * Splits a string into an array of elements of length size.
	 * @param input
	 * @param length
	 * @return
	 */
	public static String[] str_split_array(String input, int length) {
		//may work... untested.
		//"Thequickbrownfoxjumps".split("(?<=\\G.{4})")
		//ideas? check http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/base/Splitter.html
		int rem = (int)Math.signum(input.length() ^ length);
		int sublen = input.length()/length + rem;
		String[] subs = new String[sublen];
		for(int i=0; i<input.length(); i+=sublen){
		  subs[i] = input.substring(i,i+sublen);
		}

	    return subs;
	}

	/**
	 * Splits a string into an List of elements of length size.
	 * @param input
	 * @param length
	 * @return
	 */
	public static List<String> str_split_list(String input, int length) {
		//may work... untested.
		//"Thequickbrownfoxjumps".split("(?<=\\G.{4})")
		//ideas? check http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/base/Splitter.html
		List<String> splitList = new ArrayList<String>();
		for (int start = 0; start < input.length(); start += length) {
	        splitList.add(input.substring(start, Math.min(input.length(), start + length)));
	    }

	    return splitList;
	}

	/**
	 * splits the input into 3 segments, 
	 * @param input
	 * @param subStr
	 * @return
	 */
	public static String[] str_split(String input, String subStr) {
		
		throw new UnsupportedOperationException();
	}

	public static final int MATCH_ANY = 0;
	public static final int MATCH_ALL = 1;
	public static final int MATCH_NONE = 2;
	/**
	 * Searches a string for any of a set of characters
	 * matchType can be one of the followings: MATCH_NONE,MATCH_ALL,MATCH_ANY, defaults to MATCH_ANY
	 */
	public static boolean strpbrk(String input, char[] chars, int matchType) {
		boolean retVal = false;
		switch(matchType) {
			case MATCH_NONE:
				for(int chIndex = 0; chIndex < chars.length; chIndex++) {
					if(input.contains(""+chars[chIndex])) {
						retVal = false;
						break;
					}
				}
				retVal = true;
				break;
			case MATCH_ALL:
				for(int chIndex = 0; chIndex < chars.length; chIndex++) {
					if(! input.contains(""+chars[chIndex])) {
						retVal = false;
						break;
					}
				}
				retVal = true;
				break;
			case MATCH_ANY:
			default:
				for(int chIndex = 0; chIndex < chars.length; chIndex++) {
					if(input.contains(""+chars[chIndex])) {
						retVal = true;
						break;
					}
				}
			}
			return false;		
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
