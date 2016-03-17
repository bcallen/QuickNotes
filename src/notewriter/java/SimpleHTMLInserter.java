package notewriter.java;


import static javax.xml.xpath.XPathConstants.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import org.w3c.dom.*;

public class  SimpleHTMLInserter {

	 XPath xpath = XPathFactory.newInstance().newXPath();
	
	public SimpleHTMLInserter(){
		
	}
	
	public static void insert(String xmlBodyFilePath,String xmlToAdd, String xPathAddAfter) {
        File xmlFile = new File(xmlBodyFilePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
	    try{
			// read from files
		    db = dbf.newDocumentBuilder();
		    Document doc = db.parse(xmlFile);
		    // find the node to add to
		    XPath xpath = XPathFactory.newInstance().newXPath();

		    Node existingHTML1 = (Node) xpath.evaluate(xPathAddAfter, doc.getDocumentElement(), NODE);
		    Element node =  DocumentBuilderFactory
		    	    .newInstance()
		    	    .newDocumentBuilder()
		    	    .parse(new ByteArrayInputStream(xmlToAdd.getBytes()))
		    	    .getDocumentElement();
		    // insert the nodes
		    existingHTML1.getParentNode().insertBefore(node,existingHTML1.getNextSibling());
		    
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