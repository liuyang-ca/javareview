package ca.liu.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public enum ClassPathXmlApplicationContext implements BeanFactory {
	instance;
	
	private Map<String, Object> beanMap;
	
	public Object getBean(String id) {
		if(beanMap == null) {
			parseFile(new File("config.xml"));
		}
		
		return beanMap.get(id);
	}

	
	public void parseFile(File f) {
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(f);
			NodeList list = doc.getElementsByTagName("bean");
			beanMap = new HashMap<String, Object>();
			for(int i=0; i<list.getLength(); i++) {
				Node node = list.item(i);
				try {
					beanMap.put(node.getAttributes().getNamedItem("id").getNodeValue(), Class.forName(node.getAttributes().getNamedItem("class").getNodeValue()).newInstance());
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int i=0; i<list.getLength(); i++) {
				Element ele = (Element) list.item(i);
				NodeList childList = ele.getElementsByTagName("property");
				if(childList != null) {
					for(int j=0; j<childList.getLength(); j++) {
						Object obj = getBean(ele.getAttribute("id"));
						Object value = getBean(childList.item(j).getAttributes().getNamedItem("ref").getNodeValue());
						obj.getClass().getField(childList.item(j).getAttributes().getNamedItem("name").getNodeValue()).set(obj, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
