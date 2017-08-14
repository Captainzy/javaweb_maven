package parseXmlFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			File f = new File("C:\\Users\\TZ\\Desktop\\web.xml");
			Map<String,Object> m = null;
			try {
				 m = ParseXmlFileUtil.parseXmlToMapByDom4j(f);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			try {
				m = ParseXmlFileUtil.parseXmlToMapByDom4j("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>哈哈哈</Result>");
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ParseXmlFileUtil.parseStringToXmlByJdk("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>哈哈哈</Result>");
			
			try {
				List lit = ParseXmlFileUtil.parseXmlToListByJdc(f, "listener");
				lit.size();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
