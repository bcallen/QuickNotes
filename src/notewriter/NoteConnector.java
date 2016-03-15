package notewriter;

public class NoteConnector {
	
	private static final String DEFAULT_FILE_NAME = "Notes to add to project memo.html";
	//private cMap = new ConnectionMap();
	public NoteConnector(){
		
	}
	
	public void write(String note, String cString, boolean isDirectory, boolean isFile){
		if (isDirectory){
			writeToFile(note, cString);
		} else {
			postToURL(note, cString);
		}
	}
	
	private void writeToFile(String note, String cString){
		writeToFile(note, cString, DEFAULT_FILE_NAME);
	}
	
	private void writeToFile(String note, String cString, String fileName){
		//convert note to HTML
		note = finalizeMarkDownNote(note);
		
		//TODO does file exist?
		//if not create from template
		//if so append at top
		//include date and user name 
	}
	
	private void postToURL(String note, String cString){
		//create POST request
		//submit if valid URL
		//wait
		//consume response and report
	}
	
	private String finalizeMarkDownNote(String rawNote){
		//TODO add user and pub date
		//TODO attempt to convert from markdown to HTML using either PegDown or Txtmark (initial version will just write raw text)
		String finalNote;
		finalNote = rawNote;
		return finalNote;
	}
	
}
