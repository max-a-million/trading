package datp.broker.connector;

import datp.network.NetworkDataPath;
import datp.network.NetworkCommandPath;
import datp.broker.BrokerConnectorProxy;
import datp.broker.connector.transaq.TransaqConnector;
import datp.logging.Logs;

public class TransaqConnectorProxy extends BrokerConnectorProxy {
	
	private static BrokerConnectorProxy Instance;
	public static BrokerConnectorProxy getInstance() {
		if (Instance == null)
			Instance = new TransaqConnectorProxy();
		return Instance;
	}
	
	public final TransaqConnector Transaq;
	private NetworkCommandPath CmdPath;
	private NetworkDataPath AlgoPath;
	private NetworkDataPath ProxyPath;
		
	private TransaqConnectorProxy() {
		Transaq = new TransaqConnector(this);
	}
	
	public void setDataPaths(NetworkDataPath algoPath, NetworkDataPath proxyPath) {
		AlgoPath = algoPath;
		ProxyPath = proxyPath;
	}
	
	public void connect() {
		if (Transaq == null) 
			return;
		Transaq.initialize();
		Transaq.connect();
	}
	
	public void disconnect() {
		if (Transaq == null)
			return;
		Transaq.disconnect();
		Transaq.deinitialize();
	}
	
	public NetworkCommandPath getCommandBridge() {
		if (CmdPath == null)
			CmdPath = new NetworkCommandBridge();
		return CmdPath;
	}
	
	public void sendData(Object o) {
		
		Class<? extends Object> dataType = o.getClass();
		
		switch (dataType.getName()) {
		
		case "datp.broker.connector.transaq.ServerStatus":
			datp.broker.connector.transaq.ServerStatus ss = (datp.broker.connector.transaq.ServerStatus)o;
			Logs.message("Transaq Connector (DM)", "server status", ss.getConnectionStatus(), ss.getTimeZone());
			try {
				AlgoPath.ServerStatus();
				ProxyPath.ServerStatus();
			} catch (NullPointerException e) {
				Logs.message("Trasaq Connector Proxy", "Server status", "error", e.getMessage());
			}
			break;
			
		case "datp.broker.connector.transaq.Markets":
			datp.broker.connector.transaq.Markets mm = (datp.broker.connector.transaq.Markets)o;
			
			break;
			
		case "datp.broker.connector.transaq.Securities":
			datp.broker.connector.transaq.Securities s =  (datp.broker.connector.transaq.Securities)o;
			
			break;
		}
	}
	
	public class NetworkCommandBridge implements NetworkCommandPath {

		public void GetHistory() {
			
		}
		
	}

}
