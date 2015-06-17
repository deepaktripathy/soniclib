package com.soniclib.type;

/**
 * 
 * Will walk all/specific set of elements as defined by the implementer.
 * 
 * TODO: HOW THIS SHOULD BE USED/CALLED?
 * 
 * Sequence: initialize -> 
 * walk() would walk the set of user specified elements, may do any pre-process and hand 
 * over each of the elements to the process().
 * 
 * process() would be overridden/implemented and would process the data at this index 
 * and return any results. If the result is null, it means that the result may contain 
 * complex objects and getStatus must be used to get the result.
 * 
 * Additionally the pre-process and post-process are called before and after a walk/single-iteration.
 * 
 * The initialize/finalize would be called once per instance and mainly to associate/release any states.
 * But these are not the same as any class's initialize/finalize; In out case, 
 * the very first walk generates a initialize -> preProcess & the very last walk generates postProcess->finalize().
 * This way we can do any totaling and time sensitive operations. 
 * 
 * getStatus() will contain any status info that the last walk->process would encounter.
 * For example we can ask to walk to a set of elements and calculate the total of all. 
 * The status would contain the lastIndex (like 5, or 5.2.3 for child lists) If this process 
 * is successful, the status would contain status code and outValue, 
 * else error code and/or exception.
 * 
 * The returned status object would contain a aggregate object. See elsewhere for that.
 * 
 * User should not be calling all of these rather, whatever is needed.
 * 
 * NOTE: Why walker, not runner? Could not find what to use, but seems like a walker can walk and jump 
 * from anywhere to anywhere, but a runner will need a directional path to run.
 * So ideally we would have a walker and then derive a runner from there, confirming to the OO behavior 
 * of more restrictive/specialized functionality from parent to child.
 * 
 * @author deepak.tripathy
 *
 */
public interface IElementWalker {

	
	public Object initialize();

	public Object preProcess();
	
	public void walk();

	public Object process();
	
	public Object postProcess();

	/* named differently as conflicted with Object.finalize()*/
	public Object finalizeProcess();
	
	public Object getStatus();
}
