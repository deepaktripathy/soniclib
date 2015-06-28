package com.soniclib.bfs;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.soniclib.IFileProcessor;
import com.soniclib.utils.StringUtil;


/**
 * I have a file search tool project that is almost complete with a hacked together search code using DFS.
 * This was using conditional flags etc to imitate BFS, but needed a REAL BFS.
 * 
 * So, this started out as a demo for BFS and later on integrated some features of one of my 
 * personal projects for file searching, like processors and processing depth.
 * 
 * The need for this came from the assumption that if you are locating a file, it is probably somewhere 
 * near the root folder than deep down and if we are using DFS, it almost hogs the CPU till it is found.
 * No wonder windows searches using BFS than DFS.
 *   
 * TODO: Ideally this should be inside its own class and different constructors for different behavior.
 * TODO: 2: Add folder selectors, depth selectors, FileFilters based on other parameters etc.
 * 
 * depth inspiration from:
 * http://stackoverflow.com/questions/10258305/how-to-implement-a-breadth-first-search-to-a-certain-depth
 * 
 * @author deepak tripathy
 *
 */
public class FileScanner {

	public void processFolderDfs(File root, FileFilter filter) 	{       
		if(root == null) return;

		//store all visited folders in a list to lookup later
		ArrayList<String> visitedList = new ArrayList<String>();

		System.out.print("Current File-" + root.getName());
		visitedList.add(root.getAbsolutePath());//mark visited

		//for every child file
		if(root.isDirectory()) {
			File[] files = (filter != null) ?root.listFiles(filter) :root.listFiles();
			for(File n: files) {
				//if child is not visited then recurse down
				if(visitedList.indexOf(n.getAbsolutePath()) == -1) {//not visited 
					processFolderDfs(n, filter);
				}
			}
		}
	}

	/**
	 * Takes in a folder path and prints ALL the children folders.
	 * 
	 * @param root file
	 * @param filter FileFilter to filter children
	 */
	public void processFolderBfs(File root, FileFilter filter, int toDepth, IFileProcessor processor) {
		//sanity check
		if(root == null) return;

		Queue<File> childrenQueue = new LinkedList<File>();

		//store all visited folders in a list to lookup later
		ArrayList<String> visitedList = new ArrayList<String>();

		int depth = 0;//will hold the current depth
		int timeToDepthIncrease;//would slide up/down based on current set of nodes  
		boolean pendingDepthIncrease = false;//a flag to toggle between increase depth NOW or waiting

		visitedList.add(root.getAbsolutePath());
		//Add root to the queue
		childrenQueue.add(root);
		timeToDepthIncrease = 1;

		while(!childrenQueue.isEmpty()) {
			if(toDepth != K_PROCESS_DEPTH_ALL && depth > toDepth)
				break;

			//process each file from queue & remove
			File r = childrenQueue.remove(); 
			timeToDepthIncrease--;
			//this is where you process the file, store, use, collect info or apply command to it.
			System.out.println("Depth-" + depth + ", file-" + r.getName());
			if(processor != null) processor.process(r);

			if(r.isDirectory()) {
				File[] files = (filter != null) ?r.listFiles(filter) :r.listFiles();
				for(File n: files) 	{
					if(visitedList.indexOf(n.getAbsolutePath()) == -1) {//not visited
						if(pendingDepthIncrease) {
							timeToDepthIncrease = childrenQueue.size();
							pendingDepthIncrease = false;
						}
						childrenQueue.add(n);
						visitedList.add(n.getAbsolutePath());//mark visited
						//System.out.println("File added to queue and marked as visited: " + n.getName());
					}
					else {
						//this should not be reached normally.
						System.out.println("SKIPPING file " + n.getName());
					}
				}
			}
			if(timeToDepthIncrease == 0) {
				depth++;
				pendingDepthIncrease = true;
			}
			//System.out.println("Processing the next item in the queue, entries: " + childrenQueue);
		}
		//System.out.println("Returning from processFolderBfs()");
	}

	public static int K_PROCESS_DEPTH_ALL = -1;
	/**
	 * Takes in a folder path and prints ALL the children folders.
	 * 
	 * @param root file
	 * @param filter FileFilter to filter children
	 */
	public void processFolderBfs(File root, FileFilter filter, int toDepth) {
		//sanity check
		if(root == null) return;

		Queue<File> childrenQueue = new LinkedList<File>();

		//store all visited folders in a list to lookup later
		ArrayList<String> visitedList = new ArrayList<String>();

		visitedList.add(root.getAbsolutePath());
		//Add root to the queue
		childrenQueue.add(root);
		int depth = 0;
		int timeToDepthIncrease = 1;
		boolean pendingDepthIncrease = false;

		while(!childrenQueue.isEmpty()) {
			if(toDepth != K_PROCESS_DEPTH_ALL && depth > toDepth)
				break;

			//System.out.println("Processing queue entries: " + childrenQueue);
			//process each file from queue & remove
			File r = childrenQueue.remove(); 
			timeToDepthIncrease--;
			//this is where you process the file, store, use, collect info or apply command to it.
			System.out.println("Depth-" + depth + ", file-" + r.getName());

			if(r.isDirectory()) {
				File[] files = (filter != null) ?r.listFiles(filter) :r.listFiles();
				for(File n: files) 	{
					if(visitedList.indexOf(n.getAbsolutePath()) == -1) {//not visited
						if(pendingDepthIncrease) {
							timeToDepthIncrease = childrenQueue.size();
							pendingDepthIncrease = false;
						}
						childrenQueue.add(n);
						visitedList.add(n.getAbsolutePath());//mark visited
						//System.out.println("File added to queue and marked as visited: " + n.getName());
					}
					else {
						//this should not be reached normally.
						//System.out.println("SKIPPING file " + n.getName());
					}
				}
			}

			if(timeToDepthIncrease == 0) {
				depth++;
				pendingDepthIncrease = true;
			}
			//System.out.println("Depth "+depth+", Processing the next item in the queue, entries: " + childrenQueue);
			//System.out.println("break here to test");
		}
		//System.out.println("Returning from processFolderBfs()");
	}


