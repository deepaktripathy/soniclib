package com.soniclib;

import java.io.File;

/**
 * This would have any operation specific to Files only.
 * 
 * Let us just keep two methods getResult & getStatistics 
 * 
 * @author deepak tripathy
 *
 */
public interface IFileProcessor extends IProcessor{

	/**
	 * Returns the result of any operation. Ex. if the operation was to find multiple files, 
	 * then it may return a list of files that were found. Implement as needed. 
	 * @return
	 */
	public Object getResult();
	
	/**
	 * Returns the statistics for all the files, like path, size etc
	 * @return
	 */
	public Object getStatistics();
}
