package com.soniclib.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This contains any missing functionality that java.util.String does not have as well as others.
 * 
 * @author deepak tripathy
 *
 */
public class StringUtil {
	
	/**
	 * IgnoreCase variant of java.util.String.endsWith()
	 * 
	 * @param sourceStr
	 * @param endingStr
	 * @return
	 */
	public static boolean endsWithIgnoreCase(String sourceStr, String endingStr) {
		return sourceStr.toUpperCase().endsWith(endingStr.toUpperCase());
	}

	/**
	 * IgnoreCase variant of java.util.String.startsWith()
	 * 
	 * @param sourceStr
	 * @param startingStr
	 * @return
	 */
	public static boolean startsWithIgnoreCase(String sourceStr, String startingStr) {
		return sourceStr.toUpperCase().startsWith(startingStr.toUpperCase());
	}
	
	/**
	 * IgnoreCase variant of java.util.String.contain()
	 * 
	 * @param sourceStr
	 * @param containedStr
	 * @return
	 */
	public static boolean containsIgnoreCase(String sourceStr, String containedStr) {
		return sourceStr.toUpperCase().contains(containedStr.toUpperCase());
	}

	/**
	 * Modifies the supplied StringBuffer. Replaces all occurrence of findStr with the replaceStr
	 * @param sb
	 * @param findStr
	 * @param replaceStr
	 */
	public static void replaceAll(StringBuffer sb, String findStr, String replaceStr) {
		for( int index = 0; (index = sb.indexOf(findStr, index)) > -1; index+=replaceStr.length()+1) {
			sb.replace(index, index+ replaceStr.length(), replaceStr);
		}
	}



	/**
	 * Returns all the indices where there was a match for findStr
	 * 
	 * @param sourceStr
	 * @param findStr
	 * @return
	 */
	public static List<Integer> indexOfAll(String sourceStr, String findStr) {
		List<Integer> indices = null;
		for( int index = 0; (index = sourceStr.indexOf(findStr, index)) > -1; index+=findStr.length()+1) {
			if(indices == null) indices = new ArrayList<Integer>();
			indices.add(index);
		}
		return indices;
	}
}
