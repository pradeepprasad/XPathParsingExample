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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.xpath.XPathException;
import org.xml.sax.SAXException;

public class XpathAxisExample {
	
	public static void main(String[] args){
	try {
		//get the file input.
		File topologyXmlFile= new File("E:\\data c data\\Pradeep\\Work Item\\Sonu\\top.xml");
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db= dbf.newDocumentBuilder();
		Document doc=db.parse(topologyXmlFile);
		//get the xpath instance to get search elements in xml file using xpath expression.
		XPath xpath= XPathFactory.newInstance().newXPath();	
		//get the high level element given in expression.
		NodeList nodes =(NodeList) xpath.evaluate("//machines/machine",doc, XPathConstants.NODESET);
			if(nodes.getLength() > 0)
			{
				for(int i=0;i<nodes.getLength();i++)
				{
						//get the high lower level elements.
						Node nNode=nodes.item(i);
						String mN=(String)xpath.evaluate("child::name/text()", nNode, XPathConstants.STRING);
						NodeList instanceList=(NodeList)xpath.evaluate("child::instances/instance", nNode, XPathConstants.NODESET);
						//get the child elements exist in higher level elements.
						for (int j=0;(j<instanceList.getLength()&&instanceList!=null);j++)
						{
							//Form report string with required elements in file.
							Node cNode=instanceList.item(j);
							String instanceName1=(String)xpath.evaluate("child::name/text()", cNode, XPathConstants.STRING);
							String instanceType1=(String)xpath.evaluate("child::type/text()", cNode, XPathConstants.STRING);
							String instanceTier1=(String)xpath.evaluate("child::tier/text()", cNode, XPathConstants.STRING);
							System.out.println("MN: "+ mN + " IN: "+instanceName1 + " IT: "+instanceType1 +" ITT: "+instanceTier1);
						}   		       
				}
			}else{
				//Display appropriate message in case high level elements not found in input file.
				//Most probably input file is wrong.
				System.out.println("Please check the input file - No single machine found");
			}
				//Handle possible exception while parsing the file with xpath.
				} catch (ParserConfigurationException e) {
		         e.printStackTrace();
				} catch (SAXException e) {
		         e.printStackTrace();
				} catch (IOException e) {
		         e.printStackTrace();
				} catch (XPathExpressionException e) {
		         e.printStackTrace();
				} catch (XPathException e) {
				e.printStackTrace();
        		}
		}
	}