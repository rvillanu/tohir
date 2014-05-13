package tohir.dto;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;

public class ContributionWriter {
	public ContributionWriter() {
		
	}
	
	public void writeContributionsXML(String requester, String recipient, String network_name) throws ParserConfigurationException, FileNotFoundException, IOException {
		
		try {
			// https://www.youtube.com/watch?v=dvI46rF85wM
			//DocumentBuilderFactory
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			//DocumentBuilder
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//Document
			Document xmlDoc = docBuilder.newDocument();
			
			//Build XML Elements and Text Nodes
			//<contribution>
			//	... (see notes)
			//</contribution>
			Element rootElement = xmlDoc.createElement("contribution");
			Element requesterElement = xmlDoc.createElement("requester");
			Element recipientElement = xmlDoc.createElement("recipient");
			Element networkElement = xmlDoc.createElement("network_name");
			//Element modificationElement = xmlDoc.createElement("modification");
			
			//Element insertElement = xmlDoc.createElement("insert");
			//Element deleteElement = xmlDoc.createElement("delete");
			//Element updateElement = xmlDoc.createElement("update");
			
			Text requesterText = xmlDoc.createTextNode(requester);
			Text recipientText = xmlDoc.createTextNode(recipient);
			Text networkText = xmlDoc.createTextNode(network_name);
			requesterElement.appendChild(requesterText);
			recipientElement.appendChild(recipientText);
			networkElement.appendChild(networkText);
			
			// append text to insert/delete/update Elements
			// append insert/delete/update Elements to modification
			
			rootElement.appendChild(requesterElement);
			rootElement.appendChild(recipientElement);
			rootElement.appendChild(networkElement);
			
			xmlDoc.appendChild(rootElement);
			
			//Set OutputFormat
			OutputFormat outFormat = new OutputFormat();
			outFormat.setIndenting(true);
			
			//Declare the File
			File xmlFile = new File("testXML.txt");
			//Declare the FileOutputStream
			FileOutputStream outStream = new FileOutputStream(xmlFile);
			
			//XMLSerializer to serialize the XML data with
			XMLSerializer serializer = new XMLSerializer(outStream, outFormat);
			//the specified OutputFormat
			serializer.serialize(xmlDoc);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, IOException {
		ContributionWriter cw = new ContributionWriter();
		cw.writeContributionsXML("charles", "ryan", "A");
	}
}
