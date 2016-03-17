package notewriter.java;

import org.pegdown.PegDownProcessor;
import java.util.Date;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class NoteConnector {
	
	private static final String DEFAULT_FILE_NAME = "Notes to add to project memo.html";
	private static final Path HTML_TEMPLATE_REL_PATH = Paths.get("resources/template.html");
	//private cMap = new ConnectionMap();
	public NoteConnector(){
		
	}

	public void write(String note, String cString, boolean isDirectory, boolean isFile){
		if (isDirectory){
			writeToFile(note, cString, false);
		} else if (isFile) {
			writeToFile(note, cString, true);
		} else {
			postToURL(note, cString);
		}
	}
	
	private void writeToFile(String note, String cString, boolean includesFileName){
		
		Path filePath;
		//HTMLEditorKit htmlEdit = new HTMLEditorKit();
		HTMLDocument htmlDoc;
		
		//get file path
		if (!includesFileName){
			if(!cString.endsWith("\\")){
				cString = cString.concat("\\");
			}
			cString = cString.concat(DEFAULT_FILE_NAME);
		} 
		
		filePath = Paths.get(cString);

		//convert note to HTML
		note = finalizeMarkDownNote(note);
		
		//copy out template if needed		
		if (!fileExists(cString)){
			try {
				Files.copy(HTML_TEMPLATE_REL_PATH, filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//htmlDoc = new HTMLDocument(filePath);
		//HERE
	}
	
	private void postToURL(String note, String cString){
		//create POST request
		//submit if valid URL
		//wait
		//consume response and report
	}
	
	private void copyTemplateBodyToFile(String TemplatePath){
		
	}
	
	private String getDirFromPath(String path){
		File f = new File(path);
		return f.getParent();
	}
	
	private boolean fileExists(String path){
		File f = new File(path);
		return f.isFile();
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
