package com.soniclib.comparator;
import java.util.List;
import java.util.Map;

import com.soniclib.utils.php.MixedObject;


/**
 * Implemented a comparator to compare two lists. List elements must be identical to return match.
 * If elements are of different sizes, then returns appropriately, -1, or +1.
 * if one of the elements is null, then returns appropriately. Ideally a List should not contain a null element.
 * If elements are comparable, then they are compared using the original element's comparator, 
 * else string compared using the toString().
 * 
 * NOTE: If deepCompare is not set and an object contains a Collection type object, 
 * then the objects are compared using the standard .equals(). If the contained 
 * collection does not implement a .equals() then the object references are compared 
 * and the comparison may be invalid.
 * 
 * @author Deepak
 *
 */
public class ListComparator extends CollectionComparator{
	private List  list1;
	private List list2;
	private boolean deepCompare = false; //0: compare top level only, 1: strict, compare deep
	
	public ListComparator(List list1, List list2) {
		this.list1 = list1;
		this.list2 = list2;
	}

	public void setDeepCompare(boolean deep) {
		this.deepCompare = deep;
	}
	
	/**
 	 * Compares external lists against each other
	 * @return
	 */
	public int compare() {
		//normally both maps may have some corelation say upto certain indices from start, 
		//so if we do it from end to center, then we may save few comparisons.
		
		return smartCompare(list1, list2);
	}

	/**
	 * Compares external lists against each other
	 */
	public int compare(Object list1, Object list2) {
		List list11 = (List)list1;
		List list22 = (List)list2;
		
		//normally both maps may have some corelation say upto certain indices from start, 
		//so if we do it from end to center, then we may save few comparisons.
		
		return smartCompare2(list1, list2);
	}
	
	//recursive, drills down for any element if it is a List/Map/MixedObject & deepCompare is set.
	private int smartCompare2(Object list1, Object list2) {
		List list11 = (List)list1;
		List list22 = (List)list2;

		//break comparison if unequal sizes
		if(list11.size() != list22.size())
			return list11.size() < list22.size() ?-1 :1;
			
		//normally both lists may have some co-relation say up to certain indices, 
		//so if we do it from end thru center, then we may save few comparisons.
		int counter = 0;
		while(counter < list11.size()) {

			int retVal = 0;
			//compare backwards from end
			Object obj11 = list11.get(list11.size()-1-counter);
			Object obj22 = list22.get(list22.size()-1-counter);
			if(obj11==null && obj22!=null) return -1;
			if(obj11!=null && obj22==null) return 1;
			if(obj11==null && obj22==null) continue;

			//can not compare different objects even if they have the same ancestor/parents			
			if(! obj11.getClass().equals(obj22.getClass()))
				throw new ClassCastException("Cannot compare unknown Object types");

			if(obj11 instanceof MixedObject && obj22 instanceof MixedObject) {
				return ((MixedObject)obj11).compareTo((MixedObject)obj22);
			}
			//not mixed object, may be java object, Lists of maps?
			else if(obj11 instanceof List && obj22 instanceof List){
				if(deepCompare)
					return new ListComparator((List)obj11, (List)obj22).compare();
				else
					return ((Comparable)obj11).compareTo((Comparable)obj22);
			}
			else if(obj11 instanceof Map && obj22 instanceof Map) {
				if(deepCompare)
					return new MapComparator((Map)obj11, (Map)obj22).compare();
				else
					return ((Comparable)obj11).compareTo((Comparable)obj22);
			}
			//primitive wrappers or custom types, do string comparison
			else if(obj11 instanceof Comparable && obj22 instanceof Comparable) {
				retVal = ((Comparable)obj11).compareTo((Comparable)obj22);
			}
			//not even comparable, do string comparison
			else {
				retVal = obj11.toString().compareTo(obj22.toString());
			}
			
			
			//break if find difference in current layer
			if(retVal != 0)
				return retVal;
		}
			
		return 0;
	}

	//old way; dont use.
	//non-recursive, each iteration checks end elements of the remaining elements of the last operation.
	private int smartCompare(Object list1, Object list2) {
		List list11 = (List)list1;
		List list22 = (List)list2;
		
		if(list11.size() != list22.size())
			return list11.size() < list22.size() ?-1 :1;
			
		//normally both lists may have some co-relation say up to certain indices, 
		//so if we do it from end thru center, then we may save few comparisons.
		int counter = 0;
		while(counter < list11.size()) {
			///compare forward from start
			Object obj11 = list11.get(counter);
			Object obj22 = list22.get(counter);
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
			obj11 = list11.get(list11.size()-1-counter);
			obj22 = list22.get(list22.size()-1-counter);
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
			if(counter > Math.round(list11.size() / 2))
				break;
		}
			
		return 0;
	}
}
