package ca.liu.soaptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class SOAPTest {
	@Test
	public void printDocTest() throws TransformerException, ParserConfigurationException {
        //We need a Document
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        ////////////////////////
        //Creating the XML tree

        //create the root element and add it to the document
        Element root = doc.createElement("root");
        doc.appendChild(root);

        //create a comment and put it in the root element
        Comment comment = doc.createComment("Just a thought");
        root.appendChild(comment);

        //create child element, add an attribute, and add to root
        Element child = doc.createElement("child");
        child.setAttribute("name", "value");
        root.appendChild(child);

        //add a text element to the child
        Text text = doc.createTextNode("Filler, ... I could have had a foo!");
        child.appendChild(text);
        
        /////////////////
        //Output the XML
        printDoc(doc);
	}
	
	private void printDoc(Document doc) throws TransformerException {
        /////////////////
        //Output the XML

        //set up a transformer
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        //create string from xml tree
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
        String xmlString = sw.toString();

        //print xml
        System.out.println("Here's the xml:\n\n" + xmlString);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void sandbox() throws SOAPException, ParserConfigurationException, SAXException, IOException, TransformerException {		
        String fileRequestInput = "src/test/resources/soap.xml";          
        
        File xmlFileRequest = new File(fileRequestInput);  
                File file = new File(fileRequestInput);  
  
        FileInputStream fstreamRequest = new FileInputStream(xmlFileRequest);  
          
//        DocumentBuilderFactory docFactory = null;     
//        DocumentBuilder docBuilder = null;     
//        Document document = null;     
//  
//        docFactory = DocumentBuilderFactory.newInstance();     
//        docBuilder = docFactory.newDocumentBuilder();     
//        document = docBuilder.parse(fstreamRequest);
        
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage msg = factory.createMessage(null, fstreamRequest);
        
        //Send the message and get a reply   
        
		
		
        //First create the connection
        SOAPConnectionFactory soapConnFactory = 
                           SOAPConnectionFactory.newInstance();
        SOAPConnection connection = 
                            soapConnFactory.createConnection();
        
        //Set the destination
        String destination = 
            "http://1.151.218.32:43778/Stream";
        //Send the message
        SOAPMessage reply = connection.call(msg, destination);

        printDoc(reply.getSOAPPart());
         //Close the connection            
         connection.close();  
	}
}
