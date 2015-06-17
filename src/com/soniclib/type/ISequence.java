package com.soniclib.type;

/**
 * Definitions:
 * Set: a bunch of numbers (similar objects?) with no specific order.
 * Sequence: A sequence is an ordered list or set of numbers (similar objects?). The idea of ordering is important!
 * 
 * Range: The range of this sequence = largest-smallest values.
 * Bounds: The [lowest, highest] values are considered to be the bounds.
 * Enum/Enumeration: Is 
 * 
 * Creates a sequence of values that can be a bounds/range or a enum etc.
 * 
 * Important: The next & previous return us a sequence instead of the template object because these are another sequence.
 * So ex: we have a source sequence of {2,4,6,8,10,14,20,30}, we construct a subsequence of {2,4}, so the next would be {6,8}.
 * 
 * TODO: Since this iterates on a single entity, how can we iterate over say 3 items over a batch?
 * 
 * A range is a superset of a bunch of ranges. So it is logical for a Range to return a subRange, which can be a List.
 * So why we are returning a template type?
 * 
 * Something similar is here: http://gleichmann.wordpress.com/2008/01/21/declarative-programming-a-range-type-for-java/
 * 
 * Even if the supplied object may look like unordered, there is the natural order of insertion.
 * 
 * @author deepak.tripathy
 *
 * @param <T>
 */
public interface ISequence<T> {
	
	public T value();
	
	/** initial value */
	public T from();
	
	/** final value */
	public T to();

	/** next value, can not be higher than 'to' value */
	public ISequence<T> next();
	
	/** previous value, can not be lower than 'from' value */
	public ISequence<T> previous();
}