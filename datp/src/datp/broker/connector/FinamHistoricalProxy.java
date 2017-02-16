package datp.broker.connector;

import datp.broker.BrokerConnectorProxy;

public class FinamHistoricalProxy extends BrokerConnectorProxy {
	
	private static BrokerConnectorProxy Instance;
	public static BrokerConnectorProxy getInstance() {
		if (Instance == null)
			Instance = new FinamHistoricalProxy();
		return Instance;
	}
	
	private FinamHistoricalProxy() {
	}
	
	public void connect() {
	}
	
	public void disconnect() {
	}
	
	public void command() {
	}
	
	public void Subscribe() {
		
	}
	
	public void sendData(Object o) {
	}
}
