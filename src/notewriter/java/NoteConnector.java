package notewriter.java;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.pegdown.PegDownProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



/*TODO Create a new interface with connection type specific classes to support different types of targets. 
 * This class becomes truly a connector to identify the appropriate connection and act on the common
 * interface for the connection (e.g. HTML file, text file, HTTP post request...).
 */
//TODO set default attributes in config file
public class NoteConnector {
	
	private String defaultFileName;
	private static final Path HTML_TEMPLATE_REL_PATH = Paths.get("src/notewriter/resources/template.html");
	private static final String CAT_REGEX = "@([A-Za-z_]*)";
	private static final String TAG_REGEX = "#([A-Za-z_]*)";
	private static final String TITLE_REGEX = "\\*([A-Za-z_]*)";
	private static final String IMPORTANCE_REGEX = "!([0-9]*)";
	
	//private cMap = new ConnectionMap();
	public NoteConnector(){
		defaultFileName = "Notes.html";
	}
	
	public NoteConnector(String inDefaultFileName){
		defaultFileName = inDefaultFileName;
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
		String firstLine;
		String noteCategory;
		
		//get file path
		if (!includesFileName){
			if(!cString.endsWith("\\")){
				cString = cString.concat("\\");
			}
			cString = cString.concat(defaultFileName);
		} 
		
		filePath = Paths.get(cString);

		firstLine = note.substring(0, note.indexOf("\n"));
		note = note.substring(note.indexOf("\n"), note.length());

		Pattern findCat = Pattern.compile(CAT_REGEX);
		Matcher match = findCat.matcher(firstLine);
		
		if (match.find()){
			noteCategory = match.group(1);
		} else {
			noteCategory = "MainTitle";
		}
		
		
		//convert note to HTML
		note = finalizeMarkDownNote(note, firstLine);
		
		//copy HTML template to file path if needed		
		if (!fileExists(cString)){
			try {
				Files.copy(HTML_TEMPLATE_REL_PATH, filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		SimpleHTMLInserter.insertAfterTag(cString, note, noteCategory);
	}
	
	private void postToURL(String note, String cString){
		/*TODO finalize this and set up server side REST API*/
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(cString);

		try{
			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("param-1", "12345"));
			params.add(new BasicNameValuePair("param-2", "Hello!"));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	
			//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
	
			if (entity != null) {
			    InputStream instream = entity.getContent();
			    try {
			        // do something useful
			    } finally {
			        instream.close();
			    }
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		//create POST request
		//submit if valid URL
		//wait
		//consume response and report
	}

	private boolean fileExists(String path){
		File f = new File(path);
		return f.isFile();
	}
	
	private String finalizeMarkDownNote(String rawNote, String firstLine){
		//TODO when refactoring, split apart functionality in this method (identifying tags & creating header vs actually handling markdown
		String header, finalNote;

		
		header = createHTMLNoteHeader(firstLine);

		
		PegDownProcessor pg = new PegDownProcessor();
		finalNote = pg.markdownToHtml(rawNote);
		
		finalNote = header.concat(finalNote);
		finalNote = finalNote.concat(createHTMLNoteFooter());

		return finalNote;
	}
	
	private String createHTMLNoteHeader(String noteFirstLine){
		Pattern findTag = Pattern.compile(TAG_REGEX);
		Pattern findTitle = Pattern.compile(TITLE_REGEX);
		Pattern findImportance = Pattern.compile(IMPORTANCE_REGEX);

		//Add user + date/time stamp
		String dateString;
		//get current date time with Date()
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY HH:mm:ss");
		dateString = dateFormat.format(new Date());
		//lookup user name from system		
		String userName = System.getProperty("user.name").replace("."," ");
		
		String tag = parseNoteHeaderInfo(noteFirstLine, findTag, "");
		String title = parseNoteHeaderInfo(noteFirstLine, findTitle, "");
		String importance = parseNoteHeaderInfo(noteFirstLine, findImportance, "2");
		
		String noteHeader = String.format("<div class=`note-post` importance=`%s` tag=`%s`>\n<h4>%s - <small>%s: %s</small></h4>\n".replace('`', '"'),
										  importance,
										  tag,
										  title,
										  userName,
										  dateString);
		return noteHeader;
		
	}

	private String createHTMLNoteFooter(){
		return "\n</div>";
	}
	
	private String parseNoteHeaderInfo(String noteFirstLine, Pattern regPattern, String defaultVal){

		Matcher parseMatch = regPattern.matcher(noteFirstLine);
		String str;
		
		if (parseMatch.find()){
			str = parseMatch.group(1);
		} else {
			str = defaultVal;
		}		
		return str;
		
	}
}
