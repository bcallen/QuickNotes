package notewriter.java;


import static javax.xml.xpath.XPathConstants.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

/*import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import org.w3c.dom.*;*/
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class  SimpleHTMLInserter {

	// XPath xpath = XPathFactory.newInstance().newXPath();
	
	public SimpleHTMLInserter(){
		
	}
	
	public static void insertAfterTag(String htmlBodyFilePath,String htmlToAdd, String htmlTagAddAfter) {
        File htmlFile = new File(htmlBodyFilePath);
    
        Jsoup js;
	    try{
			// read from files
		    Document doc = js.parse(htmlFile, "UTF-8");
		    // find the node to add to

		    Element existingHTML1 = doc.getElementById(htmlTagAddAfter);
		    Element node =  js.parse(htmlToAdd);
		    // insert the nodes
		    node = existingHTML1.after(node);
		    existingHTML1.getParentNode().insertBefore(importedNode,existingHTML1.getNextSibling());
		    
		    Transformer transformer =
	                TransformerFactory.newInstance().newTransformer();
	        StreamResult result = new StreamResult(new FileOutputStream(xmlBodyFilePath));
	        DOMSource source = new DOMSource(doc);
	        transformer.transform(source, result);
		    
	    } catch (Exception e) {
	    	// TODO handle this
			e.printStackTrace();
	    }

	  }
}