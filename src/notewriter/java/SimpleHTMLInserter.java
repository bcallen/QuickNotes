package notewriter.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class  SimpleHTMLInserter {

	// XPath xpath = XPathFactory.newInstance().newXPath();
	
	public SimpleHTMLInserter(){
		
	}
	
	public static void insertAfterTag(String htmlBodyFilePath,String htmlToAdd, String htmlTagAddAfter) {
        File htmlFile = new File(htmlBodyFilePath);
    
	    try{
			// read from file
		    Document doc = Jsoup.parse(htmlFile, "UTF-8");

		    Element existingHTML1 = doc.getElementById(htmlTagAddAfter);
		    Element node =  Jsoup.parse(htmlToAdd);
		    // insert the node
		    node = existingHTML1.after(node);
		    
		    BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
		    htmlWriter.write(doc.toString());
		    htmlWriter.close();
		    
	    } catch (Exception e) {
	    	// TODO handle this
			e.printStackTrace();
	    }

	  }
}