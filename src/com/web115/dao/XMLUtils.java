package com.web115.dao;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLUtils {

	/**
	 * 要生成的XML文件
	 */
	private File file = null;
	/**
	 * 文件创建器生成工厂
	 */
	private DocumentBuilderFactory factory = null;
	/**
	 * 文件创建器
	 */
	private DocumentBuilder builder = null;
	/**
	 * 文件
	 */
	private Document document = null;
	/**
	 * XML文件的根节点
	 */
	private Element element = null;

	/**
	 * 默认构造一个对象，进行XML文件的操作
	 */
	public XMLUtils() {

	}

	/**
	 * 根据给定要想生成的 .xml 文件的路径 和给定的根节点名，进行初始化构造
	 * 
	 * @param fileName    要生成的 .XML 文件的具体url
	 * @param rootTagName 要生成的 .XML 文件的根节点名
	 */
	public XMLUtils(String fileName, String rootTagName) {

		initXmlFile(fileName, rootTagName);

	}

	/**
	 * 据给定要想生成的 .xml 文件的路径，进行初始化构造，其根节点名默认为文件名
	 * 
	 * @param fileName 要生成的 .XML 文件的具体url
	 */
	public XMLUtils(String fileName) {

		initXmlFile(fileName, fileName.substring(0, fileName.length() - 4));

	}

	/**
	 * 根据给定要想生成的 .xml 文件的路径 和给定的根节点名，进行初始化构造
	 * 
	 * @param fileName    要生成的 .XML 文件的具体url
	 * @param rootTagName 要生成的 .XML 文件的根节点名
	 */
	public void initXmlFile(String fileName, String rootTagName) {
		file = new File(fileName);
		// 创建DocumentBuilderFactory
		factory = DocumentBuilderFactory.newInstance();

		try {
			// 创建DocumentBuilder
			builder = factory.newDocumentBuilder();

			// 创建Document
			document = builder.newDocument();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		element = document.createElement(rootTagName);

	}

	/**
	 * 根据给定要想生成的 .xml 文件的路径，进行初始化构造，其根节点名默认为文件名
	 * 
	 * @param fileName 要生成的 .XML 文件的具体url
	 */
	public void initXmlFile(String fileName) {

		initXmlFile(fileName, fileName.substring(0, fileName.length() - 4));

	}

	/**
	 * 向给定的节点创建子节点
	 * 
	 * @param element     给定的将要创建子节点的节点
	 * @param tagName     要创建的子节点名
	 * @param textContent 子节点的内容
	 * @return 创建子节点后，将节点返回
	 */
	public Element createElement(Element element, String tagName, String textContent) {
		Element son = document.createElement(tagName);
		son.setTextContent(textContent);
		element.appendChild(son);
		return element;
	}

	/**
	 * 创建一个节点并返回
	 * 
	 * @param tagName     给定的节点名
	 * @param textContent 给定的节点内容
	 * @return 节点创建完毕后返回
	 */
	public Element createReturnableElement(String tagName, String textContent) {
		Element element = document.createElement(tagName);
		element.setTextContent(textContent);
		return element;
	}

	/**
	 * 向根节点添加子节点
	 * 
	 * @param element 子节点
	 */
	public void addElementToRoot(Element element) {
		this.element.appendChild(element);
	}

	/**
	 * 不向根节点添加任何子节点，直接为其设置内容
	 * 
	 * @param textContent 给定的根节点内容
	 */
	public void setRootContent(String textContent) {
		this.element.setTextContent(textContent);
	}

	/**
	 * 向根节点设置属性
	 * 
	 * @param name		要设置的属性名
	 * @param value		要设置的属性值
	 */
	public void setRootAttribute(String name,String value) {
		
		this.element.setAttribute(name, value);
		
	}
	
	/**
	 * 为给定的节点添加属性并返回
	 * 
	 * @param element	要添加属性的节点
	 * @param name		属性名
	 * @param value		属性值
	 * @return			节点属性添加完毕后返回
	 */
	public Element setElementAttribute(Element element,String name,String value) {
		element.setAttribute(name, value);
		return element;
	}
	
	/**
	 * 根据当前的Document状态生成XML文件
	 */
	public void generateXML() {

		TransformerFactory factory = null;	// 转换器生成工厂
		Transformer transformer = null;		// 转换器

		document.appendChild(element);		// 将根节点加载入文件对象中

		// 创建TransformerFactory对象
		factory = TransformerFactory.newInstance();

		try {
			// 创建Transformer对象
			transformer = factory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}

		// 设置输出数据时换行
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		try {
			// 使用Transformer的transform()方法将DOM树转换成XML
			transformer.transform(new DOMSource(document), new StreamResult(file));
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
