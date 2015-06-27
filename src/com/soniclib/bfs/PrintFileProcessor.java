package com.soniclib.bfs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.soniclib.IFileProcessor;

/**
 * Prints all the files that were processed. 
 * 
 * TODO: move some of the common functionality to an in-between abstract class.
 * 
 * @author deepak tripathy
 *
 */
public class PrintFileProcessor implements IFileProcessor{

	private List<String> printList;
	
	@Override
	public void process(Object o) {
		if(printList == null) printList = new ArrayList<String>();
		
		System.out.println("Processing file " + o);
		printList.add( ((File)o).getAbsolutePath());
	}

	@Override
	public Object getResult() {
		return printList;
		
	}

	@Override
	public Object getStatistics() {
		throw new UnsupportedOperationException();
	}


}
