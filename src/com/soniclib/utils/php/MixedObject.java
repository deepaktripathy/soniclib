package com.soniclib.utils.php;
import java.util.List;
import java.util.Map;

import com.soniclib.comparator.ListComparator;
import com.soniclib.comparator.MapComparator;



/**
 * A generic container like the PHP's mixed object. The right type of object 
 * would be created and stored depending on the caller. A mixed type contains 
 * only One of the specified types. Any complex types derived from List or Map 
 * must be re-casted to the right type.
 * 
 * TODO: Ideally a mixed object can contain more than one object types, say an int 
 * and a char. How do we do that? Put in a List? Also we should be able to create 
 * a mixed object of one type and then add another different object to it. 
 * 
 * User have to use getType to check the return value of the get() method.
 * 
 * toString would return the string representation of the contained type.
 * To compare with another mixed type, comparable is implemented.
 * 
 * NOTE: If deepCompare is not set and an object contains a Collection type object, 
 * then the objects are compared using the standard .equals(). If the contained 
 * collection does not implement a .equals() then the object references are compared 
 * and the comparison may be invalid.
 * 
 * TODO: This is very old code & design. With the advent of JAVA 7 & 8, and Generics, 
 * this should be designed as a generic container.
 * 
 * @author Deepak Tripathy
 *
 */
public class MixedObject implements Comparable{
	public static final int K_TYPE_STRING = 0;
	public static final int K_TYPE_INTEGER = 1;
	public static final int K_TYPE_DOUBLE = 2;
	public static final int K_TYPE_BOOLEAN = 3;
	public static final int K_TYPE_LIST = 4;
	public static final int K_TYPE_MAP = 5;
	public static final int K_TYPE_CUSTOM = 6; //for any user defined custom type
		
	private int type = K_TYPE_STRING;//default assumption
	private boolean deepCompare = false; //0: compare top level only, 1: strict, compare deep
	private Object myObject = null;
	
	private MixedObject() { }
	
	public MixedObject(int value) {
		myObject = value;
		type = K_TYPE_INTEGER;
	}
	public MixedObject(double value) {
		myObject = value;
		type = K_TYPE_DOUBLE;
	}
	public MixedObject(boolean value) {
		myObject = value;
		type = K_TYPE_BOOLEAN;
	}
	public MixedObject(String value) {
		myObject = value;
		type = K_TYPE_STRING;
	}
	public MixedObject(List value) {
		myObject = value;
		type = K_TYPE_LIST;
	}
	public MixedObject(Map value) {
		myObject = value;
		type = K_TYPE_MAP;
	}
	
	

	public int getType() {
		return type;
	}
	
	/**
	 * Useful for collection types. If this is not-strict, then the top level 
	 * is compared, and if it is strict, then the comparison is propagated 
	 * below the children till all the leafs are compared.
	 * @param strictMode
	 */
	public void setDeepCompare(boolean deep) {
		this.deepCompare = deep;
	}
	
	/**
	 * Returns the contained object, user must cast to the right type
	 * @return
	 */
	public Object get() {
		return myObject;
	}
	
	public String toString() {
		return myObject.toString();
	}

	/**
	 * Returns the actual formatted String output of the contained object/sub-objects 
	 * if they implement MixedObject.toFormattedString, else toString is called 
	 * on each contained element
	 * @return
	 */
	public String toFormattedString() {
		if(myObject instanceof MixedObject)
			return ((MixedObject) myObject).toFormattedString();
		else
			return myObject.toString();
	}

	/**
	 * Compares the contained object with a foreign object. Compares the objects 
	 * without checking the foreign object's type. Default comparison is relaxed 
	 * compare in which case only the top level collection is compared, but in strict 
	 * mode the comparison is done thru the last leaf.
	 * NOTE: The custom of unknown objects are compared with the default comparison of the 
	 * objects if they are 'Comparable', else a ClassCastException is thrown.
	 * @throws ClassCastException if mismatching custom/unknown objects. 
	 */
	public int compareTo(Object anotherObj) {
		if(anotherObj instanceof MixedObject)
			throw new ClassCastException("Cannot compare different types");

		if(this.type != ((MixedObject)anotherObj).type)
			throw new ClassCastException("Cannot compare different types");
		else {
			switch(type) {
			case K_TYPE_STRING:
				return ((String)myObject).compareTo((String)anotherObj);
			case K_TYPE_INTEGER:
				return ((Integer)myObject).compareTo((Integer)anotherObj);
			case K_TYPE_DOUBLE:
				return ((Double)myObject).compareTo((Double)anotherObj);
			case K_TYPE_BOOLEAN:
				return ((Boolean)myObject).compareTo((Boolean)anotherObj);
			case K_TYPE_LIST: 
			{
				ListComparator comp = new ListComparator((List)myObject, (List)anotherObj);
				comp.setDeepCompare(this.deepCompare);
				return comp.compare();
			}
			case K_TYPE_MAP: 
			{
				MapComparator comp = new MapComparator((Map)myObject, (Map)anotherObj);
				comp.setDeepCompare(this.deepCompare);
				return comp.compare();
			}
			case K_TYPE_CUSTOM:
			default:
				//if they implement comparable, then they are compared accordingly, else throw error
				if(myObject instanceof Comparable && anotherObj instanceof Comparable)
					return ((Comparable)myObject).compareTo(anotherObj);
				else
					throw new ClassCastException("Cannot compare unknown Object types");
			}
		}
	}
}
