package com.pradeep.xpath;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XpathParsing {

	public static void main(String[] args) {
				
		try {
	         File inputFile = new File("E:\\data c data\\Pradeep\\Work Item\\Sonu\\top.xml");
	         DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();

	         String expression = "/topology/machines/machine/name[text()] |/topology/machines/machine/instances/instance/name[text()]|/topology/machines/machine/instances/instance/type[text()]|/topology/machines/machine/instances/instance/tier[text()]";	        
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
	         for (int i = 0; i < nodeList.getLength(); i++) {
	            org.w3c.dom.Node nNode =  nodeList.item(i);
	            System.out.println("\nCurrent Element :" 
	               + nNode.getNodeName());
	            System.out.println(" Text Value :" 
	 	               + nNode.getTextContent());
	              }
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }

	}

}