package com.web115.test;

import com.web115.dao.XMLUtils;

public class XMLTest {

	public static void main(String[] args) {
		
		XMLUtils xmlUtils = new XMLUtils("WebContent/xml/aaa.xml","login");
		xmlUtils.setRootContent("success");
		xmlUtils.generateXML();
		
	}
	
}
