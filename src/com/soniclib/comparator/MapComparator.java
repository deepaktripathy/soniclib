package com.soniclib.comparator;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Compares two maps based on the compare_type.
 * 
 * NOTE: If deepCompare is not set and an object contains a Collection type object, 
 * then the objects are compared using the standard .equals(). If the contained 
 * collection does not implement a .equals() then the object references are compared 
 * and the comparison may be invalid.
 * 
 * @author Deepak
 *
 */
public class MapComparator implements Comparator{
	public static final int K_COMPARE_ALL = 0;
	public static final int K_COMPARE_KEYS_ONLY = 1;
	public static final int K_COMPARE_VALUES_ONLY = 2;//compares values in source order

	private Map map1;
	private Map map2;
	private int compareMode = 0;
	private boolean strictCompare = false; //Useful for K_COMPARE_ALL & K_COMPARE_VALUES_ONLY. 
	private boolean deepCompare = false; //0: compare top level only, 1: strict, compare deep
	
	public MapComparator(Map map1, Map map2) {
		this.map1 = map1;
		this.map2 = map2;
	}

	/**
	 * 
	 * @param map1
	 * @param map2
	 * @param compareMode must be one of the existing, else defaults to K_COMPARE_ALL
	 */
	public MapComparator(Map map1, Map map2, int compareMode) {
		this.map1 = map1;
		this.map2 = map2;
		if(compareMode > -1 && compareMode < 3)
			this.compareMode = compareMode;
	}

	/**
	 * In deep compare mode drills down all the way to the leafs for K_COMPARE_ALL & K_COMPARE_VALUES_ONLY
	 * @param mode
	 */
	public void setDeepCompare(boolean deep) {
		this.deepCompare = deep;
	}
	
	/**
 	 * Compares external maps against each other
	 * @return
	 */
	public int compare() {
		//normally both maps may have some corelation say upto certain indices from start, 
		//so if we do it from end to center, then we may save few comparisons.
		
		return smartCompare(map1, map2, compareMode);
	}

	/**
	 * Compares external maps against each other
	 */
	public int compare(Object map1, Object map2) {
		Map map11 = (Map)map1;
		Map map22 = (Map)map2;
		
		//normally both maps may have some corelation say upto certain indices from start, 
		//so if we do it from end to center, then we may save few comparisons.
		
		return smartCompare(map1, map2, compareMode);
	}
	
	//recursive, drills down for any element if it is a List/Map/MixedObject & deepCompare is set.
	private int smartCompare2(Object list1, Object list2) {
		Map map11 = (Map)map1;
		Map map22 = (Map)map2;
		
		if(map11.size() != map22.size())
			return map11.size() < map22.size() ?-1 :1;
			
		ListComparator comp = null;
		//collect the lists and pass to the ListComparator.
		if(compareMode == K_COMPARE_KEYS_ONLY) {
			List keyList11 = (List)map11.keySet();
			List keyList22 = (List)map22.keySet();
			comp = new ListComparator(keyList11, keyList22);
		}
		else if(compareMode == K_COMPARE_VALUES_ONLY) {
			List valueList11 = (List)map11.values();
			List valueList22 = (List)map22.values();
			comp = new ListComparator(valueList11, valueList22);
		}
		else {//compare both keys & values
			List entryList11 = (List)map11.entrySet();
			List entryList22 = (List)map22.entrySet();
			comp = new ListComparator(entryList11, entryList22);
		}
		comp.setDeepCompare(deepCompare);
		return comp.compare();
	}

	//old way; dont use.
	private int smartCompare(Object map1, Object map2, int compareMode) {
		Map map11 = (Map)map1;
		Map map22 = (Map)map2;
		
		if(map11.size() != map22.size())
			return map11.size() < map22.size() ?-1 :1;
			
		//normally both maps may have some co-relation say up to certain keys or values 
		//or both, as callers are usually comparing for some sort of commonness 
		//so if we do it from end thru center, then we may save few comparisons.
		int counter = 0;
		while(counter < map11.size()) {
			///compare forward from start
			Object obj11 = map11.get(counter);
			Object obj22 = map22.get(counter);
			if(obj11==null && obj22!=null) return -1;
			if(obj11!=null && obj22==null) return 1;
			if(obj11==null && obj22==null) continue;

			int retVal1 = 0;
			if(obj11 instanceof Comparable && obj22 instanceof Comparable)
				retVal1 = ((Comparable)obj11).compareTo((Comparable)obj22);
			else
				retVal1 = obj11.toString().compareTo(obj22.toString());
			if(retVal1 != 0)
				return retVal1;

			//compare backwards from end
			obj11 = map11.get(map11.size()-1-counter);
			obj22 = map22.get(map22.size()-1-counter);
			if(obj11==null && obj22!=null) return -1;
			if(obj11!=null && obj22==null) return 1;
			if(obj11==null && obj22==null) continue;

			int retVal2 = 0;
			if(obj11 instanceof Comparable && obj22 instanceof Comparable)
				retVal1 = ((Comparable)obj11).compareTo((Comparable)obj22);
			else
				retVal1 = obj11.toString().compareTo(obj22.toString());
			if(retVal2 != 0)
				return retVal1;
			
			//break if at midway and everything is compared.
			if(counter > Math.round(map11.size() / 2))
				break;
		}
			
		return 0;
	}
	
}
