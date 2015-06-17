package com.soniclib.type;
/**
 * Would know how to iterate data supplied by a boundary data type.
 * Ideally this is like a range and this knows how to traverse by itself, and which is the next direction etc.
 * 
 * This is generic, so the datatype can be anything.
 * TODO: How to make it generic without creating individual classes?
 * TODO: How to make iterating over more than one elements? In this case the from and to gets screwed up.
 * TODO: Why cant we query hasPre() or hasNext()? or even get an iterator from it?
 *
 * Since the BoundsRange can iterate/jump in more than one increment, it returns a [from,to] cursor and it is 
 * up to the user to interpret it accordingly.
 * 
 *  
 * @author deepak.tripathy
 *
 */
public class BoundsSequence<T> implements ISequence{

	private Boundary bounds = null;
	
	 private int elementSetCount = 1;//default: standard one element per move

	
	//commented as this applies to integers, not generic
	public void setBounds(int from, int to) {
		bounds = new Boundary(from, to);
	}
	
	// same as above, just different notation.
	public void setBounds(Boundary bounds) {
		this.bounds = bounds;
	}

	/**
	 * Depends on the elementGroupCount, use iterator style hasNext()->next() will be better as this decouples
	 */
	@Override
	public Object from() {
		return bounds.from();
	}
	
	/**
	 * Depends on the elementGroupCount, use iterator style hasNext()->next() will be better as this decouples
	 */
	@Override
	public Object to() {
		// TODO Auto-generated method stub
		return bounds.to();
	}

	@Override
	public Object value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISequence next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISequence previous() {
		// TODO Auto-generated method stub
		return null;
	}

}

/**
 * Defines a [from,to] boundary without any regards to the direction (scalar)
 * 
 * @author deepak.tripathy
 *
 */
class Boundary {
	private int from, to;
	public Boundary(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public int from() { return from; }
	public int to() { return to; }
	
}