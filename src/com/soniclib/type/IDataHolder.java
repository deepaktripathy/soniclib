package com.soniclib.type;

/**
 * Just a custom type. User must call getData to get the data and may cast it to the appropriate type. 
 * Optional operation: getType return the Class object associated with this data. Each implementation 
 * would have its own setter.
 *  
 * @author deepak.tripathy
 *
 */
public interface IDataHolder{
	public Object getData();
	public Class getType();
}