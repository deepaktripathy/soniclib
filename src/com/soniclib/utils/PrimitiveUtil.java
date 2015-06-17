package com.soniclib.utils;

public class PrimitiveUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean contains(char[]array, char ch) {
		for(int index = 0; index < array.length; index++) {
			if(array[index] == ch) 
				return true;
		}
		return false;
	}

	/**
	 * Returns a  new String filled with the supplied chars.
	 * @param ch
	 * @param length
	 * @return
	 */
	public static String fillChar(char ch, int length) {
		String retStr = null;
		StringBuffer sb = new StringBuffer(length);
		for (int count = 0; count < length; count++) {
			sb.append(ch);
		}
		
		return sb.toString();
	}

	public static boolean containsChar(char[] chars, char ch) {
		for(int index = 0; index < chars.length; index++) {
			char inChar = chars[index];
			if(ch == inChar)
				return true;
		}
		return false;
	}

	/**
	 * Returns a String populated with the number of chars.
	 * Can be useful to pre-populate spaces etc.
	 * @param ch
	 * @param count
	 * @return
	 */
	public static String nTimes(char ch, int count) {
		StringBuffer sb = new StringBuffer(count);
		for(int index = 0; index < count; index++) {
			sb.append(ch);
		}
		return sb.toString();
	}

}
