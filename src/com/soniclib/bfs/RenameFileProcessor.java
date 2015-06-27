package com.soniclib.bfs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.soniclib.IFileProcessor;
import com.soniclib.utils.StringUtil;

/**
 * Prints all the files that were processed. 
 * 
 * TODO: move some of the common functionality to an in-between abstract class.
 * 
 * For safety and simplicity, lets assume that the filename is going to be renamed 
 * instead of the while path/moving to another location.
 * 
 * @author deepak tripathy
 *
 */
public class RenameFileProcessor implements IFileProcessor{

	private boolean renamed;
	private String resultStr = "PROCESSING NOT INITIATED";
	
	@Override
	public void process(Object o) {
		//System.out.println("Processing file " + o);
		try {
			File file = (File)o;
			String filePath = file.getAbsolutePath();
			//rename the first match, to make it simple
			if(StringUtil.endsWithIgnoreCase(filePath, "3F.txt")) {
				String newName = file.getAbsolutePath();
				//newName = newName.substring(0, newName.indexOf(file.getName()) );
				newName = newName.substring(0, newName.lastIndexOf('.') );
				newName += ".mine";
				System.out.println("Renaming file " + file.getAbsolutePath() + " to " + newName);
				File file2 = new File(newName);
				file.renameTo(file2);
				resultStr = "SUCCESSFUL";
			}
		}
		catch(Exception ex) {
			resultStr = "Error in Procesing!";
		}
	}

	@Override
	public Object getResult() {
		return resultStr;
	}

	@Override
	public Object getStatistics() {
		throw new UnsupportedOperationException();
	}


}
