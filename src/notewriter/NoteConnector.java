package notewriter;

public class NoteConnector {
	//private cMap = new ConnectionMap();
	public NoteConnector(){
		
	}
	
	public void write(String note, String cString, boolean isDirectory){
		if (isDirectory){
			writeToFile(note, cString);
		} else {
			postToURL(note, cString);
		}
	}
	
	private void writeToFile(String note, String cString){
		//does file exist?
		//if not create
		//if so append
		//include date and user name
	}
	
	private void postToURL(String note, String cString){
		//create POST request
		//submit if valid URL
		//wait
		//consume response and report
	}
	
}
