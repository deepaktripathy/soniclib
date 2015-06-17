package com.soniclib.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * compares two Map Entry elements NOT maps. If the key/values are not 
 * comparable, then they are String Compared.
 * Otherwise it will throw ClassCastException.
 * TODO: What to do with a null value?
 * 
 * @author Deepak
 *
 */
public class MapEntryComparator implements Comparator{

	public int compare(Object arg0, Object arg1) {
		Map.Entry entry1 = (Map.Entry) arg0;
		Map.Entry entry2 = (Map.Entry) arg1;
		Object key1 = entry1.getKey();
		Object key2 = entry2.getKey();
		Object value1 = entry1.getValue();
		Object value2 = entry2.getValue();
		
		//compare from key -> value
		int retVal = ((Comparable)key1).compareTo(key2);
		if(retVal == 0)
			retVal = ((Comparable)key1).compareTo(key2);
		
		return retVal;
	}

}
