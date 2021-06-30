package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class App {

	private static final String CRITERIA = "modification";
	private static final QName Q_NAME = new QName("name");

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		String fileName = args[0];
		List<String> attributeList = new ArrayList<>();

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader parser = factory.createXMLEventReader(new FileInputStream(fileName));

		while (parser.hasNext()) {
			XMLEvent nextEvent = parser.nextEvent();
				if (nextEvent.isStartElement()) {
					StartElement startElement = nextEvent.asStartElement();
					if (startElement.getName().getLocalPart().equals(CRITERIA)) {
						if (!attributeList.contains(startElement.getAttributeByName(Q_NAME).getValue())) {
							attributeList.add(startElement.getAttributeByName(Q_NAME).getValue());
						}
					}
				}
		}
		System.out.println(attributeList.size());
	}
}