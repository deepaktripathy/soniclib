package com.soniclib;

/**
 * This should allow to separate any processing logic from non-processing logic, say finding/iteration etc.
 * So create a processor and pass it to any finder/iterator and that will then call the preocessor.process() 
 * method, passing in a object to process that this processor knows how to handle.
 * 
 * It is the responsibility of the processor to handle/store any result and to report/consume any exceptions.
 * 
 * @author deepak tripathy
 *
 */
public interface IProcessor {
	public void process(Object o);

}
