package duanxin.modem.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/**
 * @author zouyang
 * @time 2017年1月16日 下午2:42:50
 * @description 串口检测工具类
 */
public class SerialPortsUtil {
	private static final List<Integer> baudrateList = getBaudRateList();//波特率数组
	public static final String MANUFACTURER = getManuFacturer();//短信猫设备提供商
	public static final int CARDCOUNT = getCardCount();//短信猫里插的能工作的电话卡的数量
	/**
	 * @author zouyang
	 * @time 2017年1月16日 下午8:35:41
	 * @description 得到串口对应的Map信息
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>> getAvaliableSerialPortsSet(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier temPort;
		while (portList.hasMoreElements()) {
			temPort = portList.nextElement();
			if (temPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				list = helloSerialPortTest(temPort,list);
			}
		}
		return list;
	}

	public static List<Map<String,String>> helloSerialPortTest(CommPortIdentifier temPort,List<Map<String,String>> list){
			for (int n = 0; n < baudrateList.size(); n++) {
				/**
				 * Map里包含串口的基本信息
				 * 串口名serialPortName、比特率baudRate、所有者curOwner,短信猫提供商manuFacturer，还需其他信息可调整程序获取信息
				 */
				Map<String,String> m = null;
				SerialPort serialPort = null;
				try {
					serialPort = (SerialPort) temPort.open("P"+temPort.getName()+n, 30);
				} catch (PortInUseException e) {
					e.printStackTrace();
					break;
				}
				try {
					serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
					serialPort.setSerialPortParams(baudrateList.get(n), // 波特率
							SerialPort.DATABITS_8, // 数据位数
							SerialPort.STOPBITS_1, // 停止位
							SerialPort.PARITY_NONE);// 奇偶位
					serialPort.enableReceiveTimeout(100);
				} catch (UnsupportedCommOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				InputStream inputStream;
				try {
					inputStream = serialPort.getInputStream();
					OutputStream outputStream = serialPort.getOutputStream();
					outputStream.write('A');
					outputStream.write('T');
					outputStream.write('\r');
					int i;
					StringBuffer sb = new StringBuffer();
					int c;
					while ((c = inputStream.read()) != -1) {
						sb.append((char) c);
					}
					if (sb.indexOf("OK") >= 0) {
						System.out.println("成功收到指令返回值。");
						m = new HashMap<String,String>();
						//波特率
						m.put("baudRate",String.valueOf(baudrateList.get(n)));
						//串口名
						m.put("serialPortName", serialPort.getName().substring(4));
						//当前所有者
						m.put("curOwner", temPort.getCurrentOwner());
						//短信猫设备提供商
						m.put("manuFacturer", MANUFACTURER);
						System.out.println(sb.toString());
					}
					inputStream.close();  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               
                serialPort.close();
                if(m != null){
                	list.add(m);
                }
			}
			return list;
	}
	
	public static boolean resetMessageCat(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier temPort;
		while (portList.hasMoreElements()) {
			temPort = portList.nextElement();
			if (temPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				SerialPortsUtil.resetSerialPorts(temPort,list,'1');
			}
		}
		return true;
	}
	
	public static boolean stopMessageCat(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier temPort;
		while (portList.hasMoreElements()) {
			temPort = portList.nextElement();
			if (temPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				SerialPortsUtil.resetSerialPorts(temPort,list,'0');
			}
		}
		return true;
	}
	
	public static void resetSerialPorts(CommPortIdentifier temPort,List<Map<String,String>> list,char chr){
		//使用AT指令来进行远程复位重启短信猫设备。AT+CFUN=1是重启短信猫软件堆栈和硬件堆栈的指令。
		//程序员通过超级终端来进行执行就可以了。(注意：执行后稍等30秒左右再进行其他操作)
		for (int n = 0; n < baudrateList.size(); n++) {
			/**
			 * Map里包含串口的基本信息
			 * 串口名serialPortName、比特率baudRate、所有者curOwner,短信猫提供商manuFacturer，还需其他信息可调整程序获取信息
			 */
			Map<String,String> m = null;
			SerialPort serialPort = null;
			try {
				serialPort = (SerialPort) temPort.open("P"+temPort.getName()+n, 30);
			} catch (PortInUseException e) {
				e.printStackTrace();
				break;
			}
			try {
				serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
				serialPort.setSerialPortParams(baudrateList.get(n), // 波特率
						SerialPort.DATABITS_8, // 数据位数
						SerialPort.STOPBITS_1, // 停止位
						SerialPort.PARITY_NONE);// 奇偶位
				serialPort.enableReceiveTimeout(100);
			} catch (UnsupportedCommOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InputStream inputStream;
			try {
				inputStream = serialPort.getInputStream();
				OutputStream outputStream = serialPort.getOutputStream();
				outputStream.write('A');  
				outputStream.write('T');  
				outputStream.write('+');  
				outputStream.write('C');  
				outputStream.write('F');  
				outputStream.write('U');  
				outputStream.write('N');
				outputStream.write('='); 
				outputStream.write(chr); 
				outputStream.write('\r');
				
				int i;
				StringBuffer sb = new StringBuffer();
				int c;
				while ((c = inputStream.read()) != -1) {
					sb.append((char) c);
				}
				System.out.println(sb.toString());
				inputStream.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}
           
            serialPort.close();
		}
		System.out.println("重启短信猫了");
	}
	/**
	 * @author zouyang
	 * @time 2017年2月16日 下午3:15:21
	 * @description 波特率数组
	 * @return
	 */
	private static List<Integer> getBaudRateList(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<Integer> list = new ArrayList<Integer>();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			String src =Thread.currentThread().getContextClassLoader().getResource("").getPath();
			File file = new File(src+"gsm-modem.xml");
			Document d = db.parse(file);
			NodeList nodeList = d.getElementsByTagName("baudrate");
			for(int i = 0;i<nodeList.getLength();i++){
				Node node = nodeList.item(i);
				list.add(Integer.valueOf(node.getFirstChild().getNodeValue()));
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @author zouyang
	 * @time 2017年2月16日 下午3:34:31
	 * @description 短信猫厂商
	 * @return
	 */
	private static String getManuFacturer(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String manuFacturer = "";
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			String src =Thread.currentThread().getContextClassLoader().getResource("").getPath();
			File file = new File(src+"gsm-modem.xml");
			Document d = db.parse(file);
			d.getElementsByTagName("");
			NodeList nodeList = d.getElementsByTagName("manufacturer");
			manuFacturer = nodeList.item(0).getFirstChild().getNodeValue();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return manuFacturer;
	}
	/**
	 * @author zouyang
	 * @time 2017年2月16日 下午3:34:46
	 * @description 短信猫插卡数
	 * @return
	 */
	private static int getCardCount(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		int cardCount = 0;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			String src =Thread.currentThread().getContextClassLoader().getResource("").getPath();
			File file = new File(src+"gsm-modem.xml");
			Document d = db.parse(file);
			d.getElementsByTagName("");
			NodeList nodeList = d.getElementsByTagName("cardcount");
			cardCount = Integer.valueOf(nodeList.item(0).getFirstChild().getNodeValue());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return cardCount;
	}
}
