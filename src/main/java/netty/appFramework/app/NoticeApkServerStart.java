package netty.appFramework.app;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import netty.appFramework.netty.core.NettyServer;

public class NoticeApkServerStart {
	public static void main(String[] args){
		String path = new File("").getAbsolutePath() + "/netty.appFramework.config/basic.xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		int port = 0;
		try {
			DocumentBuilder df = dbf.newDocumentBuilder();
			Document d = df.parse(path);
			NodeList nodeList = d.getElementsByTagName("server-port");
			port = Integer.valueOf(nodeList.item(0).getTextContent());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		NettyServer.start(port);
	}
}
