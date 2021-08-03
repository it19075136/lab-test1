package com.hackerthon.common;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class QueryUtil extends CommonUtil {
	
	public static String Q(String id) throws Exception {
		NodeList n; Element e = null;
		n = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(CommonConstants.XML_PATH))
				.getElementsByTagName(CommonConstants.QUERY);
		for (int x = 0; x < n.getLength(); x++) {
			e = (Element) n.item(x);
			if (e.getAttribute(CommonConstants.ID).equals(id))
				break;
		}
		return e.getTextContent().trim();
	}
}
