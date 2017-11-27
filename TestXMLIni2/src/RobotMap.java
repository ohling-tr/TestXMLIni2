import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class RobotMap {

	private String mIniFile;
	private Document mIniDoc;
	private XPath m_xPath;
	private boolean m_IsDashboardTest;
	
	public RobotMap() {
		
		mIniFile = "D:\\robot.ini";

		try {
	         File inputFile = new File(mIniFile);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

  	         mIniDoc = dBuilder.parse(inputFile);
	         mIniDoc.getDocumentElement().normalize();
	         m_xPath =  XPathFactory.newInstance().newXPath();
      
	      } catch (ParserConfigurationException e) {
	          e.printStackTrace();
	      } catch (SAXException e) {
	          e.printStackTrace();
	      } catch (IOException e) {
	          e.printStackTrace();
	      } 
		
		m_IsDashboardTest = getNodeBVal("dashboardTest");
		System.out.println("retDashboard: " + m_IsDashboardTest);

	}
	
	public double getPIDP(String pidName) {
		return getPIDP(pidName, 0.5);
	}
	
	public double getPIDP(String pidName, double PIDPVal) {
		
		try {
	         String expression = "/robot/pid[@pidcontroller='" + pidName + "']";
	         NodeList nodeList = (NodeList) m_xPath.compile(expression).evaluate(mIniDoc, XPathConstants.NODESET);
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node nNode = nodeList.item(i);
	             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                try {
	                	PIDPVal = Double.valueOf(eElement.getElementsByTagName("p").item(0).getTextContent());
	                } catch(Exception e) {
	                }
	             } 
	          }
		} catch (XPathExpressionException e) {
	    }
		
		return PIDPVal;
	}
	
	public double getPIDI(String pidName) {
		return getPIDI(pidName, 0.0);
	}
	
	public double getPIDI(String pidName, double PIDIVal) {
		
		try {
	         String expression = "/robot/pid[@pidcontroller='" + pidName + "']";
	         NodeList nodeList = (NodeList) m_xPath.compile(expression).evaluate(mIniDoc, XPathConstants.NODESET);	         
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node nNode = nodeList.item(i);	             
	             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                try {
	                	PIDIVal = Double.valueOf(eElement.getElementsByTagName("i").item(0).getTextContent());
	                } catch(Exception e) {            	
	                }	                
	             } 
	          }
		} catch (XPathExpressionException e) {
	    }
		
		return PIDIVal;
	}
	
	public double getPIDD(String pidName) {
		return getPIDD(pidName, 0.0);
	}
	
	public double getPIDD(String pidName, double PIDDVal) {
		
		try {
	         String expression = "/robot/pid[@pidcontroller='" + pidName + "']";
	         NodeList nodeList = (NodeList) m_xPath.compile(expression).evaluate(mIniDoc, XPathConstants.NODESET);	         
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node nNode = nodeList.item(i);	             
	             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                try {
	                	PIDDVal = Double.valueOf(eElement.getElementsByTagName("d").item(0).getTextContent());
	                } catch(Exception e) {            	
	                }	                
	             } 
	          }
		} catch (XPathExpressionException e) {
	    }
		
		return PIDDVal;
	}
	
	public int getSpeedControllerPort(String speedController) {
		
		int iPort = Integer.valueOf(getNodeChildIVal("speedController", "speedController", speedController, "port"));
		return iPort;
		
	}
	
	public String getSpeedControllerModel(String speedController) {
		
		return getNodeChildSVal("speedController", "speedController", speedController, "model") ;
		
	}
	
	public boolean getNodeBVal(String argNode) {
		boolean retBool = false;
		if ( getNodeVal(argNode).equalsIgnoreCase("true")) {
			retBool = true;
		}
		return retBool;
	}
	
	public String getNodeVal(String argNode) {
		
		String elementVal = "";
		try {
			String searchExpr = "/robot/" + argNode + "";
			System.out.println("searchExpr: " + searchExpr);
			NodeList nodeList = (NodeList) m_xPath.compile(searchExpr).evaluate(mIniDoc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                try {
	                	elementVal = eElement.getTextContent();
	                	System.out.println(argNode + ": " + elementVal);
	                } catch(Exception e) {
	                }
	            } 
	        }
			
		} catch(Exception e) {
			
		}
		return elementVal;
		
	}
	
	public Integer getNodeChildIVal(String argNode, String nodeName, String nodeValue, String tagName) {
		int iPort = -1;
		try {
        	iPort = Integer.valueOf(getNodeChildSVal(argNode, nodeName, nodeValue, tagName));
        } catch(Exception e) {            	
        }	
		return iPort;
	}
	public String getNodeChildSVal(String argNode, String nodeName, String nodeValue, String tagName) {
		String nodeChildVal = "";
		try {
	         String expression = "/robot/" + argNode + "[@" + nodeName + "='" + nodeValue + "']";
	         System.out.println("searchExpr: " + expression + tagName);
	         NodeList nodeList = (NodeList) m_xPath.compile(expression).evaluate(mIniDoc, XPathConstants.NODESET);	         
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node nNode = nodeList.item(i);	             
	             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                try {
	                	nodeChildVal = eElement.getElementsByTagName(tagName).item(0).getTextContent();
	                } catch(Exception e) {            	
	                }	                
	             } 
	          }
		} catch (XPathExpressionException e) {
	    }
		return nodeChildVal;
	}
}
