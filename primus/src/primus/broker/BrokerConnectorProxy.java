package primus.broker;

import primus.network.NetworkCommandPath;
import primus.network.NetworkDataPath;

public abstract class BrokerConnectorProxy {
	
	public abstract void connect();
	
	public abstract void disconnect();
	
	public abstract NetworkCommandPath getCommandBridge();
	
	public abstract void sendData(Object o);
	
	public void setDataPaths(NetworkDataPath algo, NetworkDataPath proxy) {};
	
	public static BrokerConnectorProxy getInstance() { 	return null;  }
}
