package notewriter.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationData {

	private static final String CONFIG_FILE_LOCATION = "src/notewriter/resources/Config.xml";
	private int maxPaths; 
	private Document configDoc;
	
	private XPathFactory xPathfactory = XPathFactory.newInstance();
	private XPath xpath = xPathfactory.newXPath();
	
	public ConfigurationData(Integer inp_maxPaths){
		loadConfigFile();
		maxPaths = inp_maxPaths;
		
	}
	
	public String[][] getPathMap(){
		//TODO could lighten library dependencies by not using XPath
		String[][] map;
		map = new String[maxPaths][2];

		try{
			XPathExpression expr = xpath.compile("//PATHMAPS/PATHMAP");
			NodeList nl = (NodeList) expr.evaluate(configDoc, XPathConstants.NODESET);
			for(int i=0; i < nl.getLength(); i++){
				Node mapNode = nl.item(i);
				if (i < map.length && mapNode.getNodeType() == Node.ELEMENT_NODE){
					Element mapElement = (Element) mapNode;
					map[i][0] = mapElement.getAttribute("key");
					map[i][1] = mapElement.getTextContent();
				} else if (i < map.length){
					map[i][0] = null;
					map[i][1] = null;
				}
			}
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		return map;
	}
	
	
	public String getDefaultDocName(){
		String defaultName = "";
		try{
			XPathExpression expr = xpath.compile("//DEFAULT_FILE/text()");
			defaultName = expr.evaluate(configDoc, XPathConstants.STRING).toString();
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}	
		return defaultName;
	}
	
	public void setPathMap(String[][] map){
		//TODO clean this up
		try{
			XPathExpression expr = xpath.compile("//PATHMAPS");
			
			Node node = (Node) expr.evaluate(configDoc, XPathConstants.NODE);
			deleteAllChildNodes(node);
			for(int i=0; i < map.length; i++){
				if (null != map[i][0] && null != map[i][1]){
					Element newPathNode = configDoc.createElement("PATHMAP");
					
					node.appendChild(newPathNode);
					Attr attrId = configDoc.createAttribute("id");
					Attr attrKey = configDoc.createAttribute("key");
					attrId.setValue(Integer.toString(i + 1));
					attrKey.setValue(map[i][0]);
					newPathNode.setAttributeNode(attrId);
					newPathNode.setAttributeNode(attrKey);
					newPathNode.setTextContent(map[i][1]);
				}
			}
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
	
		
	private void deleteAllChildNodes(Node node){
		NodeList nl = node.getChildNodes();
		int len = nl.getLength();
		
		for(int i= (len - 1); i >= 0; i--){
			node.removeChild(nl.item(i));
		}
	}
	

	private void loadConfigFile(){
		File inputFile = new File(CONFIG_FILE_LOCATION);
		DocumentBuilderFactory factory =
		DocumentBuilderFactory.newInstance();
		try {
		    DocumentBuilder builder = factory.newDocumentBuilder();
			configDoc = builder.parse(inputFile);
	        configDoc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}

	
	public void saveConfigFile(){
		try{
			// write the content into xml file
	        TransformerFactory transformerFactory =
	        TransformerFactory.newInstance();
	        Transformer transformer =
	        transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(configDoc);
	        StreamResult result = new StreamResult(new File(CONFIG_FILE_LOCATION));
	        transformer.transform(source, result);
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	}
	
}
