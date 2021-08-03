package com.hackerthon.common;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class TransformUtil extends CommonUtil {

	private static final ArrayList<Map<String, String>> l = new ArrayList<Map<String, String>>();

	private static Map<String, String> m = null;

	public static void rEQUESTtRANSFORM() throws Exception {

		Source x = new StreamSource(new File(CommonConstants.EMPLOYEE_REQUEST));
		Source s = new StreamSource(new File(CommonConstants.EMPLOYEE_MODIFIED));
		Result o = new StreamResult(new File(CommonConstants.EMPLOYEE_RESPONSE));
		TransformerFactory.newInstance().newTransformer(s).transform(x, o);
	}

	public static ArrayList<Map<String, String>> XMLXPATHS() throws Exception {

		Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(CommonConstants.EMPLOYEE_RESPONSE);
		XPath x = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) x.compile(CommonConstants.EMPLYOEE_COUNT).evaluate(d, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			m = new HashMap<String, String>();
			m.put("XpathEmployeeIDKey", (String) x.compile(CommonConstants.EMPLOYEE_ID_1 + i + CommonConstants.EMPLOYEE_ID_1)
					.evaluate(d, XPathConstants.STRING));
			m.put("XpathEmployeeNameKey", (String) x.compile(CommonConstants.EMPLOYEE_ID_1 + i + CommonConstants.EMPLOYEE_NAME_KEY)
					.evaluate(d, XPathConstants.STRING));
			m.put("XpathEmployeeAddressKey",
					(String) x.compile(CommonConstants.EMPLOYEE_ID_1 + i +  CommonConstants.EMPLOYEE_ADRESS_KEY).evaluate(d,
							XPathConstants.STRING));
			m.put("XpathFacultyNameKey", (String) x.compile(CommonConstants.EMPLOYEE_ID_1 + i +  CommonConstants.EMPLOYEE_FACULTY_KEY)
					.evaluate(d, XPathConstants.STRING));
			m.put("XpathDepartmentKey", (String) x.compile(CommonConstants.EMPLOYEE_ID_1 + i + CommonConstants.EMPLOYEE_DEPARTMENT_KEY)
					.evaluate(d, XPathConstants.STRING));
			m.put("XpathDesignationKey", (String) x.compile(CommonConstants.EMPLOYEE_ID_1 + i +  CommonConstants.EMPLOYEE_DESIGNATION_KEY)
					.evaluate(d, XPathConstants.STRING));
			l.add(m);
		}
		return l;
	}
}