	/**
	 * Takes in a folder path and prints the children folders filtered by filter.
	 * 
	 * @param root file
	 * @param filter FileFilter to filter children
	 */
	public void printFolderBfs(File root, FileFilter filter) {
		//sanity check
		if(root == null) return;

		Queue<File> childrenQueue = new LinkedList<File>();

		//store all visited folders in a list to lookup later
		ArrayList<String> visitedList = new ArrayList<String>();

		visitedList.add(root.getAbsolutePath());
		//Add root to the queue
		childrenQueue.add(root);

		while(!childrenQueue.isEmpty()) {
			//System.out.println("Processing queue entries: " + childrenQueue);
			//process each file from queue & remove
			File r = childrenQueue.remove(); 
			//this is where you process the file, store, use, collect info or apply command to it.
			System.out.println("Filename-" + r.getName());

			if(r.isDirectory()) {
				File[] files = (filter != null) ?r.listFiles(filter) :r.listFiles();
				for(File n: files) {
					if(visitedList.indexOf(n.getAbsolutePath()) == -1) {//not visited
						childrenQueue.add(n);
						visitedList.add(n.getAbsolutePath());//mark visited
						//System.out.println("File added to queue and marked as visited: " + n.getName());
					}
					else {
						//this should not be reached normally.
						//System.out.println("SKIPPING file " + n.getName());
					}
				}
			}
			//System.out.println("Processing the next item in the queue, entries: " + childrenQueue);
		}
		//System.out.println("Returning from processFolderBfs()");
	}

	/**
	 * Takes in a folder path and prints ALL the children folders.
	 * 
	 * @param root file
	 */
	public void printFolderBfs(File root) {
		//sanity check
		if(root == null) return;

		Queue<File> childrenQueue = new LinkedList<File>();

		//store all visited folders in a list to lookup later
		ArrayList<String> visitedList = new ArrayList<String>();

		visitedList.add(root.getAbsolutePath());
		//Add root to the queue
		childrenQueue.add(root);

		while(!childrenQueue.isEmpty()) {
			//process each file from queue & remove
			File r = childrenQueue.remove(); 
			//this is where you process the file, store, use, collect info or apply command to it.
			System.out.println("Filename-" + r.getName());

			if(r.isDirectory()) {
				File[] files = r.listFiles();
				for(File n: files) {
					if(visitedList.indexOf(n.getAbsolutePath()) == -1) {//not visited
						childrenQueue.add(n);
						visitedList.add(n.getAbsolutePath());//mark visited
						//System.out.println("File added to queue and marked as visited: " + n.getName());
					}
					else {
						//this should not be reached normally.
						//System.out.println("SKIPPING file " + n.getName());
					}
				}
			}
			//System.out.println("Processing the next item in the queue, entries: " + childrenQueue);
		}
		//System.out.println("Returning from processFolderBfs()");
	}

	public static void main(String[] args) {

		FileScanner s = new FileScanner();

		String pathStr = "testdata/bfsDemo";
		File folder = new File(pathStr);
		FileFilter tff = s.new TextFileFilter();

		//s.processFolderDfs(folder, tff);

		System.out.println("Breadth First Search Demo");
		System.out.println("=========================");

		//prints list of filenames as it traverses
		s.printFolderBfs(folder);
		/*	        
	        //prints list of filenames with a specific filter
	        s.printFolderBfs(folder, tff);

	        //processes list of filenames with a specific filter using custom processor
	        PrintFileProcessor processor = new PrintFileProcessor();
	        s.processFolderBfs(folder, tff, K_PROCESS_DEPTH_ALL, processor);
	        System.out.println("Processor result- " + processor.getResult());

	        //processes list of filenames with a specific filter using custom processor upto certain level
	        RenameFileProcessor processor2 = new RenameFileProcessor();
	        s.processFolderBfs(folder, tff, 3, processor2);
	        System.out.println("Processor result- " + processor2.getResult());
		 */	        
		System.out.println("Breadth First Search Demo complete");
	}



	/**
	 * Accepts most of the text file types. Must pass folders else can not go to next level
	 * @author deepak tripathy
	 *
	 */
	public class TextFileFilter implements FileFilter {

		@Override
		public boolean accept(File file) {
			boolean flag = file.isDirectory() ||
					StringUtil.endsWithIgnoreCase(file.getName(), ".txt") || 
					StringUtil.endsWithIgnoreCase(file.getName(), ".data") ||
					StringUtil.endsWithIgnoreCase(file.getName(), ".properties") ||
					StringUtil.endsWithIgnoreCase(file.getName(), ".ini") ||
					StringUtil.endsWithIgnoreCase(file.getName(), ".csv");

			if(!flag)
				System.out.println("TextFileFilter:: Skipped file " + file.getName());
			//System.out.println("Flag = " + flag + " for file " + file.getName());
			return flag;
		}

	}
}
