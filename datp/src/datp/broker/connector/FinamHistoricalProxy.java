package datp.broker.connector;

import datp.logging.Logs;
import datp.network.NetworkCommandPath;
import datp.network.NetworkDataPath;
import datp.broker.BrokerConnectorProxy;
import datp.broker.connector.finam.historical.FinamHistoricalServer;

public class FinamHistoricalProxy extends BrokerConnectorProxy {
	
	private static BrokerConnectorProxy Instance;
	public static BrokerConnectorProxy getInstance() {
		if (Instance == null)
			Instance = new FinamHistoricalProxy();
		return Instance;
	}
	
	public final FinamHistoricalServer HistoricalServer;
	private NetworkCommandPath CmdPath;
	private NetworkDataPath AlgoPath;
	private NetworkDataPath ProxyPath;
	
	private FinamHistoricalProxy() {
		HistoricalServer = new FinamHistoricalServer();
	}
	
	public void setDataPaths(NetworkDataPath algoPath, NetworkDataPath proxyPath) {
		AlgoPath = algoPath;
		ProxyPath = proxyPath;
	}
	
	public void connect() {
		Logs.message("finam-historical", "proxy", "connect", "ok");
	}
	
	public void disconnect() {
		Logs.message("finam-historical", "proxy", "disconnect", "ok");
	}
	
	public NetworkCommandPath getCommandBridge() {
		
		if (CmdPath == null)
			CmdPath = new NetworkCommandBridge();
		
		return CmdPath;
	}
	
	public void sendData(Object o) {
	}
	
	public class NetworkCommandBridge implements NetworkCommandPath {

		public void GetHistory() {
			
		}
		
	}
}
