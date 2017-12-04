import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Autonomous {
	
	private XMLInputFactory m_xFact;
	private XMLEventReader m_autoEvent;
	private XMLEvent m_autoCommand;
	private int cmdIndx;
//	private ArrayList<String> strArray;
	private double m_autoSpeed;
	private double m_autoDistance;

	public Autonomous(String fileName) {
		
		cmdIndx = -1;
//		strArray = new ArrayList<String>();
		m_autoSpeed = 0;
		m_autoDistance = 0;
		
		System.out.println("auto filename: " + fileName);
		try {
			m_xFact = XMLInputFactory.newInstance();
			m_autoEvent = m_xFact.createXMLEventReader(new FileReader(fileName));
		} catch (Exception e) {
			System.out.println("XML factory exception" + fileName);
		}
	}
	
	public boolean hasEvent() {
		return m_autoEvent.hasNext();
	}
	
	public String getCommand() {
		boolean isCommandEnd = false;
		int i = 0;
		String cmdName = "";
		try {
			while (hasEvent() && !isCommandEnd) {
				m_autoCommand = m_autoEvent.nextEvent();
//				System.out.println("event command: " + m_autoCommand.getEventType());
				switch(m_autoCommand.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
//					i = buildCommand();
					cmdName = buildCommand();
					break;
				case XMLStreamConstants.END_ELEMENT:
					EndElement endCommand = m_autoCommand.asEndElement();
					System.out.println("end command: " + endCommand.getName());
					isCommandEnd = true;
					break;
				case XMLStreamConstants.END_DOCUMENT:
					isCommandEnd = true;
					break;
				default:
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();	
		}
		return cmdName;
	}
	
	private String buildCommand() {
// reset all command attributes to defaults
//		cmdIndx = cmdIndx + 1;
//		strArray.add(cmdIndx, "");
		m_autoSpeed = 0.7;
		m_autoDistance = 0.0;
		StartElement startCommand = m_autoCommand.asStartElement();
//		System.out.println("start command: " + startCommand.getName());
		Iterator<Attribute> itAttr = m_autoCommand.asStartElement().getAttributes();
		while (itAttr.hasNext()) {
			Attribute attr = itAttr.next();
			String aVal = attr.getValue();
			String aName = attr.getName().toString();
//			System.out.println("attribute: " + aName + aVal);
			switch (aName) {
			case "speed":
//				strArray.set(cmdIndx, aVal);
				try {
					m_autoSpeed = Double.valueOf(aVal);
				} catch (Exception e){
				}
				break;
			case "distance":
				try {
					m_autoDistance = Double.valueOf(aVal);
				} catch (Exception e) {
				}
				break;
			default:
			}
// switch case by value for attribute
		}
//		return cmdIndx;
		return String.valueOf(startCommand.getName());
	}
	
//	public double getSpeed(int i) {
//		try {
//			return Double.valueOf(strArray.get(i));
//		} catch (Exception e) {
//			return 0;
//		}
//	}
	public double getSpeed(){
		return m_autoSpeed;
	}
	
	public double getDistance(){
		return m_autoDistance;
	}
	
}
