import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class TestXMLIni {

	public static RobotMap robotMap;
	public static SomeSystem aSystem;
	private static String fileName;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if ( args.length > 0 ) {
			fileName = args[0];
		} else {
			fileName = "";
		}
		robotMap = new RobotMap(fileName);
		aSystem = new SomeSystem();
		
		
	}

}
