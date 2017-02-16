package datp.broker.connector.transaq;

import datp.logging.*;
import datp.broker.*;

import java.io.*;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Callback;

public class TransaqConnector {
	
	public interface TransaqDll extends Library {
		int GetServiceInfo(String request, String[] response);
		String Initialize(String logPath, int logLevel);
		String SetLogLevel(int logLevel);
		String SendCommand(String data);
		boolean SetCallback(TransaqDataManager callback);
		boolean FreeMemory(String data);
		String UnInitialize();
	}
	
	private TransaqDll CommandManager;
	private TransaqDataManager DataManager;
	private final BrokerConnectorProxy Proxy;
	
	public TransaqConnector(BrokerConnectorProxy brokerProxy) {
		Proxy = brokerProxy;
	}
	
	public void initialize() {
		CommandManager = (TransaqDll)Native.loadLibrary("d:\\GitHub\\trading\\datp\\txmlconnector64.dll", TransaqDll.class);
		DataManager = new TransaqDataManager();
		
		CommandManager.Initialize("d:\\transaq.log", 2);
		CommandManager.SetCallback(DataManager);
	}
	
	public void deinitialize() {
		CommandManager.UnInitialize();
	}
	
	public void connect() {
		String message = "<command id=\"connect\">" +
                ///"<host>tr1.finam.ru</host>" +
                ///"<port>3900</port>" +
                "<login>TCNN9957</login>>" +
                "<password>wuvuF8</password>" +
                "<host>tr1-demo5.finam.ru</host>" +
                "<port>3939</port>" +
                "<logsdir>d:\\transaq.log</logsdir>" +
                "<loglevel>2</loglevel>" +
                "<autopos>false</autopos>" +
                "<notes_file>d:\\transaq.log\\Notes.xml</notes_file>" +
                "<rqdelay>300</rqdelay>" +
             "</command>";
		String result = CommandManager.SendCommand(message);
		Logs.message("Transaq Connector (CM)", "command", result);
	}
	
	public void disconnect() {
		String message = "<command id=\"disconnect\"/>";
		CommandManager.SendCommand(message);
	}
	
	public class TransaqDataManager implements Callback {
		
		public boolean callback(String data) {
			
			byte[] bytes = data.getBytes();
			String message = new String(bytes, java.nio.charset.Charset.forName("UTF-8"));
			
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
		        InputSource source = new InputSource();
		        source.setCharacterStream(new StringReader(message));
		        Document document = builder.parse(source);
		        Element element = document.getDocumentElement();
		        
		        switch (element.getNodeName()) {
		        
		        case "markets":
		        	JAXBContext contextMarkets = JAXBContext.newInstance(datp.broker.connector.transaq.Markets.class);
		        	Unmarshaller transformMarkets = contextMarkets.createUnmarshaller();
		        	datp.broker.connector.transaq.Markets markets = (datp.broker.connector.transaq.Markets)transformMarkets.unmarshal(element);
		        	Proxy.sendData(markets);
		        	for (Market market : markets.getMarketsList()) 
		        		Logs.message("Transaq Connector (DM)", "markets", market.getId(), market.getValue());
		        	
		        	break;
		        	
		        case "server_status":
		        	JAXBContext contextServerStatus = JAXBContext.newInstance(datp.broker.connector.transaq.ServerStatus.class);
		        	Unmarshaller transformServerStatus = contextServerStatus.createUnmarshaller();
		        	datp.broker.connector.transaq.ServerStatus serverStatus = (datp.broker.connector.transaq.ServerStatus)transformServerStatus.unmarshal(element);
		        	Proxy.sendData(serverStatus);
		        	break;
		        	
		        case "securities":
		        	JAXBContext contextSecurities = JAXBContext.newInstance(datp.broker.connector.transaq.Securities.class);
		        	Unmarshaller transformSecurities = contextSecurities.createUnmarshaller();
		        	datp.broker.connector.transaq.Securities Securities = (datp.broker.connector.transaq.Securities)transformSecurities.unmarshal(element);
		        	Proxy.sendData(Securities);
		        	for (Security sec : Securities.getSecuritiesList()) {
		        		int id = sec.getId();
		        		String code = sec.getCode();
		        		Logs.message("Transaq Connector (DM)", "security", 
		        				"id -> " + String.valueOf(id), "code -> " + code, 
		        				"name -> " + sec.getName(), "type -> " + sec.getType(), 
		        				"step -> " + sec.getStep(), "lotsize -> " + sec.getSize());
		        	}
		        	break;
		        }
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    }
			
			return true;
		}
	}
}
