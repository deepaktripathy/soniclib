package com.soniclib.type;

/**
 * This knows how to parse a given data type (usually Strings) to another type, say Date
 * For example there is a String data in International format and we need a date type of 
 * 24-hour US format with time zone attached. This may be able to provide that.
 * 
 * @author deepak.tripathy
 *
 */
public interface ITypeParser {

	/**
	 * Parses and if needed decorates the underlying data as a different object or 
	 * a more decorated similar object.
	 * 
	 * @param underlyingData
	 * @return
	 */
	public Object parse(Object underlyingData);
}
