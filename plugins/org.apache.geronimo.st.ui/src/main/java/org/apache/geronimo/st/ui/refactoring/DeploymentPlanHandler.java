package org.apache.geronimo.st.ui.refactoring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAX Handler for geronimo deployment plan
 */
public class DeploymentPlanHandler extends DefaultHandler {
	protected String file;
	protected Locator locator;
	protected List<DeploymentPlanTextNode> nodeList = new ArrayList<DeploymentPlanTextNode>();

	protected static final int START = 0, IN_ROOT_ELEMENT = 1,
			IN_CONTEXT_ROOT = 2, IN_ENVIRONMENT = 3, IN_MODULEID = 4,
			IN_ARTIFACTID = 5;

	protected int state = START;

	public DeploymentPlanHandler(String file) {
		this.file = file;
	}

	public List<DeploymentPlanTextNode> getNodeList() {
		return nodeList;
	}

	public int getNodeOffset(String nodeName) {
		for (DeploymentPlanTextNode n : nodeList) {
			if (n.getName().equals(nodeName))
				return n.getOffset();
		}
		return -1;
	}

	public String getNodeValue(String nodeName) {
		for (DeploymentPlanTextNode n : nodeList) {
			if (n.getName().equals(nodeName))
				return n.getValue();
		}
		return null;
	}

	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = null;
		DeploymentPlanTextNode wtn = null;
		switch (state) {
		case IN_CONTEXT_ROOT:
			value = new String(ch, start, length);
			wtn = new DeploymentPlanTextNode();
			try {
				int offset = getOffset(locator.getLineNumber(), locator
						.getColumnNumber());
				wtn.setName(DeploymentPlanTextNode.CONTEXT_ROOT);
				wtn.setValue(value);
				wtn.setOffset(offset - length);
				nodeList.add(wtn);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case IN_ARTIFACTID:
			value = new String(ch, start, length);
			wtn = new DeploymentPlanTextNode();
			try {
				int offset = getOffset(locator.getLineNumber(), locator
						.getColumnNumber());
				wtn.setName(DeploymentPlanTextNode.ARTIFACT_ID);
				wtn.setValue(value);
				wtn.setOffset(offset - length);
				nodeList.add(wtn);

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (state) {
		case START:
			if (localName.equals("web-app") || localName.equals("openejb-jar")
					|| localName.equals("connector")
					|| localName.equals("application"))
				state = IN_ROOT_ELEMENT;
			break;
		case IN_ROOT_ELEMENT:
			if (localName.equals("environment"))
				state = IN_ENVIRONMENT;
			else if (localName.equals("context-root"))
				state = IN_CONTEXT_ROOT;
			break;
		case IN_ENVIRONMENT:
			if (localName.equals("moduleId"))
				state = IN_MODULEID;
			break;
		case IN_MODULEID:
			if (localName.equals("artifactId"))
				state = IN_ARTIFACTID;
			break;
		default:
			break;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (state) {
		case IN_ROOT_ELEMENT:
			if (localName.equals("web-app") || localName.equals("openejb-jar")
					|| localName.equals("connector")
					|| localName.equals("application"))
				state = START;
			break;
		case IN_ENVIRONMENT:
			if (localName.equals("environment"))
				state = IN_ROOT_ELEMENT;
			break;
		case IN_MODULEID:
			if (localName.equals("moduleId"))
				state = IN_ENVIRONMENT;
			break;
		case IN_ARTIFACTID:
			if (localName.equals("artifactId"))
				state = IN_MODULEID;
			break;
		case IN_CONTEXT_ROOT:
			if (localName.equals("context-root"))
				state = IN_ROOT_ELEMENT;
			break;
		default:
			break;
		}
	}

	// return the offset of the DeploymentPlanTextNode's end
	protected int getOffset(int lineNumber, int columnNumber)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));

		if (lineNumber < 1 || columnNumber < 1)
			return -1;

		int current;
		int offset = 0;

		for (int i = 1; i < lineNumber;) {
			do {
				current = br.read();
				offset++;
			} while (current != '\n');
			i++;
		}
		offset += (columnNumber - 1);

		br.close();

		return offset;
	}
}