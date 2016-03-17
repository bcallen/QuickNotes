package notewriter.java;

import org.pegdown.PegDownProcessor;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
		//HTMLDocument htmlDoc = new HTMLDocument();
		//convert note to HTML
		note = finalizeMarkDownNote(note);
		
		
		//TODO does file exist?
		//if not copy in template
		//if so append of body 
	}
	
	private void postToURL(String note, String cString){
		//create POST request
		//submit if valid URL
		//wait
		//consume response and report
	}
	
	private String finalizeMarkDownNote(String rawNote){
		String header, finalNote;
		
		//Add user + date/time stamp
		String dateString;
		//get current date time with Date()
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY HH:mm:ss");
		dateString = dateFormat.format(new Date());
		//lookup user name from system
		String userName = System.getProperty("user.name");
		
		header = String.format("==Note from %s on %s==\n", userName, dateString);
		rawNote = header.concat(rawNote);
		
		PegDownProcessor pg = new PegDownProcessor();
		finalNote = pg.markdownToHtml(rawNote);

		return finalNote;
	}
	
}
