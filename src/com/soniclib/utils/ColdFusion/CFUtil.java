package com.soniclib.utils.ColdFusion;

import java.util.Iterator;
import java.util.List;

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
	 * @return A copy of the string, after making replacements.
	 */
	public static String ReplaceList(String input, List<String> list1, List<String> list2, boolean caseInsensitive) {
		String input1 = new String(input);
		StringBuffer sb = new StringBuffer(input);
		for(int index=0; index < list1.size(); index++) {
			String findStr = list1.get(index);
			String replaceStr = list2.get(index);
			//TODO: replace the following with a more efficient method using StringBuffer.
			input.replaceAll(findStr, replaceStr);
		}
		
		return input1;
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
		Iterator<Double> sublistIter = subList.iterator();
		while(sublistIter.hasNext()) {
			Double obj = sublistIter.next();
			sum += obj;
		}
		return sum;
	}

	/** Unfinished, should be a generic one for add/multiply, div/mod etc.*/
	private static double arrayOperation(Object lastResult, Object element, int operation) {
		throw new UnsupportedOperationException();
	}
	 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
