package notewriter;

import java.io.File;
import java.util.HashMap;
//import java.net.URL;  Can use this lib to check whether URL is valid in future

public class ConnectionMap{

	private HashMap<String, String> paths;
	private HashMap<String, Boolean> pathIsDirectory;
	
	public ConnectionMap(){
		paths = new HashMap<String, String>();
		pathIsDirectory = new HashMap<String, Boolean>();
	}
	
	public void load(String key, String path){
		File file = new File(path);
		boolean isFile;
		if (file.isDirectory()){
			isFile = true;
		} else {
			//TODO check whether is valid URL.  Add error checking.
			isFile = false;
		}
		paths.put(key, path);
		pathIsDirectory.put(key, isFile);
	}
	
	public String getPath(String key){
		return paths.get(key);
	}
	
	public boolean isPath(String key){
		return pathIsDirectory.get(key);
	}
	
	public boolean isURL(String key){
		//TODO actually check for this (above)
		return !(pathIsDirectory.get(key));
	}
	
	

}
