package primus.broker.connector;

import primus.broker.BrokerConnectorProxy;
import primus.broker.connector.finam.historical.FinamHistoricalServer;
import primus.logging.Logs;
import primus.network.NetworkCommandPath;
import primus.network.NetworkDataPath;

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
