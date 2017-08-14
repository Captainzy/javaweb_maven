package parseXmlFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * 
 * @author zouyang
 *	解析Xml文件工具类
 */
public class ParseXmlFileUtil {
	/**
	 * @author zouyang
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @time 2017年2月9日 上午11:39:15
	 * @description 通过Java最原始的API来实现字符串转XML文件
	 * 参数必须是XML格式的字符串，比如：str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>哈哈哈</Result>"
	 */
	public static Document parseStringToXmlByJdk(String str) throws ParserConfigurationException, SAXException, IOException{
		StringReader sr = new StringReader(str);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dc = db.parse(is);
		return dc;
	}
	
	/**
	 * @author zouyang
	 * @time 2017年2月9日 上午11:52:09
	 * @description 解析XML文件,根据节点名获取数据
	 * @param file
	 * @return
	 * @throws TransformerConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static List<?> parseXmlToListByJdc(File file,String nodeName) throws TransformerConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(file);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		NodeList nodelist = document.getElementsByTagName(nodeName);
		List list = new ArrayList<>();
		if(nodelist.getLength()>0&&nodelist.item(0).getChildNodes().getLength()>0){
			for(int i = 0;i<nodelist.getLength();i++){
				Map<String,String> m = new HashMap<String,String>();
				Node node = nodelist.item(i);
				m.put(node.getNodeName(),node.getTextContent());
				list.add(m);
			}
		}else if(nodelist.item(0).getChildNodes().getLength()<=0){
			for(int i = 0;i<nodelist.getLength();i++){
				Map<String,String> m = new HashMap<String,String>();
				Node node = nodelist.item(i);
				list.add(node.getTextContent());
			}
		}
		
		return list;
	}
	
	public static Map<String,Object> parseXmlToMapByDom4j(File file) throws DocumentException{
		SAXReader sr = new SAXReader();
		org.dom4j.Document dc = sr.read(file);
		Map<String,Object> map = getElementValue(dc.getRootElement());
		return map;
	}
	private static Map<String,Object>  getElementValue(Element e){
		List<Element> list = e.elements();
		if(list.size()==0){
			Map<String,Object> map= new HashMap<String,Object>();
			map.put(e.getName(), e.getStringValue());
			return map;
		}
		List<Map<String,Object>> aList = new ArrayList<Map<String,Object>>();
		for(Element element : list){
			aList.add(getElementValue(element));
		}
		Map<String,Object> reMap = new HashMap<String,Object>();
		reMap.put(e.getName()+"_listChildren", aList);
		return reMap;
		
	}
	
	public static Map<String,Object> parseXmlToMapByDom4j(String text) throws DocumentException{
		org.dom4j.Document dc = DocumentHelper.parseText(text);
		Map<String,Object> map = getElementValue(dc.getRootElement());
		return map;
	}
}
