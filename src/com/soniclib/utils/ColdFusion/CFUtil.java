package com.soniclib.utils.ColdFusion;

import java.util.Collection;
import java.util.List;

import com.soniclib.utils.StringUtil;

public class CFUtil {

	/**
	 * Replaces all occurrences of the elements from a delimited list in a string 
	 * with corresponding elements from another delimited list. 
	 * NOTE: The replacement will be done by looking up from starting index to the 
	 * ending index on the last replaced result. So depending on the input, 
	 * the last operation might get overwritten.
	 * 
	 * TODO: Allow word replace mode as well as case insensitive mode.
	 * TODO: Check if this modifies the original String???? 
	 * TODO: Should throw exception if the list sizes mismatch.
	 * @param input
	 * @param list1 source List
	 * @param list2 replacement List
	 * @throws Exception if supplied lists are of dissimilar sizes
	 * @return A copy of the string, after making replacements.
	 */
	public static String ReplaceList2(String input, List<String> list1, List<String> list2, boolean caseInsensitive) throws Exception{
		if(! areSameSize(list1, list2))
			throw new Exception("Input lists are must be of same size");
		//make a copy to replace
		StringBuffer sb = new StringBuffer(new String(input));
		
		for(int index=0; index < list1.size(); index++) {
			String findStr = list1.get(index);
			String replaceStr = list2.get(index);
			StringUtil.replaceAll(sb, findStr, replaceStr);
		}
		
		return sb.toString();
	}

	/**
	 * Calculates the average of the values in an array. 
	 * Allow a range of array to do so.
	 * 
	 * @return Number. If the array parameter value is an empty array, returns zero.
	 */
	public static Double ArrayAvg (List<Double> list, int from, int to) {
		List subList = list.subList(from, to);
		double sum = 0d;
		for(Double value : list) {
			sum += value;
		}
		return sum;
	}

	/** Unfinished, should be a generic one for add/multiply, div/mod etc.*/
	private static double arrayOperation(Object lastResult, Object element, int operation) {
		throw new UnsupportedOperationException();
	}

	
	/**
	 * Returns true: if both are nulls || must be same size
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static <T>  boolean  areSameSize(Collection<T> c1, Collection<T> c2) {
		if(c1 != null && c2 != null) {
			return c1.size() == c2.size();
		}
		if(c1 == null && c2 == null) {
			return true;
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
