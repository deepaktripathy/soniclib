package com.soniclib.utils.php;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Most of the functions are inspired & tailored from PHP String functions.
 * 
 * @author Deepak
 *
 */
public class ArrayUtil {

	/**
	 * Creates an array by using one array for keys and another for its values. 
	 * This may seems fine in PHP, but in JAVA there is no such array structure that 
	 * can have key[value]. The nearest approximation is a Map and this is what it returns.
	 * Note: Can have null key as well as null values.
	 * If there are more keys than values, the extra values are associated with null keys, 
	 * but extra values are dropped since the keys for these are null.
	 *  
	 * @param keys
	 * @param values
	 * @return
	 */
	public static Map<Object, Object> array_combine(Object[] keys, Object[] values) {
		
		if(keys!= null && values != null) {
			Map<Object, Object> result = new HashMap<Object, Object>();
			int minLength = keys.length;
			if(values.length < minLength)
				minLength = values.length;

			//copy the common set
			for(int index = 0; index < minLength; index++)
				result.put(keys[index], values[index]);
			
			//now handle the surplus keys
			for(int kIndex = minLength; kIndex < keys.length; kIndex++)
				result.put(keys[kIndex], null);
			
			return result;
		}
		return null;
	}

	/**
	 * Returns an array with all keys in lowercase or uppercase
	 * Caution: it converts the keys from an  Object type to a String type.
	 * 
	 * TODO: Incomplete... why return a map if array is good enough?
	 * @param map
	 */
	public static Map array_change_key_case(Map map, boolean upperCase) {
		if(map != null) {
			Set keyset = map.keySet();
			List keys = new ArrayList(keyset);
			for(int index = 0; index< keys.size(); index++) {
				Object key = keys.get(index);
				String keyVal = key.toString();
				if(upperCase)
					map.put(keyVal.toUpperCase(), map.get(key));
				else
					map.put(keyVal.toLowerCase(), map.get(key));
				//then delete the original entry
				map.remove(key);
			}
		}
		
		return null;
	}

	/**
	 * returns an map using the values of the input array as keys and their frequency in input as values.
	 * 
	 * @param map
	 * @return
	 */
	public static Map array_count_values(Map map) {
		Map result = null;
		if(map != null) {
			Iterator iter = map.values().iterator();
			while(iter.hasNext()) {
				Object valueObj = iter.next();
				if(result == null)
					result = new HashMap();
				
				Object freq = result.get(valueObj);
				if(freq == null)
					result.put(valueObj, 1);
				else
					result.put(valueObj, ((Integer)freq)+1);
			}
		}
		return result;
	}

	/**
	 * returns an map using the keys of the input array as keys and their frequency in input as values.
	 * 
	 * @param map
	 * @return
	 */
	public static Map array_count_keys(Map map) {
		Map result = null;
		if(map != null) {
			Iterator iter = map.keySet().iterator();
			while(iter.hasNext()) {
				Object keyObj = iter.next();
				if(result == null)
					result = new HashMap();
				
				Object freq = result.get(keyObj);
				if(freq == null)
					result.put(keyObj, 1);
				else
					result.put(keyObj, ((Integer)freq)+1);
			}
		}
		return result;
	}

	/**
	 * Compares array values, and returns the differences
	 * 
	 * @param map1
	 * @param map2
	 * @return Returns an array containing all the entries from array1 whose keys are not present in any of the other arrays.
	 */
	public static Map array_diff(Map map1, Map map2) {
		Map result = null;
		
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
