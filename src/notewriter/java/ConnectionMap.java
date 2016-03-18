package notewriter.java;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
//import java.net.URL;  Can use this lib to check whether URL is valid in future
/*
 * Future TODO:  Support the following types of targets: 
 * 1) exact file path 
 * 2) directory path (default filename) 
 * 3) URL (POST template) 
 * 4) list of keys linking to 1-3 (recursive)
 */
import java.util.regex.Pattern;
public class ConnectionMap{

	private static final String IS_HTML_FILE = "\\.htm.\\s*$";

	private Pattern isHTMLFilePath;
	private HashMap<String, String> paths;
	private HashMap<String, Boolean> pathIsDirectory;
	private HashMap<String, Boolean> pathIsFile;
	
	public ConnectionMap(){
		paths = new HashMap<String, String>();
		pathIsDirectory = new HashMap<String, Boolean>();
		pathIsFile = new HashMap<String, Boolean>();
		
		isHTMLFilePath = Pattern.compile(IS_HTML_FILE);

	}
	
	public void load(String key, String path){ 
		File file = new File(path);
		
		boolean isDir = false;
		boolean isFile = false;

		Matcher htmlFileMatch = isHTMLFilePath.matcher(path);
		
		if (htmlFileMatch.find()){
			isFile = true;
		} else if (file.isDirectory()){
			isDir = true;
		}
		
		paths.put(key, path);
		pathIsDirectory.put(key, isDir); 
		pathIsFile.put(key, isFile);
	}
	
	public String getPath(String key){
		return paths.get(key);
	}
	
	public boolean isDirectory(String key){
		return pathIsDirectory.get(key);
	}
	
	protected boolean isFile(String key){
		return pathIsFile.get(key);
	}
	
	public boolean isURL(String key){
		//TODO actually check for this (above)
		return !(pathIsDirectory.get(key) || pathIsFile.get(key));
	}
}
