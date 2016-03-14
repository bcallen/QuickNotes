package notewriter;

import java.io.File;
import java.util.HashMap;
//import java.net.URL;  Can use this lib to check whether URL is valid in future

public class ConnectionMap{

	private HashMap<String, String> paths;
	private HashMap<String, Boolean> pathIsDirectory;
	private HashMap<String, Boolean> pathIsFile;
	
	public ConnectionMap(){
		paths = new HashMap<String, String>();
		pathIsDirectory = new HashMap<String, Boolean>();
		pathIsFile = new HashMap<String, Boolean>();
	}
	
	public void load(String key, String path){
		File file = new File(path);
		
		boolean isDir = false;
		boolean isFile = false;
		
		if (file.isDirectory()){
			isDir = true;
		} else if (file.isFile()){
			isFile = true;
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
	
	public boolean isFile(String key){
		return pathIsFile.get(key);
	}
	
	public boolean isURL(String key){
		//TODO actually check for this (above)
		return !(pathIsDirectory.get(key) || pathIsFile.get(key));
	}
}
