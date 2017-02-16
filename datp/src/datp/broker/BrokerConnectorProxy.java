package datp.broker;

import datp.network.*;

public abstract class BrokerConnectorProxy implements NetworkCommandPath {
	
	public abstract void connect();
	
	public abstract void disconnect();
	
	public abstract void command();
	
	public abstract void sendData(Object o);
	
	public void setDataPaths(Object algo, Object proxy) {};
	
	public static BrokerConnectorProxy getInstance() { 	return null;  }
}
