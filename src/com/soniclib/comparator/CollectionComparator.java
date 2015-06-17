package com.soniclib.comparator;
import java.util.Comparator;


/**
 * A comparator that deep compares Collections.
 * 
 * @author Deepak
 *
 */
public abstract class CollectionComparator implements Comparator{

	//TODO: move some generic code from the list & map comparators.
	public int compare(Object list1, Object list2) {
		throw new UnsupportedOperationException();
	}
}
